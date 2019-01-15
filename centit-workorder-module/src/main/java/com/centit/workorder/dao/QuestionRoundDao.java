package com.centit.workorder.dao;

import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.jdbc.dao.BaseDaoImpl;
import com.centit.workorder.po.QuestionRound;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * QuestionRoundDao  Repository.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 问题的交流null   
*/

@Repository
public class QuestionRoundDao extends BaseDaoImpl<QuestionRound, String>
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

			filterField.put("showUser" , CodeBook.EQUAL_HQL_ID);

		}
		return filterField;
	}

	public List<QuestionRound> listQuestionRound(String questionId){
		String hql = "FROM QuestionRound f WHERE f.questionId=? order by f.createTime";
		List<QuestionRound> list = this.listObjectsByFilter(hql,new Object[]{questionId});
		return  list;
	}

	public List<QuestionRound> listQuestionRoundShowUser(String questionId){
		String hql = "FROM QuestionRound f WHERE f.questionId=? and f.showUser='T' order by f.createTime";
		List<QuestionRound> list = this.listObjectsByFilter(hql,new Object[]{questionId});
		return  list;
	}

}
