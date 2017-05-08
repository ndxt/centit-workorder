package com.centit.workorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.common.JsonResultUtils;
import com.centit.framework.core.common.ResponseData;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.dao.PageDesc;
import com.centit.workorder.po.QuestionInfo;
import com.centit.workorder.service.QuestionInfoManager;
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
 * QuestionInfo  Controller.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统问题列表null   
*/


@Controller
@RequestMapping("/workorder/questioninfo")
public class QuestionInfoController  extends BaseController {
	private static final Log log = LogFactory.getLog(QuestionInfoController.class);
	
	@Resource
	private QuestionInfoManager questionInfoMag;	
	/*public void setQuestionInfoMag(QuestionInfoManager basemgr)
	{
		questionInfoMag = basemgr;
		//this.setBaseEntityManager(questionInfoMag);
	}*/

    /**
     * 查询所有   系统问题列表  列表
     *
     * @param field    json中只保存需要的属性名
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return {data:[]}
     */
    @RequestMapping(method = RequestMethod.GET)
    public void list(String[] field, PageDesc pageDesc, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> searchColumn = convertSearchColumn(request);        
        
        JSONArray listObjects = questionInfoMag.listQuestionInfosAsJson(field,searchColumn, pageDesc);

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
     * 查询单个  系统问题列表 
	
	 * @param questionId  QUESTION_ID
     *
     * @param response    {@link HttpServletResponse}
     * @return {data:{}}
     */
    @RequestMapping(value = "/{questionId}", method = {RequestMethod.GET})
    public void getQuestionInfo(@PathVariable String questionId, HttpServletResponse response) {
    	
    	QuestionInfo questionInfo =     			
    			questionInfoMag.getObjectById( questionId);
        
        JsonResultUtils.writeSingleDataJson(questionInfo, response);
    }
    
    /**
     * 新增 系统问题列表
     *
     * @param questionInfo  {@link QuestionInfo}
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST})
    public void createQuestionInfo(@RequestBody @Valid QuestionInfo questionInfo, HttpServletResponse response) {
    	Serializable pk = questionInfoMag.saveNewObject(questionInfo);
        JsonResultUtils.writeSingleDataJson(pk,response);
    }

    /**
     * 删除单个  系统问题列表 
	
	 * @param questionId  QUESTION_ID
     */
    @RequestMapping(value = "/{questionId}", method = {RequestMethod.DELETE})
    public void deleteQuestionInfo(@PathVariable String questionId, HttpServletResponse response) {
    	
    	questionInfoMag.deleteObjectById( questionId);
        
        JsonResultUtils.writeBlankJson(response);
    } 
    
    /**
     * 新增或保存 系统问题列表 
    
	 * @param questionId  QUESTION_ID
	 * @param questionInfo  {@link QuestionInfo}
     * @param response    {@link HttpServletResponse}
     */
    @RequestMapping(value = "/{questionId}", method = {RequestMethod.PUT})
    public void updateQuestionInfo(@PathVariable String questionId, 
    	@RequestBody @Valid QuestionInfo questionInfo, HttpServletResponse response) {
    	
    	
    	QuestionInfo dbQuestionInfo  =     			
    			questionInfoMag.getObjectById( questionId);
        
        

        if (null != questionInfo) {
        	dbQuestionInfo .copy(questionInfo);
        	questionInfoMag.mergeObject(dbQuestionInfo);
        } else {
            JsonResultUtils.writeErrorMessageJson("当前对象不存在", response);
            return;
        }

        JsonResultUtils.writeBlankJson(response);
    }
}
