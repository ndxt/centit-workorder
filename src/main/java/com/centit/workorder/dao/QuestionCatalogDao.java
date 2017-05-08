package com.centit.workorder.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.hibernate.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.workorder.po.QuestionCatalog;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 * QuestionCatalogDao  Repository.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统问题类别null   
*/

@Repository
public class QuestionCatalogDao extends BaseDaoImpl<QuestionCatalog,java.lang.String>
	{

	public static final Log log = LogFactory.getLog(QuestionCatalogDao.class);
	
	@Override
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("catalogId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("osId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("catalogName" , CodeBook.EQUAL_HQL_ID);

			filterField.put("creator" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createTime" , CodeBook.EQUAL_HQL_ID);

		}
		return filterField;
	} 
}
