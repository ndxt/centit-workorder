package com.centit.workorder.dao;

import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.hibernate.dao.BaseDaoImpl;
import com.centit.workorder.po.AssistOperator;
import com.centit.workorder.po.AssistOperatorId;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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

            filterField.put("questionId" , CodeBook.EQUAL_HQL_ID);

            filterField.put("operatorCode" , CodeBook.EQUAL_HQL_ID);

            filterField.put("createDate" , CodeBook.EQUAL_HQL_ID);
        }
        return filterField;
    }
}
