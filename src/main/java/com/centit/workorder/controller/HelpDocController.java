package com.centit.workorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.common.JsonResultUtils;
import com.centit.framework.core.common.ResponseData;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.dao.PageDesc;
import com.centit.workorder.po.HelpDoc;
import com.centit.workorder.po.HelpDocComment;
import com.centit.workorder.po.HelpDocScore;
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
import java.util.Map;
import java.util.Set;

/**
 * HelpDoc  Controller.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统帮助文档null   
*/


@Controller
@RequestMapping("/helpDoc")
public class HelpDocController  extends BaseController {
	private static final Log log = LogFactory.getLog(HelpDocController.class);
	
	@Resource
	private HelpDocManager helpDocMag;

    /**
     * 查询所有   系统帮助文档  列表
     * @param field    json中只保存需要的属性名
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
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
     */
    @RequestMapping(value = "search/{docId}", method = {RequestMethod.GET})
    public void getHelpDoc(@PathVariable String docId, HttpServletResponse response) {
    	
    	HelpDoc helpDoc = helpDocMag.getObjectById( docId);
        
        JsonResultUtils.writeSingleDataJson(helpDoc, response);
    }
    
    /**
     * 创建 帮助文档条目
     * @param helpDoc  {@link HelpDoc}
     */
    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    public void createHelpDoc(@RequestBody @Valid HelpDoc helpDoc, HttpServletResponse response) {
    	helpDocMag.createHelpDoc(helpDoc);
        JsonResultUtils.writeSuccessJson(response);
    }

    /**
     * 编辑帮助文档
     * @param docId  DOC_ID
     * @param helpDoc  {@link HelpDoc}
     */
    @RequestMapping(value = "/update/{docId}", method = {RequestMethod.PUT})
    public void updateHelpDoc(@PathVariable String docId,
                              @RequestBody @Valid HelpDoc helpDoc, HttpServletResponse response) {

        helpDocMag.updateHelpDoc(docId, helpDoc);
        JsonResultUtils.writeSuccessJson(response);
    }

    /**
     * 修改文档内容
     */
    @RequestMapping(value = "/editContent/{docId}", method = {RequestMethod.PUT})
    public void editContent(@PathVariable String docId,
                            @RequestBody @Valid String content, HttpServletResponse response) {

        helpDocMag.editContent(docId, content);

        JsonResultUtils.writeSuccessJson(response);
    }

    /**
     * 删除 帮助文档
	 * @param docId  DOC_ID
     */
    @RequestMapping(value = "/delete/{docId}", method = {RequestMethod.DELETE})
    public void deleteHelpDoc(@PathVariable String docId, HttpServletResponse response) {

    	helpDocMag.deleteObjectById( docId);

        JsonResultUtils.writeSuccessJson(response);
    }

    /**
     * 帮助文档类别查询接口（按层级查）
     */
    @RequestMapping(value="searchTypeByLevel/{level}", method = RequestMethod.GET)
    public void searchTypeByLevel(@PathVariable String level, HttpServletResponse response) {
    }

    /**
     * 帮助文档类别查询接口（按层问题类别）
     */
    @RequestMapping(value="searchTypeByType/{type}", method = RequestMethod.GET)
    public void searchTypeByTye(@PathVariable String type, HttpServletResponse response) {
    }

    /**
     * 帮助文档评分接口
     */
    @RequestMapping(value = "/score/{docId}", method = RequestMethod.POST)
    public void score(@PathVariable String docId,
                      @RequestBody @Valid HelpDocScore helpDocScore, HttpServletResponse response) {

        helpDocMag.score(docId, helpDocScore);
        JsonResultUtils.writeSuccessJson(response);
    }

    /**
     * 帮助文档评价接口
     */
    @RequestMapping(value = "/comment/{docId}", method = RequestMethod.POST)
    public void comment(@PathVariable String docId,
                        @RequestBody @Valid HelpDocComment helpDocComment, HttpServletResponse response) {

        helpDocMag.comment(docId, helpDocComment);
        JsonResultUtils.writeSuccessJson(response);
    }

    /**
     * 根据文档ID查询所有评分信息
     */
    @RequestMapping(value = "/searchScores/{docId}", method = RequestMethod.GET)
    public void searchScores(@PathVariable String docId, HttpServletResponse response) {
        HelpDoc helpDoc = helpDocMag.getObjectById(docId);
        Set<HelpDocScore> scoreSet = helpDoc.getHelpDocScores();

    }

    /**
     * 根据文档ID查询所有评价信息
     */
    @RequestMapping(value = "/searchComments/{docId}", method = RequestMethod.GET)
    public void searchComments(@PathVariable String docId, HttpServletResponse response) {
        HelpDoc helpDoc = helpDocMag.getObjectById(docId);
        Set<HelpDocComment> commentSet = helpDoc.getHelpDocComments();

    }
}
