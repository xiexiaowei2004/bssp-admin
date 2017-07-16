package com.powerbridge.bssp.edi.entity; /***********************************************************************
 * Module:  EdiMessageLog.java
 * Author:  宋轲
 * Purpose: Defines the Class EdiMessageLog
 ***********************************************************************/

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 报文日志
 *
 * @pdOid 8dbab969-3c05-411a-9726-50af5174adf9
 */
@TableName("edi_message_log")
public class EdiMessageLog implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     *
     * @pdOid 357b94e3-79cf-4614-9cf8-4c8f69aed3e3
     */
    @TableId("UID")
    public String uid;
    /**
     * 编号
     *
     * @pdOid 2f269e80-2ef7-4e88-898c-0ecb1651486d
     */
    @TableField("SERIAL_NO")
    public String serialNo;
    /**
     * 监管场所
     *
     * @pdOid 4fd40295-14de-414f-9f7b-920200212b40
     */
    @TableField("AREA_CODE")
    public String areaCode;
    /**
     * 单据类型
     *
     * @pdOid 9bc52f50-9706-4175-8d8f-d66a60b8061e
     */
    @TableField("DOC_TYPE")
    public String docType;
    /**
     * 业务类型
     *
     * @pdOid 7e937f45-23ff-4cb6-9f03-80bec4a5a7e3
     */
    @TableField("BIZ_TYPE")
    public String bizType;
    /**
     * 报文名称
     *
     * @pdOid 06fb9ec5-1d59-4450-afcb-c4358ed818ca
     */
    @TableField("FILE_NAME")
    public String fileName;
    /**
     * 处理时间
     *
     * @pdOid 954c2744-3118-4813-bca5-6be8950c26ec
     */
    @TableField("PROCESSING_TIME")
    public String processingTime;
    /**
     * 处理原因
     *
     * @pdOid acc52d19-4d32-42be-a9fa-7433a815397a
     */
    @TableField("PROCESSING_LOG")
    public byte[] processingLog;
    /**
     * 状态
     *
     * @pdOid 3b46475f-6824-45a0-9709-f213239d781c
     */
    @TableField("STATUS")
    public String status;

    /**
     * 对应MESSAGE_DATA中的uid
     *
     * @pdOid 3b46475f-6824-45a0-9709-f213239d781c
     */
    @TableField("MESSAGE_UID")
    public String messageUid;

    /**
     * 传输类型
     */
    @TableField("TRANS_TYPE")
    private String transType;

    @TableField(exist = false)
    private String processingLogStr;


    @TableField("CHECK_INFO")
    private byte[] checkInfo;//检查信息

    @TableField(exist = false)
    private String checkInfoStr;

    public String getCheckInfoStr() {
        return checkInfoStr;
    }

    public void setCheckInfoStr(String checkInfoStr) {
        this.checkInfoStr = checkInfoStr;
    }

    public byte[] getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(byte[] checkInfo) {
        this.checkInfo = checkInfo;
    }

    public String getProcessingLogStr() {
        return processingLogStr;
    }

    public void setProcessingLogStr(String processingLogStr) {
        this.processingLogStr = processingLogStr;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessageUid() {
        return messageUid;
    }

    public void setMessageUid(String messageUid) {
        this.messageUid = messageUid;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(String processingTime) {
        this.processingTime = processingTime;
    }

    public byte[] getProcessingLog() {
        return processingLog;
    }

    public void setProcessingLog(byte[] processingLog) {
        this.processingLog = processingLog;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    @Override
    public String toString() {
        return "EdiMessageLog{" +
                "uid='" + uid + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", docType='" + docType + '\'' +
                ", bizType='" + bizType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", processingTime='" + processingTime + '\'' +
                ", processingLog='" + processingLog + '\'' +
                ", status='" + status + '\'' +
                ", transType='" + transType + '\'' +
                '}';
    }
}