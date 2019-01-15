package com.centit.workorder.service;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.jdbc.service.BaseEntityManager;
import com.centit.support.database.utils.PageDesc;
import com.centit.workorder.po.AssistOperator;
import com.centit.workorder.po.QuestionInfo;
import com.centit.workorder.po.QuestionRound;

import java.util.List;
import java.util.Map;

/**
 * QuestionInfo  Service.
 * create by scaffold 2017-05-08
 * @author codefan@sina.com
 * 系统问题列表null
*/

public interface QuestionInfoManager extends BaseEntityManager<QuestionInfo,String>
{

	List<QuestionRound> getQuestionRoundWithQuestionId(String questionId);

    List<QuestionRound> getQuestionRoundShowUser(String questionId);

	List<QuestionInfo> getQuestionInfoWithCatalogId(String catalogId);

	List<QuestionInfo> getUnabsorbedQuestion();

	void saveQuestionRound(QuestionRound questionRound);

	void deleteQuestion(String questionId);

	JSONArray getQuestionInfo(Map<String,Object>queryParamsMap, PageDesc pageDesc);

    JSONArray getQuestionInfoList(Map<String,Object>queryParamsMap, PageDesc pageDesc);

    List<QuestionInfo> getQuestionInfoWithOperator(String osId,String operatorCode, PageDesc pageDesc);

	QuestionRound replayQuestion(QuestionRound questionRound);

    QuestionRound discussQuestion(QuestionRound questionRound);

	void createQuestion(QuestionInfo questionInfo);

	String evaluateAndCloseQuestion(String score,String questionId);

	String closeQuestion(String questionId);

    void updateShowUserTag(String roundId,String showUser);

    void updateDiscuss(QuestionRound questionRound);

	List<AssistOperator> createAssistOperator(String questionId,AssistOperator[] assistOperators);

	void deleteObject(AssistOperator[] assistOperators);

	void addDefaultReplay(String questionId);

    String loginRole(String questionId,String userCode,String userName);

	List<AssistOperator> listAssistOperator(String questionId);
}
