package com.cqut.stu.pai.controller;

import com.cqut.stu.pai.entity.Homework;
import com.cqut.stu.pai.entity.JsonData;
import com.cqut.stu.pai.entity.Student;
import com.cqut.stu.pai.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 石益然
 * @program: pai
 * @description: 学生端接口
 * @date 2020-11-15 17:15:03
 */
@RestController
@Api(tags = "学生端接口")
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @ApiOperation(value="注册用户",notes = "注册用户，传入用户信息")
    @PostMapping("/register")
    public JsonData register(@RequestBody Student student){
         return studentService.register(student);
    }

    /**
     * @param username
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 获取用户课程列表
     * @date 2020/11/16 20:24
     */
    @ApiOperation(value="获取课程列表",notes = "返回该教师的所有课程，包括置顶课程，非置顶课程，归档课程")
    @ApiImplicitParam(name = "username",value = "用户名",required = true)
    @GetMapping("/GetCourseList/{username}")
    public JsonData getCourseList(@PathVariable String username){
        return studentService.getCourseList(username);
    }

    @ApiOperation(value="加入课程",notes = "根据加课码加入课程")
    @ApiImplicitParam(name = "C_code",value = "课程码",required = true)
    @PostMapping("/JoinCourse")
    public JsonData joinCourse(@RequestBody Map<String,String> C_code){
        return studentService.joinCourse(C_code.get("C_code"));
    }

    @ApiOperation(value="课程置顶与取消",notes = "根据加课码和教师ID把课程置顶与取消")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Top",value = "是否置顶",required = true),
            @ApiImplicitParam(name = "C_code",value = "加课码",required = true)
    })
    @PutMapping("/TopCourse/{Top}/{C_code}")
    public JsonData topCourse(@PathVariable("Top") Integer Top, @PathVariable("C_code") String C_code){
        return studentService.topCourse(Top,C_code);
    }

    @ApiOperation(value="课程归档与取消",notes = "根据加课码和选择把课程归档与取消")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Archive",value = "是否归档",required = true),
            @ApiImplicitParam(name = "C_code",value = "加课码",required = true)
    })
    @PutMapping("/GuiDangCourse/{Archive}/{C_code}")
    public JsonData guiDangCourse(@PathVariable("Archive") Integer Archive, @PathVariable("C_code") String C_code){
        return studentService.guiDangCourse(Archive,C_code);
    }

    @ApiOperation(value="退出课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "学生用户名",required = true),
            @ApiImplicitParam(name = "C_code",value = "加课码",required = true)
    })
    @DeleteMapping("/RetireCourse/{username}/{C_code}")
    public JsonData deleteStudent(@PathVariable("username") String username,@PathVariable("C_code") String C_code){
        return studentService.deleteStudent(username,C_code);
    }

    @ApiOperation(value="获取课程所有作业",notes = "根据课码获取所有课程作业")
    @ApiImplicitParam(name = "C_code",value = "课程码",required = true)
    @GetMapping("/GetHomeworkList/{C_code}")
    public JsonData getHomeworkList(@PathVariable String C_code){
        JsonData jsonData = studentService.getHomeworkList(C_code);
        return jsonData;
    }


    @ApiOperation(value="提交作业",notes = "根据作业码提交课程作业")
    @PostMapping("/StuFileUpload")
    public JsonData stuFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("H_id") Integer H_id){
        return studentService.commitHomework(file,H_id);
    }

    @ApiOperation(value="获取某课程的所有老师和学生",notes = "根据课程ID获取某课程的所有老师和学生")
    @ApiImplicitParam(name = "C_code",value = "加课码",required = true)
    @GetMapping("/GetStuTea/{C_code}")
    public JsonData getStuTea(@PathVariable("C_code") String C_code){
        return studentService.getStuTea(C_code);
    }
}