package com.centit.workorder.service;

import java.util.Map;
import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.core.service.BaseEntityManager;
import com.centit.workorder.po.HelpDocComment;

/**
 * HelpDocComment  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 帮助文档评价null   
*/

public interface HelpDocCommentManager extends BaseEntityManager<HelpDocComment,java.lang.String> 
{
	
	public JSONArray listHelpDocCommentsAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc);
}
