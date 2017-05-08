package com.centit.workorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.common.JsonResultUtils;
import com.centit.framework.core.common.ResponseData;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.dao.PageDesc;
import com.centit.workorder.po.HelpDocScore;
import com.centit.workorder.service.HelpDocScoreManager;
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
 * HelpDocScore  Controller.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 帮组文档评分null   
*/


@Controller
@RequestMapping("/workorder/helpdocscore")
public class HelpDocScoreController  extends BaseController {
	private static final Log log = LogFactory.getLog(HelpDocScoreController.class);
	
	@Resource
	private HelpDocScoreManager helpDocScoreMag;	
	/*public void setHelpDocScoreMag(HelpDocScoreManager basemgr)
	{
		helpDocScoreMag = basemgr;
		//this.setBaseEntityManager(helpDocScoreMag);
	}*/

    /**
     * 查询所有   帮组文档评分  列表
     *
     * @param field    json中只保存需要的属性名
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return {data:[]}
     */
    @RequestMapping(method = RequestMethod.GET)
    public void list(String[] field, PageDesc pageDesc, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> searchColumn = convertSearchColumn(request);        
        
        JSONArray listObjects = helpDocScoreMag.listHelpDocScoresAsJson(field,searchColumn, pageDesc);

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
     * 查询单个  帮组文档评分 
	
	 * @param scoreId  SCORE_ID
     *
     * @param response    {@link HttpServletResponse}
     * @return {data:{}}
     */
    @RequestMapping(value = "/{scoreId}", method = {RequestMethod.GET})
    public void getHelpDocScore(@PathVariable String scoreId, HttpServletResponse response) {
    	
    	HelpDocScore helpDocScore =     			
    			helpDocScoreMag.getObjectById( scoreId);
        
        JsonResultUtils.writeSingleDataJson(helpDocScore, response);
    }
    
    /**
     * 新增 帮组文档评分
     *
     * @param helpDocScore  {@link HelpDocScore}
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST})
    public void createHelpDocScore(@RequestBody @Valid HelpDocScore helpDocScore, HttpServletResponse response) {
    	Serializable pk = helpDocScoreMag.saveNewObject(helpDocScore);
        JsonResultUtils.writeSingleDataJson(pk,response);
    }

    /**
     * 删除单个  帮组文档评分 
	
	 * @param scoreId  SCORE_ID
     */
    @RequestMapping(value = "/{scoreId}", method = {RequestMethod.DELETE})
    public void deleteHelpDocScore(@PathVariable String scoreId, HttpServletResponse response) {
    	
    	helpDocScoreMag.deleteObjectById( scoreId);
        
        JsonResultUtils.writeBlankJson(response);
    } 
    
    /**
     * 新增或保存 帮组文档评分 
    
	 * @param scoreId  SCORE_ID
	 * @param helpDocScore  {@link HelpDocScore}
     * @param response    {@link HttpServletResponse}
     */
    @RequestMapping(value = "/{scoreId}", method = {RequestMethod.PUT})
    public void updateHelpDocScore(@PathVariable String scoreId, 
    	@RequestBody @Valid HelpDocScore helpDocScore, HttpServletResponse response) {
    	
    	
    	HelpDocScore dbHelpDocScore  =     			
    			helpDocScoreMag.getObjectById( scoreId);
        
        

        if (null != helpDocScore) {
        	dbHelpDocScore .copy(helpDocScore);
        	helpDocScoreMag.mergeObject(dbHelpDocScore);
        } else {
            JsonResultUtils.writeErrorMessageJson("当前对象不存在", response);
            return;
        }

        JsonResultUtils.writeBlankJson(response);
    }
}
