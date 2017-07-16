package com.powerbridge.bssp.edi.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysql.cj.mysqlx.protobuf.MysqlxDatatypes;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
@TableName("edi_recv_data")
public class EdiRecvData implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId("UID")
    private String uid;
    /**
     * 业务单据编号
     */
    @TableField("DOC_ID")
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
    /**
     * 单据状态
     */@TableField("CHK_STATUS")
    private String chkStatus;
    /**
     * 全程通道
     */@TableField("RET_CHANNEL")
    private String retChannel;
    /**
     * 主管海关
     */@TableField("CUSTOMS_CODE")
    private String customsCode;
    /**
     * 国检代码
     */@TableField("CIQ_CODE")
    private String ciqCode;
    /**
     * 公共代码
     */@TableField("PUBLC_CODE")
    private String publcCode;
    /**
     * 录入时间
     */@TableField("INPUTER_TIME")
    private String inputerTime;
    /**
     * 备注
     */@TableField("NOTE")
    private String note;
    /**
     * 处理标识
     */@TableField("STATUS")
    private String status;
    /**
     * 源数据编号
     */@TableField("MESSAGE_UID")
    private String messageUid;
    /**
     * 包唯一标识
     */@TableField("POCKET_ID")
    private String pocketId;
    /**
     * 包总数
     */@TableField("TOTAL_POCKET_QTY")
    private Integer totalPocketQty;
    /**
     * 当前包序号
     */@TableField("CUR_POCKET_NO")
    private Integer curPocketNo;
    @TableField("BIG_DATA")
    private byte[] bigData;//大数据
    @TableField("CHECK_INFO")
    private byte[] checkInfo;//查验错误消息



    /**
     * 网络通道
     */
    @TableField("PASSAGEWAY")
    private Integer passageway;


    public Integer getPassageway() {
        return passageway;
    }

    public void setPassageway(Integer passageway) {
        this.passageway = passageway;
    }

    public String getBigDataStr() {
        return bigDataStr;
    }

    public void setBigDataStr(String bigDataStr) {
        this.bigDataStr = bigDataStr;
    }

    @TableField(exist = false)
    private String bigDataStr;
    public byte[] getBigData() {
        return bigData;
    }

    public void setBigData(byte[] bigData) {
        this.bigData = bigData;
    }

    public byte[] getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(byte[] checkInfo) {
        this.checkInfo = checkInfo;
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

    public String getChkStatus() {
        return chkStatus;
    }

    public void setChkStatus(String chkStatus) {
        this.chkStatus = chkStatus;
    }

    public String getRetChannel() {
        return retChannel;
    }

    public void setRetChannel(String retChannel) {
        this.retChannel = retChannel;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public String getCiqCode() {
        return ciqCode;
    }

    public void setCiqCode(String ciqCode) {
        this.ciqCode = ciqCode;
    }

    public String getPublcCode() {
        return publcCode;
    }

    public void setPublcCode(String publcCode) {
        this.publcCode = publcCode;
    }

    public String getInputerTime() {
        return inputerTime;
    }

    public void setInputerTime(String inputerTime) {
        this.inputerTime = inputerTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessageUid() {
        return messageUid;
    }

    public void setMessageUid(String messageUid) {
        this.messageUid = messageUid;
    }

    public String getPocketId() {
        return pocketId;
    }

    public void setPocketId(String pocketId) {
        this.pocketId = pocketId;
    }

    public Integer getTotalPocketQty() {
        return totalPocketQty;
    }

    public void setTotalPocketQty(Integer totalPocketQty) {
        this.totalPocketQty = totalPocketQty;
    }

    public Integer getCurPocketNo() {
        return curPocketNo;
    }

    public void setCurPocketNo(Integer curPocketNo) {
        this.curPocketNo = curPocketNo;
    }
}