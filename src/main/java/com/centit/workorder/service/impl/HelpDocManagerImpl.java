package com.centit.workorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.SysDaoOptUtils;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
import com.centit.workorder.dao.HelpDocCommentDao;
import com.centit.workorder.dao.HelpDocDao;
import com.centit.workorder.dao.HelpDocScoreDao;
import com.centit.workorder.dao.HelpDocVersionDao;
import com.centit.workorder.po.HelpDoc;
import com.centit.workorder.po.HelpDocComment;
import com.centit.workorder.po.HelpDocScore;
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

	@Resource
	private HelpDocScoreDao helpDocScoreDao;

	@Resource
	private HelpDocCommentDao helpDocCommentDao;

	@Resource
	private HelpDocVersionDao helpDocVersionDao;
	
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

	/**
	 * 创建帮助文档
	 * @param helpDoc
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createHelpDoc(HelpDoc helpDoc) {
		saveNewObject(helpDoc);
		helpDocVersionDao.saveNewObject(helpDoc.generateVersion());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void comment(String docId, HelpDocComment helpDocComment) {
		helpDocCommentDao.saveNewObject(helpDocComment);
		HelpDoc helpDoc = helpDocDao.getObjectById(docId);
		helpDoc.getHelpDocComments().add(helpDocComment);
		helpDocDao.mergeObject(helpDoc);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void score(String docId, HelpDocScore helpDocScore) {
		helpDocScoreDao.saveNewObject(helpDocScore);
		HelpDoc helpDoc = helpDocDao.getObjectById(docId);
		helpDoc.getHelpDocScores().add(helpDocScore);
		helpDocDao.mergeObject(helpDoc);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void editContent(String docId, String content) {
		HelpDoc helpDoc = helpDocDao.getObjectById( docId);
		helpDoc.setDocFile(content);
		helpDocDao.mergeObject(helpDoc);
		helpDocVersionDao.saveNewObject(helpDoc.generateVersion());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateHelpDoc(String docId, HelpDoc helpDoc) {
		HelpDoc dbHelpDoc = helpDocDao.getObjectById( docId);

		dbHelpDoc .copy(helpDoc);
		helpDocDao.mergeObject(dbHelpDoc);


	}

}

