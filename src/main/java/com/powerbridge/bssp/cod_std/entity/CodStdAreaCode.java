package com.powerbridge.bssp.cod_std.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_std_area_code")
public class CodStdAreaCode implements Serializable{
	
	@TableField(exist = false)
	private static final long serialVersionUID = -2424406813808569699L;
	
	@TableId(value="UID")
	private String uid;//主键
	
	@TableField(value="AREA_CODE")
	@Length(min = 0, max = 6)
	@NotNull
	private String areaCode;//场地代码
	
	@Length(min = 0, max = 30)
	@NotNull
	@TableField(value = "AREA_NAME")
	private String areaName;//场地名称
	
	@Length(min = 0, max = 4)
	@NotNull
	@TableField(value="CUSTOMS_CODE")
	private String customsCode;//关区代码
	
	@TableField(value="VISIT_URL_FIRST")
	private String visitUrlFirst;//访问网址1
	
	@TableField(value="VISIT_URL_SEC")
	private String visitUrlSec;//访问网址2
	
	@Length(min = 0, max = 1)
	@NotNull
	@TableField(value="IS_ENABLE")
	private String isEnable;//是否启用
	
	@TableField(value="REMARKS")
	private String remarks;//备注
	
	@TableField(value="CREATE_BY")
	private String createBy;//创建人
	
	@TableField(value="CREATE_TIME")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String createTime;//创建时间
	
	@TableField(value="UPDATE_BY")
	private String updateBy;//修改人
	
	@TableField(value="UPDATE_TIME")
	private String updateTime;//修改时间


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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCustomsCode() {
		return customsCode;
	}

	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}

	public String getVisitUrlFirst() {
		return visitUrlFirst;
	}

	public void setVisitUrlFirst(String visitUrlFirst) {
		this.visitUrlFirst = visitUrlFirst;
	}

	public String getVisitUrlSec() {
		return visitUrlSec;
	}

	public void setVisitUrlSec(String visitUrlSec) {
		this.visitUrlSec = visitUrlSec;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
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

	
	
	
}
  
    