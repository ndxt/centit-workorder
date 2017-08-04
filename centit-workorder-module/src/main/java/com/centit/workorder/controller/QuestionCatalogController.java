package com.centit.workorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.common.WebOptUtils;
import com.centit.framework.core.common.JsonResultUtils;
import com.centit.framework.core.common.ResponseMapData;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.security.model.CentitUserDetails;
import com.centit.support.algorithm.DatetimeOpt;
import com.centit.workorder.po.QuestionCatalog;
import com.centit.workorder.service.QuestionCatalogManager;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * QuestionCatalog  Controller.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统问题类别null   
*/


@Controller
@RequestMapping("os/{osId}/catalogs")
public class QuestionCatalogController  extends BaseController {
	private static final Log log = LogFactory.getLog(QuestionCatalogController.class);

	@Resource
	private QuestionCatalogManager questionCatalogMag;

    /**
     * 查询所有   系统问题类别  列表 用户展示端
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return {data:[]}
     */
    @RequestMapping(method = RequestMethod.GET)
    public void list(@PathVariable String osId, PageDesc pageDesc, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String catalogName = request.getParameter("catalogName");
        Date begin = DatetimeOpt.convertStringToDate(request.getParameter("begin"),"yyyy-MM-dd HH:mm:ss");
        Date end = DatetimeOpt.convertStringToDate(request.getParameter("end"),"yyyy-MM-dd HH:mm:ss");
        map.put("osId",osId);
        map.put("catalogName",catalogName);
        map.put("begin",begin);
        map.put("end",end);
        JSONArray listObjects = questionCatalogMag.getAllCatalog(map, pageDesc);
        ResponseMapData resData = new ResponseMapData();
        resData.addResponseData(OBJLIST, listObjects);
        resData.addResponseData(PAGE_DESC, pageDesc);
        JsonResultUtils.writeResponseDataAsJson(resData, response);
    }

    /**
     * 查询单个  系统问题类别
	 * @param catalogId  CATALOG_ID
     *
     * @param response    {@link HttpServletResponse}
     * @return {data:{}}
     */
    @RequestMapping(value = "/{catalogId}", method = {RequestMethod.GET})
    public void getQuestionCatalog(@PathVariable String catalogId, HttpServletResponse response) {
    	QuestionCatalog questionCatalog = questionCatalogMag.getObjectById(catalogId);
        JsonResultUtils.writeSingleDataJson(questionCatalog, response);
    }
    
    /**
     * 新增 系统问题类别
     *
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST})
    public void createQuestionCatalog(@RequestBody QuestionCatalog questionCatalog,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws IOException {
        CentitUserDetails centitUserDetails = WebOptUtils.getLoginUser(request);
        questionCatalog.setCreateTime(DatetimeOpt.currentUtilDate());
        questionCatalog.setCreator(centitUserDetails.getName());
        questionCatalog.setDefaultOperator(centitUserDetails.getName());
    	questionCatalogMag.saveNewObject(questionCatalog);
        JsonResultUtils.writeSuccessJson(response);
    }

    /**
     * 删除单个  系统问题类别
	 * @param catalogId  CATALOG_ID
     */
    @RequestMapping(value = "/{catalogId}", method = {RequestMethod.DELETE})
    public void deleteQuestionCatalog(@PathVariable String catalogId, HttpServletResponse response) {
        questionCatalogMag.deleteCatalog(catalogId);
        JsonResultUtils.writeSingleDataJson(catalogId,response);
    } 
    
    /**
     * 新增或保存 系统问题类别 
        修改问题类别
     * @param response    {@link HttpServletResponse}
     */
    @RequestMapping(value = "/{catalogId}", method = {RequestMethod.PUT})
    public void updateQuestionCatalog(@PathVariable String catalogId,
                                      @RequestBody QuestionCatalog questionCatalog,
                                      HttpServletResponse response) throws IOException {
        if (questionCatalog == null){
            JsonResultUtils.writeErrorMessageJson(400,"当前对象不存在", response);
            return;
        }
        questionCatalogMag.updateCatalog(catalogId,questionCatalog);
        JsonResultUtils.writeSuccessJson(response);
    }
}
