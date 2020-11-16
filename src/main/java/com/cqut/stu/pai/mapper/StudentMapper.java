package com.cqut.stu.pai.mapper;

import com.cqut.stu.pai.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
    //注册
    public void register(Student student);

    //登录
    Student loadUserByUsername(String username);
}
