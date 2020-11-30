package com.cqut.stu.pai.mapper;
import com.cqut.stu.pai.entity.response.DataVo;
import com.cqut.stu.pai.entity.response.LogVo;
import com.cqut.stu.pai.entity.response.StudentVo;
import com.cqut.stu.pai.entity.response.TeacherVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    public List<TeacherVo> getTeacherJson(Integer page, Integer limit);
    public List<StudentVo> getStudentJson(Integer page, Integer limit);
    public Boolean getLocked(Integer id);
    public Boolean getStuLocked(Integer id);
    public void locked(Integer id,Integer state);
    public void sLocked(Integer id,Integer state);
    public List<LogVo> getTeacherLog(Integer page, Integer limit);
    public List<LogVo> getStudentLog(Integer page, Integer limit);

    public void teacherLog(LogVo logVo);
    public void studentLog(LogVo logVo);
}
