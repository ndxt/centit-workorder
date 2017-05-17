package com.centit.workorder.dao;

import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.BaseDaoImpl;
import com.centit.framework.hibernate.dao.DatabaseOptUtils;
import com.centit.framework.hibernate.dao.SysDaoOptUtils;
import com.centit.support.database.QueryAndNamedParams;
import com.centit.support.database.QueryUtils;
import com.centit.workorder.po.QuestionCatalog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.workorder.po.QuestionInfo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


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

	public List<QuestionInfo> getQuestionInfoWithUser(String userCode) {
		String sql = " SELECT * from f_question_info f WHERE f.User_Code='" + userCode + "' ";
		List<Object[]> list = (List<Object[]>) DatabaseOptUtils.findObjectsBySql(this, sql);
		List<QuestionInfo> questionList = new ArrayList<>();
		if (list != null){
			questionList = objectToQuestionInfo(list);
		}
		return questionList;
	}

		public List<QuestionInfo> getQuestionInfoWithCurrentOperator(String currentOperator) {
			String sql = " SELECT * from f_question_info f WHERE f.CURRENT_OPERATOR='" + currentOperator + "' ";
			List<Object[]> list = (List<Object[]>) DatabaseOptUtils.findObjectsBySql(this, sql);
			List<QuestionInfo> questionList = new ArrayList<>();
			if (list != null){
				questionList = objectToQuestionInfo(list);
			}
			return questionList;
		}

		public JSONArray getQuestionInfo(BaseDaoImpl baseDao, Map<String, Object> queryParamsMap, PageDesc pageDesc) {
			String queryStatement =
					"select h.questionId, h.catalogId, h.osId, h.createTime,h.editState,h.questionTitle,h.questionContent,h.currentOperator,h.questionState "
							+" from QuestionInfo h WHERE 1=1 "
							+ " [ :userCode | and h.userCode = :userCode ]"
							+ " [ :currentOperator | and h.currentOperator = :currentOperator ]"
							+ " [ :questionState | and h.questionState = :questionState ]"
							+ " [ :editState | and h.editState = :editState ]"
							+ " [ :questionTitle | and h.questionTitle like :%questionTitle% ]"
							+ " [ :begin | and h.createTime > :begin ]"
							+ " [ :end | and h.createTime < :end ]";
			QueryAndNamedParams qap = QueryUtils.translateQuery(queryStatement,queryParamsMap);
			JSONArray dataList = SysDaoOptUtils.listObjectsByHqlAsJson(baseDao,
					qap.getQuery(), qap.getParams(),
					null,
					pageDesc);
			return dataList;
		}


		public QuestionInfo getQuestionInfoWithId(String questionId){
			String sql = " SELECT * from f_question_info f WHERE f.QUESTION_ID='" + questionId + "' ";
			List<Object[]> list = (List<Object[]>) DatabaseOptUtils.findObjectsBySql(this, sql);
			List<QuestionInfo> questionList = new ArrayList<>();
			if (list != null){
				questionList = objectToQuestionInfo(list);
				return questionList.get(0);
			}
			return null;
		}

		public void deleteQuestionInfoWithQuestionId(String questionId){
			String sql = " DELETE   from f_question_info  WHERE QUESTION_ID='" +  questionId + "' ";
			DatabaseOptUtils.doExecuteSql(this,sql);
		}


		/**
		 * object转成QuestionInfo对象
		 * @param list
		 * @return
		 */
	public List<QuestionInfo> objectToQuestionInfo(List<Object[]> list){
		List<QuestionInfo> questionList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			QuestionInfo questionInfo = new QuestionInfo();
			Object[] obj = (Object[]) list.get(i);
			if (obj[0] != null && !"".equals(obj[0])) {
				questionInfo.setQuestionId(obj[0].toString());
			}
			if (obj[1] != null && !"".equals(obj[1])) {
				questionInfo.setCatalogId(obj[1].toString());
			}
			if (obj[2] != null && !"".equals(obj[2])) {
				questionInfo.setOsId(obj[2].toString());
			}
			if (obj[3] != null && !"".equals(obj[3])) {
				questionInfo.setUserCode(obj[3].toString());
			}
			if (obj[4] != null && !"".equals(obj[4])) {
				questionInfo.setUserName(obj[4].toString());
			}
			if (obj[5] != null && !"".equals(obj[5])) {
				questionInfo.setQuestionTitle(obj[5].toString());
			}
			if (obj[6] != null && !"".equals(obj[6])) {
				questionInfo.setQuestionContent(obj[6].toString());
			}
			if (obj[7] != null && !"".equals(obj[7])) {
				questionInfo.setQuestionState(obj[7].toString());
			}
			if (obj[8] != null && !"".equals(obj[8])) {
				questionInfo.setCreateTime((Date)obj[8]);
			}
			if (obj[9] != null && !"".equals(obj[9])) {
				questionInfo.setEditState(obj[9].toString());
			}
			if (obj[10] != null && !"".equals(obj[10])) {
				questionInfo.setLastUpdateTime((Date)obj[10]);
			}
			if (obj[11] != null && !"".equals(obj[11])) {
				questionInfo.setCurrentOperator(obj[11].toString());
			}
			if (obj[12] != null && !"".equals(obj[12])) {
				questionInfo.setAcceptTime((Date) obj[12]);
			}
			if (obj[13] != null && !"".equals(obj[13])) {
				questionInfo.setCompleteTime((Date) obj[13]);
			}
			if (obj[14] != null && !"".equals(obj[14])) {
				questionInfo.setClosedTime((Date) obj[14]);
			}
			if (obj[15] != null && !"".equals(obj[15])) {
				questionInfo.setEvaluateScore(Integer.parseInt(obj[15].toString()));
			}
			if (obj[16] != null && !"".equals(obj[16])) {
				questionInfo.setEvaluateTime((Date)obj[16]);
			}
			questionList.add(questionInfo);
		}
		return questionList;
	}



}
