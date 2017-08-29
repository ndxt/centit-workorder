package com.centit.workorder.dao;

import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.BaseDaoImpl;
import com.centit.framework.hibernate.dao.DatabaseOptUtils;
import com.centit.workorder.po.AssistOperator;
import com.centit.workorder.po.AssistOperatorId;
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
public class AssistOperatorDao extends BaseDaoImpl<AssistOperator,AssistOperatorId> {

    public static final Log log = LogFactory.getLog(AssistOperatorDao.class);

    @Override
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();

            filterField.put("questionId" , "aid.questionId =:questionId");

            filterField.put("operatorCode" , "aid.operatorCode = :operatorCode");

            filterField.put("createDate" , CodeBook.EQUAL_HQL_ID);
        }
        return filterField;
    }

    public List<String> getList(BaseDaoImpl baseDao,String operatorCode, PageDesc pageDesc){
        String sql = "select f.QUESTION_ID from F_ASSIST_OPERATOR f where f.OPERATOR_CODE=?";
        List<String> list = (List<String>) DatabaseOptUtils.findObjectsBySql(baseDao, sql,new Object[]{operatorCode}, pageDesc);
        return list;
    }


}
