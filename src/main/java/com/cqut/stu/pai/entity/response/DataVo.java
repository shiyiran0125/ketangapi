package com.cqut.stu.pai.entity.response;

import java.util.List;

/**
 * @author 石益然
 * @program: pai
 * @description: 返回给管理后台的数据
 * @date 2020-11-26 22:40:24
 */

public class DataVo<T> {
    private Integer code;
    private String  msg;
    private Integer count;
    private List<T> data;

    public DataVo(){

    }

    public DataVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public DataVo(Integer code, String msg, Integer count) {
        this.code = code;
        this.msg = msg;
        this.count = count;
    }

    public DataVo(Integer code, String msg, Integer count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}