package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("bill_generater")
public class BillGenerater implements Serializable{
	
	@TableField(exist = false)
	private static final long serialVersionUID = -4024940690619930240L;
	
	@TableId(value = "UID")
	private String uid;   //uid主键
	
	@TableField(value = "AREA_CODE")
    private String areaCode;   //场所代码
	
	@TableField(value = "DOC_TYPE")
    private String docType;   //单据类型
	
	@TableField(value = "SERVER_TYPE")
    private String serverType;   //服务类型
	
	@TableField(value = "MODEL_NAME")
	private String modelName;    //模块名称
	
	@TableField(value = "YEAR_MONTH")
    private String yearMonth;   //年月份
	
	@TableField(value = "MASK")
    private String mask;    //项目掩码
	
	@TableField(value = "APPLY_ID")
    private String applyId;   //应用id
	
	@TableField(value = "SERIAL_NUMBER")
    private int serialNumber;   //流水号
	
	@TableField(value = "IP")
    private String ip;   //ip
	
	@TableField(value = "STATE")
    private Integer state;   //状态
	
	@TableField(value = "REMARKS")
    private String remarks;  //备注
	
	/** 创建时间 */
	@TableField(value = "CREATE_TIME")
	private String createTime;
	
	@TableField(value = "SEQ_NO")
	private String seqNo;           //单据号
	
	
	@TableField(exist = false)
	private String id ;

	@TableField(exist = false)
	private String text ;


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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
    
}