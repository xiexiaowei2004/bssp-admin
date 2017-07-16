package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_merch_classify")
public class CodMerchClassify implements Serializable{
	
	@TableField(exist = false)
	private static final long serialVersionUID = -2424426813828569699L;
	
	@TableField(value="CODE_TS")
	private String codeTs;//税则号
	
	@TableField(value="G_NAME")
	private String gName;//商品名称
	
	@TableField(value="G_REMARK")
	private String GRemark;//商品描述
	
	@TableField(value="CHANGE_MAN")
	private String changeMan;//变更人
	
	@TableField(value="CHANGE_TIME")
	private String changeTime;//变更时间
	
	@TableField(value="RECHK_MAN")
	private String rechkMan;//维护人
	
	@TableField(value="RECHK_TIME")
	private String rechkTime;//维护时间
	
	@TableField(value="RECHK_MARK")
	private String rechkMark;//维护标识
	
	@TableField(value="RECHC_COMM")
	private String rechcComm;//维护描述
	
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
	
	public String getCodeTs() {
		return codeTs;
	}

	public void setCodeTs(String codeTs) {
		this.codeTs = codeTs;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getGRemark() {
		return GRemark;
	}

	public void setGRemark(String gRemark) {
		GRemark = gRemark;
	}

	public String getChangeMan() {
		return changeMan;
	}

	public void setChangeMan(String changeMan) {
		this.changeMan = changeMan;
	}

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public String getRechkMan() {
		return rechkMan;
	}

	public void setRechkMan(String rechkMan) {
		this.rechkMan = rechkMan;
	}

	public String getRechkTime() {
		return rechkTime;
	}

	public void setRechkTime(String rechkTime) {
		this.rechkTime = rechkTime;
	}

	public String getRechkMark() {
		return rechkMark;
	}

	public void setRechkMark(String rechkMark) {
		this.rechkMark = rechkMark;
	}

	public String getRechcComm() {
		return rechcComm;
	}

	public void setRechcComm(String rechcComm) {
		this.rechcComm = rechcComm;
	}
	
	
	
}
  
    