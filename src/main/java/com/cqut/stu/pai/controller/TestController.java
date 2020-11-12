package com.cqut.stu.pai.controller;

import com.cqut.stu.pai.entity.User;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "测试接口")
@RequestMapping("/test")
public class TestController {
    /**
     * @param
     * @return java.lang.String
     * @describe:
     * @date 2020/11/11 15:19
     */
    @ApiOperation(value = "简短描述方法的作用",notes = "备注方法的详细作用")
    @ApiImplicitParam(paramType = "path",name = "id",value = "参数的描述信息",required = true)
    @GetMapping("/hello{id}")
    @ResponseBody
    public String getHello(@PathVariable Integer id){
        return "hello:" + id;
    }

    /**
     * @param userId
     * @return java.lang.String
     * @describe:
     * @date 2020/11/11 15:19
     */
    @ApiOperation(value ="访问后台管理",notes = "通过该接口访问后台管理界面入口")
    @ApiResponses({
            @ApiResponse(code = 200,message = "成功"),
            @ApiResponse(code = 500,message = "失败")
    })
    @GetMapping("/login{userId}")
    public String toAdmin(@PathVariable Integer userId){
        return "index:" + userId;
    }

    /**
     * @param username
	 * @param addr
     * @return java.lang.String
     * @describe:
     * @date 2020/11/11 15:19
     */
    @ApiOperation(value = "添加用户",notes = "添加一个用户，传入用户姓名和地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "username",value = "用户名",required = true,defaultValue = "sy"),
            @ApiImplicitParam(paramType = "query",name = "addr",value = "用户地址",required = true,defaultValue = "重庆市")
    })
    @PostMapping("/user")
    public String addUser(@RequestParam String username,@RequestParam String addr){
        return username + ":" + addr;
    }

    /**
     * @param user
     * @return java.lang.String
     * @describe:
     * @date 2020/11/11 15:19
     */
    @ApiOperation(value="修改用户",notes = "修改用户，传入用户信息")
    @PutMapping("/user")
    public String updateUser(@RequestBody User user){
        return user.toString();
    }

}
