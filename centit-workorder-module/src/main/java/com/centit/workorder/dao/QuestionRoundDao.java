package com.centit.workorder.dao;

import java.util.*;

import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.hibernate.dao.BaseDaoImpl;
import com.centit.framework.hibernate.dao.DatabaseOptUtils;
import com.centit.workorder.po.QuestionCatalog;
import com.centit.workorder.po.QuestionInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.workorder.po.QuestionRound;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * QuestionRoundDao  Repository.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 问题的交流null   
*/

@Repository
public class QuestionRoundDao extends BaseDaoImpl<QuestionRound,java.lang.String>
	{

	public static final Log log = LogFactory.getLog(QuestionRoundDao.class);

	@Resource(name = "questionInfoDao")
	private QuestionInfoDao questionInfoDao ;


	
	@Override
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("roundId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("questionId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("roundContent" , CodeBook.EQUAL_HQL_ID);

			filterField.put("editState" , CodeBook.EQUAL_HQL_ID);

			filterField.put("roundState" , CodeBook.EQUAL_HQL_ID);

			filterField.put("orA" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createTime" , CodeBook.EQUAL_HQL_ID);

			filterField.put("lastUpdateTime" , CodeBook.EQUAL_HQL_ID);

			filterField.put("userCode" , CodeBook.EQUAL_HQL_ID);

			filterField.put("userName" , CodeBook.EQUAL_HQL_ID);

		}
		return filterField;
	}

		public List<QuestionRound> listQuestionRoundWithQuestionId(String questionId){
			String hql = "From QuestionRound f WHERE f.questionId=? ";
			return this.listObjects(hql,questionId);
		}

}
