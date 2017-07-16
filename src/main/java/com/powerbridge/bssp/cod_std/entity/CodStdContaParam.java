package com.powerbridge.bssp.cod_std.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("cod_std_conta_param")
public class CodStdContaParam implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	@TableId(value = "UID")
	private String uid;//唯一主键
	
	@TableField(value = "CODE")
	private String code;//集装箱编号
	
	@TableField(value = "NAME")
     private String name;//集装箱名称
	
	@TableField(value = "MODEL")
	private String model;//集装箱型号
	
	@TableField(value = "SIZE")
	private String size;//集装箱尺寸
	
	@TableField(value = "EMPTY")
	private BigDecimal empty;//集装箱空重
	
	@TableField(value = "CABINET_TYPE")
	private String cabinetType;//柜型
	
	@TableField(value = "VOLUME")
	private BigDecimal volume;//集装箱容积
	
	@TableField(value = "BOX_NUMBER")
	private BigDecimal BoxNumber;//标准箱数量
	
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public BigDecimal getEmpty() {
		return empty;
	}

	public void setEmpty(BigDecimal empty) {
		this.empty = empty;
	}

	public String getCabinetType() {
		return cabinetType;
	}

	public void setCabinetType(String cabinetType) {
		this.cabinetType = cabinetType;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getBoxNumber() {
		return BoxNumber;
	}

	public void setBoxNumber(BigDecimal boxNumber) {
		BoxNumber = boxNumber;
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

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
