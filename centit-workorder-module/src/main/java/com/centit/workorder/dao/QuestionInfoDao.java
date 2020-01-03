package com.centit.workorder.dao;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.jdbc.dao.BaseDaoImpl;
import com.centit.support.database.utils.PageDesc;
import com.centit.support.database.utils.QueryAndNamedParams;
import com.centit.support.database.utils.QueryUtils;
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
public class QuestionInfoDao extends BaseDaoImpl<QuestionInfo, String> {

    public static final Log log = LogFactory.getLog(QuestionInfoDao.class);

    @Override
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<>();
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

    public JSONArray getQuestionInfo(Map<String, Object> queryParamsMap, PageDesc pageDesc) {
        String queryStatement =" WHERE 1=1 [ :userCode | and h.USER_CODE = :userCode ]"
                        + " [ :osId | and h.OS_ID = :osId ]"
                        + " [ :currentOperator | and h.CURRENT_OPERATOR = :currentOperator ]"
                        + " [ :questionState | and h.QUESTION_STATE = :questionState ]"
                        + " [ :editState | and h.EDIT_STATE = :editState ]"
                        + " [ :questionTitle | and h.QUESTION_TITLE like :questionTitle]"
                        + " [ :questionContent | and h.QUESTION_CONTENT like :questionContent]"
                        + " [ :begin | and h.CREATE_TIME > :begin ]"
                        + " [ :end | and h.CREATE_TIME < :end ]"
                        + " order by h.CREATE_TIME desc";
        QueryAndNamedParams qap = QueryUtils.translateQuery(queryStatement,queryParamsMap);
        JSONArray dataList = this.listObjectsByFilterAsJson(
                    qap.getQuery(), qap.getParams(), "h", pageDesc);
        return dataList;
    }

    public List<QuestionInfo> unabsorbedQuestion(){
        String hql = " WHERE CURRENT_OPERATOR IS NULL OR CURRENT_OPERATOR=''";
        List<QuestionInfo> list = this.listObjectsByFilter(hql,new Object[]{null});
        return  list;
    }

    public JSONArray questionInfo(Map<String, Object> queryParamsMap, PageDesc pageDesc) {
        String queryStatement ="WHERE 1=1 [ :userCode | and h.USER_CODE = :userCode ]"
                        + " [ :osId | and h.OS_ID = :osId ]"
                        + " [ :questionState | and h.QUESTION_STATE = :questionState ]"
                        + " [ :editState | and h.EDIT_STATE = :editState ]"
                        + " [ :questionTitle | and h.QUESTION_TITLE like :questionTitle]"
                        + " [ :questionContent | and h.QUESTION_CONTENT like :questionContent]"
                        + " [ :begin | and h.CREATE_TIME > :begin ]"
                        + " [ :end | and h.CREATE_TIME < :end ]"
                        + " [ :operator | and h.CURRENT_OPERATOR = :operator ]"
                        + " [:operatorCode | or h.QUESTION_ID in " +
            "(select f.QUESTION_ID from F_ASSIST_OPERATOR f WHERE f.OPERATOR_CODE = :operatorCode)]";
        QueryAndNamedParams qap = QueryUtils.translateQuery(queryStatement,queryParamsMap);
        JSONArray dataList = this.listObjectsByFilterAsJson(
            qap.getQuery(), qap.getParams(), "h", pageDesc);
        return dataList;
    }
}
