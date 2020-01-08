package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centit.framework.core.dao.DictionaryMapUtils;
import com.centit.framework.jdbc.dao.DatabaseOptUtils;
import com.centit.framework.jdbc.service.BaseEntityManagerImpl;
import com.centit.search.document.ObjectDocument;
import com.centit.search.service.Indexer;
import com.centit.search.service.Searcher;
import com.centit.support.algorithm.CollectionsOpt;
import com.centit.support.database.utils.PageDesc;
import com.centit.workorder.dao.*;
import com.centit.workorder.po.*;
import com.centit.workorder.service.HelpDocManager;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
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

    @Resource
    private HelpDocScoreDao helpDocScoreDao;

    @Resource
    private HelpDocCommentDao helpDocCommentDao;

    @Resource
    private Searcher esSearcher;

    @Resource
    private Indexer esIndexer;

    @Resource
    private HelpDocVersionDao helpDocVersionDao;

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
        esIndexer.updateDocument(objectDocument);

        return dbHelpDoc;
    }

    private List<HelpDoc> findChildren(String parentId){
        List<HelpDoc> result = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("parentId", "%"+parentId);
        List<HelpDoc> helpDocs = helpDocDao.listObjects(map);
        result.addAll(helpDocs);
        for(HelpDoc h : helpDocs){
            result.addAll(findChildren(h.getDocId()));
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
        List<HelpDocVersion> versions = helpDocVersionDao.listObjects(map);
        for (HelpDocVersion version : versions) {
            helpDocVersionDao.deleteObject(version);//删除所有历史版本
        }

        helpDocCommentDao.deleteObjectById(docId);//删除评论
        helpDocScoreDao.deleteObjectById(docId);//删除评分

        ObjectDocument objectDocument = helpDoc.generateObjectDocument();
        esIndexer.deleteDocument(objectDocument);
    }

    @Override
    @Transactional
    public void comment(String docId, HelpDocComment helpDocComment) {

        helpDocComment.setDocId(docId);
        helpDocCommentDao.saveNewObject(helpDocComment);
    }

    @Override
    @Transactional
    public void score(String docId, HelpDocScore helpDocScore) {

        helpDocScore.setDocId(docId);
        helpDocScoreDao.saveNewObject(helpDocScore);
    }

    @Override
    @Transactional
    public HelpDoc editContent(String docId, String content, String userCode) {
        HelpDoc helpDoc = helpDocDao.getObjectById(docId);
        //保存旧版本
        int docVersion = DatabaseOptUtils.getSequenceNextValue(helpDocVersionDao, "DOC_VERSION").intValue();
        helpDocVersionDao.saveNewObject(helpDoc.generateVersion(docVersion));
        helpDoc.setDocFile(content);
        helpDoc.setUpdateUser(userCode);
        helpDocDao.updateObject(helpDoc);

        ObjectDocument objectDocument = helpDoc.generateObjectDocument();
        esIndexer.updateDocument(objectDocument);
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
    public JSONArray searchComments(String docId) {
        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("docId",docId);
        List<HelpDocComment> list = helpDocCommentDao.listObjects(filterMap);
        JSONArray array = DictionaryMapUtils.objectsToJSONArray(list);
        return array;
    }

    @Override
    @Transactional
    public JSONObject searchScores(String docId) {
        JSONObject obj = new JSONObject();
        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("docId",docId);
        List<HelpDocScore> list = helpDocScoreDao.listObjects(filterMap);
        int times = list.size();
        if(times > 0) {
            obj.put("times", times);
            int sum = 0;
            for(int i = 0; i < times; i++){
                sum += list.get(i).getDocScore();
            }
            float score = (float)sum/times;
            DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
            String average = df.format(score);//返回的是String类型的
            obj.put("average", average);
        }
        return obj;
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
    public List<Map<String, Object>> fullSearch(String keyWord, PageDesc pageDesc){
 //        List<Map<String, Object>> list = esSearcher.search(
//                keyWord,pageDesc.getPageNo(),pageDesc.getPageSize());
        List<Map<String, Object>> list = esSearcher.search(
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

