package com.centit.workorder.controller;

import com.centit.framework.components.CodeRepositoryUtil;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.controller.WrapUpResponseBody;
import com.centit.framework.model.adapter.PlatformEnvironment;
import com.centit.framework.model.basedata.IOsInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
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
    //private static final Logger logger = LoggerFactory.getLogger(HelpDocController.class);

    @Resource
    protected PlatformEnvironment platformEnvironment;


    /**
     * 列出系统所有os信息
     */
    @RequestMapping(method = {RequestMethod.GET})
    @WrapUpResponseBody
    public List<? extends IOsInfo> getOsIdList() {
        return platformEnvironment.listOsInfos();
    }

    /**
     * 根据id获取os信息
     * @param osId osId
     */
    @RequestMapping(value = "/{osId}", method = {RequestMethod.GET})
    @WrapUpResponseBody
    public IOsInfo getOs(@PathVariable String osId) {
        return CodeRepositoryUtil.getOsInfo(osId);
    }

}
