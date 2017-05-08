package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.SysDaoOptUtils;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
import com.centit.workorder.dao.HelpDocVersionDao;
import com.centit.workorder.po.HelpDocVersion;
import com.centit.workorder.service.HelpDocVersionManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * HelpDocVersion  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统帮助文档历史版本null   
*/
@Service
public class HelpDocVersionManagerImpl 
		extends BaseEntityManagerImpl<HelpDocVersion,com.centit.workorder.po.HelpDocVersionId,HelpDocVersionDao>
	implements HelpDocVersionManager{

	public static final Log log = LogFactory.getLog(HelpDocVersionManager.class);

	
	private HelpDocVersionDao helpDocVersionDao ;
	
	@Resource(name = "helpDocVersionDao")
    @NotNull
	public void setHelpDocVersionDao(HelpDocVersionDao baseDao)
	{
		this.helpDocVersionDao = baseDao;
		setBaseDao(this.helpDocVersionDao);
	}
	
/*
 	@PostConstruct
    public void init() {
        
    }
 	
 */
	@Override
    @Transactional(propagation= Propagation.REQUIRED)
	public JSONArray listHelpDocVersionsAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc){
			
		return SysDaoOptUtils.listObjectsAsJson(baseDao, fields, HelpDocVersion.class,
    			filterMap, pageDesc);
	}
	
}

