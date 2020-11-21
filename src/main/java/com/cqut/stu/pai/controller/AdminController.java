package com.cqut.stu.pai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 石益然
 * @program: pai
 * @description: 超级管理员端接口
 * @date 2020-11-15 17:15:26
 */
@Controller
@Api(tags = "超级管理员端接口")
@RequestMapping("/admin")
public class AdminController {
    @ApiOperation(value="超级管理员",notes = "管理用户，用户信息")
    @PostMapping("/index")
    public String toIndex(){
        return "index";
    }
}