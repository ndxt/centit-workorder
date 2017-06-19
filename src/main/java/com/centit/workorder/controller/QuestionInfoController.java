package com.centit.workorder.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.centit.framework.common.WebOptUtils;
import com.centit.framework.core.common.JsonResultUtils;
import com.centit.framework.core.common.ResponseData;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.security.model.CentitUserDetails;
import com.centit.support.algorithm.DatetimeOpt;
import com.centit.workorder.comRet.QuestionRoundRet;
import com.centit.workorder.po.QuestionCatalog;
import com.centit.workorder.po.QuestionInfo;
import com.centit.workorder.po.QuestionRound;
import com.centit.workorder.service.QuestionCatalogManager;
import com.centit.workorder.service.QuestionInfoManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * QuestionInfo  Controller.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统问题列表null   
*/


@Controller
@RequestMapping("/questioninfo")
public class QuestionInfoController  extends BaseController {
	private static final Log log = LogFactory.getLog(QuestionInfoController.class);
	
	@Resource
	private QuestionInfoManager questionInfoMag;

    @Resource
    private QuestionCatalogManager questionCatalogMag;

    private QuestionInfo
    fetchQuestionInfo(HttpServletRequest request) throws IOException {
        String contentType = request.getContentType();
        if(StringUtils.indexOf(contentType,"form")>0){
            Map<String, String[]>  params = request.getParameterMap();
            Map<String, String> objMap = new HashMap<>();
            for(Map.Entry<String, String[]> ent : params.entrySet()){
                if(ent.getValue()!=null && ent.getValue().length>0)
                    objMap.put(ent.getKey(),ent.getValue()[0]);
            }
            QuestionInfo questionInfo = JSON.parseObject( JSON.toJSONString(objMap),QuestionInfo.class);
            return questionInfo;
        }else if(StringUtils.indexOf(contentType,"json")>0){
            QuestionInfo questionInfo = JSON.parseObject(request.getInputStream(),QuestionCatalog.class);
            return questionInfo;
        }
        return null;
    }


    /**
     * 查询所有   系统问题列表  列表  客户展示端
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return {data:[]}
     */
    @RequestMapping(value = "/getall/{osId}" ,method = RequestMethod.GET)
    public void list(@PathVariable String osId, PageDesc pageDesc, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String userName = request.getParameter("userName");
        String userCode = request.getParameter("userCode");
        String questionTitle = request.getParameter("questionTitle");
        String questionContent = request.getParameter("questionContent");
        String questionState = request.getParameter("questionState");
        String editState = request.getParameter("editState");
        String currentOperator = request.getParameter("currentOperator");
        Date begin = DatetimeOpt.convertStringToDate(request.getParameter("begin"),"yyyy-MM-dd HH:mm:ss");
        Date end = DatetimeOpt.convertStringToDate(request.getParameter("end"),"yyyy-MM-dd HH:mm:ss");
        map.put("osId",osId);
        map.put("userName",userName);
        map.put("userCode",userCode);
        if (questionTitle != null && !"".equals(questionTitle)){
            map.put("questionTitle","%"+questionTitle+"%");
        }
        map.put("questionContent",questionContent);
        map.put("questionState",questionState);
        map.put("currentOperator",currentOperator);
        map.put("editState",editState);
        map.put("begin",begin);
        map.put("end",end);
        JSONArray listObjects = questionInfoMag.getQuestionInfo(map, pageDesc);
        ResponseData resData = new ResponseData();
        resData.addResponseData(OBJLIST, listObjects);
        resData.addResponseData(PAGE_DESC, pageDesc);
        JsonResultUtils.writeResponseDataAsJson(resData, response);
    }

    /**
     * 查询所有   系统问题列表  列表  维护端
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return {data:[]}
     */
    @RequestMapping(value = "/getalllist" ,method = RequestMethod.GET)
    public void getlist(PageDesc pageDesc, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String osId = request.getParameter("osId");
        String userName = request.getParameter("userName");
        String userCode = request.getParameter("userCode");
        String questionTitle = request.getParameter("questionTitle");
        String questionContent = request.getParameter("questionContent");
        String questionState = request.getParameter("questionState");
        String editState = request.getParameter("editState");
        String currentOperator = request.getParameter("currentOperator");
        Date begin = DatetimeOpt.convertStringToDate(request.getParameter("begin"),"yyyy-MM-dd HH:mm:ss");
        Date end = DatetimeOpt.convertStringToDate(request.getParameter("end"),"yyyy-MM-dd HH:mm:ss");
        map.put("osId",osId);
        map.put("userName",userName);
        map.put("userCode",userCode);
        if (questionTitle != null && !"".equals(questionTitle)){
            map.put("questionTitle","%"+questionTitle+"%");
        }
        if (questionContent != null && !"".equals(questionContent)){
            map.put("questionContent","%"+questionContent+"%");
        }
        map.put("questionState",questionState);
        map.put("currentOperator",currentOperator);
        map.put("editState",editState);
        map.put("begin",begin);
        map.put("end",end);
        JSONArray listObjects = questionInfoMag.getQuestionInfo(map, pageDesc);
        ResponseData resData = new ResponseData();
        resData.addResponseData(OBJLIST, listObjects);
        resData.addResponseData(PAGE_DESC, pageDesc);
        JsonResultUtils.writeResponseDataAsJson(resData, response);
    }
    
    /**
     * 查询单个  系统问题列表   返回工单信息和所有交流信息
	 * @param questionId  QUESTION_ID
     * @param response    {@link HttpServletResponse}
     * @return {data:{}}
     */
    @RequestMapping(value = "/select/{questionId}", method = {RequestMethod.GET})
    public void getQuestionInfo(@PathVariable String questionId, HttpServletResponse response) {
        List<QuestionRound> questionRoundList = questionInfoMag.getQuestionRoundWithQuestionId(questionId);
        System.out.println("questionRoundListSize="+questionRoundList.size());
        QuestionInfo questionInfo = questionInfoMag.getObjectById(questionId);
        QuestionRoundRet questionRoundRet = new QuestionRoundRet();
        questionRoundRet.setQuestionRoundList(questionRoundList);
        questionRoundRet.setQuestionInfo(questionInfo);
        JsonResultUtils.writeSingleDataJson(questionRoundRet, response);
    }

    /**
     * 查询工单下面的所有 交流信息  只返回所有交流信息
     * @param questionId
     * @param response
     */
    @RequestMapping(value = "/selectquestionround/{questionId}", method = {RequestMethod.GET})
    public void getQuestionRoundWithQuestionId(@PathVariable String questionId, HttpServletResponse response) {
        List<QuestionRound> questionRoundList = questionInfoMag.getQuestionRoundWithQuestionId(questionId);
        JsonResultUtils.writeSingleDataJson(questionRoundList, response);
    }
    
    /**
     * 新增 系统问题列表
     * @return
     */
    @RequestMapping(value = "/createquestion",method = {RequestMethod.POST})
    public void createQuestionInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CentitUserDetails centitUserDetails = WebOptUtils.getLoginUser(request);
        QuestionInfo questionInfo = fetchQuestionInfo(request);
        questionInfo.setUserCode(centitUserDetails.getUserCode());
        questionInfo.setUserName(centitUserDetails.getUserName());
        Serializable pk = questionInfoMag.createQuestion(questionInfo);
        JsonResultUtils.writeSingleDataJson(pk,response);
    }

    /**
     * 删除单个  系统问题列表
	 * @param questionId  QUESTION_ID
     */
    @RequestMapping(value = "/deletequestion/{questionId}", method = {RequestMethod.DELETE})
    public void deleteQuestionInfo(@PathVariable String questionId, HttpServletResponse response) {
    	questionInfoMag.deleteQuestion(questionId);
        JsonResultUtils.writeSingleDataJson(questionId,response);
    } 
    
    /**
     * 新增或保存 系统问题列表
	 * @param questionId  QUESTION_ID
     * @param response    {@link HttpServletResponse}
     */
    @RequestMapping(value = "/updatequestion/{questionId}", method = {RequestMethod.POST})
    public void updateQuestionInfo(@PathVariable String questionId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        QuestionInfo questionInfo  = fetchQuestionInfo(request);
    	QuestionInfo dbQuestionInfo  = questionInfoMag.getObjectById( questionId);
        if (null != questionInfo) {
        	dbQuestionInfo .copy(questionInfo);
        	questionInfoMag.mergeObject(dbQuestionInfo);
        } else {
            JsonResultUtils.writeErrorMessageJson("当前对象不存在", response);
            return;
        }

        JsonResultUtils.writeSingleDataJson(questionId,response);
    }

    /**
     * 修改责任人
     * @param questionId  QUESTION_ID
     * @param response    {@link HttpServletResponse}
     */
    @RequestMapping(value = "/updateoperator/{questionId}", method = {RequestMethod.POST})
    public void updateOperator(@PathVariable String questionId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        QuestionInfo questionInfo  = fetchQuestionInfo(request);
        if (null != questionInfo) {
            QuestionInfo dbQuestionInfo  = questionInfoMag.getObjectById(questionId);
            dbQuestionInfo .setCurrentOperator(questionInfo.getCurrentOperator());
            questionInfoMag.mergeObject(dbQuestionInfo);
        } else {
            JsonResultUtils.writeErrorMessageJson("当前对象不存在", response);
            return;
        }
        JsonResultUtils.writeSingleDataJson(questionId,response);
    }

    /**
     * 工单追加问题描述
     * @param response
     */
    @RequestMapping(value = "/supplementalquestion", method = {RequestMethod.POST})
    public void supplementalQuestion(HttpServletRequest request, HttpServletResponse response){
        CentitUserDetails centitUserDetails = WebOptUtils.getLoginUser(request);
        String questionId = request.getParameter("questionId");
        String roundContent = request.getParameter("roundContent");
        if (questionId == null && roundContent == null){
            JsonResultUtils.writeErrorMessageJson(400,"当前对象不存在", response);
            return;
        }
        QuestionRound questionRound = new QuestionRound();
        questionRound.setQuestionId(questionId);
        questionRound.setRoundContent(roundContent);
        questionRound.setUserCode(centitUserDetails.getUserCode());
        questionRound.setUserName(centitUserDetails.getUserName());
        Serializable pk = questionInfoMag.saveQuestionRound(questionRound);
        JsonResultUtils.writeSingleDataJson(pk,response);
    }

    /**
     * 回复工单
     * @param response
     */
    @RequestMapping(value = "/replayquestion", method = {RequestMethod.POST})
    public void replay(HttpServletRequest request, HttpServletResponse response){
        String questionId = request.getParameter("questionId");
        String roundContent = request.getParameter("roundContent");
        CentitUserDetails centitUserDetails = WebOptUtils.getLoginUser(request);
        QuestionRound questionRound = new QuestionRound();
        questionRound.setQuestionId(questionId);
        questionRound.setRoundContent(roundContent);
        questionRound.setUserCode(centitUserDetails.getUserCode());
        questionRound.setUserName(centitUserDetails.getUserName());
        questionRound = questionInfoMag.replayQuestion(questionRound);
        JsonResultUtils.writeSingleDataJson(questionRound,response);
    }

    /**
     * 评价并关闭工单
     * @param questionId
     * @param response
     */
    @RequestMapping(value = "/evaluatequestion/{questionId}", method = {RequestMethod.PUT})
    public void evaluateQuestionInfo(@PathVariable String questionId,HttpServletRequest request, HttpServletResponse response){
        String evaluate = request.getParameter("evaluateScore");
        String retId = questionInfoMag.evaluateAndCloseQuestion(evaluate,questionId);
        JsonResultUtils.writeSingleDataJson(retId,response);
    }

    /**
     * 关闭工单
     * @param questionId
     * @param response
     */
    @RequestMapping(value = "/closequestion/{questionId}", method = {RequestMethod.PUT})
    public void closeQuestionInfo(@PathVariable String questionId, HttpServletResponse response){
        String retId = questionInfoMag.closeQuestion(questionId);
        JsonResultUtils.writeSingleDataJson(retId,response);
    }

    /**
     * 根据userCode获取工单列表
     * @param userCode
     * @param response
     */
    @RequestMapping(value = "/selectwithusercode/{userCode}", method = {RequestMethod.GET})
    public void listWithUserCode(@PathVariable String userCode, PageDesc pageDesc, HttpServletRequest request, HttpServletResponse response) {
        String editState = request.getParameter("editState");
        String questionState = request.getParameter("questionState");
        String questionTitle = request.getParameter("questionTitle");
        String currentOperator = request.getParameter("currentOperator");
        Date begin = DatetimeOpt.convertStringToDate(request.getParameter("begin"),"yyyy-MM-dd HH:mm:ss");
        Date end = DatetimeOpt.convertStringToDate(request.getParameter("end"),"yyyy-MM-dd HH:mm:ss");
        Map<String, Object> map = new HashMap<>();
        map.put("currentOperator",currentOperator);
        map.put("editState",editState);
        map.put("questionState",questionState);
        map.put("questionTitle",questionTitle);
        map.put("userCode",userCode);
        map.put("begin",begin);
        map.put("end",end);
        JSONArray listObjects = questionInfoMag.getQuestionInfo(map, pageDesc);
        ResponseData resData = new ResponseData();
        resData.addResponseData(OBJLIST, listObjects);
        resData.addResponseData(PAGE_DESC, pageDesc);
        JsonResultUtils.writeResponseDataAsJson(resData, response);
    }

    /**
     * 根据责任人获取工单列表
     * @param currentOperator
     * @param response
     */
    @RequestMapping(value = "/selectwithoperator/{currentOperator}", method = {RequestMethod.GET})
    public void listWithOperator(@PathVariable String currentOperator, PageDesc pageDesc, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        Date begin = DatetimeOpt.convertStringToDate(request.getParameter("begin"),"yyyy-MM-dd HH:mm:ss");
        Date end = DatetimeOpt.convertStringToDate(request.getParameter("end"),"yyyy-MM-dd HH:mm:ss");
        map.put("currentOperator",currentOperator);
        map.put("begin",begin);
        map.put("end",end);
        JSONArray listObjects = questionInfoMag.getQuestionInfo(map, pageDesc);
        ResponseData resData = new ResponseData();
        resData.addResponseData(OBJLIST, listObjects);
        resData.addResponseData(PAGE_DESC, pageDesc);
        JsonResultUtils.writeResponseDataAsJson(resData, response);
    }

    /**
     * 获取所有责任人
     * @param response
     */
    @RequestMapping(value = "/getalloperator", method = {RequestMethod.GET})
    public void getOperator(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> list = questionInfoMag.getAllOperator();
        JsonResultUtils.writeSingleDataJson(list, response);
    }


}
