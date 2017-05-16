package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.SysDaoOptUtils;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
import com.centit.support.database.QueryAndNamedParams;
import com.centit.support.database.QueryUtils;
import com.centit.workorder.dao.QuestionCatalogDao;
import com.centit.workorder.po.QuestionCatalog;
import com.centit.workorder.service.QuestionCatalogManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * QuestionCatalog  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统问题类别null   
*/
@Service
public class QuestionCatalogManagerImpl 
		extends BaseEntityManagerImpl<QuestionCatalog,java.lang.String,QuestionCatalogDao>
	implements QuestionCatalogManager{

	public static final Log log = LogFactory.getLog(QuestionCatalogManager.class);

	
	private QuestionCatalogDao questionCatalogDao ;
	
	@Resource(name = "questionCatalogDao")
    @NotNull
	public void setQuestionCatalogDao(QuestionCatalogDao baseDao)
	{
		this.questionCatalogDao = baseDao;
		setBaseDao(this.questionCatalogDao);
	}
	
/*
 	@PostConstruct
    public void init() {
        
    }
 	
 */
	@Override
    @Transactional(propagation= Propagation.REQUIRED)
	public JSONArray listQuestionCatalogsAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc){
			
		return SysDaoOptUtils.listObjectsAsJson(baseDao, fields, QuestionCatalog.class,
    			filterMap, pageDesc);
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public List<QuestionCatalog> getAll(String osId, String catalogName, Date begin, Date end) {
		List<QuestionCatalog> list = questionCatalogDao.getAllQuestionCatalog(osId,catalogName,begin,end);
		return list;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public JSONArray getAllCatalog(Map<String, Object> queryParamsMap, PageDesc pageDesc) {
		JSONArray dataList = questionCatalogDao.getCatalog(baseDao,queryParamsMap,pageDesc);
		return dataList;
	}


}

