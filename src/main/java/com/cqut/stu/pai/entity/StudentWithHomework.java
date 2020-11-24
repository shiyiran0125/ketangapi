package com.cqut.stu.pai.entity;

import org.springframework.stereotype.Component;

/**
 * @author 石益然
 * @program: pai
 * @description: 学生作业类
 * @date 2020-11-22 21:01:17
 */
@Component
public class StudentWithHomework {
    private Integer H_id;
    private Integer S_id;
    private Integer State;
    private String  Answer;
    private Integer Score;

    public StudentWithHomework(){

    }

    public StudentWithHomework(Integer h_id, Integer s_id, Integer state) {
        H_id = h_id;
        S_id = s_id;
        State = state;
    }

    public Integer getH_id() {
        return H_id;
    }

    public void setH_id(Integer h_id) {
        H_id = h_id;
    }

    public Integer getS_id() {
        return S_id;
    }

    public void setS_id(Integer s_id) {
        S_id = s_id;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }
}