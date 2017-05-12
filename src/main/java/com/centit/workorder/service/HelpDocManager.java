package com.centit.workorder.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

public interface HelpDocManager extends BaseEntityManager<HelpDoc,String>
{
	
	String createHelpDoc(HelpDoc helpDoc);

	void updateHelpDoc(String docId, HelpDoc helpDoc);

	void deleteHelpDoc(String docId);

	String comment(String docId, HelpDocComment helpDocComment);

	String score(String docId, HelpDocScore helpDocScore);

	void editContent(String docId, String content);

	JSONArray searchHelpdocByLevel(String osId);

	JSONArray searchHelpdocByType(Map<String,Object>queryParamsMap, PageDesc pageDesc);

	JSONArray searchComments(String docId);

	JSONObject searchScores(String docId);

}
