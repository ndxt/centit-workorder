package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centit.framework.jdbc.service.BaseEntityManagerImpl;
import com.centit.search.document.ObjectDocument;
import com.centit.search.service.Impl.ESIndexer;
import com.centit.search.service.Impl.ESSearcher;
import com.centit.search.service.Indexer;
import com.centit.search.service.Searcher;
import com.centit.support.algorithm.CollectionsOpt;
import com.centit.support.database.utils.PageDesc;
import com.centit.workorder.dao.HelpDocDao;
import com.centit.workorder.dao.QuestionCatalogDao;
import com.centit.workorder.po.HelpDoc;
import com.centit.workorder.po.QuestionCatalog;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HelpDoc  Service.
 * create by scaffold 2017-05-08
 * @author codefan@sina.com
 * 系统帮助文档null
*/
@Service
public class HelpDocManagerImpl
        extends BaseEntityManagerImpl<HelpDoc,String,HelpDocDao>
    implements HelpDocManager{

    public static final Logger logger = LoggerFactory.getLogger(HelpDocManager.class);

    @Autowired(required = false)
    private ESSearcher esSearcher;

    @Autowired(required = false)
    private ESIndexer esIndexer;

    @Resource
    private QuestionCatalogDao questionCatalogDao;

    private HelpDocDao helpDocDao ;

    @Resource(name = "helpDocDao")
    @NotNull
    public void setHelpDocDao(HelpDocDao baseDao)
    {
        this.helpDocDao = baseDao;
        setBaseDao(this.helpDocDao);
    }


    /**
     * 创建帮助文档
     * @param helpDoc
     */
    @Override
    @Transactional
    public HelpDoc createHelpDoc(HelpDoc helpDoc) {

        String parentDocId = helpDoc.getDocPath();

        HelpDoc parentDoc = helpDocDao.getObjectById(parentDocId);
        if(parentDoc != null){
            helpDoc.setDocPath(parentDoc.getDocPath().contains("/") ?
                    parentDoc.getDocPath() + "/" + parentDocId : "/" + parentDocId);
            helpDoc.setDocLevel(parentDoc.getDocLevel() + 1);
        }else{
            helpDoc.setDocPath("0");
            helpDoc.setDocLevel(1);
        }
        helpDocDao.saveNewObject(helpDoc);

        ObjectDocument objectDocument = helpDoc.generateObjectDocument();
        esIndexer.saveNewDocument(objectDocument);

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

    private List<HelpDoc> findChildren(String parentId){
        List<HelpDoc> result = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("parentId", "%"+parentId);
        List<HelpDoc> helpDocs = helpDocDao.listObjects(map);
        if(helpDocs != null && helpDocs.size()>0) {
            result.addAll(helpDocs);
            for (HelpDoc h : helpDocs) {
                List<HelpDoc> subDocs = findChildren(h.getDocId());
                if(subDocs!=null && subDocs.size()>0) {
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
        helpDocDao.deleteObjectById(docId);
        List<HelpDoc> helpDocs = findChildren(docId);
        if(helpDocs !=null && helpDocs.size()>0) {
            for(HelpDoc doc : helpDocs) {
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
    public JSONArray searchHelpdocByLevel(String osId) {
        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("osId",osId);
        List<HelpDoc> list = helpDocDao.listObjects(filterMap);
        return CollectionsOpt.srotAsTreeAndToJSON(list, ( p,  c) -> {
                String parent = p.getDocId();
                String child = c.getDocPath();
                if(child.lastIndexOf("/") != -1) {
                    String temp = child.substring(child.lastIndexOf("/")+1, child.length());

                    return parent.equals(temp);
                }else{
                    return false;
                }
            }, "children");
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public List<HelpDoc> searchHelpdocByType(Map<String,Object>queryParamsMap, PageDesc pageDesc) {
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
    @Transactional
    public List<Map<String, Object>> fullTextSearch(String catalogId, PageDesc pageDesc){

        QuestionCatalog questionCatalog = questionCatalogDao.getObjectById(catalogId);
        if(questionCatalog != null){
            if (StringUtils.isNotBlank(questionCatalog.getCatalogKeyWords())){
//                List<Map<String, Object>> list = searcher.search(
////                        questionCatalog.getCatalogKeyWords(),pageDesc.getPageNo(),pageDesc.getPageSize());
                List<Map<String, Object>> list = esSearcher.search(
                        questionCatalog.getCatalogKeyWords(),pageDesc.getPageNo(),pageDesc.getPageSize()).getRight();

                if (list != null && list.size()>0){
                    for (int i=0;i<list.size();i++){
                        JSONObject json = JSONObject.parseObject((String) list.get(i).get("optUrl"));
                        list.get(i).put("docId",json.get("docId").toString());
                        list.get(i).put("docPath",json.get("docPath").toString());
                    }
                }
                return list;
            }
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> fullSearch(Map<String,Object>searchQuery,String keyWord, PageDesc pageDesc){
 //        List<Map<String, Object>> list = esSearcher.search(
//                keyWord,pageDesc.getPageNo(),pageDesc.getPageSize());
        List<Map<String, Object>> list = esSearcher.search(searchQuery,
                keyWord,pageDesc.getPageNo(),pageDesc.getPageSize()).getRight();
        if (list != null && list.size()>0){
            for (int i=0;i<list.size();i++){
                JSONObject json = JSONObject.parseObject((String) list.get(i).get("optUrl"));
                list.get(i).put("docId",json.get("docId").toString());
                list.get(i).put("docPath",json.get("docPath").toString());
                HelpDoc helpDoc = helpDocDao.getObjectById(json.get("docId").toString());
                if (helpDoc != null){
                    list.get(i).put("lastUpdateTime",helpDoc.getLastUpdateTime());
                }
            }
        }
        return list;
    }
}

