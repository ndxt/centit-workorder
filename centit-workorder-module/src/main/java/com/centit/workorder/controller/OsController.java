package com.centit.workorder.controller;

import com.centit.framework.core.common.JsonResultUtils;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.ip.po.OsInfo;
import com.centit.framework.ip.service.IntegrationEnvironment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * OsController  Controller.
 * create by zk 2017-08-01
 * @author zk@centit.com
 * 系统
*/


@Controller
@RequestMapping("os")
public class OsController extends BaseController {
	private static final Log log = LogFactory.getLog(OsController.class);

    @Resource
    protected IntegrationEnvironment integrationEnvironment;


    /**
     * 列出系统所有os信息
     * @param response
     * @throws Exception
     */
    @RequestMapping(method = {RequestMethod.GET})
    public void getOsIdList(HttpServletResponse response) throws Exception {
        List<OsInfo> list = integrationEnvironment.listOsInfos();
        JsonResultUtils.writeSingleDataJson(list, response);
    }

}
