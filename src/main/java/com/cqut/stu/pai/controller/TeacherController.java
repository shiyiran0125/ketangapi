package com.cqut.stu.pai.controller;

import com.cqut.stu.pai.entity.Course;
import com.cqut.stu.pai.entity.Homework;
import com.cqut.stu.pai.entity.JsonData;
import com.cqut.stu.pai.entity.Teacher;
import com.cqut.stu.pai.entity.response.MenberOfStudent;
import com.cqut.stu.pai.service.TeacherService;
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
 * @description: 教师端接口
 * @date 2020-11-15 17:14:27
 */
@RestController
@Api(tags = "教师端接口")
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @ApiOperation(value="注册用户",notes = "注册用户，传入用户信息")
    @PostMapping("/register")
    public JsonData register(@RequestBody Teacher teacher){
        return teacherService.register(teacher);
    }

    /**
     * @param username
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 获取用户课程列表
     * @date 2020/11/16 20:24
     */
    @ApiOperation(value="获取课程列表",notes = "返回该教师的所有课程，包括置顶课程，非置顶课程，归档课程")
    @ApiImplicitParam(paramType = "query",name = "username",value = "用户名",required = true)
    @GetMapping("/GetCourseList")
    public JsonData getCourseList(@RequestParam String username){
        return teacherService.getCourseList(username);
    }

    @ApiOperation(value="修改课程",notes = "修改课程")
    @PutMapping("/ModifyCourse")
    public JsonData modifyCourse(@RequestBody Course course){
        return teacherService.modifyCourse(course);
    }

    @ApiOperation(value="添加课程",notes = "添加课程")
    @PostMapping("/AddCourse")
    public JsonData addCourse(@RequestBody Course course){
        return teacherService.addCourse(course);
    }


    @ApiOperation(value="删除课程",notes = "根据加课码删除课程")
    @ApiImplicitParam(paramType = "query",name = "C_code",value = "加课码",required = true)
    @DeleteMapping("/DeleteCourse")
    public JsonData deleteCourse(@RequestParam String C_code){
        return teacherService.deleteCourse(C_code);
    }


    @ApiOperation(value="加课码停用及启用",notes = "根据加课码管理")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "Use",value = "是否启用",required = true),
            @ApiImplicitParam(paramType = "query",name = "C_code",value = "加课码",required = true)
    })
    @PutMapping("/CourseCode")
    public JsonData courseCode(@RequestParam Integer Use, @RequestParam String C_code){
        return teacherService.courseCode(Use,C_code);
    }

    @ApiOperation(value="课程置顶与取消",notes = "根据加课码和教师ID把课程置顶与取消")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Top",value = "是否置顶",required = true),
            @ApiImplicitParam(name = "C_code",value = "加课码",required = true)
    })
    @PutMapping("/TopCourse")
    public JsonData topCourse(@RequestParam Integer Top, @RequestParam String C_code){
        return teacherService.topCourse(Top,C_code);
    }


    @ApiOperation(value="课程归档与取消",notes = "根据加课码和选择把课程归档与取消")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Archive",value = "是否归档",required = true),
            @ApiImplicitParam(name = "C_code",value = "加课码",required = true)
    })
    @PutMapping("/GuiDangCourse")
    public JsonData guiDangCourse(@RequestParam Integer Archive, @RequestParam String C_code){
        return teacherService.guiDangCourse(Archive,C_code);
    }

    @ApiOperation(value="加入课程",notes = "根据加课码加入课程")
    @ApiImplicitParam(name = "C_code",value = "课程码",required = true)
    @PostMapping("/JoinCourse")
    public JsonData joinCourse(@RequestBody Map<String,String> C_code){
        return teacherService.joinCourse(C_code.get("C_code"));
    }

    @ApiOperation(value="获取课程所有作业",notes = "根据课码获取所有课程作业")
    @ApiImplicitParam(name = "C_code",value = "课程码",required = true)
    @GetMapping("/GetHomeworkList")
    public JsonData getHomeworkList(@RequestParam String C_code){
        JsonData jsonData = teacherService.getHomeworkList(C_code);
        return jsonData;
    }

    @ApiOperation(value="创建作业",notes = "根据课码创建课程作业")
    @PostMapping("/AddHomework")
    public JsonData addHomework(@RequestParam("file") MultipartFile file, Homework homework, HttpServletRequest request){
        return teacherService.addHomework(file,homework,request);
    }

    @ApiOperation(value="修改作业",notes = "根据课码修改课程作业")
    @PutMapping("/ModifyHomework")
    public JsonData modifyHomework(@RequestBody Homework homework){
        return teacherService.modifyHomework(homework);
    }

    @ApiOperation(value="删除作业",notes = "根据作业ID删除课程作业")
    @ApiImplicitParam(name = "H_id",value = "作业码",required = true)
    @DeleteMapping("/DeleteHomework")
    public JsonData deleteHomework(@RequestParam String H_id){
        return teacherService.deleteHomework(H_id);
    }

    @ApiOperation(value="获取所有学生的一次作业",notes = "根据作业ID获取所有学生的一次作业")
    @ApiImplicitParam(name = "H_id",value = "作业码",required = true)
    @GetMapping("/GetStuHomework")
    public JsonData getStuHomework(@RequestParam String H_id){
        return teacherService.getStuHomework(H_id);
    }

    @ApiOperation(value="获取某课程的所有老师和学生",notes = "根据课程ID获取某课程的所有老师和学生")
    @ApiImplicitParam(name = "C_code",value = "加课码",required = true)
    @GetMapping("/GetStuTea/{C_code}")
    public JsonData getStuTea(@PathVariable("C_code") String C_code){
        return teacherService.getStuTea(C_code);
    }

    @ApiOperation(value="修改作业",notes = "根据课码修改课程作业")
    @PutMapping("/ModifyStudent")
    public JsonData modifyStudent(@RequestBody MenberOfStudent student){
        return teacherService.modifyStudent(student);
    }

    @ApiOperation(value="删除课程下的学生")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "学生用户名",required = true),
            @ApiImplicitParam(name = "C_code",value = "加课码",required = true)
    })
    @DeleteMapping("/DeleteStudent/{username}/{C_code}")
    public JsonData deleteStudent(@PathVariable("username") String username,@PathVariable("C_code") String C_code){
        return teacherService.deleteStudent(username,C_code);
    }

}