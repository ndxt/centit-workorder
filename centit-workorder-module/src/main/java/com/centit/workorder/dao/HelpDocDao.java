package com.centit.workorder.dao;

import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.hibernate.dao.BaseDaoImpl;
import com.centit.workorder.po.HelpDoc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;



/**
 * HelpDocDao  Repository.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统帮助文档null   
*/

@Repository
public class HelpDocDao extends BaseDaoImpl<HelpDoc,java.lang.String>
	{

	public static final Log log = LogFactory.getLog(HelpDocDao.class);
	
	@Override
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<>();

			filterField.put("docId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("catalogId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("docTitle" , CodeBook.EQUAL_HQL_ID);

			filterField.put("docLevel" , CodeBook.EQUAL_HQL_ID);

			filterField.put("docPath" , CodeBook.EQUAL_HQL_ID);

			filterField.put("docFile" , CodeBook.EQUAL_HQL_ID);

			filterField.put("osId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("optId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("optMethod" , CodeBook.EQUAL_HQL_ID);

			filterField.put("updateUser" , CodeBook.EQUAL_HQL_ID);

			filterField.put("lastUpdateTime" , CodeBook.EQUAL_HQL_ID);

			filterField.put(CodeBook.ORDER_BY_HQL_ID , "size(helpDocComments)");

		}
		return filterField;
	}

}
