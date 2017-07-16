package com.powerbridge.bssp.approval.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：AppSysWorklog
 * 类描述：AppSysWorklog 表实体类
 * 创建人：haihuihuang
 * 创建时间：2017年5月9日 上午10:41:00
 */
@TableName("app_sys_worklog")
public class AppSysWorklog implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID")
    private String ID;

    /**
     * 任务编号
     */
    @TableField(value = "TASK_NO")
    private String taskNo;

    /**
     * 模块类型
     */
    @TableField(value = "SUBSYS")
    private String subSys;

    /**
     * 业务类型
     */
    @TableField(value = "BIZ_TYPE")
    private String bizType;

    /**
     * 单据编号
     */
    @TableField(value = "DOC_NO")
    private String docNo;

    /**
     * 通道号
     */
    @TableField(value = "CHANNEL")
    private String channel;

    /**
     * 通道描述
     */
    @TableField(value = "CHANNEL_DESC")
    private String channelDesc;

    /**
     * 岗位编码
     */
    @TableField(value = "POS_CODE")
    private String posCode;

    /**
     * 操作人编码
     */
    @TableField(value = "PROC_OP_ID")
    private String procOpId;

    /**
     * 下一步骤编码
     */
    @TableField(value = "NEXT_STEP_CODE")
    private String nextStepCode;

    /**
     * 下一岗位代码
     */
    @TableField(value = "NEXT_POS_CODE")
    private String nextPosCode;

    /**
     * 单据类型
     */
    @TableField(value = "DOC_TYPE")
    private String docType;

    /**
     * INIT_HANDLE
     */
    @TableField(value = "INIT_HANDLE")
    private String initHandle;

    /**
     * 处理结果
     */
    @TableField(value = "OP_NOTES")
    private String opNotes;

    /**
     * 处理日期
     */
    @TableField(value = "PROC_DATE")
    private Date procDate;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getSubSys() {
        return subSys;
    }

    public void setSubSys(String subSys) {
        this.subSys = subSys;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getProcOpId() {
        return procOpId;
    }

    public void setProcOpId(String procOpId) {
        this.procOpId = procOpId;
    }

    public String getNextStepCode() {
        return nextStepCode;
    }

    public void setNextStepCode(String nextStepCode) {
        this.nextStepCode = nextStepCode;
    }

    public String getNextPosCode() {
        return nextPosCode;
    }

    public void setNextPosCode(String nextPosCode) {
        this.nextPosCode = nextPosCode;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getInitHandle() {
        return initHandle;
    }

    public void setInitHandle(String initHandle) {
        this.initHandle = initHandle;
    }

    public String getOpNotes() {
        return opNotes;
    }

    public void setOpNotes(String opNotes) {
        this.opNotes = opNotes;
    }

    public Date getProcDate() {
        return procDate;
    }

    public void setProcDate(Date procDate) {
        this.procDate = procDate;
    }
}