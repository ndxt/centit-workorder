package com.centit.workorder.controller;

import com.alibaba.fastjson.JSONObject;
import com.centit.fileserver.controller.FileController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file")
public class UpAndDownFileController extends FileController {

    private static final Logger logger = LoggerFactory.getLogger(UpAndDownFileController.class);

    /**
     * 文件上传后的处理工作，如果需要对文件处理或者返回特定的数据给前段可以在这个方法中做
     * @param fileMd5 文件的md5 和 size可以确定文件的位置
     * @param size 文件大小
     * @param retJson 返回前段的json对象，可以在这个方法中修改
     */
    @Override
    protected void fileUploadCompleteOpt(String fileMd5, long size,
                                         JSONObject retJson) {

    }
}
