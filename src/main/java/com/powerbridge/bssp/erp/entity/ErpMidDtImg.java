package com.powerbridge.bssp.erp.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.io.Serializable;

/**
 * <p>
 * 料件中间表
 * </p>
 *
 * @author lindapeng
 * @since 2017-06-02
 */
@TableName("erp_mid_dt_img")
public class ErpMidDtImg implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("UID")
	private String uid;
    /**
     * 商品料号
     */
	@TableField("GDS_MTNO")
	private String gdsMtno;
    /**
     * 商品序号
     */
	@TableField("GDS_SEQNO")
	private BigDecimal gdsSeqno;
    /**
     * 主料标志
     */
	@TableField("ADJMTR_MARKCD")
	private String adjmtrMarkcd;
    /**
     * 商品编码
     */
	@TableField("GDECD")
	private String gdecd;
    /**
     * 商品名称
     */
	@TableField("GDS_NM")
	private String gdsNm;
    /**
     * 商品规格型号
     */
	@TableField("GDS_SPCF_MODEL_DESC")
	private String gdsSpcfModelDesc;
    /**
     * 申报计量单位
     */
	@TableField("DCL_UNITCD")
	private String dclUnitcd;
    /**
     * 法定单位
     */
	@TableField("LAWF_UNITCD")
	private String lawfUnitcd;
    /**
     * 第二单位
     */
	@TableField("SECD_LAWF_UNITCD")
	private String secdLawfUnitcd;
    /**
     * 征减免方式
     */
	@TableField("LVYRLF_MODECD")
	private String lvyrlfModecd;
    /**
     * 产终地
     */
	@TableField("NATCD")
	private String natcd;
    /**
     * 申报单价
     */
	@TableField("DCL_UPRC_AMT")
	private BigDecimal dclUprcAmt;
    /**
     * 币制
     */
	@TableField("DCL_CURRCD")
	private String dclCurrcd;
    /**
     * 申报单价人民币
     */
	@TableField("DCL_UPRC_AMT_RMB")
	private BigDecimal dclUprcAmtRmb;
    /**
     * 法定单位比例
            法定单位比例
     */
	@TableField("FST_SF_VAL")
	private BigDecimal fstSfVal;
    /**
     * 第二单位比例
     */
	@TableField("SECD_SF_VAL")
	private BigDecimal secdSfVal;
    /**
     * 重量比例因子
     */
	@TableField("WT_SF_VAL")
	private BigDecimal wtSfVal;
    /**
     * 英文商品名称
     */
	@TableField("ENG_GDS_NM")
	private String engGdsNm;
    /**
     * 英文规格型号
     */
	@TableField("ENG_GDS_SPCF_MODEL_DESC")
	private String engGdsSpcfModelDesc;
    /**
     * 企业单位
     */
	@TableField("ENT_UNITCD")
	private String entUnitcd;
    /**
     * 企业单位比例因子
     */
	@TableField("ENT_SF_VAL")
	private BigDecimal entSfVal;
    /**
     * 备注
     */
	@TableField("RMK")
	private String rmk;


	/**
	 * 是否保税
	 */
	@TableField("BONDED_FLAG")

	private String bondedFlag;

	/**
	 * 修改标记
	 */
	@TableField("MODF_MARKCD")
	private String modfMarkcd;


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getGdsMtno() {
		return gdsMtno;
	}

	public void setGdsMtno(String gdsMtno) {
		this.gdsMtno = gdsMtno;
	}

	public BigDecimal getGdsSeqno() {
		return gdsSeqno;
	}

	public void setGdsSeqno(BigDecimal gdsSeqno) {
		this.gdsSeqno = gdsSeqno;
	}

	public String getAdjmtrMarkcd() {
		return adjmtrMarkcd;
	}

	public void setAdjmtrMarkcd(String adjmtrMarkcd) {
		this.adjmtrMarkcd = adjmtrMarkcd;
	}

	public String getGdecd() {
		return gdecd;
	}

	public void setGdecd(String gdecd) {
		this.gdecd = gdecd;
	}

	public String getGdsNm() {
		return gdsNm;
	}

	public void setGdsNm(String gdsNm) {
		this.gdsNm = gdsNm;
	}

	public String getGdsSpcfModelDesc() {
		return gdsSpcfModelDesc;
	}

	public void setGdsSpcfModelDesc(String gdsSpcfModelDesc) {
		this.gdsSpcfModelDesc = gdsSpcfModelDesc;
	}

	public String getDclUnitcd() {
		return dclUnitcd;
	}

	public void setDclUnitcd(String dclUnitcd) {
		this.dclUnitcd = dclUnitcd;
	}

	public String getLawfUnitcd() {
		return lawfUnitcd;
	}

	public void setLawfUnitcd(String lawfUnitcd) {
		this.lawfUnitcd = lawfUnitcd;
	}

	public String getSecdLawfUnitcd() {
		return secdLawfUnitcd;
	}

	public void setSecdLawfUnitcd(String secdLawfUnitcd) {
		this.secdLawfUnitcd = secdLawfUnitcd;
	}

	public String getLvyrlfModecd() {
		return lvyrlfModecd;
	}

	public void setLvyrlfModecd(String lvyrlfModecd) {
		this.lvyrlfModecd = lvyrlfModecd;
	}

	public String getNatcd() {
		return natcd;
	}

	public void setNatcd(String natcd) {
		this.natcd = natcd;
	}

	public BigDecimal getDclUprcAmt() {
		return dclUprcAmt;
	}

	public void setDclUprcAmt(BigDecimal dclUprcAmt) {
		this.dclUprcAmt = dclUprcAmt;
	}

	public String getDclCurrcd() {
		return dclCurrcd;
	}

	public void setDclCurrcd(String dclCurrcd) {
		this.dclCurrcd = dclCurrcd;
	}

	public BigDecimal getDclUprcAmtRmb() {
		return dclUprcAmtRmb;
	}

	public void setDclUprcAmtRmb(BigDecimal dclUprcAmtRmb) {
		this.dclUprcAmtRmb = dclUprcAmtRmb;
	}

	public BigDecimal getFstSfVal() {
		return fstSfVal;
	}

	public void setFstSfVal(BigDecimal fstSfVal) {
		this.fstSfVal = fstSfVal;
	}

	public BigDecimal getSecdSfVal() {
		return secdSfVal;
	}

	public void setSecdSfVal(BigDecimal secdSfVal) {
		this.secdSfVal = secdSfVal;
	}

	public BigDecimal getWtSfVal() {
		return wtSfVal;
	}

	public void setWtSfVal(BigDecimal wtSfVal) {
		this.wtSfVal = wtSfVal;
	}

	public String getEngGdsNm() {
		return engGdsNm;
	}

	public void setEngGdsNm(String engGdsNm) {
		this.engGdsNm = engGdsNm;
	}

	public String getEngGdsSpcfModelDesc() {
		return engGdsSpcfModelDesc;
	}

	public void setEngGdsSpcfModelDesc(String engGdsSpcfModelDesc) {
		this.engGdsSpcfModelDesc = engGdsSpcfModelDesc;
	}

	public String getEntUnitcd() {
		return entUnitcd;
	}

	public void setEntUnitcd(String entUnitcd) {
		this.entUnitcd = entUnitcd;
	}

	public BigDecimal getEntSfVal() {
		return entSfVal;
	}

	public void setEntSfVal(BigDecimal entSfVal) {
		this.entSfVal = entSfVal;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getBondedFlag() {
		return bondedFlag;
	}

	public void setBondedFlag(String bondedFlag) {
		this.bondedFlag = bondedFlag;
	}

	public String getModfMarkcd() {
		return modfMarkcd;
	}

	public void setModfMarkcd(String modfMarkcd) {
		this.modfMarkcd = modfMarkcd;
	}
}
