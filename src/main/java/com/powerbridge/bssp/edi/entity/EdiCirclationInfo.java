package com.powerbridge.bssp.edi.entity;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("edi_circlation_info")
public class EdiCirclationInfo implements Serializable {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;                    
	@TableId(value = "UID")
	private String uid;//唯一主键
	
	@TableField(value = "AREA_CODE")
	private String areaCode;//监管场所
	
	@TableField(value = "CHANNEL")
	private String channel;//环节号
	
	@NotNull
	@TableField(value = "DOC_TYPE")
	private String docType;//单据类型
	
	@NotNull
	@TableField(value = "SEQ_NO")
	private String seqNo;//单据编号
	
	@NotNull
	@TableField(value = "BIZ_TYPE")
	private String bizType;//业务类型
	
	@NotNull
	@TableField(value = "POS_CODE")
	private String posCode;//岗位编号
	
	@TableField(value = "NEXT_CHANNEL")
	private String nextChannel;//下一环节号
	
	@TableField(value = "NEXT_POST_CODE")
	private String nextPosCode;//下一审批岗位编号
	
	@NotNull
	@TableField(value = "STATUS")
	private String status;//处理标识
	
	@TableField(value = "OP_USER")
	private String opUser;//操作人
	
	@TableField(value = "OP_DATE")
	private String opDate;//操作时间 
	
	@TableField(value = "REMARKS")
	private String remarks;//备注  
	
	@TableField(value = "CREATE_BY")
	private String createBy;//创建人代码
	
	@TableField(value = "CREATE_NAME")
	private String createName;//创建人名称
	
	@TableField(value = "CREATE_TIME")
	private  String  createTime;//创建时间
	
	@TableField(value = "UPDATE_BY")
	private String updateBy;//修改人代码
	
	@TableField(value = "UPDATE_NAME")
	private String updateName;//修改人名称

	@TableField(value = "UPDATE_TIME")
	private String updateTime;//修改时间

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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getPosCode() {
		return posCode;
	}

	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	public String getNextChannel() {
		return nextChannel;
	}

	public void setNextChannel(String nextChannel) {
		this.nextChannel = nextChannel;
	}

	public String getNextPosCode() {
		return nextPosCode;
	}

	public void setNextPosCode(String nextPosCode) {
		this.nextPosCode = nextPosCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOpUser() {
		return opUser;
	}

	public void setOpUser(String opUser) {
		this.opUser = opUser;
	}

	public String getOpDate() {
		return opDate;
	}

	public void setOpDate(String opDate) {
		this.opDate = opDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
