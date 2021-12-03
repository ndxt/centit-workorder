package com.centit.workorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centit.framework.common.JsonResultUtils;
import com.centit.framework.common.ResponseData;
import com.centit.framework.common.ResponseMapData;
import com.centit.framework.common.WebOptUtils;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.controller.WrapUpResponseBody;
import com.centit.support.algorithm.CollectionsOpt;
import com.centit.support.database.utils.PageDesc;
import com.centit.workorder.po.HelpDoc;
import com.centit.workorder.service.HelpDocManager;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HelpDoc  Controller.
 * create by scaffold 2017-05-08
 *
 * @author codefan@sina.com
 * 系统帮助文档null
 */


@Controller
@RequestMapping("/os/{osId}/documents")
public class HelpDocController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(HelpDocController.class);

    @Resource
    private HelpDocManager helpDocMag;

    /**
     * 查询单个  系统帮助文档
     *
     * @param docId DOC_ID
     */
    @ApiOperation(value = "查询单个 系统帮助文档")
    @WrapUpResponseBody
    @RequestMapping(value = "/{docId}", method = {RequestMethod.GET})
    public HelpDoc getHelpDoc(@PathVariable String docId) {
        return helpDocMag.getObjectById(docId);
    }

    /**
     * 创建 帮助文档条目
     *
     * @param helpDoc {@link HelpDoc}
     */
    @ApiOperation(value = "创建帮助文档")
    @RequestMapping(method = {RequestMethod.POST})
    public void saveHelpDoc(@PathVariable String osId, @RequestBody HelpDoc helpDoc,
                              HttpServletRequest request, HttpServletResponse response) {
        helpDoc.setOsId(osId);
        helpDoc.setUpdateUser(WebOptUtils.getCurrentUserCode(request));
        HelpDoc result = helpDocMag.saveHelpDoc(helpDoc);
        JsonResultUtils.writeSingleDataJson(result, response);
    }

    /**
     * 编辑帮助文档
     *
     * @param docId   DOC_ID
     * @param helpDoc {@link HelpDoc}
     */
    @ApiOperation(value = "编辑帮助文档")
    @RequestMapping(value = "/{docId}", method = {RequestMethod.PUT})
    public void updateHelpDoc(@PathVariable String docId, @RequestBody HelpDoc helpDoc,
                              HttpServletRequest request, HttpServletResponse response) {
        //不保存 历史版本
        helpDoc.setUpdateUser(WebOptUtils.getCurrentUserCode(request));
        HelpDoc result = helpDocMag.editHelpDoc(docId, helpDoc);
        JsonResultUtils.writeSingleDataJson(result, response);
    }

    /**
     * 编辑帮助文档内容
     */
    @ApiOperation(value = "编辑帮助文档内容")
    @RequestMapping(value = "/{docId}/content", method = {RequestMethod.PUT})
    public void editContent(@PathVariable String docId, @RequestBody Map<String, String> content,
                            HttpServletRequest request, HttpServletResponse response) {
        // 保存 历史版本，
        String userCode = WebOptUtils.getCurrentUserCode(request);
        HelpDoc result = helpDocMag.editContent(docId, content.get("content"), userCode);
        JsonResultUtils.writeSingleDataJson(result, response);
    }

    /**
     * 删除 帮助文档
     *
     * @param docId DOC_ID
     */
    @ApiOperation(value = "删除 帮助文档")
    @RequestMapping(value = "/{docId}", method = {RequestMethod.DELETE})
    public void deleteHelpDoc(@PathVariable String docId, HttpServletResponse response) {
        //全部删除 不可恢复 包括历史版本
        helpDocMag.deleteHelpDoc(docId);
        JsonResultUtils.writeSuccessJson(response);
    }

    /**
     * 帮助文档查询接口（按层级查）
     */
    @ApiOperation(value = "帮助文档查询接口（按层级查）")
    @RequestMapping(value = "/levelSearch", method = RequestMethod.GET)
    public void levelSearch(@PathVariable String osId, HttpServletResponse response) {
        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("osId", osId);
        List<HelpDoc> list = helpDocMag.listObjects(filterMap);
        ResponseMapData resData = new ResponseMapData();
        if (CollectionUtils.isEmpty(list)) {
            resData.addResponseData("isNew", true);
        } else {
            resData.addResponseData("isNew", false);
        }
        JSONArray listObjects = helpDocMag.searchHelpdocByLevel(list);
        resData.addResponseData(OBJLIST, listObjects);
        JsonResultUtils.writeResponseDataAsJson(resData, response);
    }

    /**
     * 帮助文档查询接口（按问题类别）
     */
    @ApiOperation(value = "帮助文档查询接口（按问题类别）")
    @RequestMapping(method = RequestMethod.GET)
    public void typeSearch(@PathVariable("osId") String osId, String catalogId,
                           PageDesc pageDesc, HttpServletResponse response) {
        //分页， 排序 按照 评价次数
        Map<String, Object> map = new HashMap<>();
        map.put("catalogId", catalogId);
        map.put("osId", osId);
        List<HelpDoc> listObjects = helpDocMag.searchHelpdocByType(map, pageDesc);
        ResponseMapData resData = new ResponseMapData();
        resData.addResponseData(OBJLIST, listObjects);
        resData.addResponseData(PAGE_DESC, pageDesc);
        JsonResultUtils.writeResponseDataAsJson(resData, response);
    }

    /**
     * 帮助文档全文检索 关键字查询
     */
    @ApiOperation(value = "帮助文档全文检索 关键字查询")
    @RequestMapping(value = "/fullSearch/{keyWord}", method = RequestMethod.GET)
    public void fullSearch(@PathVariable String osId, @PathVariable String keyWord, PageDesc pageDesc, HttpServletResponse response) {
        List<Map<String, Object>> result = helpDocMag.fullSearch(CollectionsOpt.createHashMap("osId", osId), keyWord, pageDesc);
        JsonResultUtils.writeSingleDataJson(result, response);
    }

    @ApiOperation(value = "编辑帮助文档的目录")
    @ApiImplicitParams({@ApiImplicitParam(
        name = "docId", value = "移动的文档Id",
        required = true, paramType = "path", dataType = "String"
    ), @ApiImplicitParam(
        name = "targetDocId", value = "移动到的目标文档Id",
        required = true, paramType = "path", dataType = "String"
    ), @ApiImplicitParam(
        name = "action", value = "inner：移动到文档的子文档  before：移动到文档之前  after：移动到文档之后"
    )})
    @WrapUpResponseBody
    @RequestMapping(value = "/catalog/moveAfter/{docId}/{targetDocId}", method = {RequestMethod.PUT})
    public ResponseData catalog(@PathVariable String docId, @PathVariable String targetDocId,
                                @RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String action = jsonObject.getString("action");
        if ("before".equals(action) || "after".equals(action)) {
            helpDocMag.catalog(docId, targetDocId, action);
        } else if ("inner".equals(action)) {
            HelpDoc targetHelpDoc = helpDocMag.getObjectById(targetDocId);
            HelpDoc helpDoc = helpDocMag.getObjectById(docId);
            helpDoc.setPrevDocId(targetDocId);
            helpDoc.setDocPath(targetHelpDoc.getDocPath() + "/" + targetDocId);
            helpDocMag.editHelpDoc(docId, helpDoc);
        }

        return ResponseData.successResponse;
    }

    /**
     * 帮助文档目录初始化
     */
    @ApiOperation(value = "帮助文档目录初始化")
    @WrapUpResponseBody
    @RequestMapping(value = "/catalogInit", method = RequestMethod.GET)
    public ResponseData catalogInit(@PathVariable String osId, String action) {
        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("osId", osId);
        List<HelpDoc> list = helpDocMag.listObjects(filterMap);
        helpDocMag.updatePrevDoc(list, action);
        return ResponseData.successResponse;
    }
}
