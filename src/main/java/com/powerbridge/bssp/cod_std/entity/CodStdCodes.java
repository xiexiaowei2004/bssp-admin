package com.powerbridge.bssp.cod_std.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("cod_std_codes")
public class CodStdCodes implements Serializable{

	private static final long serialVersionUID = 1L;
    
	@TableId(value = "UID")
	private String uid;//唯一主键
	
	@TableField(value = "ATTR_VALUE")
	private String attrValue;//属性值
	
	@TableField(value = "ATTR_NAME")
	private String attrName;//属性名称
	
	@TableField(value = "DICTIONARY_VALUE")
	private String dictionaryValue;//字典值
	
	@TableField(value = "DICTIONARY_DESCRIPT")
	private String dictionaryDescript;//字典描述
	
	@TableField(value = "ORDER_NO")
	private int orderNo;//排序号
	
	@TableField(value = "IS_ENABLE")
	private String isEnable;//是否启用
	
	@TableField(value = "CREATE_BY")
	private String createBy;//创建人
	
	@TableField(value = "CREATE_TIME")
	private  Timestamp  createTime;//创建时间
	
	@TableField(value = "UPDATE_BY")
	private String updateBy;//修改人

	@TableField(value = "UPDATE_TIME")
	private Timestamp updateTime;//修改时间
	
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


	
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getDictionaryValue() {
		return dictionaryValue;
	}

	public void setDictionaryValue(String dictionaryValue) {
		this.dictionaryValue = dictionaryValue;
	}

	public String getDictionaryDescript() {
		return dictionaryDescript;
	}

	public void setDictionaryDescript(String dictionaryDescript) {
		this.dictionaryDescript = dictionaryDescript;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
}
