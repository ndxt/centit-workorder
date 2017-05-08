package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.SysDaoOptUtils;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
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
	
}

