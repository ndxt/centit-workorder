package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
import com.centit.support.algorithm.DatetimeOpt;
import com.centit.workorder.dao.AssistOperatorDao;
import com.centit.workorder.dao.QuestionCatalogDao;
import com.centit.workorder.dao.QuestionInfoDao;
import com.centit.workorder.dao.QuestionRoundDao;
import com.centit.workorder.po.*;
import com.centit.workorder.service.QuestionInfoManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * QuestionInfo  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统问题列表null   
*/
@Service
public class QuestionInfoManagerImpl 
		extends BaseEntityManagerImpl<QuestionInfo,java.lang.String,QuestionInfoDao>
	implements QuestionInfoManager{

	public static final Log log = LogFactory.getLog(QuestionInfoManager.class);

	@Value("${app.home}")
	private String home;
	@Value("${dir.config}")
	private String config;

	@Resource(name = "questionRoundDao")
	private QuestionRoundDao questionRoundDao ;

	@Resource(name = "assistOperatorDao")
	private AssistOperatorDao assistOperatorDao ;

	@Resource(name = "questionCatalogDao")
	private QuestionCatalogDao questionCatalogDao ;

	private QuestionInfoDao questionInfoDao ;
	
	@Resource(name = "questionInfoDao")
    @NotNull
	public void setQuestionInfoDao(QuestionInfoDao baseDao)
	{
		this.questionInfoDao = baseDao;
		setBaseDao(this.questionInfoDao);
	}


	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public List<QuestionRound> getQuestionRoundWithQuestionId(String questionId) {
		List<QuestionRound> list = questionRoundDao.listObjectByProperty("questionId",questionId);
		return list;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public List<QuestionInfo> getQuestionInfoWithCatalogId(String catalogId) {
		List<QuestionInfo> list = questionInfoDao.listObjectByProperty("catalogId",catalogId);
		return list;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public List<QuestionInfo> getUnabsorbedQuestion() {
		List<QuestionInfo> list = questionInfoDao.listObjectByProperty("editState","N");
		return list;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public Serializable saveQuestionRound(QuestionRound questionRound) {
		questionRound.setOrA("Q");
		questionRound.setCreateTime(DatetimeOpt.currentUtilDate());
		Serializable pk = questionRoundDao.saveNewObject(questionRound);
		return pk;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public void deleteQuestion(String questionId) {
		questionRoundDao.deleteObjectsAsTabulation("questionId",questionId);
		questionInfoDao.deleteObjectsAsTabulation("questionId",questionId);
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public JSONArray getQuestionInfo(Map<String, Object> queryParamsMap, PageDesc pageDesc) {
		JSONArray dataList = questionInfoDao.getQuestionInfo(baseDao,queryParamsMap,pageDesc);
		return dataList;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public QuestionRound replayQuestion(QuestionRound questionRound) {
		questionRound.setEditState("U");
		questionRound.setRoundState("C");
		questionRound.setOrA("A");
		questionRound.setCreateTime(DatetimeOpt.currentUtilDate());
		questionRound.setLastUpdateTime(DatetimeOpt.currentUtilDate());
		questionRoundDao.saveNewObject(questionRound);
		QuestionInfo questionInfo = questionInfoDao.getObjectById(questionRound.getQuestionId());
		questionInfo.setLastUpdateTime(DatetimeOpt.currentUtilDate());
		questionInfo.setEditState("U");
		questionInfoDao.saveObject(questionInfo);
		return questionRound;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public Serializable createQuestion(QuestionInfo questionInfo) {
		String catalogId = questionInfo.getCatalogId();
		QuestionCatalog questionCatalog = questionCatalogDao.getObjectById(catalogId);
		questionInfo.setCurrentOperator(questionCatalog.getDefaultOperator());
		questionInfo.setCreateTime(DatetimeOpt.currentUtilDate());
		questionInfo.setQuestionState("N");
		questionInfo.setEditState("N");
		questionInfo.setOsId(questionCatalog.getOsId());
		Serializable pk = questionInfoDao.saveNewObject(questionInfo);
		return pk;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public String evaluateAndCloseQuestion(String score, String questionId) {
		int evaluateScore = Integer.valueOf(score);
		QuestionInfo questionInfo = questionInfoDao.getObjectById(questionId);
		questionInfo.setEvaluateScore(evaluateScore);
		questionInfo.setQuestionState("C");
		questionInfo.setClosedTime(DatetimeOpt.currentUtilDate());
		questionInfoDao.mergeObject(questionInfo);
		return questionId;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public String closeQuestion(String questionId) {
		QuestionInfo questionInfo = questionInfoDao.getObjectById(questionId);
		questionInfo.setQuestionState("C");
		questionInfo.setClosedTime(DatetimeOpt.currentUtilDate());
		questionInfoDao.mergeObject(questionInfo);
		return questionId;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public Serializable createAssistOperator(AssistOperator assistOperator) {
		assistOperator.setCreateDate(DatetimeOpt.currentUtilDate());
		return assistOperatorDao.saveNewObject(assistOperator);
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public void deleteObject(AssistOperatorId assistOperatorId) {
		assistOperatorDao.deleteObjectForceById(assistOperatorId);
	}

}

