package com.cqut.stu.pai.service.impl;

import com.cqut.stu.pai.entity.response.LogVo;
import com.cqut.stu.pai.mapper.AdminMapper;
import com.cqut.stu.pai.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 石益然
 * @program: pai
 * @description:
 * @date 2020-11-28 19:17:10
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public void teacherLog(LogVo logVo) {
        adminMapper.teacherLog(logVo);
    }

    @Override
    public void studentLog(LogVo logVo) {
        adminMapper.studentLog(logVo);
    }
}