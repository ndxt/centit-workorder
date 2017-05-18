package com.centit.workorder.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.core.service.BaseEntityManager;
import com.centit.workorder.po.QuestionCatalog;

/**
 * QuestionCatalog  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统问题类别null   
*/

public interface QuestionCatalogManager extends BaseEntityManager<QuestionCatalog,java.lang.String> 
{
	
	public JSONArray listQuestionCatalogsAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc);

	public List<QuestionCatalog> getAll(String osId,String catalogName,Date begin,Date end);

    JSONArray getAllCatalog(Map<String,Object>queryParamsMap, PageDesc pageDesc);

}
