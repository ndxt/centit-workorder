package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.SysDaoOptUtils;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
import com.centit.workorder.dao.HelpDocDao;
import com.centit.workorder.po.HelpDoc;
import com.centit.workorder.service.HelpDocManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * HelpDoc  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统帮助文档null   
*/
@Service
public class HelpDocManagerImpl 
		extends BaseEntityManagerImpl<HelpDoc,java.lang.String,HelpDocDao>
	implements HelpDocManager{

	public static final Log log = LogFactory.getLog(HelpDocManager.class);

	
	private HelpDocDao helpDocDao ;
	
	@Resource(name = "helpDocDao")
    @NotNull
	public void setHelpDocDao(HelpDocDao baseDao)
	{
		this.helpDocDao = baseDao;
		setBaseDao(this.helpDocDao);
	}
	
/*
 	@PostConstruct
    public void init() {
        
    }
 	
 */
	@Override
    @Transactional(propagation= Propagation.REQUIRED)
	public JSONArray listHelpDocsAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc){
			
		return SysDaoOptUtils.listObjectsAsJson(baseDao, fields, HelpDoc.class,
    			filterMap, pageDesc);
	}
	
}

