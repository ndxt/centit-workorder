package com.centit.workorder.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.hibernate.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.workorder.po.HelpDocScore;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 * HelpDocScoreDao  Repository.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 帮组文档评分null   
*/

@Repository
public class HelpDocScoreDao extends BaseDaoImpl<HelpDocScore,java.lang.String>
	{

	public static final Log log = LogFactory.getLog(HelpDocScoreDao.class);
	
	@Override
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("scoreId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("docId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("docScore" , CodeBook.EQUAL_HQL_ID);

			filterField.put("userCode" , CodeBook.EQUAL_HQL_ID);

			filterField.put("userName" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createTime" , CodeBook.EQUAL_HQL_ID);

		}
		return filterField;
	} 
}
