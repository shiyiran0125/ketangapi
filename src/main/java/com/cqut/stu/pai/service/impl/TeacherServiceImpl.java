package com.cqut.stu.pai.service.impl;

import com.cqut.stu.pai.entity.*;
import com.cqut.stu.pai.entity.response.CourseWithTeacher;
import com.cqut.stu.pai.entity.response.MenberOfStudent;
import com.cqut.stu.pai.entity.response.MenberOfTeacher;
import com.cqut.stu.pai.entity.response.StuHomework;
import com.cqut.stu.pai.mapper.TeacherMapper;
import com.cqut.stu.pai.service.TeacherService;
import com.cqut.stu.pai.util.FileUpload;
import com.cqut.stu.pai.util.GenerateCourseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 石益然
 * @program: pai
 * @description: 教师业务类
 * @date 2020-11-14 17:49:00
 */
@Service
public class TeacherServiceImpl implements TeacherService, UserDetailsService {
    @Autowired
    TeacherMapper teacherMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return teacherMapper.loadUserByUsername(username);
    }

    @Transactional
    @Override
    public JsonData register(Teacher teacher) {
        Teacher Oldteacher = teacherMapper.loadUserByUsername(teacher.getUsername());
        int havaExist = teacherMapper.haveExistInStu(teacher.getUsername());
        if (Oldteacher == null && havaExist < 1) {
            teacherMapper.register(teacher);
            teacher.setPassword(null);
            JsonData jsonData = JsonData.success("注册成功",teacher);
            return jsonData;
        }else {
            JsonData jsonData = JsonData.Error("该账号已经存在");
            return jsonData;
        }

    }

    /**
     * @param userId
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 获取该教师课程列表
     * @date 2020/11/18 10:04
     */
    @Override
    public JsonData getCourseList(String username) {
        Teacher user = (Teacher) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<CourseWithTeacher> list = teacherMapper.getCourseList(user.getId());
        List<CourseWithTeacher> topList = new ArrayList<>();
        List<CourseWithTeacher> dList = new ArrayList<>();
        List<Map<String,String>> ArList = new ArrayList<>();
        for (CourseWithTeacher ct:list
             ) {
            if (ct.getArchive()==1){
                Map<String,String> map = new HashMap<>();
                map.put("C_code",ct.getC_code());
                map.put("C_name",ct.getC_name());
                ArList.add(map);
            } else if (ct.getTop()==0){
                dList.add(ct);
            }else {
                topList.add(ct);
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("topCourse",topList);
        map.put("dCourse",dList);
        map.put("guidangCourse",ArList);
        JsonData jsonData = new JsonData(200,map,"success");
        return jsonData;
    }


    @Transactional
    @Override
    public JsonData addCourse(Course course) {
        String C_code = GenerateCourseCode.generateCourseCode();
        int count =teacherMapper.getCountOfCourse(C_code);
        while (count>0){
            C_code = GenerateCourseCode.generateCourseCode();
            count =teacherMapper.getCountOfCourse(C_code);
        }
        course.setC_code(C_code);
        teacherMapper.addCourse(course);
        //课程成功添加,继续给教室课程表添加数据
        Teacher user = (Teacher) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getId();
        teacherMapper.addTeaCou(C_code,userId);
        JsonData jsonData = new JsonData(200,"","success");
        return jsonData;
    }

    /**
     * @param C_code
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 删除课程
     * @date 2020/11/18 23:02
     */
    @Transactional
    @Override
    public JsonData deleteCourse(String C_code) {
        teacherMapper.deleteCt(C_code);
        teacherMapper.deleteCourse(C_code);
        JsonData jsonData = new JsonData(200,"","success");
        return jsonData;
    }

    /**
     * @param course
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 修改课程
     * @date 2020/11/18 23:03
     */
    @Override
    public JsonData modifyCourse(Course course) {
        teacherMapper.modifyCourse(course);
        JsonData jsonData = new JsonData(200,"","success");
        return jsonData;
    }

    /**
     * @param Top
	 * @param C_code
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 置顶与取消
     * @date 2020/11/19 9:34
     */
    @Override
    public JsonData topCourse(Integer Top, String C_code) {
        Teacher user = (Teacher) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Top==1){
            teacherMapper.topCourse(user.getId(),C_code);
        }else {
            teacherMapper.notTopCourse(user.getId(),C_code);
        }
        JsonData jsonData = new JsonData(200,"","success");
        return jsonData;
    }

    /**
     * @param Use
	 * @param C_code
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 加课码启用与停用
     * @date 2020/11/19 9:35
     */
    @Transactional
    @Override
    public JsonData courseCode(Integer Use, String C_code) {
        if (Use==1){
            teacherMapper.courseCodeUse(C_code);
        }else {
            teacherMapper.courseCodeStop(C_code);
        }
        JsonData jsonData = new JsonData(200,"","success");
        return jsonData;
    }

    @Transactional
    @Override
    public JsonData guiDangCourse(Integer Archive, String C_code) {
        Teacher user = (Teacher) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Archive==1){
            teacherMapper.guiDangCourseUse(user.getId(),C_code);
        }else {
            teacherMapper.guiDangCourseStop(user.getId(),C_code);
        }
        JsonData jsonData = new JsonData(200,"","success");
        return jsonData;
    }

    @Transactional
    @Override
    public JsonData joinCourse(String C_code) {
        Teacher user = (Teacher) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int count = teacherMapper.getCountOfCourse(C_code);
        JsonData jsonData =null;
        if (count<0){
            jsonData = new JsonData(404,"","error");
        }else {
            teacherMapper.addTeaCou(C_code,user.getId());
            jsonData = new JsonData(200,"","success");
        }
        return jsonData;
    }

    /**
     * @param C_code
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 获取某课程的所有作业
     * @date 2020/11/19 14:58
     */
    @Override
    public JsonData getHomeworkList(String C_code) {
        List<Homework> list = teacherMapper.getHomeworkList(C_code);
        Map<String,List<Homework>> map = new HashMap<>();
        map.put("homeworkList",list);
        return new JsonData(200,map,"success");
    }

    /**
     * @param file
	 * @param homework
	 * @param request
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 发布作业
     * @date 2020/11/19 17:57
     */
    @Transactional
    @Override
    public JsonData addHomework(MultipartFile file, Homework homework, HttpServletRequest request) {
        String Url = FileUpload.uploadFile(file,request);
        List<Integer> list = teacherMapper.getAllStuInCourse(homework.getC_code());
        homework.setAnnex(Url);
        homework.setSubmitted(0);
        homework.setUnsubmitted(list.size());
        homework.setUncorrected(0);
        homework.setCorrected(0);
        teacherMapper.addHomework(homework);
        List<StudentWithHomework> listStuWithHom = new ArrayList<>();
        for (Integer S_id:list
             ) {
            listStuWithHom.add(new StudentWithHomework(homework.getH_id(),S_id,0));
        }
        teacherMapper.insertToStuWor(listStuWithHom);
        return new JsonData(200,"","success");
    }

    /**
     * @param homework
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 修改作业
     * @date 2020/11/19 22:35
     */
    @Transactional
    @Override
    public JsonData modifyHomework(Homework homework) {
        teacherMapper.modifyHomework(homework);
        return new JsonData(200,"","success");
    }

    @Transactional
    @Override
    public JsonData deleteHomework(String H_id) {
        teacherMapper.deleteStuHom(H_id);
        teacherMapper.deleteHomework(H_id);
        return new JsonData(200,"","success");
    }

    /**
     * @param H_id
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 获取某作业下所有学生的提交
     * @date 2020/11/20 17:16
     */
    @Override
    public JsonData getStuHomework(String H_id) {
        List<StuHomework> list = teacherMapper.getStuHomework(H_id);
        Map<String,List<StuHomework>> map = new HashMap<>();
        map.put("stuHomeworks",list);
        return new JsonData(200,map,"success");
    }

    /**
     * @param C_code
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 根据课程ID获取某课程的所有老师和学生
     * @date 2020/11/21 15:34
     */
    @Override
    public JsonData getStuTea(String C_code) {
        List<MenberOfTeacher> listT = teacherMapper.getTeaMen(C_code);
        List<MenberOfStudent> listS = teacherMapper.getStuMen(C_code);
        Map<String,Object> map = new HashMap<>();
        map.put("Teachers",listT);
        map.put("Students",listS);
        return new JsonData(200,map,"success");
    }


    @Transactional
    @Override
    public JsonData modifyStudent(MenberOfStudent student){
        int count = teacherMapper.getStuBySid(student.getSid(),student.getUsername());
        if (count>0){
            return new JsonData(400,"","error");
        }
        teacherMapper.modifyStudent(student);
        return new JsonData(200,"","success");
    }

    @Transactional
    @Override
    public JsonData deleteStudent(String username,String C_code){
        teacherMapper.deleteStuHome(username,C_code);
        teacherMapper.deleteStudent(username,C_code);
        teacherMapper.updateHomework(C_code);
        return new JsonData(200,"","success");
    }

    public JsonData modifyScore(Integer H_id, String sid,Integer Score){
        teacherMapper.modifyScore(H_id,sid,Score);
        String C_code = teacherMapper.getCodeByHid(H_id);
        teacherMapper.updateHomework(C_code);
        return new JsonData(200,"","success");
    }
}