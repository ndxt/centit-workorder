package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
import com.centit.framework.model.adapter.PlatformEnvironment;

import com.centit.framework.model.basedata.IUserInfo;
import com.centit.workorder.dao.QuestionCatalogDao;
import com.centit.workorder.dao.QuestionInfoDao;
import com.centit.workorder.dao.QuestionRoundDao;
import com.centit.workorder.po.QuestionCatalog;
import com.centit.workorder.po.QuestionInfo;
import com.centit.workorder.po.QuestionRound;
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

	@Resource(name = "questionCatalogDao")
	private QuestionCatalogDao questionCatalogDao ;

	@Resource
	protected PlatformEnvironment platformEnvironment;

	private QuestionInfoDao questionInfoDao ;
	
	@Resource(name = "questionInfoDao")
    @NotNull
	public void setQuestionInfoDao(QuestionInfoDao baseDao)
	{
		this.questionInfoDao = baseDao;
		setBaseDao(this.questionInfoDao);
	}

//	@Override
//    @Transactional(propagation= Propagation.REQUIRED)
//	public JSONArray listQuestionInfosAsJson(
//            String[] fields,
//            Map<String, Object> filterMap, PageDesc pageDesc){
//
//		return SysDaoOptUtils.listObjectsAsJson(baseDao, fields, QuestionInfo.class,
//    			filterMap, pageDesc);
//	}

//	@Override
//	@Transactional(propagation= Propagation.REQUIRED)
//	public List<QuestionInfo> getQuestionInfoWithUserCode(String userCode) {
//		List<QuestionInfo> list = questionInfoDao.getQuestionInfoWithUser(userCode);
//		return list;
//	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public List<QuestionRound> getQuestionRoundWithQuestionId(String questionId) {
		List<QuestionRound> list = questionRoundDao.listQuestionRoundWithQuestionId(questionId);
		return list;
	}

//	@Override
//	@Transactional(propagation= Propagation.REQUIRED)
//	public List<QuestionInfo> getQuestionInfoWithOperator(String operator) {
//		List<QuestionInfo> list = questionInfoDao.getQuestionInfoWithCurrentOperator(operator);
//		return list;
//	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public Serializable saveQuestionRound(QuestionRound questionRound) {
		questionRound.setOrA("Q");
		questionRound.setCreateTime(new Date());
		Serializable pk = questionRoundDao.saveNewObject(questionRound);
		return pk;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public void deleteQuestion(String questionId) {
		questionRoundDao.deleteObjectsAsTabulation("questionId",questionId);
		questionInfoDao.deleteObjectsAsTabulation("questionId",questionId);
	}

//	@Override
//	@Transactional(propagation= Propagation.REQUIRED)
//	public QuestionInfo getQuestionInfoWithId(String questionId) {
//		QuestionInfo questionInfo = questionInfoDao.getQuestionInfoWithId(questionId);
//		return questionInfo;
//	}

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
		questionRound.setCreateTime(new Date());
		questionRound.setLastUpdateTime(new Date());
		questionRoundDao.saveNewObject(questionRound);
		QuestionInfo questionInfo = questionInfoDao.getObjectById(questionRound.getQuestionId());
		questionInfo.setLastUpdateTime(new Date());
		questionInfo.setEditState("U");
		questionInfoDao.saveObject(questionInfo);
		return questionRound;
	}

	@Override
	public List<String> getAllOperator() {
		List<String> operator = new ArrayList<>();
		List<? extends IUserInfo> userInfos =  platformEnvironment.listAllUsers();
		for (IUserInfo user : userInfos){
			operator.add(user.getUserName());
		}
		return operator;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public Serializable createQuestion(QuestionInfo questionInfo) {
		String catalogId = questionInfo.getCatalogId();
		QuestionCatalog questionCatalog = questionCatalogDao.getObjectById(catalogId);
		questionInfo.setCurrentOperator(questionCatalog.getDefaultOperator());
		questionInfo.setCreateTime(new Date());
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
		questionInfo.setClosedTime(new Date());
		questionInfoDao.mergeObject(questionInfo);
		return questionId;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public String closeQuestion(String questionId) {
		QuestionInfo questionInfo = questionInfoDao.getObjectById(questionId);
		questionInfo.setQuestionState("C");
		questionInfo.setClosedTime(new Date());
		questionInfoDao.mergeObject(questionInfo);
		return questionId;
	}

}

