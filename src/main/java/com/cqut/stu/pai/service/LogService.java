package com.cqut.stu.pai.service;

import com.cqut.stu.pai.entity.response.LogVo;

public interface LogService {
    public void teacherLog(LogVo logVo);
    public void studentLog(LogVo logVo);
}
