package com.powerbridge.bssp.edi.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 报文头表
 * </p>
 *
 * @author simon.xie
 * @since 2017-06-01
 */
@TableName("edi_message_head")
public class EdiMessageHead implements Serializable {
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
     * 报文类型
     */
	@TableField("CLASS_NAME")
	private String className;
    /**
     * 报文版本号
     */
	@TableField("CLASS_VER")
	private String classVer;
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
     * 单据编号
     */
	@TableField("SEQ_NO")
	private String seqNo;
    /**
     * 环节编号
     */
	@TableField("APP_STEP_ID")
	private String appStepId;
    /**
     * 报文传送时间
     */
	@TableField("FILE_DATETIME")
	private Date fileDatetime;
    /**
     * 报文唯一序号
     */
	@TableField("FILE_KEY_ID")
	private String fileKeyId;
    /**
     * 报文名称
     */
	@TableField("FILE_NAME")
	private String fileName;
    /**
     * 报文序号
     */
	@TableField("FILE_ORDER")
	private Integer fileOrder;
    /**
     * 报文数
     */
	@TableField("FILE_SIZE")
	private Integer fileSize;
    /**
     * 报文加签
     */
	@TableField("FILE_SIGN")
	private String fileSign;
    /**
     * 代码
     */
	@TableField("PUBLIC_CODE")
	private String publicCode;
    /**
     * 名称
     */
	@TableField("PUBLIC_NAME")
	private String publicName;
    /**
     * 监管场所
     */
	@TableField("AREA_CODE")
	private String areaCode;
    /**
     * Token
     */
	@TableField("TOKEN")
	private String token;
    /**
     * 存储时间
     */
	@TableField("STORAGE_TIME")
	private Date storageTime;
    /**
     * 处理时间
     */
	@TableField("PROCESSING_TIME")
	private Date processingTime;
    /**
     * 报文存储路径
     */
	@TableField("FILE_URL")
	private String fileUrl;
    /**
     * 处理标识
     */
	@TableField("STATUS")
	private String status;


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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassVer() {
		return classVer;
	}

	public void setClassVer(String classVer) {
		this.classVer = classVer;
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

	public String getAppStepId() {
		return appStepId;
	}

	public void setAppStepId(String appStepId) {
		this.appStepId = appStepId;
	}

	public Date getFileDatetime() {
		return fileDatetime;
	}

	public void setFileDatetime(Date fileDatetime) {
		this.fileDatetime = fileDatetime;
	}

	public String getFileKeyId() {
		return fileKeyId;
	}

	public void setFileKeyId(String fileKeyId) {
		this.fileKeyId = fileKeyId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getFileOrder() {
		return fileOrder;
	}

	public void setFileOrder(Integer fileOrder) {
		this.fileOrder = fileOrder;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileSign() {
		return fileSign;
	}

	public void setFileSign(String fileSign) {
		this.fileSign = fileSign;
	}

	public String getPublicCode() {
		return publicCode;
	}

	public void setPublicCode(String publicCode) {
		this.publicCode = publicCode;
	}

	public String getPublicName() {
		return publicName;
	}

	public void setPublicName(String publicName) {
		this.publicName = publicName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getStorageTime() {
		return storageTime;
	}

	public void setStorageTime(Date storageTime) {
		this.storageTime = storageTime;
	}

	public Date getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(Date processingTime) {
		this.processingTime = processingTime;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
