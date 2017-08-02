package com.centit.workorder.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.core.service.BaseEntityManager;
import com.centit.workorder.po.HelpDoc;
import com.centit.workorder.po.HelpDocComment;
import com.centit.workorder.po.HelpDocScore;

import java.util.List;
import java.util.Map;

/**
 * HelpDoc  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统帮助文档null   
*/

public interface HelpDocManager extends BaseEntityManager<HelpDoc,String> {
	
	void createHelpDoc(HelpDoc helpDoc, String parentDocId);

	void editHelpDoc(String docId, HelpDoc helpDoc);

	void deleteHelpDoc(String docId);

	void comment(String docId, HelpDocComment helpDocComment);

	void score(String docId, HelpDocScore helpDocScore);

	void editContent(String docId, String content, String userCode);

	JSONArray searchHelpdocByLevel(String osId);
//	JSONArray treeSearch(String osId);

    List<HelpDoc> searchHelpdocByType(Map<String,Object>queryParamsMap, PageDesc pageDesc);

	JSONArray searchComments(String docId);

	JSONObject searchScores(String docId);

	List<Map<String, Object>> fullTextSearch(String keyWords, PageDesc pageDesc);

}
