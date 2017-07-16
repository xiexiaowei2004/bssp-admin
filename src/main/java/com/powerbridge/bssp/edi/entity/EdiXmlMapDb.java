package com.powerbridge.bssp.edi.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@TableName("edi_xml_map_db")
public class EdiXmlMapDb implements Serializable {
	
	@TableField(exist = false)
	private static final long serialVersionUID = -2424406813808569699L;
	
	@TableId(value="UID")
	private String uid;//主键
	
	@TableField(value="AREA_CODE")
	private String areaCode;//监管场地代码
	
	@TableField(value = "DOC_TYPE")
	private String docType;//单据类型
	
	@TableField(value="BIZ_TYPE")
	private String bizType;//业务类型
	
	@TableField(value="XML_NAME")
	private String xmlName;//报文字段
	
	@TableField(value="XML_TYPE")
	private String xmlType;//字段类型
	
	@TableField(value="DB_TABLE")
	private String dbTable;//业务表名
	
	@TableField(value="DB_COLUMN")
	private String dbColumn;//业务字段
	
	@TableField(value="DB_TYPE")
	private String dbType;//业务字段类型
	
	@TableField(value="DB_LENGTH")
	private String dbLength;//业务长度
	
	@TableField(value="XML_ROOT")
	private String xmlRoot;//上级根节点
	
	@TableField(value="STATUS")
	private String status;//启用标识
	
	@TableField(value="XML_LENGTH")
	private String xmlLength;//字段长度
	
	@TableField(value="REMARKS")
	private String remarks;//备注
	
	@TableField(value="CREATE_BY")
	private String createBy;//创建人代码
	
	@TableField(value="CREATE_NAME")
	private String createName;//创建人名称
	
	@TableField(value="CREATE_TIME")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String createTime;//创建时间
	
	@TableField(value="UPDATE_BY")
	private String updateBy;//修改人代码
	
	@TableField(value="UPDATE_NAME")
	private String updateName;//修改人名称
	
	@TableField(value="UPDATE_TIME")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String updateTime;//修改时间

	@TableField(value="ORDER_NO")
	private  String orderNo;  //排序号

	@TableField(value="PASSAGEWAY")
	private  String passageway;  //通道
	
	@TableField(exist = false)
	private String alias;   //别名

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
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

	public String getXmlName() {
		return xmlName;
	}

	public void setXmlName(String xmlName) {
		this.xmlName = xmlName;
	}

	public String getXmlType() {
		return xmlType;
	}

	public void setXmlType(String xmlType) {
		this.xmlType = xmlType;
	}

	public String getDbTable() {
		return dbTable;
	}

	public void setDbTable(String dbTable) {
		this.dbTable = dbTable;
	}

	public String getDbColumn() {
		return dbColumn;
	}

	public void setDbColumn(String dbColumn) {
		this.dbColumn = dbColumn;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getDbLength() {
		return dbLength;
	}

	public void setDbLength(String dbLength) {
		this.dbLength = dbLength;
	}

	public String getXmlRoot() {
		return xmlRoot;
	}

	public void setXmlRoot(String xmlRoot) {
		this.xmlRoot = xmlRoot;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getXmlLength() {
		return xmlLength;
	}

	public void setXmlLength(String xmlLength) {
		this.xmlLength = xmlLength;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPassageway() {
		return passageway;
	}

	public void setPassageway(String passageway) {
		this.passageway = passageway;
	}
}
  
    