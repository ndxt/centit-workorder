package com.centit.workorder.controller;

import com.centit.framework.common.WebOptUtils;
import com.centit.framework.core.common.JsonResultUtils;
import com.centit.framework.core.common.ResponseData;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.security.model.CentitUserDetails;
import com.centit.workorder.po.QuestionInfo;
import com.centit.workorder.po.QuestionRound;
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
import java.io.IOException;
import java.util.List;

/**
 * Created by zhang_gd on 2017/8/2.
 */

@Controller
@RequestMapping("/questions/{questionId}/round")
public class QuestionRoundController extends BaseController {
    private static final Log log = LogFactory.getLog(QuestionRoundController.class);

    @Resource
    private QuestionInfoManager questionInfoMag;


    /**
     *  查询工单下面的所有 交流信息  只返回所有交流信息
     * @param questionId  QUESTION_ID
     * @param response    {@link HttpServletResponse}
     * @return {data:{}}
     */
    @RequestMapping(method = {RequestMethod.GET})
    public void getQuestionInfo(@PathVariable String questionId, HttpServletResponse response) {
        List<QuestionRound> questionRoundList = questionInfoMag.getQuestionRoundWithQuestionId(questionId);
        JsonResultUtils.writeSingleDataJson(questionRoundList, response);
    }

    /**
     * 查询单个  系统问题列表   返回工单信息和所有可以给用户显示的交流信息
     * @param questionId  QUESTION_ID
     * @param response    {@link HttpServletResponse}
     * @return {data:{}}
     */
    @RequestMapping(value = "/showUser", method = {RequestMethod.GET})
    public void getQuestionInfoShowUser(@PathVariable String questionId, HttpServletResponse response) {
        List<QuestionRound> questionRoundList = questionInfoMag.getQuestionRoundShowUser(questionId);
        QuestionInfo questionInfo = questionInfoMag.getObjectById(questionId);
        ResponseData resData = new ResponseData();
        resData.addResponseData("questionInfo", questionInfo);
        resData.addResponseData("questionRoundList",questionRoundList );
        JsonResultUtils.writeResponseDataAsJson(resData, response);
    }

    /**
     * 查询单个  系统问题列表   返回工单信息和所有交流信息
     * @param questionId
     * @param response
     */
    @RequestMapping(value = "/questionRound", method = {RequestMethod.GET})
    public void getQuestionRound(@PathVariable String questionId, HttpServletResponse response) {
        List<QuestionRound> questionRoundList = questionInfoMag.getQuestionRoundWithQuestionId(questionId);
        QuestionInfo questionInfo = questionInfoMag.getObjectById(questionId);
        ResponseData resData = new ResponseData();
        resData.addResponseData("questionInfo", questionInfo);
        resData.addResponseData("questionRoundList",questionRoundList );
        JsonResultUtils.writeResponseDataAsJson(resData, response);
    }

    /**
     * 查询工单下面所有可以给用户查看的交流信息
     * @param questionId
     * @param response
     */
    @RequestMapping(value = "/questionRoundShowUser", method = {RequestMethod.GET})
    public void getQuestionRoundShowUser(@PathVariable String questionId, HttpServletResponse response) {
        List<QuestionRound> questionRoundList = questionInfoMag.getQuestionRoundShowUser(questionId);
        JsonResultUtils.writeSingleDataJson(questionRoundList, response);
    }

    /**
     * 编辑讨论内容
     * @param roundId  QUESTION_ID
     * @param response    {@link HttpServletResponse}
     */
    @RequestMapping(value = "/{roundId}", method = {RequestMethod.PUT})
    public void editDiscussRound(@PathVariable String roundId,
                                 @RequestBody QuestionRound questionRound,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
        if (questionRound == null){
            JsonResultUtils.writeErrorMessageJson("当前对象不存在", response);
            return;
        }
        CentitUserDetails centitUserDetails = WebOptUtils.getLoginUser(request);
        questionRound.setUserCode(centitUserDetails.getUserCode());
        questionRound.setUserName(centitUserDetails.getUserName());
        questionRound.setRoundId(roundId);
        questionInfoMag.updateDiscuss(questionRound);
        JsonResultUtils.writeSuccessJson(response);
    }

    /**
     * 工单追加问题描述
     * @param response
     */
    @RequestMapping(value = "/supplemental", method = {RequestMethod.POST})
    public void supplementalQuestion(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @PathVariable String questionId,
                                     @RequestBody  QuestionRound questionRound){
        if (questionRound == null){
            JsonResultUtils.writeErrorMessageJson(400,"当前对象不存在", response);
            return;
        }
        CentitUserDetails centitUserDetails = WebOptUtils.getLoginUser(request);
        questionRound.setUserCode(centitUserDetails.getUserCode());
        questionRound.setUserName(centitUserDetails.getUserName());
        questionRound.setQuestionId(questionId);
        questionInfoMag.saveQuestionRound(questionRound);
        JsonResultUtils.writeSuccessJson(response);
    }

    /**
     * 回复工单
     * @param response
     */
    @RequestMapping(value = "/reply", method = {RequestMethod.POST})
    public void replay(HttpServletRequest request,
                       HttpServletResponse response,
                       @PathVariable String questionId,
                       @RequestBody QuestionRound questionRound){
        CentitUserDetails centitUserDetails = WebOptUtils.getLoginUser(request);
        questionRound.setUserCode(centitUserDetails.getUserCode());
        questionRound.setUserName(centitUserDetails.getUserName());
        questionRound.setQuestionId(questionId);
        questionRound = questionInfoMag.replayQuestion(questionRound);
        JsonResultUtils.writeSingleDataJson(questionRound,response);
    }

    /**
     * 工单问题讨论
     * @param response
     */
    @RequestMapping(value = "/discuss", method = {RequestMethod.POST})
    public void discuss(HttpServletRequest request,
                        HttpServletResponse response,
                        @PathVariable String questionId,
                        @RequestBody QuestionRound questionRound){
        CentitUserDetails centitUserDetails = WebOptUtils.getLoginUser(request);
        questionRound.setUserCode(centitUserDetails.getUserCode());
        questionRound.setUserName(centitUserDetails.getUserName());
        questionRound.setQuestionId(questionId);
        questionRound = questionInfoMag.discussQuestion(questionRound);
        JsonResultUtils.writeSingleDataJson(questionRound,response);
    }

    /**
     * 修改showUser标识
     * @param roundId
     * @param response
     */
    @RequestMapping(value = "/{roundId}/showUserTag", method = {RequestMethod.PUT})
    public void updateShowUserTag(@PathVariable String roundId,
                                  String showUser,
                                  HttpServletResponse response){
        questionInfoMag.updateShowUserTag(roundId,showUser);
        JsonResultUtils.writeSuccessJson(response);
    }

}
