package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.SysDaoOptUtils;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
import com.centit.workorder.dao.QuestionInfoDao;
import com.centit.workorder.po.QuestionInfo;
import com.centit.workorder.service.QuestionInfoManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * QuestionInfo  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统问题列表null   
*/
@Service
public class QuestionInfoManagerImpl 
		extends BaseEntityManagerImpl<QuestionInfo,java.lang.String,QuestionInfoDao>
	implements QuestionInfoManager{

	public static final Log log = LogFactory.getLog(QuestionInfoManager.class);

	
	private QuestionInfoDao questionInfoDao ;
	
	@Resource(name = "questionInfoDao")
    @NotNull
	public void setQuestionInfoDao(QuestionInfoDao baseDao)
	{
		this.questionInfoDao = baseDao;
		setBaseDao(this.questionInfoDao);
	}
	
/*
 	@PostConstruct
    public void init() {
        
    }
 	
 */
	@Override
    @Transactional(propagation= Propagation.REQUIRED)
	public JSONArray listQuestionInfosAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc){
			
		return SysDaoOptUtils.listObjectsAsJson(baseDao, fields, QuestionInfo.class,
    			filterMap, pageDesc);
	}
	
}

