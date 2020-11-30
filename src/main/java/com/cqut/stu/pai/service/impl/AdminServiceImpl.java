package com.cqut.stu.pai.service.impl;

import com.cqut.stu.pai.entity.response.DataVo;
import com.cqut.stu.pai.entity.response.LogVo;
import com.cqut.stu.pai.entity.response.StudentVo;
import com.cqut.stu.pai.entity.response.TeacherVo;
import com.cqut.stu.pai.mapper.AdminMapper;
import com.cqut.stu.pai.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 石益然
 * @program: pai
 * @description: 超级管理员业务类
 * @date 2020-11-14 17:47:18
 */
@Service
public class AdminServiceImpl implements AdminService, UserDetailsService {
    @Autowired
    AdminMapper adminMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public DataVo<TeacherVo> getTeacherJson(Integer page, Integer limit) {
        List<TeacherVo> list =  adminMapper.getTeacherJson((page-1)*limit,limit);
        DataVo<TeacherVo> dataVo = new DataVo<>(0,"ok",list.size());
        dataVo.setData(list);
        return dataVo;
    }

    @Override
    public DataVo<Object> locked(Integer id) {
        DataVo<Object> dataVo = new DataVo<Object>(200,"ok");
        Boolean state = adminMapper.getLocked(id);
        if (state){
            adminMapper.locked(id,0);
        }else {
            adminMapper.locked(id,1);
        }
        return dataVo;
    }

    @Override
    public DataVo<?> sLocked(Integer id) {
        DataVo<Object> dataVo = new DataVo<Object>(200,"ok");
        Boolean state = adminMapper.getStuLocked(id);
        if (state){
            adminMapper.sLocked(id,0);
        }else {
            adminMapper.sLocked(id,1);
        }
        return dataVo;
    }

    @Override
    public DataVo<StudentVo> getStudentJson(Integer page, Integer limit) {
        List<StudentVo> list = adminMapper.getStudentJson((page-1)*limit,limit);
        DataVo<StudentVo> dataVo = new DataVo<>(0,"ok",list.size());
        dataVo.setData(list);
        return dataVo;
    }

    @Override
    public DataVo<?> getTeacherLog(Integer page, Integer limit) {
        List<LogVo> list = adminMapper.getTeacherLog((page-1)*limit,limit);
        DataVo<LogVo> dataVo = new DataVo<>(0,"ok",list.size());
        dataVo.setData(list);
        return dataVo;
    }

    @Override
    public DataVo<?> getStudentLog(Integer page, Integer limit) {
        List<LogVo> list = adminMapper.getStudentLog((page-1)*limit,limit);
        DataVo<LogVo> dataVo = new DataVo<>(0,"ok",list.size());
        dataVo.setData(list);
        return dataVo;
    }
}