package com.powerbridge.bssp.cod_std.entity;

import java.io.Serializable;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("COD_STD_CLEARANCE_TYPE")
public class CodStdClearanceType implements Serializable{
	
	@TableField(exist = false)
	private static final long serialVersionUID = -2424406813808562132L;
	
	@TableId(value="UID")
	private String uid;//主键
	
	@TableField(value="CODE")
	private String code;//通关业务代码
	
	@TableField(value="NAME")
	private String name;//通关业务名字
	
	@TableField(value="IS_ENABLE")
	private String isEnable;//是否启用
	
	@TableField(value="REMARKS")
	private String remarks;//备注
	
	@TableField(value="CREATE_BY")
	private String createBy;//创建人
	
	@TableField(value="CREATE_TIME")
/*	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
*/	private String createTime;//创建时间
	
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
  
    