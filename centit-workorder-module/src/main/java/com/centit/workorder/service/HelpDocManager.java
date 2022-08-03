package com.centit.workorder.service;

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
    HelpDoc saveHelpDoc(HelpDoc helpDoc);

    HelpDoc editHelpDoc(String docId, HelpDoc helpDoc);

    void deleteHelpDoc(String docId);

    HelpDoc editContent(String docId, String content, String userCode);

    List<HelpDoc> searchHelpDocByLevel(List<HelpDoc> list);
//    JSONArray treeSearch(String osId);

    List<HelpDoc> searchHelpdocByType(Map<String,Object>queryParamsMap, PageDesc pageDesc);


    List<Map<String, Object>> fullSearch(Map<String,Object>searchQuery,String catalogId, PageDesc pageDesc);

    /**
     * 帮助文档增加目录自定义排序功能，更新文档的 prevDicId
     * @param list
     */
    void updatePrevDoc(List<HelpDoc> list, String action);

    void catalog(String docId, String targetDocId, String action);
    void innerCatalog(String docId, String targetDocId);
}
