package com.centit.workorder.controller;

import com.centit.framework.common.ResponseData;
import com.centit.framework.common.WebOptUtils;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.controller.WrapUpResponseBody;
import com.centit.support.algorithm.CollectionsOpt;
import com.centit.support.common.ObjectException;
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
     * @return {data:{[]}}
     */
    @RequestMapping(value = "/{questionId}", method = {RequestMethod.GET})
    @WrapUpResponseBody
    public List<QuestionRound> getQuestionInfo(@PathVariable String questionId) {
        return questionInfoMag.getQuestionRoundWithQuestionId(questionId);
    }

    /**
     * 查询单个  系统问题列表   返回工单信息和所有可以给用户显示的交流信息
     * @param questionId  QUESTION_ID
     * @return {data:{}}
     */
    @RequestMapping(value = "/showUser/{questionId}", method = {RequestMethod.GET})
    @WrapUpResponseBody
    public ResponseData getQuestionInfoShowUser(@PathVariable String questionId) {
        List<QuestionRound> questionRoundList = questionInfoMag.getQuestionRoundShowUser(questionId);
        QuestionInfo questionInfo = questionInfoMag.getObjectById(questionId);
        return ResponseData.makeResponseData(CollectionsOpt.createHashMap(
                "questionInfo", questionInfo,
                "questionRoundList",questionRoundList
        ));
    }

    /**
     * 查询单个  系统问题列表   返回工单信息和所有交流信息
     * @param questionId QUESTION_ID
     * @return {data:{}}
     */
    @RequestMapping(value = "/questionRound/{questionId}", method = {RequestMethod.GET})
    @WrapUpResponseBody
    public ResponseData getQuestionRound(@PathVariable String questionId) {
        List<QuestionRound> questionRoundList = questionInfoMag.getQuestionRoundWithQuestionId(questionId);
        QuestionInfo questionInfo = questionInfoMag.getObjectById(questionId);
        return ResponseData.makeResponseData(CollectionsOpt.createHashMap(
                "questionInfo", questionInfo,
                "questionRoundList",questionRoundList
        ));
    }

    /**
     * 查询工单下面所有可以给用户查看的交流信息
     * @param questionId
     * param response
     * @return  List QuestionRound
     */
    @RequestMapping(value = "/questionRoundShowUser/{questionId}", method = {RequestMethod.GET})
    @WrapUpResponseBody
    public List<QuestionRound> getQuestionRoundShowUser(@PathVariable String questionId) {
        return questionInfoMag.getQuestionRoundShowUser(questionId);
    }

    /**
     * 编辑讨论内容
     * @param roundId  QUESTION_ID
     */
    @RequestMapping(value = "/{roundId}", method = {RequestMethod.PUT})
    @WrapUpResponseBody
    public void editDiscussRound(@PathVariable String roundId,
                                 @RequestBody QuestionRound questionRound,
                                 HttpServletRequest request) {
        if (questionRound == null){
            throw new ObjectException(questionRound, ObjectException.DATA_NOT_FOUND_EXCEPTION, "当前对象不存在");
        }
        questionRound.setUserCode(WebOptUtils.getCurrentUserCode(request));
        questionRound.setUserName(WebOptUtils.getCurrentUserName(request));
        questionRound.setRoundId(roundId);
        questionInfoMag.updateDiscuss(questionRound);
    }

    /*
     * 工单追加问题描述
     * @param request
     */
    @RequestMapping(value = "/supplemental/{questionId}", method = {RequestMethod.POST})
    @WrapUpResponseBody
    public void supplementalQuestion(HttpServletRequest request,
                                     @PathVariable String questionId,
                                     @RequestBody  QuestionRound questionRound){
        if (questionRound == null){
            throw new ObjectException(questionRound, ObjectException.DATA_NOT_FOUND_EXCEPTION, "当前对象不存在");
        }
        questionRound.setUserCode(WebOptUtils.getCurrentUserCode(request));
        questionRound.setUserName(WebOptUtils.getCurrentUserName(request));
        questionRound.setQuestionId(questionId);
        questionInfoMag.saveQuestionRound(questionRound);
    }

    /*
     * 回复工单
     * @param response
     */
    @RequestMapping(value = "/reply/{questionId}", method = {RequestMethod.POST})
    @WrapUpResponseBody
    public void replay(HttpServletRequest request,
                       @PathVariable String questionId,
                       @RequestBody QuestionRound questionRound){
        questionRound.setUserCode(WebOptUtils.getCurrentUserCode(request));
        questionRound.setUserName(WebOptUtils.getCurrentUserName(request));
        questionRound.setQuestionId(questionId);
        questionRound = questionInfoMag.replayQuestion(questionRound);
    }

    /*
     * 工单问题讨论
     * @param response
     */
    @RequestMapping(value = "/discuss/{questionId}", method = {RequestMethod.POST})
    @WrapUpResponseBody
    public void discuss(HttpServletRequest request,
                        @PathVariable String questionId,
                        @RequestBody QuestionRound questionRound){
        questionRound.setUserCode(WebOptUtils.getCurrentUserCode(request));
        questionRound.setUserName(WebOptUtils.getCurrentUserName(request));
        questionRound.setQuestionId(questionId);
        questionRound = questionInfoMag.discussQuestion(questionRound);
    }

    /*
     * 修改showUser标识
     * @param roundId
     * @param response
     */
    @RequestMapping(value = "/{roundId}/showUserTag", method = {RequestMethod.PUT})
    @WrapUpResponseBody
    public void updateShowUserTag(@PathVariable String roundId,
                                  String showUser){
        questionInfoMag.updateShowUserTag(roundId,showUser);
    }

}
