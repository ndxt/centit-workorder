package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.SysDaoOptUtils;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
import com.centit.workorder.dao.QuestionInfoDao;
import com.centit.workorder.dao.QuestionRoundDao;
import com.centit.workorder.po.QuestionInfo;
import com.centit.workorder.po.QuestionRound;
import com.centit.workorder.service.QuestionInfoManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
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

	@Resource(name = "questionRoundDao")
	private QuestionRoundDao questionRoundDao ;
	
	private QuestionInfoDao questionInfoDao ;
	
	@Resource(name = "questionInfoDao")
    @NotNull
	public void setQuestionInfoDao(QuestionInfoDao baseDao)
	{
		this.questionInfoDao = baseDao;
		setBaseDao(this.questionInfoDao);
	}
	
/*
 	@PostConstruct
    public void init() {
        
    }
 	
 */
	@Override
    @Transactional(propagation= Propagation.REQUIRED)
	public JSONArray listQuestionInfosAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc){
			
		return SysDaoOptUtils.listObjectsAsJson(baseDao, fields, QuestionInfo.class,
    			filterMap, pageDesc);
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public List<QuestionInfo> getQuestionInfoWithUserCode(String userCode) {
		List<QuestionInfo> list = questionInfoDao.getQuestionInfoWithUser(userCode);
		return list;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public List<QuestionRound> getQuestionRoundWithQuestionId(String questionId) {
		List<QuestionRound> list = questionRoundDao.getQuestionRoundWithQuestionId(questionId);
		return list;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public List<QuestionInfo> getQuestionInfoWithOperator(String operator) {
		List<QuestionInfo> list = questionInfoDao.getQuestionInfoWithCurrentOperator(operator);
		return list;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public Serializable saveQuestionRound(QuestionRound questionRound) {
		Serializable pk = questionRoundDao.saveNewObject(questionRound);
		return pk;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public void deleteQuestion(String questionId) {
		questionRoundDao.deleteQuestionRoundWithQuestionId(questionId);
		questionInfoDao.deleteQuestionInfoWithQuestionId(questionId);
//		questionInfoDao.deleteObjectById(questionId);
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public QuestionInfo getQuestionInfoWithId(String questionId) {
		QuestionInfo questionInfo = questionInfoDao.getQuestionInfoWithId(questionId);
		return questionInfo;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public JSONArray getQuestionInfo(Map<String, Object> queryParamsMap, PageDesc pageDesc) {
		JSONArray dataList = questionInfoDao.getQuestionInfo(baseDao,queryParamsMap,pageDesc);
		return dataList;
	}

}

