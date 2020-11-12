package com.cqut.stu.pai.entity;

/**
 * @author 石益然
 * @program: ketangpai
 * @description: 这是一个封装自定义返回消息的JSON类
 * @date 2020-11-11 11:15:11
 */
public class JsonData {
    //错误码
    private int code;
    //返回的数据
    private Object data;
    //返回数据的描述
    private String msg;

    //构造方法
    public JsonData() {
    }

    public JsonData(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public JsonData(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static JsonData buildSuccess(Object data) {
        return new JsonData(0, data);
    }

    public static JsonData buildError(String msg) {
        return new JsonData(-1, "", msg);
    }

    public static JsonData buildError(int code, String msg) {
        return new JsonData(code, "", msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}