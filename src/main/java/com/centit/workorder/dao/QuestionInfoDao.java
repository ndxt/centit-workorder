package com.centit.workorder.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.hibernate.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.workorder.po.QuestionInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 * QuestionInfoDao  Repository.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统问题列表null   
*/

@Repository
public class QuestionInfoDao extends BaseDaoImpl<QuestionInfo,java.lang.String>
	{

	public static final Log log = LogFactory.getLog(QuestionInfoDao.class);
	
	@Override
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("questionId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("catalogId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("osId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("userCode" , CodeBook.EQUAL_HQL_ID);

			filterField.put("userName" , CodeBook.EQUAL_HQL_ID);

			filterField.put("questionTitle" , CodeBook.EQUAL_HQL_ID);

			filterField.put("questionContent" , CodeBook.EQUAL_HQL_ID);

			filterField.put("questionState" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createTime" , CodeBook.EQUAL_HQL_ID);

			filterField.put("editState" , CodeBook.EQUAL_HQL_ID);

			filterField.put("lastUpdateTime" , CodeBook.EQUAL_HQL_ID);

		}
		return filterField;
	} 
}
