package com.centit.workorder.service;

import java.util.Map;
import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.core.service.BaseEntityManager;
import com.centit.workorder.po.QuestionRound;

/**
 * QuestionRound  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 问题的交流null   
*/

public interface QuestionRoundManager extends BaseEntityManager<QuestionRound,java.lang.String> 
{
	
	public JSONArray listQuestionRoundsAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc);
}
