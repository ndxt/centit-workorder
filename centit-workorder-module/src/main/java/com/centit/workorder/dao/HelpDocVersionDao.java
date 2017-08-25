package com.centit.workorder.dao;

import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.hibernate.dao.BaseDaoImpl;
import com.centit.workorder.po.HelpDocVersion;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;



/**
 * HelpDocVersionDao  Repository.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统帮助文档历史版本null   
*/

@Repository
public class HelpDocVersionDao extends BaseDaoImpl<HelpDocVersion,com.centit.workorder.po.HelpDocVersionId> {

	public static final Log log = LogFactory.getLog(HelpDocVersionDao.class);
	
	@Override
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<>();

			filterField.put("docId" , "cid.docId = :docId");

			filterField.put("docVersion" , "cid.docVersion = :docVersion");

			filterField.put("docFile" , CodeBook.EQUAL_HQL_ID);

			filterField.put("docTitle" , CodeBook.EQUAL_HQL_ID);

			filterField.put("updateUser" , CodeBook.EQUAL_HQL_ID);

			filterField.put("lastUpdateTime" , CodeBook.EQUAL_HQL_ID);

		}
		return filterField;
	} 
}
