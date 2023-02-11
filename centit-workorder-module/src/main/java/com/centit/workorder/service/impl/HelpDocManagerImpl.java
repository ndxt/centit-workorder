package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.centit.framework.jdbc.dao.DatabaseOptUtils;
import com.centit.framework.jdbc.service.BaseEntityManagerImpl;
import com.centit.framework.model.adapter.PlatformEnvironment;
import com.centit.framework.system.po.OptInfo;
import com.centit.search.document.ObjectDocument;
import com.centit.search.service.Impl.ESIndexer;
import com.centit.search.service.Impl.ESSearcher;
import com.centit.support.algorithm.CollectionsOpt;
import com.centit.support.algorithm.StringBaseOpt;
import com.centit.support.database.utils.PageDesc;
import com.centit.workorder.dao.HelpDocDao;
import com.centit.workorder.po.HelpDoc;
import com.centit.workorder.service.HelpDocManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * HelpDoc  Service.
 * create by scaffold 2017-05-08
 *
 * @author codefan@sina.com
 * 系统帮助文档null
 */
@Service
public class HelpDocManagerImpl
    extends BaseEntityManagerImpl<HelpDoc, String, HelpDocDao>
    implements HelpDocManager {

    public static final Logger logger = LoggerFactory.getLogger(HelpDocManager.class);
    private static final String FIRST_PREV_DOC_ID = "0";

    @Autowired(required = false)
    private ESSearcher esSearcher;

    @Autowired(required = false)
    private ESIndexer esIndexer;


    private HelpDocDao helpDocDao;
    @Autowired(required = false)
    private PlatformEnvironment platformEnvironment;

    @Resource(name = "helpDocDao")
    @NotNull
    public void setHelpDocDao(HelpDocDao baseDao) {
        this.helpDocDao = baseDao;
        setBaseDao(this.helpDocDao);
    }


    /**
     * 创建帮助文档
     *
     * @param helpDoc
     */
    @Override
    @Transactional
    public HelpDoc createHelpDoc(HelpDoc helpDoc) {
        String parentDocId = helpDoc.getDocPath();
        if (parentDocId != null) {
            HelpDoc parentDoc = helpDocDao.getObjectById(parentDocId);
            if (parentDoc != null) {
                helpDoc.setDocPath(parentDoc.getDocPath().contains("/") ?
                    parentDoc.getDocPath() + "/" + parentDocId : "/" + parentDocId);
                helpDoc.setDocLevel(parentDoc.getDocLevel() + 1);
                helpDoc.setCatalogId(parentDocId);
            } else {
                helpDoc.setDocPath(FIRST_PREV_DOC_ID);
                helpDoc.setDocLevel(1);
                helpDoc.setCatalogId(FIRST_PREV_DOC_ID);
            }
        }
        helpDoc.setPrevDocId(FIRST_PREV_DOC_ID);
        helpDoc.setOrderInd(0);
        helpDocDao.saveNewObject(helpDoc);
        DatabaseOptUtils.doExecuteSql(helpDocDao, "update f_help_doc set order_ind=order_ind+1 where doc_id<>? and catalog_id=?",
            new String[]{helpDoc.getDocId(), helpDoc.getCatalogId()});
        ObjectDocument objectDocument = helpDoc.generateObjectDocument();
        esIndexer.saveNewDocument(objectDocument);
        return helpDoc;
    }

    @Override
    public HelpDoc saveHelpDoc(HelpDoc helpDoc) {
        if (StringBaseOpt.isNvl(helpDoc.getDocId())) {
            createHelpDoc(helpDoc);
            if (platformEnvironment != null && helpDoc.getOptId() != null) {
                OptInfo optInfo = new OptInfo();
                optInfo.setOptId(helpDoc.getOptId());
                optInfo.setDocId(helpDoc.getDocId());
                optInfo.setTopOptId(helpDoc.getOsId());
                platformEnvironment.updateOptInfo(optInfo);
            }
        } else {
            editHelpDoc(helpDoc.getDocId(), helpDoc);
        }
        return helpDoc;
    }

    @Override
    @Transactional
    public HelpDoc editHelpDoc(String docId, HelpDoc helpDoc) {
        HelpDoc dbHelpDoc = helpDocDao.getObjectById(docId);
        String oldPath=dbHelpDoc.getDocPath();
        dbHelpDoc.copyNotNullProperty(helpDoc);
        if (!oldPath.equals(helpDoc.getDocPath())) {
            String[] docPaths = helpDoc.getDocPath().split("/");
            innerCatalog(docId, docPaths[docPaths.length - 1]);
        }else {
            helpDocDao.updateObject(dbHelpDoc);
        }
        ObjectDocument objectDocument = helpDoc.generateObjectDocument();
        esIndexer.mergeDocument(objectDocument);
        return dbHelpDoc;
    }

    private List<HelpDoc> findChildren(String parentId) {
        List<HelpDoc> result = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("parentId", "%/" + parentId);
        List<HelpDoc> helpDocs = helpDocDao.listObjectsByProperties(map);
        if (helpDocs != null && helpDocs.size() > 0) {
            result.addAll(helpDocs);
            for (HelpDoc h : helpDocs) {
                List<HelpDoc> subDocs = findChildren(h.getDocId());
                if (subDocs != null && subDocs.size() > 0) {
                    result.addAll(subDocs);
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public void deleteHelpDoc(String docId) {
        HelpDoc helpDoc = helpDocDao.getObjectById(docId);

        // 修改删除文档的下一个文档 的prevDocId
        HelpDoc siblingHelpDoc = helpDocDao.getObjectByProperties(
            CollectionsOpt.createHashMap("prevDocId", helpDoc.getDocId()));
        if (siblingHelpDoc != null) {
            siblingHelpDoc.setPrevDocId(helpDoc.getPrevDocId());
            helpDocDao.updateObject(siblingHelpDoc);
        }

        helpDocDao.deleteObjectById(docId);
        List<HelpDoc> helpDocs = findChildren(docId);
        if (helpDocs != null && helpDocs.size() > 0) {
            for (HelpDoc doc : helpDocs) {
                helpDocDao.deleteObjectById(doc);//删除子孙节点
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("docId", docId);

        ObjectDocument objectDocument = helpDoc.generateObjectDocument();
        esIndexer.deleteDocument(objectDocument);
    }


    @Override
    @Transactional
    public HelpDoc editContent(String docId, String content, String userCode) {
        HelpDoc helpDoc = helpDocDao.getObjectById(docId);
        //保存旧版本
        helpDoc.setDocFile(content);
        helpDoc.setUpdateUser(userCode);
        helpDocDao.updateObject(helpDoc);

        ObjectDocument objectDocument = helpDoc.generateObjectDocument();
        esIndexer.mergeDocument(objectDocument);
        return helpDoc;
    }


    @Override
    public List<HelpDoc> searchHelpDocByLevel(List<HelpDoc> list) {
        list.sort(Comparator.comparing(HelpDoc::getOrderInd));
        return storedAsTree(list, getHelpDocParentChild());
    }

    private List<HelpDoc> storedAsTree(List<HelpDoc> list, CollectionsOpt.ParentChild<HelpDoc> c) {
        List<HelpDoc> treeList = new ArrayList<HelpDoc>();
        for (HelpDoc m : list) {
            treeList.add(m);
        }
        for (HelpDoc cNode : treeList) {
            for (HelpDoc pNode : treeList) {
                if (pNode != cNode && c.parentAndChild(pNode, cNode)) {
                    List<HelpDoc> childList;
                    if (pNode.getChildren() == null) {
                        childList = new ArrayList<HelpDoc>();
                    } else {
                        childList = pNode.getChildren();
                    }
                    childList.add(cNode);
                    pNode.setChildren(childList);
                    break;
                }
            }
        }
        List<HelpDoc> resList = new ArrayList<HelpDoc>();
        for (HelpDoc node : treeList) {
            if ("0".equals(node.getCatalogId())) {
                resList.add(node);
            }
        }
        return resList;
    }

    private CollectionsOpt.ParentChild<HelpDoc> getHelpDocParentChild() {
        return (p, c) -> StringUtils.equals(p.getDocId(), c.getCatalogId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<HelpDoc> searchHelpdocByType(Map<String, Object> queryParamsMap, PageDesc pageDesc) {
        List<HelpDoc> helpDocs = listObjectsByProperties(queryParamsMap);
        return helpDocs;
    }

    @Override
    public List<Map<String, Object>> fullSearch(Map<String, Object> searchQuery, String keyWord, PageDesc pageDesc) {
        List<Map<String, Object>> list = esSearcher.search(searchQuery,
            keyWord, pageDesc.getPageNo(), pageDesc.getPageSize()).getRight();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                JSONObject json = JSONObject.parseObject((String) list.get(i).get("optUrl"));
                list.get(i).put("docId", json.get("docId").toString());
                list.get(i).put("docPath", json.get("docPath").toString());
                HelpDoc helpDoc = helpDocDao.getObjectById(json.get("docId").toString());
                if (helpDoc != null) {
                    list.get(i).put("lastUpdateTime", helpDoc.getLastUpdateTime());
                }
            }
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void catalog(String docId, String targetDocId, String action) {
        HelpDoc dbHelpDoc = helpDocDao.getObjectById(docId);
        HelpDoc targetHelpDoc = helpDocDao.getObjectById(targetDocId);
        if (dbHelpDoc.getCatalogId().equals(targetHelpDoc.getCatalogId())) {
            int dbOrderInd = dbHelpDoc.getOrderInd();
            if ("after".equals(action)) {
                dbHelpDoc.setOrderInd(targetHelpDoc.getOrderInd());
                DatabaseOptUtils.doExecuteSql(helpDocDao, "update f_help_doc set order_ind=order_ind-1 where catalog_id=? and order_ind>? and order_ind<=?",
                    new Object[]{targetHelpDoc.getCatalogId(), dbOrderInd, targetHelpDoc.getOrderInd()});
            } else {
                dbHelpDoc.setOrderInd(targetHelpDoc.getOrderInd());
                DatabaseOptUtils.doExecuteSql(helpDocDao, "update f_help_doc set order_ind=order_ind+1 where catalog_id=? and order_ind>=? and order_ind<?",
                    new Object[]{targetHelpDoc.getCatalogId(), targetHelpDoc.getOrderInd(), dbOrderInd});
            }
        } else {
            DatabaseOptUtils.doExecuteSql(helpDocDao, "update f_help_doc set order_ind=order_ind-1 where catalog_id=? and order_ind>?",
                new Object[]{dbHelpDoc.getCatalogId(), dbHelpDoc.getOrderInd()});
            if ("after".equals(action)) {
                dbHelpDoc.setPrevDocId(targetHelpDoc.getDocId());
                dbHelpDoc.setOrderInd(targetHelpDoc.getOrderInd() + 1);
                DatabaseOptUtils.doExecuteSql(helpDocDao, "update f_help_doc set order_ind=order_ind+1 where catalog_id=? and order_ind>?",
                    new Object[]{targetHelpDoc.getCatalogId(), targetHelpDoc.getOrderInd()});
            } else {
                dbHelpDoc.setPrevDocId(targetHelpDoc.getPrevDocId());
                dbHelpDoc.setOrderInd(targetHelpDoc.getOrderInd());
                DatabaseOptUtils.doExecuteSql(helpDocDao, "update f_help_doc set order_ind=order_ind+1 where catalog_id=? and order_ind>=?",
                    new Object[]{targetHelpDoc.getCatalogId(), targetHelpDoc.getOrderInd()});
            }
        }
        dbHelpDoc.setCatalogId(targetHelpDoc.getCatalogId());
        dbHelpDoc.setDocLevel(targetHelpDoc.getDocLevel());
        dbHelpDoc.setLastUpdateTime(new Date());
        dbHelpDoc.setDocPath(targetHelpDoc.getDocPath());
        helpDocDao.updateObject(dbHelpDoc);
    }

    @Override
    public void innerCatalog(String docId, String targetDocId) {
        if(!StringBaseOpt.isNvl(targetDocId)) {
            HelpDoc targetHelpDoc = helpDocDao.getObjectById(targetDocId);
            HelpDoc dbHelpDoc = helpDocDao.getObjectById(docId);
            dbHelpDoc.setPrevDocId(FIRST_PREV_DOC_ID);
            dbHelpDoc.setDocLevel(dbHelpDoc.getDocLevel() + 1);
            dbHelpDoc.setDocPath(targetHelpDoc.getDocPath() + "/" + targetDocId);
            DatabaseOptUtils.doExecuteSql(helpDocDao, "update f_help_doc set order_ind=order_ind+1 where catalog_id=?",
                new Object[]{targetDocId});
            DatabaseOptUtils.doExecuteSql(helpDocDao, "update f_help_doc set order_ind=order_ind-1 where catalog_id=? and order_ind>?",
                new Object[]{dbHelpDoc.getCatalogId(), dbHelpDoc.getOrderInd()});
            dbHelpDoc.setOrderInd(0);
            dbHelpDoc.setCatalogId(targetDocId);
            helpDocDao.updateObject(dbHelpDoc);
        }
    }

    @Override
    public void updatePrevDoc(List<HelpDoc> list, String action) {
        if (action == null) {
            // 更新 PrevDoc
            updatePrevDoc(list);
        } else {
            // 更新 level
            for (HelpDoc helpDoc : list) {
                int level = helpDoc.getDocPath().split("/").length;
                if (level != helpDoc.getDocLevel()) {
                    logger.info("将【{}】 的level从【{}】改为【{}】 ", helpDoc.getDocTitle(), helpDoc.getDocLevel(), level);
                    helpDoc.setDocLevel(level);
                    helpDocDao.updateObject(helpDoc);
                }
            }
        }
    }

    private void updatePrevDoc(List<HelpDoc> docArray) {
        final String docId_str = "docId";
        final String docTitle_str = "docTitle";
        for (int i = 1; i < docArray.size(); i++) {
            HelpDoc helpDoc = this.getObjectById(docArray.get(i).getDocId());
            HelpDoc prevHelpDoc = this.getObjectById(docArray.get(i - 1).getDocId());
            if (helpDoc.getPrevDocId() == null || !helpDoc.getPrevDocId().equals(prevHelpDoc.getDocId())) {
                logger.info("修改 【{}】 的上一个doc为 【{}】 ", helpDoc.getDocTitle(), prevHelpDoc.getDocTitle());
                helpDoc.setPrevDocId(prevHelpDoc.getDocId());
                this.updateObject(helpDoc);
            } else {
                logger.info("无需修改-- 【{}】 ", helpDoc.getDocTitle());
            }
            List<HelpDoc> childrenDoc = prevHelpDoc.getChildren();
            if (childrenDoc != null) {
                String docTitle = childrenDoc.get(0).getDocTitle();
                String docId = childrenDoc.get(0).getDocId();
                HelpDoc childrenHelpDoc = this.getObjectById(docId);
                if (childrenHelpDoc.getPrevDocId() == null || !childrenHelpDoc.getPrevDocId().equals(prevHelpDoc.getDocId())) {
                    logger.info("修改 【{}】 的上一个doc为 【{}】 ", docTitle, prevHelpDoc.getDocTitle());
                    childrenHelpDoc.setPrevDocId(prevHelpDoc.getDocId());
                    this.updateObject(childrenHelpDoc);
                } else {
                    logger.info("无需修改--- 【{}】 ", docTitle);
                }
                updatePrevDoc(childrenDoc);
            }
        }
    }

}

