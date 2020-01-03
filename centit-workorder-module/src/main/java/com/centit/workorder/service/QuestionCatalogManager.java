package com.centit.workorder.service;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.jdbc.service.BaseEntityManager;
import com.centit.support.database.utils.PageDesc;
import com.centit.workorder.po.QuestionCatalog;

import java.util.Map;

/**
 * QuestionCatalog  Service.
 * create by scaffold 2017-05-08
 * @author codefan@sina.com
 * 系统问题类别null
*/

public interface QuestionCatalogManager extends BaseEntityManager<QuestionCatalog,String>
{

    JSONArray listCatalogAsJson(Map<String,Object>queryParamsMap, PageDesc pageDesc);

    JSONArray listAllCatalog(Map<String, Object> queryParamsMap);

    void deleteCatalog(String catalogId);

    String updateCatalog(String catalogId,QuestionCatalog questionCatalog);

}
