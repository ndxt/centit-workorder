package com.centit.workorder.dao;

import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.BaseDaoImpl;
import com.centit.framework.hibernate.dao.DatabaseOptUtils;
import com.centit.framework.hibernate.dao.SysDaoOptUtils;
import com.centit.support.database.QueryAndNamedParams;
import com.centit.support.database.QueryUtils;
import com.centit.workorder.po.QuestionInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.workorder.po.QuestionCatalog;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 * QuestionCatalogDao  Repository.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统问题类别null   
*/

@Repository
public class QuestionCatalogDao extends BaseDaoImpl<QuestionCatalog,java.lang.String>
	{

	public static final Log log = LogFactory.getLog(QuestionCatalogDao.class);
	
	@Override
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("catalogId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("osId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("catalogName" , CodeBook.EQUAL_HQL_ID);

			filterField.put("creator" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createTime" , CodeBook.EQUAL_HQL_ID);

		}
		return filterField;
	}

		public List<QuestionCatalog> getAllQuestionCatalog(String osId,String catalogName,Date begin,Date end){
			StringBuilder str = new StringBuilder();
			str.append(" from QuestionCatalog q  where  1=1 ").append(osId);
			if (osId != null && "".equals(osId)){
				str.append(" and q.osId= ").append(osId);
			}
			if (catalogName != null && "".equals(catalogName)){
				str.append(" and q.catalogName= ").append(catalogName);
			}
			if (begin != null && "".equals(begin)){
				str.append(" and q.begin> ").append(begin);
			}
			if (end != null && "".equals(end)){
				str.append(" and q.end< ").append(end);
			}
			String hql = str.toString();
			List<Object[]> list = (List<Object[]>) DatabaseOptUtils.findObjectsBySql(this,hql);
			List<QuestionCatalog> catalogList = objectToQuestionCatalog(list);
			return catalogList;
		}

        public JSONArray getCatalog(BaseDaoImpl baseDao,Map<String, Object> queryParamsMap, PageDesc pageDesc) {
            String queryStatement =
					"select h.catalogId, h.catalogName, h.createTime ,h.creator,h.timeLimit"
                            +" from QuestionCatalog h WHERE 1=1 "
                            + " [ :osId | and h.osId = :osId ]"
                            + " [ :catalogName | and h.catalogName = :catalogName ]"
                            + " [ :begin | and h.createTime > :begin ]"
                            + " [ :end | and h.createTime < :end ]";
            QueryAndNamedParams qap = QueryUtils.translateQuery(queryStatement,queryParamsMap);
            JSONArray dataList = SysDaoOptUtils.listObjectsByHqlAsJson(baseDao,
                    qap.getQuery(), qap.getParams(),
                    null,
                    pageDesc);
            return dataList;
        }



		public List<QuestionCatalog> objectToQuestionCatalog(List<Object[]> list){
			List<QuestionCatalog> questionList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				QuestionCatalog questionCatalog = new QuestionCatalog();
				Object[] obj = (Object[]) list.get(i);
				if (obj[0] != null && !"".equals(obj[0])) {
					questionCatalog.setCatalogId(obj[0].toString());
				}
				if (obj[1] != null && !"".equals(obj[1])) {
					questionCatalog.setOsId(obj[1].toString());
				}
				if (obj[2] != null && !"".equals(obj[2])) {
					questionCatalog.setCatalogName(obj[2].toString());
				}
				if (obj[3] != null && !"".equals(obj[3])) {
					questionCatalog.setCreator(obj[3].toString());
				}
				if (obj[4] != null && !"".equals(obj[4])) {
					questionCatalog.setCreateTime((Date) obj[4]);
				}
				if (obj[5] != null && !"".equals(obj[5])) {
					questionCatalog.setDefaultOperator(obj[5].toString());
				}
				if (obj[6] != null && !"".equals(obj[6])) {
					questionCatalog.setTimeLimit(Integer.valueOf(obj[6].toString()));
				}
				questionList.add(questionCatalog);
			}
			return questionList;
		}

}
