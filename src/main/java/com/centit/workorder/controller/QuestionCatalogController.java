package com.centit.workorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.common.JsonResultUtils;
import com.centit.framework.core.common.ResponseData;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.dao.PageDesc;
import com.centit.workorder.po.QuestionCatalog;
import com.centit.workorder.service.QuestionCatalogManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Map;
/**
 * QuestionCatalog  Controller.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统问题类别null   
*/


@Controller
@RequestMapping("/workorder/questioncatalog")
public class QuestionCatalogController  extends BaseController {
	private static final Log log = LogFactory.getLog(QuestionCatalogController.class);
	
	@Resource
	private QuestionCatalogManager questionCatalogMag;	
	/*public void setQuestionCatalogMag(QuestionCatalogManager basemgr)
	{
		questionCatalogMag = basemgr;
		//this.setBaseEntityManager(questionCatalogMag);
	}*/

    /**
     * 查询所有   系统问题类别  列表
     *
     * @param field    json中只保存需要的属性名
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return {data:[]}
     */
    @RequestMapping(method = RequestMethod.GET)
    public void list(String[] field, PageDesc pageDesc, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> searchColumn = convertSearchColumn(request);        
        
        JSONArray listObjects = questionCatalogMag.listQuestionCatalogsAsJson(field,searchColumn, pageDesc);

        if (null == pageDesc) {
            JsonResultUtils.writeSingleDataJson(listObjects, response);
            return;
        }
        
        ResponseData resData = new ResponseData();
        resData.addResponseData(OBJLIST, listObjects);
        resData.addResponseData(PAGE_DESC, pageDesc);

        JsonResultUtils.writeResponseDataAsJson(resData, response);
    }
    
    /**
     * 查询单个  系统问题类别 
	
	 * @param catalogId  CATALOG_ID
     *
     * @param response    {@link HttpServletResponse}
     * @return {data:{}}
     */
    @RequestMapping(value = "/{catalogId}", method = {RequestMethod.GET})
    public void getQuestionCatalog(@PathVariable String catalogId, HttpServletResponse response) {
    	
    	QuestionCatalog questionCatalog =     			
    			questionCatalogMag.getObjectById( catalogId);
        
        JsonResultUtils.writeSingleDataJson(questionCatalog, response);
    }
    
    /**
     * 新增 系统问题类别
     *
     * @param questionCatalog  {@link QuestionCatalog}
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST})
    public void createQuestionCatalog(@RequestBody @Valid QuestionCatalog questionCatalog, HttpServletResponse response) {
    	Serializable pk = questionCatalogMag.saveNewObject(questionCatalog);
        JsonResultUtils.writeSingleDataJson(pk,response);
    }

    /**
     * 删除单个  系统问题类别 
	
	 * @param catalogId  CATALOG_ID
     */
    @RequestMapping(value = "/{catalogId}", method = {RequestMethod.DELETE})
    public void deleteQuestionCatalog(@PathVariable String catalogId, HttpServletResponse response) {
    	
    	questionCatalogMag.deleteObjectById( catalogId);
        
        JsonResultUtils.writeBlankJson(response);
    } 
    
    /**
     * 新增或保存 系统问题类别 
    
	 * @param catalogId  CATALOG_ID
	 * @param questionCatalog  {@link QuestionCatalog}
     * @param response    {@link HttpServletResponse}
     */
    @RequestMapping(value = "/{catalogId}", method = {RequestMethod.PUT})
    public void updateQuestionCatalog(@PathVariable String catalogId, 
    	@RequestBody @Valid QuestionCatalog questionCatalog, HttpServletResponse response) {
    	
    	
    	QuestionCatalog dbQuestionCatalog  =     			
    			questionCatalogMag.getObjectById( catalogId);
        
        

        if (null != questionCatalog) {
        	dbQuestionCatalog .copy(questionCatalog);
        	questionCatalogMag.mergeObject(dbQuestionCatalog);
        } else {
            JsonResultUtils.writeErrorMessageJson("当前对象不存在", response);
            return;
        }

        JsonResultUtils.writeBlankJson(response);
    }
}
