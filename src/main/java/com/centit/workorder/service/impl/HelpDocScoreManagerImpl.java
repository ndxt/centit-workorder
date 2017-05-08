package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.SysDaoOptUtils;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
import com.centit.workorder.dao.HelpDocScoreDao;
import com.centit.workorder.po.HelpDocScore;
import com.centit.workorder.service.HelpDocScoreManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * HelpDocScore  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 帮组文档评分null   
*/
@Service
public class HelpDocScoreManagerImpl 
		extends BaseEntityManagerImpl<HelpDocScore,java.lang.String,HelpDocScoreDao>
	implements HelpDocScoreManager{

	public static final Log log = LogFactory.getLog(HelpDocScoreManager.class);

	
	private HelpDocScoreDao helpDocScoreDao ;
	
	@Resource(name = "helpDocScoreDao")
    @NotNull
	public void setHelpDocScoreDao(HelpDocScoreDao baseDao)
	{
		this.helpDocScoreDao = baseDao;
		setBaseDao(this.helpDocScoreDao);
	}
	
/*
 	@PostConstruct
    public void init() {
        
    }
 	
 */
	@Override
    @Transactional(propagation= Propagation.REQUIRED)
	public JSONArray listHelpDocScoresAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc){
			
		return SysDaoOptUtils.listObjectsAsJson(baseDao, fields, HelpDocScore.class,
    			filterMap, pageDesc);
	}
	
}

