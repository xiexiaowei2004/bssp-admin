package com.powerbridge.bssp.common.Bean;

import java.io.Serializable;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：PullDown
 * 类描述：下拉选择
 * 创建人：haihuihuang
 * 创建时间：2017/5/20 23:08
 */
public class PullDown implements Serializable{

    private static final long serialVersionUID = 8574885830477217273L;

    private String id;

    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
