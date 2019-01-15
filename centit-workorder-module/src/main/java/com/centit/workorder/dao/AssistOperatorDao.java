package com.centit.workorder.dao;

import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.jdbc.dao.BaseDaoImpl;
import com.centit.framework.jdbc.dao.DatabaseOptUtils;
import com.centit.support.database.utils.PageDesc;
import com.centit.workorder.po.AssistOperator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhang_gd on 2017/7/27.
 */
@Repository
public class AssistOperatorDao extends BaseDaoImpl<AssistOperator,String> {

    public static final Log log = LogFactory.getLog(AssistOperatorDao.class);

    @Override
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();

            filterField.put("questionId" , "questionId =:questionId");

            filterField.put("operatorCode" , "operatorCode = :operatorCode");

            filterField.put("createDate" , CodeBook.EQUAL_HQL_ID);
        }
        return filterField;
    }

    public List<String> getList(BaseDaoImpl baseDao, String operatorCode, PageDesc pageDesc){
        String sql = "select f.QUESTION_ID from F_ASSIST_OPERATOR f where f.OPERATOR_CODE=?";
        List<String> list = (List<String>) DatabaseOptUtils.getScalarObjectQuery(baseDao, sql,new Object[]{operatorCode});
        return list;
    }


}
