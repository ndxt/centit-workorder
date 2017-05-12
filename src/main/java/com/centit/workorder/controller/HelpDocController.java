package com.centit.workorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
     * 查询单个  系统帮助文档 
	 * @param docId  DOC_ID
     */
    @RequestMapping(value = "/search/{docId}", method = {RequestMethod.GET})
    public void getHelpDoc(@PathVariable String docId, HttpServletResponse response) {
    	
    	HelpDoc helpDoc = helpDocMag.getObjectById( docId);
        
        JsonResultUtils.writeSingleDataJson(helpDoc, response);
    }
    
    /**
     * 创建 帮助文档条目
     * @param helpDoc  {@link HelpDoc}
     */
    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    public void createHelpDoc(@Valid HelpDoc helpDoc, HttpServletResponse response) {
        //返回 主键 docId
        String docId = helpDocMag.createHelpDoc(helpDoc);
        JsonResultUtils.writeSingleDataJson(docId, response);
    }

    /**
     * 编辑帮助文档
     * @param docId  DOC_ID
     * @param helpDoc  {@link HelpDoc}
     */
    @RequestMapping(value = "/update/{docId}", method = {RequestMethod.POST})
    public void updateHelpDoc(@PathVariable String docId,
                              HelpDoc helpDoc, HttpServletResponse response) {
        //不保存 历史版本

        helpDocMag.updateHelpDoc(docId, helpDoc);
        JsonResultUtils.writeSuccessJson(response);
    }

    /**
     * 修改文档内容
     */
    @RequestMapping(value = "/editContent/{docId}", method = {RequestMethod.PUT})
    public void editContent(@PathVariable String docId,
                            @Valid String content, HttpServletResponse response) {

        // 保存 历史版本，
        helpDocMag.editContent(docId, content);
        JsonResultUtils.writeSuccessJson(response);
    }

    /**
     * 删除 帮助文档
	 * @param docId  DOC_ID
     */
    @RequestMapping(value = "/delete/{docId}", method = {RequestMethod.DELETE})
    public void deleteHelpDoc(@PathVariable String docId, HttpServletResponse response) {
        //全部删除 不可恢复 包括历史版本

        helpDocMag.deleteHelpDoc(docId);
        JsonResultUtils.writeSuccessJson(response);
    }

    /**
     * 帮助文档查询接口（按层级查）
     */
    @RequestMapping(value="/levelSearch/{osId}", method = RequestMethod.GET)
    public void levelSearch(@PathVariable String osId,HttpServletResponse response) {
        JSONArray listObjects = helpDocMag.searchHelpdocByLevel(osId);
        ResponseData resData = new ResponseData();
        resData.addResponseData(OBJLIST, listObjects);
        JsonResultUtils.writeResponseDataAsJson(resData, response);
    }

    /**
     * 帮助文档查询接口（按问题类别）
     */
    @RequestMapping(value="/typeSearch", method = RequestMethod.GET)
    public void typeSearch(@RequestParam(value="catalogId", required = false, defaultValue = "0") String catalogId,
                            @RequestParam(value="osId") String osId,
                            PageDesc pageDesc, HttpServletResponse response) {
        //分页， 排序 按照 评分次数高低  或者 评价次数
        Map<String, Object> map = new HashMap<>();
        map.put("catalogId", catalogId);
        map.put("osId", osId);
        JSONArray listObjects = helpDocMag.searchHelpdocByType(map, pageDesc);
        ResponseData resData = new ResponseData();
        resData.addResponseData(OBJLIST, listObjects);
        resData.addResponseData(PAGE_DESC, pageDesc);

        JsonResultUtils.writeResponseDataAsJson(resData, response);
    }

    /**
     * 帮助文档评分接口
     */
    @RequestMapping(value = "/score/{docId}", method = RequestMethod.POST)
    public void score(@PathVariable String docId,
                      @Valid HelpDocScore helpDocScore, HttpServletResponse response) {
        //返回主键

        String scoreId = helpDocMag.score(docId, helpDocScore);
        JsonResultUtils.writeSingleDataJson(scoreId, response);
    }

    /**
     * 帮助文档评价接口
     */
    @RequestMapping(value = "/comment/{docId}", method = RequestMethod.POST)
    public void comment(@PathVariable String docId,
                        @Valid HelpDocComment helpDocComment, HttpServletResponse response) {
        //返回 主键

        String commentId = helpDocMag.comment(docId, helpDocComment);
        JsonResultUtils.writeSingleDataJson(commentId, response);
    }

    /**
     * 根据文档ID查询所有评分信息
     */
    @RequestMapping(value = "/searchScores/{docId}", method = RequestMethod.GET)
    public void searchScores(@PathVariable String docId, HttpServletResponse response) {
        //只返回一个 平均分 和一个 评分次数
        JSONObject obj = helpDocMag.searchScores(docId);

        JsonResultUtils.writeSingleDataJson(obj, response);
    }

    /**
     * 根据文档ID查询所有评价信息
     */
    @RequestMapping(value = "/searchComments/{docId}", method = RequestMethod.GET)
    public void searchComments(@PathVariable String docId, HttpServletResponse response) {
        //返回一个评价列表
       JSONArray listObjects = helpDocMag.searchComments(docId);

        ResponseData resData = new ResponseData();
        resData.addResponseData(OBJLIST, listObjects);
        JsonResultUtils.writeResponseDataAsJson(resData, response);
    }
}
