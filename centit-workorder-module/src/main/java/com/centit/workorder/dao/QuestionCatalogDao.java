package com.centit.workorder.dao;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.jdbc.dao.BaseDaoImpl;
import com.centit.support.database.utils.PageDesc;
import com.centit.support.database.utils.QueryAndNamedParams;
import com.centit.support.database.utils.QueryUtils;
import com.centit.workorder.po.QuestionCatalog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * QuestionCatalogDao  Repository.
 * create by scaffold 2017-05-08
 * @author codefan@sina.com
 * 系统问题类别null
*/

@Repository
public class QuestionCatalogDao extends BaseDaoImpl<QuestionCatalog, String>
    {

    public static final Log log = LogFactory.getLog(QuestionCatalogDao.class);

    @Override
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<>();
            filterField.put("catalogId" , CodeBook.EQUAL_HQL_ID);
            filterField.put("osId" , CodeBook.EQUAL_HQL_ID);
            filterField.put("catalogName" , CodeBook.EQUAL_HQL_ID);
            filterField.put("creator" , CodeBook.EQUAL_HQL_ID);
            filterField.put("createTime" , CodeBook.EQUAL_HQL_ID);
            filterField.put("defaultOperator" , CodeBook.EQUAL_HQL_ID);
            filterField.put("timeLimit" , CodeBook.EQUAL_HQL_ID);
            filterField.put("catalogKeyWords" , CodeBook.EQUAL_HQL_ID);
        }
        return filterField;
    }

    public JSONArray listCatalogAsJson(Map<String, Object> queryParamsMap, PageDesc pageDesc) {
        String queryStatement =
            "WHERE 1=1  [ :osId | and h.OS_ID = :osId ]"
                + " [ :catalogName | and h.CATALOG_NAME = :catalogName ]"
                + " [ :begin | and h.CREATE_TIME > :begin ]"
                + " [ :end | and h.CREATE_TIME < :end ]";
        QueryAndNamedParams qap = QueryUtils.translateQuery(queryStatement, queryParamsMap);
        return this.listObjectsByFilterAsJson(qap.getQuery(), qap.getParams(), "h", pageDesc);
    }

    public List<QuestionCatalog> listCatalog(Map<String, Object> queryParamsMap) {
        String queryStatement = "WHERE 1=1 [:osId | and h.OS_ID = :osId ]"
                        + " [ :catalogName | and h.CATALOG_NAME = :catalogName ]"
                        + " [ :begin | and h.CREATE_TIME > :begin ]"
                        + " [ :end | and h.CREATE_TIME < :end ]"
                        +" order by h.SORT ";
        QueryAndNamedParams qap = QueryUtils.translateQuery(queryStatement,queryParamsMap);
        return this.listObjectsByFilter(qap.getQuery(), qap.getParams(), "h");
    }

}
