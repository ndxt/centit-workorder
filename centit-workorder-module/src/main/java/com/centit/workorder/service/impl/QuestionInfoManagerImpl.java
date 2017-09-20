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
import java.util.ArrayList;
import java.util.HashMap;
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
		return questionRoundDao.listQuestionRound(questionId);
	}

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public List<QuestionRound> getQuestionRoundShowUser(String questionId) {
        return questionRoundDao.listQuestionRoundShowUser(questionId);
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
		List<QuestionInfo> list = questionInfoDao.unabsorbedQuestion();
		return list;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public void saveQuestionRound(QuestionRound questionRound) {
		questionRound.setOrA("Q");
		questionRound.setShowUser("T");
		questionRound.setLastUpdateTime(DatetimeOpt.currentUtilDate());
		questionRound.setCreateTime(DatetimeOpt.currentUtilDate());
		questionRoundDao.saveNewObject(questionRound);
//		QuestionInfo dbQuestionInfo = questionInfoDao.getObjectById(questionRound.getQuestionId());
//		dbQuestionInfo.setLastUpdateTime(DatetimeOpt.currentUtilDate());
//		dbQuestionInfo.setQuestionState("H");
//		questionInfoDao.mergeObject(dbQuestionInfo);
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
		if(dataList == null){
			dataList = new JSONArray();
		}
		return dataList;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public JSONArray getQuestionInfoList(Map<String, Object> queryParamsMap, PageDesc pageDesc) {
		JSONArray dataList = questionInfoDao.questionInfo(baseDao,queryParamsMap,pageDesc);
		return dataList;
	}

	@Override
    @Transactional(propagation= Propagation.REQUIRED)
    public List<QuestionInfo> getQuestionInfoWithOperator(String osId,String operatorCode, PageDesc pageDesc) {
		Map<String,Object> queryParamsMap = new HashMap<String,Object>();
        List<String> list = assistOperatorDao.getList(assistOperatorDao,operatorCode,pageDesc);
        List<QuestionInfo> questionInfoList = new ArrayList<QuestionInfo>(list.size()*2);
        for (String questionId : list){
			queryParamsMap.put("osId",osId);
            queryParamsMap.put("questionId",questionId);
			QuestionInfo questionInfo = questionInfoDao.getObjectByProperties(queryParamsMap);
			if (questionInfo != null)
            	questionInfoList.add(questionInfo);
        }
        return questionInfoList;
    }

    @Override
	@Transactional(propagation= Propagation.REQUIRED)
	public QuestionRound replayQuestion(QuestionRound questionRound) {
		questionRound.setEditState("U");
		questionRound.setRoundState("C");
		questionRound.setOrA("A");
//		questionRound.setShowUser("T");
		questionRound.setCreateTime(DatetimeOpt.currentUtilDate());
		questionRound.setLastUpdateTime(DatetimeOpt.currentUtilDate());
		questionRoundDao.saveNewObject(questionRound);
		QuestionInfo questionInfo = questionInfoDao.getObjectById(questionRound.getQuestionId());
		questionInfo.setLastUpdateTime(DatetimeOpt.currentUtilDate());
		questionInfo.setCompleteTime(DatetimeOpt.currentUtilDate());
		questionInfo.setEditState("U");
		questionInfo.setQuestionState("R");
		questionInfoDao.mergeObject(questionInfo);
		return questionRound;
	}

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public QuestionRound discussQuestion(QuestionRound questionRound) {
        questionRound.setEditState("U");
        questionRound.setOrA("A");
//        String showUser = StringUtils.isBlank(questionRound.getShowUser())?"F":questionRound.getShowUser();
        questionRound.setShowUser("F");
        questionRound.setCreateTime(DatetimeOpt.currentUtilDate());
        questionRound.setLastUpdateTime(DatetimeOpt.currentUtilDate());
        questionRoundDao.saveNewObject(questionRound);
        return null;
    }

    @Override
	@Transactional(propagation= Propagation.REQUIRED)
	public void createQuestion(QuestionInfo questionInfo) {
		String catalogId = questionInfo.getCatalogId();
		QuestionCatalog questionCatalog = questionCatalogDao.getObjectById(catalogId);
		questionInfo.setCurrentOperator(questionCatalog.getDefaultOperator());
		questionInfo.setCreateTime(DatetimeOpt.currentUtilDate());
		questionInfo.setQuestionState("U");
		questionInfo.setEditState("N");
		questionInfo.setOsId(questionCatalog.getOsId());
		questionInfoDao.saveNewObject(questionInfo);
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public String evaluateAndCloseQuestion(String score, String questionId) {
		int evaluateScore = Integer.valueOf(score);
		QuestionInfo questionInfo = questionInfoDao.getObjectById(questionId);
		questionInfo.setEvaluateScore(evaluateScore);
		questionInfo.setEvaluateTime(DatetimeOpt.currentUtilDate());
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
    public void updateShowUserTag(String roundId, String showUser) {
        QuestionRound dbQuestionRound = questionRoundDao.getObjectById(roundId);
        dbQuestionRound.setShowUser(showUser);
        questionRoundDao.mergeObject(dbQuestionRound);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void updateDiscuss(QuestionRound questionRound) {
        QuestionRound dbQuestionRound = questionRoundDao.getObjectById(questionRound.getRoundId());
        dbQuestionRound.setLastUpdateTime(DatetimeOpt.currentUtilDate());
        dbQuestionRound.copyNotNullProperty(questionRound);
        questionRoundDao.mergeObject(dbQuestionRound);
    }

    @Override
	@Transactional(propagation= Propagation.REQUIRED)
	public List<AssistOperatorId> createAssistOperator(String questionId,AssistOperator[] assistOperators) {

        Map<String,Object> map = new HashMap<>();
        map.put("questionId",questionId);
        List<AssistOperator> list = assistOperatorDao.listObjects(map);
        assistOperatorDao.deleteObjectsAsTabulation(list);

        if (assistOperators != null && assistOperators.length>0){
            for (AssistOperator assistOperator:assistOperators){
                assistOperator.setCreateDate(DatetimeOpt.currentUtilDate());
            }
            return  assistOperatorDao.saveNewObjects(assistOperators);
        }
        return null;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public void deleteObject(AssistOperator[] assistOperators) {
	    for(AssistOperator assistOperator : assistOperators){
            assistOperatorDao.deleteObjectById(assistOperator.getAid());
        }
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public void addDefaultReplay(String questionId) {
		QuestionRound round = new QuestionRound();
//		round.setOrA("A");
		round.setQuestionId(questionId);
		round.setCreateTime(DatetimeOpt.currentUtilDate());
		round.setUserCode("system");
		round.setUserName("system");
		round.setShowUser("T");
		round.setRoundContent("你好，已经为您的问题分配工程师，请耐心等待");
		questionRoundDao.saveNewObject(round);
	}

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public String loginRole(String questionId, String userCode, String userName) {
        Map<String,Object> map = new HashMap<>();
        map.put("questionId",questionId);
        map.put("currentOperator",userName);
        QuestionInfo questionInfo = questionInfoDao.getObjectByProperties(map);
        if (questionInfo != null){
            return "O";
        }
        AssistOperator assistOperator = assistOperatorDao.getObjectById(new AssistOperatorId(questionId,userCode));
        if (assistOperator != null){
            return "A";
        }
        return null;
    }

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
    public List<AssistOperator> listAssistOperator(String questionId){
		Map<String,Object> map = new HashMap<>();
		map.put("questionId",questionId);
		return assistOperatorDao.listObjects(map);
	}

}

