package com.centit.workorder.dao;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.jdbc.dao.BaseDaoImpl;
import com.centit.framework.jdbc.dao.DatabaseOptUtils;
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


        public JSONArray getCatalog(BaseDaoImpl baseDao,Map<String, Object> queryParamsMap, PageDesc pageDesc) {
            String queryStatement =
                    "select h.catalogId, h.catalogName, h.createTime ,h.creator,h.timeLimit,h.defaultOperator,h.catalogKeyWords"
                            +" from QuestionCatalog h WHERE 1=1 "
                            + " [ :osId | and h.osId = :osId ]"
                            + " [ :catalogName | and h.catalogName = :catalogName ]"
                            + " [ :begin | and h.createTime > :begin ]"
                            + " [ :end | and h.createTime < :end ]";
            QueryAndNamedParams qap = QueryUtils.translateQuery(queryStatement,queryParamsMap);
            JSONArray dataList = DatabaseOptUtils.listObjectsByNamedSqlAsJson(baseDao,
                        qap.getQuery(), qap.getParams());
            return dataList;
        }

        public List<QuestionCatalog> list(BaseDaoImpl baseDao,Map<String, Object> queryParamsMap, PageDesc pageDesc) {
            String queryStatement = " from QuestionCatalog h WHERE 1=1 "
                            + " [ :osId | and h.osId = :osId ]"
                            + " [ :catalogName | and h.catalogName = :catalogName ]"
                            + " [ :begin | and h.createTime > :begin ]"
                            + " [ :end | and h.createTime < :end ]"
                            +" order by h.sort ";
            QueryAndNamedParams qap = QueryUtils.translateQuery(queryStatement,queryParamsMap);
            List<QuestionCatalog> dataList = (List<QuestionCatalog>) DatabaseOptUtils.getScalarObjectQuery(baseDao,
                    qap.getQuery(), qap.getParams());
            return dataList;
        }

}
