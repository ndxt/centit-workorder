package com.centit.workorder.service;

import java.util.Map;
import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.core.service.BaseEntityManager;
import com.centit.workorder.po.QuestionInfo;

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
}
