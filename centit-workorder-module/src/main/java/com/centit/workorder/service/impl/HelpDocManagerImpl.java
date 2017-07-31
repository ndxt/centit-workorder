package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centit.framework.core.dao.DictionaryMapUtils;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.DatabaseOptUtils;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
import com.centit.support.algorithm.ListOpt;
import com.centit.support.database.QueryAndNamedParams;
import com.centit.support.database.QueryUtils;
import com.centit.workorder.dao.HelpDocCommentDao;
import com.centit.workorder.dao.HelpDocDao;
import com.centit.workorder.dao.HelpDocScoreDao;
import com.centit.workorder.dao.HelpDocVersionDao;
import com.centit.workorder.po.HelpDoc;
import com.centit.workorder.po.HelpDocComment;
import com.centit.workorder.po.HelpDocScore;
import com.centit.workorder.service.HelpDocManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.util.Date;
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

	public static final Log log = LogFactory.getLog(HelpDocManager.class);

	@Resource
	private HelpDocScoreDao helpDocScoreDao;

	@Resource
	private HelpDocCommentDao helpDocCommentDao;

	@Resource
	private HelpDocVersionDao helpDocVersionDao;
	
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
	@Transactional(propagation = Propagation.REQUIRED)
	public String createHelpDoc(HelpDoc helpDoc) {

		helpDoc.setLastUpdateTime(new Date());
		saveNewObject(helpDoc);
		return helpDoc.getDocId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateHelpDoc(String docId, HelpDoc helpDoc) {
		HelpDoc dbHelpDoc = helpDocDao.getObjectById( docId);

		dbHelpDoc .copyNotNullProperty(helpDoc);
		helpDocDao.mergeObject(dbHelpDoc);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteHelpDoc(String docId) {
		helpDocDao.deleteObjectById(docId);

		helpDocVersionDao.deleteObjectsAsTabulation("cid.docId", docId);//删除所有历史版本
		helpDocCommentDao.deleteObjectsAsTabulation("docId", docId);//删除评论
		helpDocScoreDao.deleteObjectsAsTabulation("docId", docId);//删除评分
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String comment(String docId, HelpDocComment helpDocComment) {

		helpDocComment.setDocId(docId);
		helpDocCommentDao.saveNewObject(helpDocComment);
		return helpDocComment.getCommentId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String score(String docId, HelpDocScore helpDocScore) {

		helpDocScore.setDocId(docId);
		helpDocScoreDao.saveNewObject(helpDocScore);
		return helpDocScore.getScoreId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void editContent(String docId, String content) {
		HelpDoc helpDoc = helpDocDao.getObjectById( docId);
		//保存旧版本
		helpDocVersionDao.saveNewObject(helpDoc.generateVersion());
		helpDoc.setDocFile(content);
		helpDocDao.mergeObject(helpDoc);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public JSONArray searchHelpdocByLevel(String osId) {
		Map<String, Object> map = new HashMap<>();
		map.put("osId", osId);
//		List<HelpDoc> list = listObjects(map);
		List<HelpDoc> list = helpDocDao.listObjectByProperty("osId", osId);
//		List<HelpDoc> list = helpDocDao.getCurrentSession().createQuery("from HelpDoc h where 1=1").list();
		return ListOpt.srotAsTreeAndToJSON(list,  ( p,  c) -> {
				String parent = p.getDocId();
				String child = c.getDocPath();
				if(child.lastIndexOf("/") != -1) {
					String temp = child.substring(child.lastIndexOf("/")+1, child.length());

					return parent.equals(temp);
				}else{
					return false;
				}
			}, "c");

	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public JSONArray treeSearch(String osId) {
		Map<String, Object> map = new HashMap<>();
		map.put("osId", osId);
		String queryStatement =
				"select h.DOC_ID as id,h.DOC_TITLE as name, h.DOC_PATH as parentId "
						+" from f_help_doc h "
						+" where 1=1 "
						+ " [ :osId | and h.OS_ID = :osId ]";

		QueryAndNamedParams qap = QueryUtils.translateQuery(queryStatement,map);
		JSONArray dataList = //DictionaryMapUtils.objectsToJSONArray(
				DatabaseOptUtils.findObjectsAsJSONByHql(
						baseDao,qap.getQuery(), qap.getParams(),null);
		return dataList;

	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public JSONArray searchHelpdocByType(Map<String,Object>queryParamsMap, PageDesc pageDesc) {
		String queryStatement =
				"select h.DOC_ID,h.DOC_TITLE, h.DOC_FILE, h.DOC_LEVEL, h.DOC_PATH "
				+" from f_help_doc h left join "
				+" (select c.DOC_ID,count(c.COMMENT_ID) count"
				+" from f_help_doc_comment c"
				+" group by c.DOC_ID) m "
				+" on h.DOC_ID = m.DOC_ID "
				+" where 1=1 "
				+ " [ :catalogId | and h.CATALOG_ID = :catalogId ]"
				+ " [ :osId | and h.OS_ID = :osId ]"
				+ " order by count desc";

		QueryAndNamedParams qap = QueryUtils.translateQuery(queryStatement,queryParamsMap);
		JSONArray dataList = //DictionaryMapUtils.objectsToJSONArray(
				DatabaseOptUtils.findObjectsAsJSONByHql(baseDao,
					qap.getQuery(), qap.getParams(),pageDesc);
		return dataList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public JSONArray searchComments(String docId) {
		List<HelpDocComment> list = helpDocCommentDao.listObjectByProperty("docId", docId);
		JSONArray array = DictionaryMapUtils.objectsToJSONArray(list);
		return array;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public JSONObject searchScores(String docId) {
		JSONObject obj = new JSONObject();
		List<HelpDocScore> list = helpDocScoreDao.listObjectByProperty("docId", docId);
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
}

