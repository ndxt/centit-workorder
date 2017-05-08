package com.centit.workorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.common.JsonResultUtils;
import com.centit.framework.core.common.ResponseData;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.dao.PageDesc;
import com.centit.workorder.po.HelpDoc;
import com.centit.workorder.service.HelpDocManager;
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
 * HelpDoc  Controller.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统帮助文档null   
*/


@Controller
@RequestMapping("/workorder/helpdoc")
public class HelpDocController  extends BaseController {
	private static final Log log = LogFactory.getLog(HelpDocController.class);
	
	@Resource
	private HelpDocManager helpDocMag;	
	/*public void setHelpDocMag(HelpDocManager basemgr)
	{
		helpDocMag = basemgr;
		//this.setBaseEntityManager(helpDocMag);
	}*/

    /**
     * 查询所有   系统帮助文档  列表
     *
     * @param field    json中只保存需要的属性名
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return {data:[]}
     */
    @RequestMapping(method = RequestMethod.GET)
    public void list(String[] field, PageDesc pageDesc, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> searchColumn = convertSearchColumn(request);        
        
        JSONArray listObjects = helpDocMag.listHelpDocsAsJson(field,searchColumn, pageDesc);

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
     * 查询单个  系统帮助文档 
	
	 * @param docId  DOC_ID
     *
     * @param response    {@link HttpServletResponse}
     * @return {data:{}}
     */
    @RequestMapping(value = "/{docId}", method = {RequestMethod.GET})
    public void getHelpDoc(@PathVariable String docId, HttpServletResponse response) {
    	
    	HelpDoc helpDoc =     			
    			helpDocMag.getObjectById( docId);
        
        JsonResultUtils.writeSingleDataJson(helpDoc, response);
    }
    
    /**
     * 新增 系统帮助文档
     *
     * @param helpDoc  {@link HelpDoc}
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST})
    public void createHelpDoc(@RequestBody @Valid HelpDoc helpDoc, HttpServletResponse response) {
    	Serializable pk = helpDocMag.saveNewObject(helpDoc);
        JsonResultUtils.writeSingleDataJson(pk,response);
    }

    /**
     * 删除单个  系统帮助文档 
	
	 * @param docId  DOC_ID
     */
    @RequestMapping(value = "/{docId}", method = {RequestMethod.DELETE})
    public void deleteHelpDoc(@PathVariable String docId, HttpServletResponse response) {
    	
    	helpDocMag.deleteObjectById( docId);
        
        JsonResultUtils.writeBlankJson(response);
    } 
    
    /**
     * 新增或保存 系统帮助文档 
    
	 * @param docId  DOC_ID
	 * @param helpDoc  {@link HelpDoc}
     * @param response    {@link HttpServletResponse}
     */
    @RequestMapping(value = "/{docId}", method = {RequestMethod.PUT})
    public void updateHelpDoc(@PathVariable String docId, 
    	@RequestBody @Valid HelpDoc helpDoc, HttpServletResponse response) {
    	
    	
    	HelpDoc dbHelpDoc  =     			
    			helpDocMag.getObjectById( docId);
        
        

        if (null != helpDoc) {
        	dbHelpDoc .copy(helpDoc);
        	helpDocMag.mergeObject(dbHelpDoc);
        } else {
            JsonResultUtils.writeErrorMessageJson("当前对象不存在", response);
            return;
        }

        JsonResultUtils.writeBlankJson(response);
    }
}
