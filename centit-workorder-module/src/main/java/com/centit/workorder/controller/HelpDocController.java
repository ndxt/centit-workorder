package com.centit.workorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.common.JsonResultUtils;
import com.centit.framework.common.ResponseMapData;
import com.centit.framework.common.WebOptUtils;
import com.centit.framework.core.controller.BaseController;
import com.centit.support.database.utils.PageDesc;
import com.centit.workorder.po.HelpDoc;
import com.centit.workorder.service.HelpDocManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HelpDoc  Controller.
 * create by scaffold 2017-05-08
 * @author codefan@sina.com
 * 系统帮助文档null
*/


@Controller
@RequestMapping("/os/{osId}/documents")
public class HelpDocController  extends BaseController {
    //private static final Logger logger = LoggerFactory.getLogger(HelpDocController.class);

    @Resource
    private HelpDocManager helpDocMag;

    /**
     * 查询单个  系统帮助文档
     * @param docId  DOC_ID
     */
    @RequestMapping(value = "/{docId}", method = {RequestMethod.GET})
    public void getHelpDoc(@PathVariable String docId, HttpServletResponse response) {

        HelpDoc helpDoc = helpDocMag.getObjectById(docId);

        JsonResultUtils.writeSingleDataJson(helpDoc, response);
    }

    /**
     * 创建 帮助文档条目
     * @param helpDoc  {@link HelpDoc}
     */
    @RequestMapping(method = {RequestMethod.POST})
    public void createHelpDoc(@PathVariable String osId, @RequestBody HelpDoc helpDoc,
                              HttpServletRequest request, HttpServletResponse response) {

        helpDoc.setOsId(osId);
        helpDoc.setUpdateUser(WebOptUtils.getCurrentUserCode(request));
        HelpDoc result = helpDocMag.createHelpDoc(helpDoc);
        JsonResultUtils.writeSingleDataJson(result, response);
    }

    /**
     * 编辑帮助文档
     * @param docId  DOC_ID
     * @param helpDoc  {@link HelpDoc}
     */
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
     * @param docId  DOC_ID
     */
    @RequestMapping(value = "/{docId}", method = {RequestMethod.DELETE})
    public void deleteHelpDoc(@PathVariable String docId, HttpServletResponse response) {
        //全部删除 不可恢复 包括历史版本
        helpDocMag.deleteHelpDoc(docId);
        JsonResultUtils.writeSuccessJson(response);
    }

    /**
     * 帮助文档查询接口（按层级查）
     */
    @RequestMapping(value="/levelSearch", method = RequestMethod.GET)
    public void levelSearch(@PathVariable String osId,HttpServletResponse response) {
        JSONArray listObjects = helpDocMag.searchHelpdocByLevel(osId);
        ResponseMapData resData = new ResponseMapData();
        resData.addResponseData(OBJLIST, listObjects);
        JsonResultUtils.writeResponseDataAsJson(resData, response);
    }

    /**
     * 帮助文档查询接口（按问题类别）
     */
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
     * 帮助文档全文检索
     */
    @RequestMapping(value = "/fullTextSearch/{catalogId}", method = RequestMethod.GET)
    public void fullTextSearch(@PathVariable String catalogId, PageDesc pageDesc, HttpServletResponse response) {
        List<Map<String, Object>> result = helpDocMag.fullTextSearch(catalogId, pageDesc);
        JsonResultUtils.writeSingleDataJson(result, response);
    }

    /**
     * 帮助文档全文检索 关键字查询
     */
    @RequestMapping(value = "/fullSearch/{keyWord}", method = RequestMethod.GET)
    public void fullSearch(@PathVariable String keyWord, PageDesc pageDesc, HttpServletResponse response) {
        List<Map<String, Object>> result = helpDocMag.fullSearch(keyWord, pageDesc);
        JsonResultUtils.writeSingleDataJson(result, response);
    }
}
