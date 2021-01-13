package com.centit.workorder.dao;

import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.jdbc.dao.BaseDaoImpl;
import com.centit.framework.jdbc.dao.DatabaseOptUtils;
import com.centit.support.database.utils.DatabaseAccess;
import com.centit.workorder.po.AssistOperator;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhang_gd on 2017/7/27.
 */
@Repository
public class AssistOperatorDao extends BaseDaoImpl<AssistOperator,String> {
    @Override
    public Map<String, String> getFilterField() {
        Map<String, String> filterField = new HashMap<>();
        filterField.put("questionId" , "questionId =:questionId");
        filterField.put("operatorCode" , "operatorCode = :operatorCode");
        filterField.put("createDate" , CodeBook.EQUAL_HQL_ID);
        return filterField;
    }

    public List<String> listQuestionId(String operatorCode){
        String sql = "select f.QUESTION_ID from F_ASSIST_OPERATOR f where f.OPERATOR_CODE=?";
        List<Object[]> list =  DatabaseOptUtils.listObjectsBySql(this, sql,new Object[]{operatorCode});
        return DatabaseAccess.fetchSingleColumnAsString(list);
    }

}
