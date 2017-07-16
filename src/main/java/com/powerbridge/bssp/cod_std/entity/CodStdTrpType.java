package com.powerbridge.bssp.cod_std.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("cod_std_trp_type")
public class CodStdTrpType implements Serializable{
	
	
	@TableField(exist = false)
	private static final long serialVersionUID = -3619530071285592056L;
	
	@TableId(value = "UID")
	private String uid;  //主键
	
	@TableField(value = "CODE")
    private String code;  //运输类型代码
	
	@TableField(value = "NAME")
    private String name;   //运输类型简称
	
	@TableField(value = "ClEARANCE_CODE")
	private String clearanceCode;   //通关业务代码
	
	@TableField(value = "CUSTOMS_CODE")
	private String customsCode;   //关区代码
	
	@TableField(value = "AREA_CODE")
	private String areaCode;   //场地代码
	
	@TableField(value = "FULL_NAME")
	private String fullName;   //运输类型全称
	
	@TableField(value = "REMARKS")
	private String remarks;   //说明
	
	@TableField(value = "IS_ENABLE")
	private String isEnable;    //是否启用
	
	@TableField(value = "CREATE_BY")
    private String createBy;    //创建人
	
	@TableField(value = "CREATE_TIME")
	private String createTime;    //创建时间

	@TableField(value = "UPDATE_BY")
    private String updateBy;    //修改人
	
	@TableField(value = "UPDATE_TIME")
    private String updateTime;    //修改时间

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClearanceCode() {
		return clearanceCode;
	}

	public void setClearanceCode(String clearanceCode) {
		this.clearanceCode = clearanceCode;
	}

	public String getCustomsCode() {
		return customsCode;
	}

	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}