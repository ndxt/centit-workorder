package com.centit.workorder.dao;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.BaseDaoImpl;
import com.centit.framework.hibernate.dao.DatabaseOptUtils;
import com.centit.support.database.QueryAndNamedParams;
import com.centit.support.database.QueryUtils;
import com.centit.workorder.po.QuestionInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
			filterField.put("currentOperator" , CodeBook.EQUAL_HQL_ID);
			filterField.put("acceptTime" , CodeBook.EQUAL_HQL_ID);
			filterField.put("completeTime" , CodeBook.EQUAL_HQL_ID);
			filterField.put("closedTime" , CodeBook.EQUAL_HQL_ID);
			filterField.put("evaluateScore" , CodeBook.EQUAL_HQL_ID);
			filterField.put("evaluateTime" , CodeBook.EQUAL_HQL_ID);
		}
		return filterField;
	}

		public JSONArray getQuestionInfo(BaseDaoImpl baseDao, Map<String, Object> queryParamsMap, PageDesc pageDesc) {
			String queryStatement =
					"select h.questionId, h.catalogId, h.osId, h.createTime,h.editState,h.questionTitle,h.questionContent,h.currentOperator,h.questionState,h.userName "
							+" from QuestionInfo h WHERE 1=1 "
							+ " [ :userCode | and h.userCode = :userCode ]"
							+ " [ :osId | and h.osId = :osId ]"
							+ " [ :currentOperator | and h.currentOperator = :currentOperator ]"
							+ " [ :questionState | and h.questionState = :questionState ]"
							+ " [ :editState | and h.editState = :editState ]"
							+ " [ :questionTitle | and h.questionTitle like :questionTitle]"
							+ " [ :questionContent | and h.questionContent like :questionContent]"
							+ " [ :begin | and h.createTime > :begin ]"
							+ " [ :end | and h.createTime < :end ]";
			QueryAndNamedParams qap = QueryUtils.translateQuery(queryStatement,queryParamsMap);
			JSONArray dataList = DatabaseOptUtils.findObjectsAsJSONByHql(baseDao,
						qap.getQuery(), qap.getParams(),pageDesc);
			return dataList;
		}

		public List<QuestionInfo> unabsorbedQuestion(){
			String hql = "FROM QuestionInfo f WHERE f.currentOperator  IS NULL OR f.currentOperator=''";
			List<QuestionInfo> list = this.listObjects(hql, (Object[])null);
			return  list;
		}
}
