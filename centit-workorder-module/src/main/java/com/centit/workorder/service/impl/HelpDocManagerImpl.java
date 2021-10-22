package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centit.framework.jdbc.service.BaseEntityManagerImpl;
import com.centit.search.document.ObjectDocument;
import com.centit.search.service.Impl.ESIndexer;
import com.centit.search.service.Impl.ESSearcher;
import com.centit.support.algorithm.CollectionsOpt;
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

    @Autowired(required = false)
    private ESSearcher esSearcher;

    @Autowired(required = false)
    private ESIndexer esIndexer;


    private HelpDocDao helpDocDao;

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
        HelpDoc parentDoc = helpDocDao.getObjectById(parentDocId);
        if (parentDoc != null) {
            helpDoc.setDocPath(parentDoc.getDocPath().contains("/") ?
                parentDoc.getDocPath() + "/" + parentDocId : "/" + parentDocId);
            helpDoc.setDocLevel(parentDoc.getDocLevel() + 1);
        } else {
            helpDoc.setDocPath("0");
            helpDoc.setDocLevel(1);
        }

        // 设置prevDocId
        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("osId", helpDoc.getOsId());
        filterMap.put("docLevel", helpDoc.getDocLevel());
        filterMap.put("docPath", helpDoc.getDocPath());
        // 获取同一层级的所有文档
        List<HelpDoc> list = helpDocDao.listObjects(filterMap);
        LinkedList<HelpDoc> sortHelpDocs = sortList(list);
        // 排序后找到最后一个文档，将其设置为新增目录的prevDocId
        String prevDocId = sortHelpDocs.get(sortHelpDocs.size() - 1).getDocId();
        helpDoc.setPrevDocId(prevDocId);
        helpDocDao.saveNewObject(helpDoc);

        ObjectDocument objectDocument = helpDoc.generateObjectDocument();
        esIndexer.saveNewDocument(objectDocument);

        return helpDoc;
    }

    @Override
    @Transactional
    public HelpDoc editHelpDoc(String docId, HelpDoc helpDoc) {
        HelpDoc dbHelpDoc = helpDocDao.getObjectById(docId);

        // 除了修改名字外还移动了文档的位置（修改了目录）
        if (helpDoc.getDocPath() != null && !helpDoc.getDocPath().equals(dbHelpDoc.getDocPath())) {
            // （1）修改移动文档的下一个文档 的prevDocId
            HelpDoc siblingHelpDoc = helpDocDao.getObjectByProperty("prevDocId", helpDoc.getDocId());
            if (siblingHelpDoc != null) {
                siblingHelpDoc.setPrevDocId(helpDoc.getPrevDocId());
                helpDocDao.updateObject(siblingHelpDoc);
            }

            // （2）修改移动文档的prevDocId docLevel docPath
            // 先获取移动后的父文档
            String parentDocId = helpDoc.getDocPath().substring(helpDoc.getDocPath().lastIndexOf("/") + 1);
            HelpDoc parentDoc = helpDocDao.getObjectById(parentDocId);
            Map<String, Object> filterMap = new HashMap<>();
            filterMap.put("osId", parentDoc.getOsId());
            filterMap.put("docLevel", parentDoc.getDocLevel() + 1);
            if ("0".equals(parentDoc.getDocPath())) {
                filterMap.put("docPath", "/" + parentDoc.getDocId());
            } else {
                filterMap.put("docPath", parentDoc.getDocPath() + "/" + parentDoc.getDocId());
            }
            // 获取子一层级的所有文档
            List<HelpDoc> list = helpDocDao.listObjects(filterMap);
            if (list != null && !list.isEmpty()) {
                LinkedList<HelpDoc> sortHelpDocs = sortList(list);
                // 排序后找到子一层级的最后一个文档
                HelpDoc endDoc = sortHelpDocs.get(sortHelpDocs.size() - 1);
                helpDoc.setPrevDocId(endDoc.getDocId());
                helpDoc.setDocLevel(endDoc.getDocLevel());
                helpDoc.setDocPath(endDoc.getDocPath());
            }
        }
        dbHelpDoc.copyNotNullProperty(helpDoc);

        helpDocDao.updateObject(dbHelpDoc);
        ObjectDocument objectDocument = helpDoc.generateObjectDocument();
        esIndexer.mergeDocument(objectDocument);

        return dbHelpDoc;
    }

    private List<HelpDoc> findChildren(String parentId) {
        List<HelpDoc> result = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("parentId", "%" + parentId);
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

    private static LinkedList<HelpDoc> sortList(List<HelpDoc> list) {
        LinkedList<HelpDoc> linkedList = new LinkedList<>();
        for (HelpDoc helpDoc : list) {
            linkedList.add(helpDoc);
            if (linkedList.size() > 1) {
                for (int i = 0; i < linkedList.size(); i++) {
                    HelpDoc tempDoc = linkedList.get(i);
                    for (int j = i + 1; j < linkedList.size(); j++) {
                        if (tempDoc.getPrevDocId() != null &&
                            linkedList.get(j).getDocId().equals(tempDoc.getPrevDocId())) {
                            linkedList.add(j + 1, tempDoc);
                            linkedList.remove(i);
                            break;
                        }
                    }
                }
            }
        }
        return linkedList;
    }

    @Override
    @Transactional
    public JSONArray searchHelpdocByLevel(List<HelpDoc> list) {
        return CollectionsOpt.srotAsTreeAndToJSON(sortList(list), (p, c) -> {
            String parent = p.getDocId();
            String child = c.getDocPath();
            if (child.lastIndexOf("/") != -1) {
                String temp = child.substring(child.lastIndexOf("/") + 1, child.length());

                return parent.equals(temp);
            } else {
                return false;
            }
        }, "children");
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
//        dbHelpDoc.copyNotNullProperty(helpDoc);
        // 目标文档(B)，修改文档的prevDocId
        HelpDoc targetHelpDoc = helpDocDao.getObjectById(targetDocId);
        // 要移动文档的下一个文档(C)，修改文档的prevDocId
        HelpDoc siblingHelpDoc = helpDocDao.getObjectByProperty("prevDocId", dbHelpDoc.getDocId());
        // 移动文档的children文档(A1,A11,A111……)，修改children文档的path和level
        List<HelpDoc> childrenHelpDocs = this.findChildren(docId);

        if (!childrenHelpDocs.isEmpty()) {
            // 如果children数据比较多,改用sql的方式来修改
            int levelMinus = dbHelpDoc.getDocLevel() - targetHelpDoc.getDocLevel();
            String oldDocPath = dbHelpDoc.getDocPath() + "/";
            String newDocPath = targetHelpDoc.getDocPath() + "/";
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
                childrenHelpDoc.setDocLevel(childrenHelpDoc.getDocLevel() - levelMinus);
                helpDocDao.updateObject(childrenHelpDoc);
            }
        }

        if (siblingHelpDoc != null) {
            siblingHelpDoc.setPrevDocId(dbHelpDoc.getPrevDocId());
            helpDocDao.updateObject(siblingHelpDoc);
        }

        // 判断是移动到目标文档之前，还是移动到目标文档之后
        if ("after".equals(action)) {
            dbHelpDoc.setPrevDocId(targetHelpDoc.getDocId());
            siblingHelpDoc = helpDocDao.getObjectByProperty("prevDocId", targetHelpDoc.getDocId());
            if (siblingHelpDoc != null) {
                siblingHelpDoc.setPrevDocId(targetHelpDoc.getDocId());
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
    }

    @Override
    public void updatePrevDoc(List<HelpDoc> list, String action) {
        if (action == null) {
            // 更新 PrevDoc
            JSONArray listObjects = this.searchHelpdocByLevel(list);
            updatePrevDoc(listObjects);
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

    private void updatePrevDoc(JSONArray docArray) {
        final String docId_str = "docId";
        final String docTitle_str = "docTitle";
        for (int i = 1; i < docArray.size(); i++) {
            JSONObject docJson = docArray.getJSONObject(i);
            JSONObject prevDocJson = docArray.getJSONObject(i - 1);
            HelpDoc helpDoc = this.getObjectById(docJson.getString(docId_str));
            if (helpDoc.getPrevDocId() == null || !helpDoc.getPrevDocId().equals(prevDocJson.getString(docId_str))) {
                logger.info("修改 【{}】 的上一个doc为 【{}】 ", helpDoc.getDocTitle(), prevDocJson.getString(docTitle_str));
                helpDoc.setPrevDocId(prevDocJson.getString(docId_str));
                this.updateObject(helpDoc);
            } else {
                logger.info("无需修改-- 【{}】 ", helpDoc.getDocTitle());
            }
            JSONArray childrenDoc = prevDocJson.getJSONArray("children");
            if (childrenDoc != null) {
                String docTitle = childrenDoc.getJSONObject(0).getString(docTitle_str);
                String docId = childrenDoc.getJSONObject(0).getString(docId_str);
                HelpDoc childrenHelpDoc = this.getObjectById(docId);
                if (childrenHelpDoc.getPrevDocId() == null || !childrenHelpDoc.getPrevDocId().equals(prevDocJson.getString(docId_str))) {
                    logger.info("修改 【{}】 的上一个doc为 【{}】 ", docTitle, prevDocJson.getString(docTitle_str));
                    childrenHelpDoc.setPrevDocId(prevDocJson.getString(docId_str));
                    this.updateObject(childrenHelpDoc);
                } else {
                    logger.info("无需修改--- 【{}】 ", docTitle);
                }
                updatePrevDoc(childrenDoc);
            }
        }
    }

}

