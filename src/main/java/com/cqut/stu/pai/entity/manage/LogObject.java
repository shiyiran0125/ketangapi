package com.cqut.stu.pai.entity.manage;

/**
 * @author 石益然
 * @program: pai
 * @description: 登录日志
 * @date 2020-11-24 16:06:39
 */
public class LogObject {
    private String title;
    private String content;
    private String url;

    public LogObject(String title, String content, String url) {
        this.title = title;
        this.content = content;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}