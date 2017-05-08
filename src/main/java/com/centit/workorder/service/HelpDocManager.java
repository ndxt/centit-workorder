package com.centit.workorder.service;

import java.util.Map;
import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.core.service.BaseEntityManager;
import com.centit.workorder.po.HelpDoc;

/**
 * HelpDoc  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统帮助文档null   
*/

public interface HelpDocManager extends BaseEntityManager<HelpDoc,java.lang.String> 
{
	
	public JSONArray listHelpDocsAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc);
}
