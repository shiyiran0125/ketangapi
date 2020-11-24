package com.cqut.stu.pai.service.impl;

import com.cqut.stu.pai.entity.*;
import com.cqut.stu.pai.entity.response.*;
import com.cqut.stu.pai.mapper.StudentMapper;
import com.cqut.stu.pai.service.StudentService;
import com.cqut.stu.pai.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author 石益然
 * @program: pai
 * @description: 学生业务类
 * @date 2020-11-14 17:48:16
 */
@Service
public class StudentServiceImpl implements StudentService, UserDetailsService {
    @Autowired
    StudentMapper studentMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return studentMapper.loadUserByUsername(username);
    }

    /**
     * @param student
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 注册
     * @date 2020/11/23 17:18
     */
    @Transactional
    @Override
    public JsonData register(Student student) {
        Student OldStudent = studentMapper.loadUserByUsername(student.getUsername());
        int haveExist = studentMapper.haveExistInTea(student.getUsername());
        if (OldStudent == null && haveExist==0) {
            studentMapper.register(student);
            student.setPassword(null);
            JsonData jsonData = JsonData.success("注册成功",student);
            return jsonData;
        }else {
            JsonData jsonData = JsonData.Error("该账号已经存在");
            return jsonData;
        }
    }

    /**
     * @param username
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 获取所有课程
     * @date 2020/11/23 17:18
     */
    @Override
    public JsonData getCourseList(String username) {
        Student user = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<CourseWithStudent> list = studentMapper.getCourseList(user.getId());
        List<CourseWithStudent> topList = new ArrayList<>();
        List<CourseWithStudent> dList = new ArrayList<>();
        List<Map<String,String>> ArList = new ArrayList<>();
        for (CourseWithStudent ct:list
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

    /**
     * @param C_code
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 加课
     * @date 2020/11/23 15:46
     */
    @Transactional
    @Override
    public JsonData joinCourse(String C_code) {
        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int count = studentMapper.getCountOfCourse(C_code);
        JsonData jsonData =null;
        if (count<=0){
            jsonData = new JsonData(404,"","error");
        }else {
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            studentMapper.addStuCou(C_code,student.getId(),formatter.format(localDateTime));
            List<Integer> list = studentMapper.getAllHomeIdOfCourse(C_code);
            List<StudentWithHomework> listStuWithHom = new ArrayList<>();
            for (Integer item:list
                 ) {
                listStuWithHom.add(new StudentWithHomework(item,student.getId(),0));
            }
            studentMapper.insertToStuWor(listStuWithHom);
            studentMapper.updateUnSubmitted(list);
            jsonData = new JsonData(200,"","success");
        }
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
        Student user = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Top==1){
            studentMapper.topCourse(user.getId(),C_code);
        }else {
            studentMapper.notTopCourse(user.getId(),C_code);
        }
        JsonData jsonData = new JsonData(200,"","success");
        return jsonData;
    }

    /**
     * @param Archive
	 * @param C_code
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 归档课程
     * @date 2020/11/23 17:18
     */
    @Transactional
    @Override
    public JsonData guiDangCourse(Integer Archive, String C_code) {
        Student user = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Archive==1){
            studentMapper.guiDangCourseUse(user.getId(),C_code);
        }else {
            studentMapper.guiDangCourseStop(user.getId(),C_code);
        }
        JsonData jsonData = new JsonData(200,"","success");
        return jsonData;
    }

    /**
     * @param username
	 * @param C_code
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 退课
     * @date 2020/11/23 17:15
     */
    @Transactional
    @Override
    public JsonData deleteStudent(String username,String C_code){
        studentMapper.deleteStuHome(username,C_code);
        List<Integer> list = studentMapper.getAllHomeIdOfCourse(C_code);
        studentMapper.deleteStudent(username,C_code);
        studentMapper.updateHomework(C_code);
        return new JsonData(200,"","success");
    }

    /**
     * @param C_code
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 获取某课程的所有作业
     * @date 2020/11/19 14:58
     */
    @Override
    public JsonData getHomeworkList(String C_code) {
        Student user = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<StudentAllHomeworkInCourse> list = studentMapper.getHomeworkList(C_code,user.getId());
        Map<String,List<StudentAllHomeworkInCourse>> map = new HashMap<>();
        map.put("homeworkList",list);
        return new JsonData(200,map,"success");
    }

    /**
     * @param file
	 * @param H_id
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 提交作业
     * @date 2020/11/23 17:17
     */
    @Transactional
    @Override
    public JsonData commitHomework(MultipartFile file, Integer H_id) {
        Student user = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String url = FileUpload.uploadFile(file,null);
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        studentMapper.commitHomework(url,H_id,formatter.format(localDateTime),user.getId());
        studentMapper.modifySubmit(H_id);
        return new JsonData(200,"","success");
    }

    /**
     * @param C_code
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 根据课程ID获取某课程的所有老师和学生
     * @date 2020/11/21 15:34
     */
    @Override
    public JsonData getStuTea(String C_code) {
        List<MenberOfTeacher> listT = studentMapper.getTeaMen(C_code);
        List<MenberOfStudent> listS = studentMapper.getStuMen(C_code);
        Map<String,Object> map = new HashMap<>();
        map.put("Teachers",listT);
        map.put("Students",listS);
        return new JsonData(200,map,"success");
    }
}