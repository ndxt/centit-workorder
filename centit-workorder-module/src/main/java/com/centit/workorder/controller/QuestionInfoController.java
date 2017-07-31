package com.centit.workorder.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.centit.framework.common.WebOptUtils;
import com.centit.framework.core.common.JsonResultUtils;
import com.centit.framework.core.common.ResponseData;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.model.adapter.PlatformEnvironment;
import com.centit.framework.model.basedata.IUserInfo;
import com.centit.framework.security.model.CentitUserDetails;
import com.centit.support.algorithm.DatetimeOpt;
import com.centit.workorder.po.*;
import com.centit.workorder.service.QuestionCatalogManager;
import com.centit.workorder.service.QuestionInfoManager;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/questionInfo")
public class QuestionInfoController  extends BaseController {
	private static final Log log = LogFactory.getLog(QuestionInfoController.class);
	
	@Resource
	private QuestionInfoManager questionInfoMag;

    @Resource
    protected PlatformEnvironment platformEnvironment;

    /**
     * 查询所有   系统问题列表  列表  客户展示端
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return {data:[]}
     */
    @RequestMapping(value = "/list/{osId}" ,method = RequestMethod.GET)
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
     * 获取问题类别下的所有问题列表
     * @param catalogId
     * @param response
     */
    @RequestMapping(value = "/listWithCatalogId/{catalogId}", method = {RequestMethod.GET})
    public void getQuestionInfo(@PathVariable String catalogId, HttpServletResponse response){
        List<QuestionInfo> questionInfoList = questionInfoMag.getQuestionInfoWithCatalogId(catalogId);
        JsonResultUtils.writeSingleDataJson(questionInfoList, response);
    }
    
    /**
     * 查询单个  系统问题列表   返回工单信息和所有交流信息
	 * @param questionId  QUESTION_ID
     * @param response    {@link HttpServletResponse}
     * @return {data:{}}
     */
    @RequestMapping(value = "/select/{questionId}", method = {RequestMethod.GET})
    public void getQuestionInfoWithCatalog(@PathVariable String questionId, HttpServletResponse response) {
        List<QuestionRound> questionRoundList = questionInfoMag.getQuestionRoundWithQuestionId(questionId);
        QuestionInfo questionInfo = questionInfoMag.getObjectById(questionId);
        ResponseData resData = new ResponseData();
        resData.addResponseData("questionInfo", questionInfo);
        resData.addResponseData("questionRoundList",questionRoundList );
        JsonResultUtils.writeResponseDataAsJson(resData, response);
    }

    /**
     * 查询工单下面的所有 交流信息  只返回所有交流信息
     * @param questionId
     * @param response
     */
    @RequestMapping(value = "/selectQuestionRound", method = {RequestMethod.GET})
    public void getQuestionRoundWithQuestionId(@PathVariable String questionId, HttpServletResponse response) {
        List<QuestionRound> questionRoundList = questionInfoMag.getQuestionRoundWithQuestionId(questionId);
        JsonResultUtils.writeSingleDataJson(questionRoundList, response);
    }

    /**
     * 获取未的工单列表
     * @param response
     */
    @RequestMapping(value = "/selectUnabsorbedQuestion", method = {RequestMethod.GET})
    public void getUnabsorbedQuestionList(HttpServletResponse response){
        List<QuestionInfo> QuestionInfoList = questionInfoMag.getUnabsorbedQuestion();
        JsonResultUtils.writeSingleDataJson(QuestionInfoList, response);
    }
    
    /**
     * 新增 系统问题列表
     * @return
     */
    @RequestMapping(value = "/createQuestion",method = {RequestMethod.POST})
    public void createQuestionInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestBody QuestionInfo questionInfo) throws IOException {
        CentitUserDetails centitUserDetails = WebOptUtils.getLoginUser(request);
        questionInfo.setUserCode(centitUserDetails.getUserCode());
        questionInfo.setUserName(centitUserDetails.getUserName());
        Serializable pk = questionInfoMag.createQuestion(questionInfo);
        JsonResultUtils.writeSingleDataJson(pk,response);
    }

    /**
     * 删除单个  系统问题列表
	 * @param questionId  QUESTION_ID
     */
    @RequestMapping(value = "/deleteQuestion/{questionId}", method = {RequestMethod.DELETE})
    public void deleteQuestionInfo(@PathVariable String questionId, HttpServletResponse response) {
    	questionInfoMag.deleteQuestion(questionId);
        JsonResultUtils.writeSingleDataJson(questionId,response);
    } 
    
    /**
     * 修改问题
	 * @param questionId  QUESTION_ID
     * @param response    {@link HttpServletResponse}
     */
    @RequestMapping(value = "/updateQuestion/{questionId}", method = {RequestMethod.POST})
    public void updateQuestionInfo(@PathVariable String questionId,
                                   @RequestBody QuestionInfo questionInfo,
                                   HttpServletResponse response) throws IOException {
        if (questionInfo == null){
            JsonResultUtils.writeErrorMessageJson("当前对象不存在", response);
            return;
        }
    	QuestionInfo dbQuestionInfo  = questionInfoMag.getObjectById( questionId);
        dbQuestionInfo.copy(questionInfo);
        questionInfoMag.mergeObject(dbQuestionInfo);
        JsonResultUtils.writeSingleDataJson(questionId,response);
    }

    /**
     * 修改责任人
     * @param questionId  QUESTION_ID
     * @param response    {@link HttpServletResponse}
     */
    @RequestMapping(value = "/updateOperator/{questionId}", method = {RequestMethod.POST})
    public void updateOperator(@PathVariable String questionId,
                               HttpServletResponse response,
                               @RequestBody QuestionInfo questionInfo) throws IOException {
        if (questionInfo == null){
            JsonResultUtils.writeErrorMessageJson("当前对象不存在", response);
            return;
        }
        QuestionInfo dbQuestionInfo  = questionInfoMag.getObjectById(questionId);
        dbQuestionInfo .setCurrentOperator(questionInfo.getCurrentOperator());
        questionInfoMag.mergeObject(dbQuestionInfo);
        JsonResultUtils.writeSingleDataJson(questionId,response);
    }

    /**
     * 工单追加问题描述
     * @param response
     */
    @RequestMapping(value = "/supplementalQuestion", method = {RequestMethod.POST})
    public void supplementalQuestion(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestBody  QuestionRound questionRound){
        if (questionRound == null){
            JsonResultUtils.writeErrorMessageJson(400,"当前对象不存在", response);
            return;
        }
        CentitUserDetails centitUserDetails = WebOptUtils.getLoginUser(request);
        questionRound.setUserCode(centitUserDetails.getUserCode());
        questionRound.setUserName(centitUserDetails.getUserName());
        Serializable pk = questionInfoMag.saveQuestionRound(questionRound);
        JsonResultUtils.writeSingleDataJson(pk,response);
    }

    /**
     * 回复工单
     * @param response
     */
    @RequestMapping(value = "/replayQuestion", method = {RequestMethod.POST})
    public void replay(HttpServletRequest request,
                       HttpServletResponse response,
                       @RequestBody QuestionRound questionRound){
        CentitUserDetails centitUserDetails = WebOptUtils.getLoginUser(request);
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
    @RequestMapping(value = "/evaluateQuestion/{questionId}", method = {RequestMethod.PUT})
    public void evaluateQuestionInfo(@PathVariable String questionId,
                                     String evaluateScore,
                                     HttpServletResponse response){
        String retId = questionInfoMag.evaluateAndCloseQuestion(evaluateScore,questionId);
        JsonResultUtils.writeSingleDataJson(retId,response);
    }

    /**
     * 关闭工单
     * @param questionId
     * @param response
     */
    @RequestMapping(value = "/closeQuestion/{questionId}", method = {RequestMethod.PUT})
    public void closeQuestionInfo(@PathVariable String questionId, HttpServletResponse response){
        String retId = questionInfoMag.closeQuestion(questionId);
        JsonResultUtils.writeSingleDataJson(retId,response);
    }

    /**
     * 根据userCode获取工单列表
     * @param userCode
     * @param response
     */
    @RequestMapping(value = "/selectWithUserCode/{userCode}", method = {RequestMethod.GET})
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
    @RequestMapping(value = "/selectWithOperator/{currentOperator}", method = {RequestMethod.GET})
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
    @RequestMapping(value = "/selectOperatorList", method = {RequestMethod.GET})
    public void getOperator(HttpServletResponse response) throws Exception {
        List<? extends IUserInfo> userInfoList =  platformEnvironment.listAllUsers();
        JsonResultUtils.writeSingleDataJson(userInfoList, response);
    }

    /**
     * 添加协助处理人员
     * @return
     */
    @RequestMapping(value = "/addAssistOperator",method = {RequestMethod.POST})
    public void addAssistOperator(HttpServletResponse response,
                                  @RequestBody AssistOperator assistOperator) throws IOException {
        if (StringUtils.isBlank(assistOperator.getAid().getOperatorCode())
                || StringUtils.isBlank(assistOperator.getAid().getQuestionId())){
            JsonResultUtils.writeErrorMessageJson(400,"当前对象不存在", response);
            return;
        }
        Serializable pk = questionInfoMag.createAssistOperator(assistOperator);
        JsonResultUtils.writeSingleDataJson(pk,response);
    }

    /**
     * 删除协助处理人员
     * @return
     */
    @RequestMapping(value = "/deleteAssistOperator/{questionId}/{operatorCode}",method = {RequestMethod.DELETE})
    public void deleteAssistOperator(HttpServletResponse response,
                                     @PathVariable String questionId,
                                     @PathVariable String operatorCode) throws IOException {
        if (StringUtils.isBlank(questionId) || StringUtils.isBlank(operatorCode)){
            JsonResultUtils.writeErrorMessageJson(400,"当前对象不存在", response);
            return;
        }
        questionInfoMag.deleteObject(new AssistOperatorId(questionId,operatorCode));
        JsonResultUtils.writeSuccessJson(response);
    }


}
