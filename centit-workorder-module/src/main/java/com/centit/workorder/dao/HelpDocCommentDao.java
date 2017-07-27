package com.centit.workorder.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.hibernate.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.workorder.po.HelpDocComment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 * HelpDocCommentDao  Repository.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 帮助文档评价null   
*/

@Repository
public class HelpDocCommentDao extends BaseDaoImpl<HelpDocComment,java.lang.String>
	{

	public static final Log log = LogFactory.getLog(HelpDocCommentDao.class);
	
	@Override
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("commentId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("docId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("docComment" , CodeBook.EQUAL_HQL_ID);

			filterField.put("userCode" , CodeBook.EQUAL_HQL_ID);

			filterField.put("userName" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createTime" , CodeBook.EQUAL_HQL_ID);

		}
		return filterField;
	} 
}
