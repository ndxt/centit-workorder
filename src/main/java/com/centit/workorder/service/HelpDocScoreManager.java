package com.centit.workorder.service;

import java.util.Map;
import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.core.service.BaseEntityManager;
import com.centit.workorder.po.HelpDocScore;

/**
 * HelpDocScore  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 帮组文档评分null   
*/

public interface HelpDocScoreManager extends BaseEntityManager<HelpDocScore,java.lang.String> 
{
	
	public JSONArray listHelpDocScoresAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc);
}
