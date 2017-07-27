package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
import com.centit.workorder.dao.*;
import com.centit.workorder.po.HelpDoc;
import com.centit.workorder.po.QuestionCatalog;
import com.centit.workorder.po.QuestionInfo;
import com.centit.workorder.service.QuestionCatalogManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * QuestionCatalog  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统问题类别null   
*/
@Service
public class QuestionCatalogManagerImpl 
		extends BaseEntityManagerImpl<QuestionCatalog,java.lang.String,QuestionCatalogDao>
	implements QuestionCatalogManager{

	public static final Log log = LogFactory.getLog(QuestionCatalogManager.class);

	@Resource(name = "questionRoundDao")
	private QuestionRoundDao questionRoundDao ;

	@Resource(name = "questionInfoDao")
	private QuestionInfoDao questionInfoDao ;

	@Resource(name = "helpDocDao")
	private HelpDocDao helpDocDao ;
	@Resource
	private HelpDocScoreDao helpDocScoreDao;

	@Resource
	private HelpDocCommentDao helpDocCommentDao;

	@Resource
	private HelpDocVersionDao helpDocVersionDao;

	
	private QuestionCatalogDao questionCatalogDao ;
	
	@Resource(name = "questionCatalogDao")
    @NotNull
	public void setQuestionCatalogDao(QuestionCatalogDao baseDao)
	{
		this.questionCatalogDao = baseDao;
		setBaseDao(this.questionCatalogDao);
	}
	
/*
 	@PostConstruct
    public void init() {
        
    }
 	
 */

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public JSONArray getAllCatalog(Map<String, Object> queryParamsMap, PageDesc pageDesc) {
		JSONArray dataArray = questionCatalogDao.getCatalog(baseDao,queryParamsMap,pageDesc);
		return dataArray;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public void deleteCatalog(String catalogId) {
		questionCatalogDao.deleteObjectById(catalogId);
		List<HelpDoc> helpDocs = helpDocDao.listHelpDocIdWithCatalogId(catalogId);
		List<QuestionInfo> questionIdList = questionInfoDao.listQuestionIdWithCatalogId(catalogId);
		helpDocDao.deleteObjectsAsTabulation("catalogId", catalogId);
		questionInfoDao.deleteObjectsAsTabulation("catalogId", catalogId);
		for (HelpDoc helpDoc:helpDocs){
			helpDocVersionDao.deleteObjectsAsTabulation("cid.docId", helpDoc.getDocId());//删除所有历史版本
			helpDocCommentDao.deleteObjectsAsTabulation("docId", helpDoc.getDocId());//删除评论
			helpDocScoreDao.deleteObjectsAsTabulation("docId", helpDoc.getDocId());//删除评分
		}
		for (QuestionInfo questionInfo:questionIdList){
			questionRoundDao.deleteObjectsAsTabulation("questionId",questionInfo.getQuestionId());
		}
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public String updateCatalog(QuestionCatalog questionCatalog) {
		String catalogId = questionCatalog.getCatalogId();
		QuestionCatalog dbQuestionCatalog = questionCatalogDao.getObjectById(catalogId);
		dbQuestionCatalog.setCatalogName(questionCatalog.getCatalogName());
		dbQuestionCatalog.setTimeLimit(questionCatalog.getTimeLimit());
		questionCatalogDao.mergeObject(dbQuestionCatalog);
		return catalogId;
	}


}

