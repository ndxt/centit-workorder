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

		public List<QuestionRound> getQuestionRoundWithQuestionId(String questionId){
			String sql = " SELECT * from f_question_round f WHERE f.QUESTION_ID='" +  questionId + "' ";
			List<Object[]> list = (List<Object[]>) DatabaseOptUtils.findObjectsBySql(this, sql);
			List<QuestionRound> questionRoundList = new ArrayList<>();
			if (list != null){
				questionRoundList = objectToQuestionRound(list);
			}
			return questionRoundList;
		}


		public void deleteQuestionRoundWithQuestionId(String questionId){
			String sql = " DELETE   from f_question_round  WHERE QUESTION_ID='" +  questionId + "' ";
			DatabaseOptUtils.doExecuteSql(this,sql);
		}


		/**
		 * object转成QuestionRound对象
		 * @param list
		 * @return
		 */
		public List<QuestionRound> objectToQuestionRound(List<Object[]> list){
			List<QuestionRound> questionRoundList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				QuestionRound questionRound = new QuestionRound();
				Object[] obj = (Object[]) list.get(i);
				if (obj[0] != null && !"".equals(obj[0])) {
					questionRound.setRoundId(obj[0].toString());
				}
				if (obj[1] != null && !"".equals(obj[1])) {
					questionRound.setQuestionId(obj[1].toString());
				}
				if (obj[2] != null && !"".equals(obj[2])) {
					questionRound.setRoundContent(obj[2].toString());
				}
				if (obj[3] != null && !"".equals(obj[3])) {
					questionRound.setEditState(obj[3].toString());
				}
				if (obj[4] != null && !"".equals(obj[4])) {
					questionRound.setRoundState(obj[4].toString());
				}
				if (obj[5] != null && !"".equals(obj[5])) {
					questionRound.setOrA(obj[5].toString());
				}
				if (obj[6] != null && !"".equals(obj[6])) {
					questionRound.setCreateTime((Date)obj[6]);
				}
				if (obj[7] != null && !"".equals(obj[7])) {
					questionRound.setLastUpdateTime((Date)obj[7]);
				}
				if (obj[8] != null && !"".equals(obj[8])) {
					questionRound.setUserCode(obj[8].toString());
				}
				if (obj[9] != null && !"".equals(obj[9])) {
					questionRound.setUserName(obj[9].toString());
				}
				questionRoundList.add(questionRound);
			}
			return questionRoundList;
		}

		public QuestionInfo getQuestionInfoWithId(String questionId){
			QuestionInfo questionInfo = questionInfoDao.getQuestionInfoWithId(questionId);
			return questionInfo;
		}

}
