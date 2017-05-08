package com.centit.workorder.service;

import java.util.Map;
import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.core.service.BaseEntityManager;
import com.centit.workorder.po.HelpDocVersion;

/**
 * HelpDocVersion  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统帮助文档历史版本null   
*/

public interface HelpDocVersionManager extends BaseEntityManager<HelpDocVersion,com.centit.workorder.po.HelpDocVersionId> 
{
	
	public JSONArray listHelpDocVersionsAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc);
}
