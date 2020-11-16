package com.cqut.stu.pai.mapper;

import com.cqut.stu.pai.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {
    //注册
    public void register(Teacher teacher);

    //登录
    Teacher loadUserByUsername(String username);
}
