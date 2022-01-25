package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
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
            } else {
                helpDoc.setDocPath(FIRST_PREV_DOC_ID);
                helpDoc.setDocLevel(1);
            }
        }
        helpDoc.setPrevDocId(FIRST_PREV_DOC_ID);
        helpDocDao.saveNewObject(helpDoc);
        int i = DatabaseOptUtils.doExecuteSql(helpDocDao, "update f_help_doc set PREV_DOCID=? where doc_id<>? and PREV_DOCID='0' and OS_ID=? and DOC_PATH=?",
            new String[]{helpDoc.getDocId(), helpDoc.getDocId(), helpDoc.getOsId(), helpDoc.getDocPath()});
        logger.debug("更新了" + i + "条");
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
        dbHelpDoc.copyNotNullProperty(helpDoc);
        helpDocDao.updateObject(dbHelpDoc);
        ObjectDocument objectDocument = helpDoc.generateObjectDocument();
        esIndexer.mergeDocument(objectDocument);
        return dbHelpDoc;
    }

    private List<HelpDoc> findChildren(String parentId) {
        List<HelpDoc> result = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("parentId", "%/" + parentId);
        List<HelpDoc> helpDocs = helpDocDao.listObjects(map);
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
        HelpDoc siblingHelpDoc = helpDocDao.getObjectByProperty("prevDocId", helpDoc.getDocId());
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
    @Transactional
    public List<HelpDoc> searchHelpDocByLevel(List<HelpDoc> list) {
        CollectionsOpt.sortAsTree(list, getHelpDocParentChild());
        JSONArray jsonArray = CollectionsOpt.treeToJSONArray(list, getHelpDocParentChild(), "children");
        List<HelpDoc> helpDocs = new ArrayList<>();
        for (Object o : jsonArray) {
            helpDocs.add(JSONObject.toJavaObject((JSONObject) o, HelpDoc.class));
        }
        sortHelpList(helpDocs);
        return helpDocs;
    }

    private void sortHelpList(List<HelpDoc> helpDocs) {
        for (HelpDoc helpDoc : helpDocs) {
            List<HelpDoc> children = helpDoc.getChildren();
            if (children == null) {
                continue;
            }
            LinkedList<HelpDoc> linkedList = sortChildren(children);
            helpDoc.setChildren(linkedList);
            sortHelpList(helpDoc.getChildren());
        }
    }

    private static LinkedList<HelpDoc> sortChildren(List<HelpDoc> list) {
        LinkedList<HelpDoc> linkedList = new LinkedList<>();
        addNextNode(list, linkedList, null);
        if (list.size() != linkedList.size()) {
            linkedList.clear();
            linkedList.addAll(list);
        }
        return linkedList;
    }

    private static void addNextNode(List<HelpDoc> list, LinkedList<HelpDoc> linkedList, HelpDoc helpDoc) {
        if (list.size() == linkedList.size()) {
            return;
        }
        for (HelpDoc compareHelpDoc : list) {
            boolean check = (helpDoc == null && FIRST_PREV_DOC_ID.equals(compareHelpDoc.getPrevDocId()))
                || (helpDoc != null && compareHelpDoc.getPrevDocId().equals(helpDoc.getDocId()));
            if (check) {
                linkedList.add(compareHelpDoc);
                addNextNode(list, linkedList, compareHelpDoc);
            }
        }
    }

    private CollectionsOpt.ParentChild<HelpDoc> getHelpDocParentChild() {
        return (p, c) -> {
            String parent = p.getDocId();
            String child = c.getDocPath();
            if (child.lastIndexOf("/") != -1) {
                String temp = child.substring(child.lastIndexOf("/") + 1);

                return parent.equals(temp);
            } else {
                return false;
            }
        };
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<HelpDoc> searchHelpdocByType(Map<String, Object> queryParamsMap, PageDesc pageDesc) {
//        String queryStatement =
//                "select h.DOC_ID,h.DOC_TITLE, h.DOC_FILE, h.DOC_LEVEL, h.DOC_PATH "
//                +" from f_help_doc h left join "
//                +" (select c.DOC_ID,count(c.COMMENT_ID) count"
//                +" from f_help_doc_comment c"
//                +" group by c.DOC_ID) m "
//                +" on h.DOC_ID = m.DOC_ID "
//                +" where 1=1 "
//                + " [ :catalogId | and h.CATALOG_ID = :catalogId ]"
//                + " [ :osId | and h.OS_ID = :osId ]"
//                + " order by count desc";
//
//        QueryAndNamedParams qap = QueryUtils.translateQuery(queryStatement,queryParamsMap);
//        JSONArray dataList = DatabaseOptUtils.findObjectsAsJSONBySql(baseDao,
//                    qap.getQuery(), qap.getParams(), pageDesc);

        List<HelpDoc> helpDocs = listObjects(queryParamsMap);
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
    public void catalog(String docId, String targetDocId, String action) {
        // 移动的文档(A)
        HelpDoc dbHelpDoc = helpDocDao.getObjectById(docId);
        String oldPath = dbHelpDoc.getDocPath();
//        dbHelpDoc.copyNotNullProperty(helpDoc);
        // 目标文档(B)，修改文档的prevDocId
        HelpDoc targetHelpDoc = helpDocDao.getObjectById(targetDocId);
        // 要移动文档的下一个文档(C)，修改文档的prevDocId
        HelpDoc siblingHelpDoc = helpDocDao.getObjectByProperty("prevDocId", dbHelpDoc.getDocId());
        // 移动文档的children文档(A1,A11,A111……)，修改children文档的path和level

        if (siblingHelpDoc != null) {
            siblingHelpDoc.setPrevDocId(dbHelpDoc.getPrevDocId());
            helpDocDao.updateObject(siblingHelpDoc);
        }

        // 判断是移动到目标文档之前，还是移动到目标文档之后
        if ("after".equals(action)) {
            dbHelpDoc.setPrevDocId(targetHelpDoc.getDocId());
            siblingHelpDoc = helpDocDao.getObjectByProperty("prevDocId", targetHelpDoc.getDocId());
            if (siblingHelpDoc != null) {
                siblingHelpDoc.setPrevDocId(dbHelpDoc.getDocId());
                helpDocDao.updateObject(siblingHelpDoc);
            }
        } else {
            dbHelpDoc.setPrevDocId(targetHelpDoc.getPrevDocId());
            targetHelpDoc.setPrevDocId(dbHelpDoc.getDocId());
            helpDocDao.updateObject(targetHelpDoc);
        }

        dbHelpDoc.setDocLevel(targetHelpDoc.getDocLevel());
        dbHelpDoc.setLastUpdateTime(new Date());
        dbHelpDoc.setDocPath(targetHelpDoc.getDocPath());
        helpDocDao.updateObject(dbHelpDoc);
        updateChildren(oldPath, targetHelpDoc, dbHelpDoc);
    }

    @Override
    public void innerCatalog(String docId, String targetDocId) {
        HelpDoc targetHelpDoc = helpDocDao.getObjectById(targetDocId);
        HelpDoc dbHelpDoc = helpDocDao.getObjectById(docId);
        String oldPath = dbHelpDoc.getDocPath();
        targetHelpDoc.setPrevDocId(dbHelpDoc.getPrevDocId());
        dbHelpDoc.setPrevDocId(FIRST_PREV_DOC_ID);
        dbHelpDoc.setDocLevel(dbHelpDoc.getDocLevel() + 1);
        dbHelpDoc.setDocPath(targetHelpDoc.getDocPath() + "/" + targetDocId);
        helpDocDao.updateObject(dbHelpDoc);
        helpDocDao.updateObject(targetHelpDoc);
        int i = DatabaseOptUtils.doExecuteSql(helpDocDao, "update f_help_doc set PREV_DOCID=? where doc_id<>? and PREV_DOCID='0' and OS_ID=? and DOC_PATH=?",
            new String[]{dbHelpDoc.getDocId(), dbHelpDoc.getDocId(), dbHelpDoc.getOsId(), dbHelpDoc.getDocPath()});
        logger.debug("更新了" + i + "条");
        updateChildren(oldPath, targetHelpDoc, dbHelpDoc);
    }

    private void updateChildren(String oldPath, HelpDoc targetHelpDoc, HelpDoc helpDoc) {
        List<HelpDoc> childrenHelpDocs = this.findChildren(helpDoc.getDocId());
        if (!childrenHelpDocs.isEmpty()) {
            // 如果children数据比较多,改用sql的方式来修改
            int levelMinus = helpDoc.getDocLevel() - targetHelpDoc.getDocLevel();
            String oldDocPath = oldPath + "/";
            String newDocPath = helpDoc.getDocPath() + "/";
            for (HelpDoc childrenHelpDoc : childrenHelpDocs) {
                String childrenDocPath = childrenHelpDoc.getDocPath();
                if (childrenDocPath.contains(oldDocPath)) {
                    // 正常情况
                    childrenDocPath = childrenDocPath.replace(oldDocPath, newDocPath);
                } else {
                    // 非正常情况，可能是原来的path不对了
                    logger.warn("文档【{}】 的path不正确", childrenHelpDoc.getDocTitle());
                }
                childrenHelpDoc.setDocPath(childrenDocPath);
                childrenHelpDoc.setDocLevel(childrenHelpDoc.getDocLevel() + levelMinus);
                helpDocDao.updateObject(childrenHelpDoc);
            }
        }
    }

    @Override
    public void updatePrevDoc(List<HelpDoc> list, String action) {
        if (action == null) {
            // 更新 PrevDoc
            updatePrevDoc(searchHelpDocByLevel(list));
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

