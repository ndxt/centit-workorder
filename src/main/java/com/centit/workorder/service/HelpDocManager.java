package com.centit.workorder.service;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.core.service.BaseEntityManager;
import com.centit.workorder.po.HelpDoc;
import com.centit.workorder.po.HelpDocComment;
import com.centit.workorder.po.HelpDocScore;

import java.util.Map;

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

	void createHelpDoc(HelpDoc helpDoc);

	void comment(String docId, HelpDocComment helpDocComment);

	void score(String docId, HelpDocScore helpDocScore);

	void editContent(String docId, String content);

	void updateHelpDoc(String docId, HelpDoc helpDoc);
}
