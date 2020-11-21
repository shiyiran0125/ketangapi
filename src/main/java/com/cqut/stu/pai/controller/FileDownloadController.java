package com.cqut.stu.pai.controller;

import com.cqut.stu.pai.util.FileDownload;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 石益然
 * @program: pai
 * @description: 文件下载
 * @date 2020-11-19 21:01:31
 */
@RestController
@Api(tags = "文件下载接口")
@RequestMapping("/File")
public class FileDownloadController {
    @GetMapping("/FileDownload")
    public void fileDownload(String fileName, HttpServletResponse response) throws Exception {
        FileDownload.logDownload(fileName,response);
    }
}