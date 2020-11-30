package com.cqut.stu.pai.service;

import com.cqut.stu.pai.entity.response.DataVo;
import com.cqut.stu.pai.entity.response.StudentVo;
import com.cqut.stu.pai.entity.response.TeacherVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface AdminService {
    public DataVo<TeacherVo> getTeacherJson(Integer page,Integer limit);

    public DataVo<?> locked(Integer id);

    public DataVo<?> sLocked(Integer id);

    public DataVo<StudentVo> getStudentJson(Integer page, Integer limit);

    public DataVo<?> getTeacherLog(Integer page, Integer limit);

    public DataVo<?> getStudentLog(Integer page, Integer limit);
}
