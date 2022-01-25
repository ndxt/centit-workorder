package com.centit.workorder.dao;

import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.jdbc.dao.BaseDaoImpl;
import com.centit.workorder.po.HelpDoc;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


/**
 * HelpDocDao  Repository.
 * create by scaffold 2017-05-08
 * @author codefan@sina.com
 * 系统帮助文档null
*/

@Repository
public class HelpDocDao extends BaseDaoImpl<HelpDoc, String> {
    @Override
    public Map<String, String> getFilterField() {
        Map<String, String> filterField = new HashMap<>();
        filterField.put("docId" , CodeBook.EQUAL_HQL_ID);
        filterField.put("catalogId" , CodeBook.EQUAL_HQL_ID);
        filterField.put("docTitle" , CodeBook.EQUAL_HQL_ID);
        filterField.put("docLevel" , CodeBook.EQUAL_HQL_ID);
        filterField.put("docPath" , CodeBook.EQUAL_HQL_ID);
        filterField.put("docFile" , CodeBook.EQUAL_HQL_ID);
        filterField.put("osId" , CodeBook.EQUAL_HQL_ID);
        filterField.put("optId" , CodeBook.EQUAL_HQL_ID);
        filterField.put("optMethod" , CodeBook.EQUAL_HQL_ID);
        filterField.put("updateUser" , CodeBook.EQUAL_HQL_ID);
        filterField.put("lastUpdateTime" , CodeBook.EQUAL_HQL_ID);
        filterField.put("(like)parentId", "(docId like :parentId or docPath like :parentId)");
        filterField.put("parentId","docPath=:parentId");
        filterField.put(CodeBook.ORDER_BY_HQL_ID , "size(helpDocComments)");
        filterField.put("prevId"," prev_docid is not null ");
        return filterField;
    }

}
