package com.powerbridge.bssp.edi.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

public class EdiReturnList implements Serializable {
    /**
     * 主键
     */@TableId("UID")
    private String uid;

    /**
     * 业务单据编号
     */@TableField("DOC_ID")
    private String docId;

    /**
     * 监管场所
     */@TableField("AREA_CODE")
    private String areaCode;

    /**
     * 单据类型
     */@TableField("DOC_TYPE")
    private String docType;

    /**
     * 业务类型
     */@TableField("BIZ_TYPE")
    private String bizType;

    /**
     * 单据编号
     */@TableField("SEQ_NO")
    private String seqNo;

    @TableField("BIG_DATA")
    private byte[] bigData;//大数据

    @TableField("NOTE")
    private byte[] note;//查验错误消息

    private static final long serialVersionUID = 1L;

    public byte[] getBigData() {
        return bigData;
    }

    public void setBigData(byte[] bigData) {
        this.bigData = bigData;
    }

    public byte[] getNote() {
        return note;
    }

    public void setNote(byte[] note) {
        this.note = note;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
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

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
}

