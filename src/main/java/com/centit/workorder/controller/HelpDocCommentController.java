package com.centit.workorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.common.JsonResultUtils;
import com.centit.framework.core.common.ResponseData;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.dao.PageDesc;
import com.centit.workorder.po.HelpDocComment;
import com.centit.workorder.service.HelpDocCommentManager;
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
 * HelpDocComment  Controller.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 帮助文档评价null   
*/


@Controller
@RequestMapping("/workorder/helpdoccomment")
public class HelpDocCommentController  extends BaseController {
	private static final Log log = LogFactory.getLog(HelpDocCommentController.class);
	
	@Resource
	private HelpDocCommentManager helpDocCommentMag;	
	/*public void setHelpDocCommentMag(HelpDocCommentManager basemgr)
	{
		helpDocCommentMag = basemgr;
		//this.setBaseEntityManager(helpDocCommentMag);
	}*/

    /**
     * 查询所有   帮助文档评价  列表
     *
     * @param field    json中只保存需要的属性名
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return {data:[]}
     */
    @RequestMapping(method = RequestMethod.GET)
    public void list(String[] field, PageDesc pageDesc, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> searchColumn = convertSearchColumn(request);        
        
        JSONArray listObjects = helpDocCommentMag.listHelpDocCommentsAsJson(field,searchColumn, pageDesc);

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
     * 查询单个  帮助文档评价 
	
	 * @param commentId  COMMENT_ID
     *
     * @param response    {@link HttpServletResponse}
     * @return {data:{}}
     */
    @RequestMapping(value = "/{commentId}", method = {RequestMethod.GET})
    public void getHelpDocComment(@PathVariable String commentId, HttpServletResponse response) {
    	
    	HelpDocComment helpDocComment =     			
    			helpDocCommentMag.getObjectById( commentId);
        
        JsonResultUtils.writeSingleDataJson(helpDocComment, response);
    }
    
    /**
     * 新增 帮助文档评价
     *
     * @param helpDocComment  {@link HelpDocComment}
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST})
    public void createHelpDocComment(@RequestBody @Valid HelpDocComment helpDocComment, HttpServletResponse response) {
    	Serializable pk = helpDocCommentMag.saveNewObject(helpDocComment);
        JsonResultUtils.writeSingleDataJson(pk,response);
    }

    /**
     * 删除单个  帮助文档评价 
	
	 * @param commentId  COMMENT_ID
     */
    @RequestMapping(value = "/{commentId}", method = {RequestMethod.DELETE})
    public void deleteHelpDocComment(@PathVariable String commentId, HttpServletResponse response) {
    	
    	helpDocCommentMag.deleteObjectById( commentId);
        
        JsonResultUtils.writeBlankJson(response);
    } 
    
    /**
     * 新增或保存 帮助文档评价 
    
	 * @param commentId  COMMENT_ID
	 * @param helpDocComment  {@link HelpDocComment}
     * @param response    {@link HttpServletResponse}
     */
    @RequestMapping(value = "/{commentId}", method = {RequestMethod.PUT})
    public void updateHelpDocComment(@PathVariable String commentId, 
    	@RequestBody @Valid HelpDocComment helpDocComment, HttpServletResponse response) {
    	
    	
    	HelpDocComment dbHelpDocComment  =     			
    			helpDocCommentMag.getObjectById( commentId);
        
        

        if (null != helpDocComment) {
        	dbHelpDocComment .copy(helpDocComment);
        	helpDocCommentMag.mergeObject(dbHelpDocComment);
        } else {
            JsonResultUtils.writeErrorMessageJson("当前对象不存在", response);
            return;
        }

        JsonResultUtils.writeBlankJson(response);
    }
}
