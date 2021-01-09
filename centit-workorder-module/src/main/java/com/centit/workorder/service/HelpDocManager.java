package com.centit.workorder.service;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.jdbc.service.BaseEntityManager;
import com.centit.support.database.utils.PageDesc;
import com.centit.workorder.po.HelpDoc;

import java.util.List;
import java.util.Map;

/**
 * HelpDoc  Service.
 * create by scaffold 2017-05-08
 * @author codefan@sina.com
 * 系统帮助文档null
*/

public interface HelpDocManager extends BaseEntityManager<HelpDoc,String> {

    HelpDoc createHelpDoc(HelpDoc helpDoc);

    HelpDoc editHelpDoc(String docId, HelpDoc helpDoc);

    void deleteHelpDoc(String docId);

    HelpDoc editContent(String docId, String content, String userCode);

    JSONArray searchHelpdocByLevel(String osId);
//    JSONArray treeSearch(String osId);

    List<HelpDoc> searchHelpdocByType(Map<String,Object>queryParamsMap, PageDesc pageDesc);

    List<Map<String, Object>> fullTextSearch(String catalogId, PageDesc pageDesc);

    List<Map<String, Object>> fullSearch(String catalogId, PageDesc pageDesc);

}
