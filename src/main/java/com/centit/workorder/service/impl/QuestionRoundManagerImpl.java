package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.SysDaoOptUtils;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
import com.centit.workorder.dao.QuestionRoundDao;
import com.centit.workorder.po.QuestionRound;
import com.centit.workorder.service.QuestionRoundManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * QuestionRound  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 问题的交流null   
*/
@Service
public class QuestionRoundManagerImpl 
		extends BaseEntityManagerImpl<QuestionRound,java.lang.String,QuestionRoundDao>
	implements QuestionRoundManager{

	public static final Log log = LogFactory.getLog(QuestionRoundManager.class);

	
	private QuestionRoundDao questionRoundDao ;
	
	@Resource(name = "questionRoundDao")
    @NotNull
	public void setQuestionRoundDao(QuestionRoundDao baseDao)
	{
		this.questionRoundDao = baseDao;
		setBaseDao(this.questionRoundDao);
	}
	
/*
 	@PostConstruct
    public void init() {
        
    }
 	
 */
	@Override
    @Transactional(propagation= Propagation.REQUIRED)
	public JSONArray listQuestionRoundsAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc){
			
		return SysDaoOptUtils.listObjectsAsJson(baseDao, fields, QuestionRound.class,
    			filterMap, pageDesc);
	}
	
}

