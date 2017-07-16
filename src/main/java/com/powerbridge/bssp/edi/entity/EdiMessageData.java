package com.powerbridge.bssp.edi.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 报文数据
 * </p>
 *
 * @author simon.xie
 * @since 2017-06-01
 */
@TableName("edi_message_data")
public class EdiMessageData implements Serializable {
	@TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("UID")
	private String uid;
    /**
     * 编号
     */
	@TableField("SERIAL_NO")
	private String serialNo;
    /**
     * 监管场所
     */
	@TableField("AREA_CODE")
	private String areaCode;
    /**
     * 单据类型
     */
	@TableField("DOC_TYPE")
	private String docType;
    /**
     * 业务类型
     */
	@TableField("BIZ_TYPE")
	private String bizType;
    /**
     * 报文名称
     */
	@TableField("FILE_NAME")
	private String fileName;
    /**
     * 传输类型
     */
	@TableField("TRANS_TYPE")
	private String transType;
    /**
     * 传输方式
     */
	@TableField("TRANS_MODE")
	private String transMode;
    /**
     * 存储时间
     */
	@TableField("STORAGE_TIME")
	private Date storageTime;
    /**
     * 大数据
     */
	@TableField("BIG_DATA")
	private byte[] bigData;
    /**
     * 处理时间
     */
	@TableField("PROCESSING_TIME")
	private String processingTime;
    /**
     * 处理标识
     */
	@TableField("STATUS")
	private String status;
	
	@TableField(exist = false)
	private String bigDataStr;

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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTransMode() {
		return transMode;
	}

	public void setTransMode(String transMode) {
		this.transMode = transMode;
	}

	public Date getStorageTime() {
		return storageTime;
	}

	public void setStorageTime(Date storageTime) {
		this.storageTime = storageTime;
	}

	public byte[] getBigData() {
		return bigData;
	}
	
	public String getBigDataStr() {
		return bigDataStr;
	}

	public void setBigData(byte[] bigData) {
		this.bigData = bigData;
	}

	public String getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(String processingTime) {
		this.processingTime = processingTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setBigDataStr(String bigDataStr) {
		this.bigDataStr = bigDataStr;
	}
}
