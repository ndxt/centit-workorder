package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.SysDaoOptUtils;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
import com.centit.workorder.dao.HelpDocCommentDao;
import com.centit.workorder.po.HelpDocComment;
import com.centit.workorder.service.HelpDocCommentManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * HelpDocComment  Service.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 帮助文档评价null   
*/
@Service
public class HelpDocCommentManagerImpl 
		extends BaseEntityManagerImpl<HelpDocComment,java.lang.String,HelpDocCommentDao>
	implements HelpDocCommentManager{

	public static final Log log = LogFactory.getLog(HelpDocCommentManager.class);

	
	private HelpDocCommentDao helpDocCommentDao ;
	
	@Resource(name = "helpDocCommentDao")
    @NotNull
	public void setHelpDocCommentDao(HelpDocCommentDao baseDao)
	{
		this.helpDocCommentDao = baseDao;
		setBaseDao(this.helpDocCommentDao);
	}
	
/*
 	@PostConstruct
    public void init() {
        
    }
 	
 */
	@Override
    @Transactional(propagation= Propagation.REQUIRED)
	public JSONArray listHelpDocCommentsAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc){
			
		return SysDaoOptUtils.listObjectsAsJson(baseDao, fields, HelpDocComment.class,
    			filterMap, pageDesc);
	}
	
}

