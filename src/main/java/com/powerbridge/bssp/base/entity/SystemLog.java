package com.powerbridge.bssp.base.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("system_log")
public class SystemLog implements Serializable{

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    @TableId(value = "ID")
	private String id;  //主键id

    @TableField(value = "APPLY_ID")
    private String applyId;//应用id


    @TableField(value = "METHOD")
    private String method;//执行方法

    @TableField(value = "REPONES_DATA")
    private String reponesData;//响应时间

    @TableField(value = "IP")
    private String ip; //ip

    @TableField(value = "DATA")
    private Date data; //执行时间

    @TableField(value = "TYPE")
    private Integer type;  //问题类型

    @TableField(value = "COMMITE")
    private String commite;  //执行描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId == null ? null : applyId.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getReponesData() {
        return reponesData;
    }

    public void setReponesData(String reponesData) {
        this.reponesData = reponesData;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCommite() {
        return commite;
    }

    public void setCommite(String commite) {
        this.commite = commite == null ? null : commite.trim();
    }
}