package com.centit.workorder.dao;

import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.jdbc.dao.BaseDaoImpl;
import com.centit.workorder.po.QuestionRound;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

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

    @Override
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<>();
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
        String hql = " WHERE QUESTION_ID=? order by CREATE_TIME";
        List<QuestionRound> list = this.listObjectsByFilter(hql,new Object[]{questionId});
        return  list;
    }

    public List<QuestionRound> listQuestionRoundShowUser(String questionId){
        String hql = "WHERE QUESTION_ID =? and SHOW_USER='T' order by CREATE_TIME";
        List<QuestionRound> list = this.listObjectsByFilter(hql,new Object[]{questionId});
        return  list;
    }

}
