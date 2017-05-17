package com.centit.workorder.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.core.service.BaseEntityManager;
import com.centit.workorder.po.QuestionInfo;
import com.centit.workorder.po.QuestionRound;

/**
 * QuestionInfo  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统问题列表null   
*/

public interface QuestionInfoManager extends BaseEntityManager<QuestionInfo,java.lang.String> 
{
	
	public JSONArray listQuestionInfosAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc);

	public List<QuestionInfo> getQuestionInfoWithUserCode(String userCode);

	public List<QuestionRound> getQuestionRoundWithQuestionId(String questionId);

	public List<QuestionInfo> getQuestionInfoWithOperator(String operator);

	public Serializable saveQuestionRound(QuestionRound questionRound);

	public void deleteQuestion(String questionId);

	public QuestionInfo getQuestionInfoWithId(String questionId);

	JSONArray getQuestionInfo(Map<String,Object>queryParamsMap, PageDesc pageDesc);

	public QuestionRound replayQuestion(QuestionRound questionRound);
}
