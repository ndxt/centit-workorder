package com.centit.workorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.common.JsonResultUtils;
import com.centit.framework.core.common.ResponseData;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.dao.PageDesc;
import com.centit.workorder.po.HelpDocVersion;
import com.centit.workorder.service.HelpDocVersionManager;
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
 * HelpDocVersion  Controller.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统帮助文档历史版本null   
*/


@Controller
@RequestMapping("/workorder/helpdocversion")
public class HelpDocVersionController  extends BaseController {
	private static final Log log = LogFactory.getLog(HelpDocVersionController.class);
	
	@Resource
	private HelpDocVersionManager helpDocVersionMag;	
	/*public void setHelpDocVersionMag(HelpDocVersionManager basemgr)
	{
		helpDocVersionMag = basemgr;
		//this.setBaseEntityManager(helpDocVersionMag);
	}*/

    /**
     * 查询所有   系统帮助文档历史版本  列表
     *
     * @param field    json中只保存需要的属性名
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return {data:[]}
     */
    @RequestMapping(method = RequestMethod.GET)
    public void list(String[] field, PageDesc pageDesc, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> searchColumn = convertSearchColumn(request);        
        
        JSONArray listObjects = helpDocVersionMag.listHelpDocVersionsAsJson(field,searchColumn, pageDesc);

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
     * 查询单个  系统帮助文档历史版本 
	
	 * @param docId  DOC_ID
	 * @param docVersion  DOC_VERSION
     *
     * @param response    {@link HttpServletResponse}
     * @return {data:{}}
     */
    @RequestMapping(value = "/{docId}/{docVersion}", method = {RequestMethod.GET})
    public void getHelpDocVersion(@PathVariable String docId,@PathVariable int docVersion, HttpServletResponse response) {
    	
    	HelpDocVersion helpDocVersion =     			
    			helpDocVersionMag.getObjectById(new com.centit.workorder.po.HelpDocVersionId(  docId, docVersion) );
    	
        JsonResultUtils.writeSingleDataJson(helpDocVersion, response);
    }
    
    /**
     * 新增 系统帮助文档历史版本
     *
     * @param helpDocVersion  {@link HelpDocVersion}
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST})
    public void createHelpDocVersion(@RequestBody @Valid HelpDocVersion helpDocVersion, HttpServletResponse response) {
    	Serializable pk = helpDocVersionMag.saveNewObject(helpDocVersion);
        JsonResultUtils.writeSingleDataJson(pk,response);
    }

    /**
     * 删除单个  系统帮助文档历史版本 
	
	 * @param docId  DOC_ID
	 * @param docVersion  DOC_VERSION
     */
    @RequestMapping(value = "/{docId}/{docVersion}", method = {RequestMethod.DELETE})
    public void deleteHelpDocVersion(@PathVariable String docId,@PathVariable int docVersion, HttpServletResponse response) {
    	
    	helpDocVersionMag.deleteObjectById(new com.centit.workorder.po.HelpDocVersionId(  docId, docVersion) );
    	
        JsonResultUtils.writeBlankJson(response);
    } 
    
    /**
     * 新增或保存 系统帮助文档历史版本 
    
	 * @param docId  DOC_ID
	 * @param docVersion  DOC_VERSION
	 * @param helpDocVersion  {@link HelpDocVersion}
     * @param response    {@link HttpServletResponse}
     */
    @RequestMapping(value = "/{docId}/{docVersion}", method = {RequestMethod.PUT})
    public void updateHelpDocVersion(@PathVariable String docId,@PathVariable int docVersion, 
    	@RequestBody @Valid HelpDocVersion helpDocVersion, HttpServletResponse response) {
    	
    	
    	HelpDocVersion dbHelpDocVersion =     			
    			helpDocVersionMag.getObjectById(new com.centit.workorder.po.HelpDocVersionId(  docId, docVersion) );
    	
        

        if (null != helpDocVersion) {
        	dbHelpDocVersion .copy(helpDocVersion);
        	helpDocVersionMag.mergeObject(dbHelpDocVersion);
        } else {
            JsonResultUtils.writeErrorMessageJson("当前对象不存在", response);
            return;
        }

        JsonResultUtils.writeBlankJson(response);
    }
}
