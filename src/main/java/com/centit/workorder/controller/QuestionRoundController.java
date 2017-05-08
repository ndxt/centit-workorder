package com.centit.workorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.common.JsonResultUtils;
import com.centit.framework.core.common.ResponseData;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.dao.PageDesc;
import com.centit.workorder.po.QuestionRound;
import com.centit.workorder.service.QuestionRoundManager;
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
 * QuestionRound  Controller.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 问题的交流null   
*/


@Controller
@RequestMapping("/workorder/questionround")
public class QuestionRoundController  extends BaseController {
	private static final Log log = LogFactory.getLog(QuestionRoundController.class);
	
	@Resource
	private QuestionRoundManager questionRoundMag;	
	/*public void setQuestionRoundMag(QuestionRoundManager basemgr)
	{
		questionRoundMag = basemgr;
		//this.setBaseEntityManager(questionRoundMag);
	}*/

    /**
     * 查询所有   问题的交流  列表
     *
     * @param field    json中只保存需要的属性名
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return {data:[]}
     */
    @RequestMapping(method = RequestMethod.GET)
    public void list(String[] field, PageDesc pageDesc, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> searchColumn = convertSearchColumn(request);        
        
        JSONArray listObjects = questionRoundMag.listQuestionRoundsAsJson(field,searchColumn, pageDesc);

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
     * 查询单个  问题的交流 
	
	 * @param roundId  ROUND_ID
     *
     * @param response    {@link HttpServletResponse}
     * @return {data:{}}
     */
    @RequestMapping(value = "/{roundId}", method = {RequestMethod.GET})
    public void getQuestionRound(@PathVariable String roundId, HttpServletResponse response) {
    	
    	QuestionRound questionRound =     			
    			questionRoundMag.getObjectById( roundId);
        
        JsonResultUtils.writeSingleDataJson(questionRound, response);
    }
    
    /**
     * 新增 问题的交流
     *
     * @param questionRound  {@link QuestionRound}
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST})
    public void createQuestionRound(@RequestBody @Valid QuestionRound questionRound, HttpServletResponse response) {
    	Serializable pk = questionRoundMag.saveNewObject(questionRound);
        JsonResultUtils.writeSingleDataJson(pk,response);
    }

    /**
     * 删除单个  问题的交流 
	
	 * @param roundId  ROUND_ID
     */
    @RequestMapping(value = "/{roundId}", method = {RequestMethod.DELETE})
    public void deleteQuestionRound(@PathVariable String roundId, HttpServletResponse response) {
    	
    	questionRoundMag.deleteObjectById( roundId);
        
        JsonResultUtils.writeBlankJson(response);
    } 
    
    /**
     * 新增或保存 问题的交流 
    
	 * @param roundId  ROUND_ID
	 * @param questionRound  {@link QuestionRound}
     * @param response    {@link HttpServletResponse}
     */
    @RequestMapping(value = "/{roundId}", method = {RequestMethod.PUT})
    public void updateQuestionRound(@PathVariable String roundId, 
    	@RequestBody @Valid QuestionRound questionRound, HttpServletResponse response) {
    	
    	
    	QuestionRound dbQuestionRound  =     			
    			questionRoundMag.getObjectById( roundId);
        
        

        if (null != questionRound) {
        	dbQuestionRound .copy(questionRound);
        	questionRoundMag.mergeObject(dbQuestionRound);
        } else {
            JsonResultUtils.writeErrorMessageJson("当前对象不存在", response);
            return;
        }

        JsonResultUtils.writeBlankJson(response);
    }
}
