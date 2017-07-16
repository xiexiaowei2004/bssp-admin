package com.powerbridge.bssp.inv.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 简单加工成品明细
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
@TableName("inv_dt_simple")
public class InvDtSimple implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId("UID")
	private String uid;

	/**
	 * 申报计量单位名称
	 */
	@TableField(exist = false)
	private String dclUnitnm;

	/**
	 * 法定计量单位名称
	 */
	@TableField(exist = false)
	private String lawfUnitnm;

	/**
	 *第二法定计量单位名称
	 */
	@TableField(exist = false)
	private String secdLawfUnitnm;

	/**
	 * 申报币制名称
	 */
	@TableField(exist = false)
	private String dclCurrnm;

	/**
	 * 国别代码名称
	 */
	@TableField(exist = false)
	private String natnm;

	/**
	 * 征减免方式名称
	 */
	@TableField(exist = false)
	private String lvyrlfModenm;

	/**
	 * 保税清单编号
	 */
	@TableField("BOND_INVT_NO")
	private String bondInvtNo;
	/**
	 * 变更次数
	 */
	@TableField("CHG_TMS_CNT")
	private BigDecimal chgTmsCnt;
	/**
	 * 商品序号
	 */
	@TableField("GDS_SEQNO")
	private BigDecimal gdsSeqno;

	/**
	 * 商品料号
	 */
	@TableField("GDS_MTNO")
	private String gdsMtno;
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
	 * 商品规格型号描述
	 */
	@TableField("GDS_SPCF_MODEL_DESC")
	private String gdsSpcfModelDesc;
	/**
	 * 美元统计总金额
	 */
	@TableField("USD_STAT_TOTAL_AMT")
	private BigDecimal usdStatTotalAmt;
	/**
	 * 申报计量单位代码
	 */
	@TableField("DCL_UNITCD")
	private String dclUnitcd;

	/**
	 * 法定计量单位代码
	 */
	@TableField("LAWF_UNITCD")
	private String lawfUnitcd;
	/**
	 * 第二法定计量单位代码
	 */
	@TableField("SECD_LAWF_UNITCD")
	private String secdLawfUnitcd;
	/**
	 * 申报单价金额
	 */
	@TableField("DCL_UPRC_AMT")
	private BigDecimal dclUprcAmt;
	/**
	 * 申报币制代码
	 */
	@TableField("DCL_CURRCD")
	private String dclCurrcd;
	/**
	 * 申报数量
	 */
	@TableField("DCL_QTY")
	private BigDecimal dclQty;
	/**
	 * 征减免方式代码
	 */
	@TableField("LVYRLF_MODECD")
	private String lvyrlfModecd;
	/**
	 * 国别代码
	 */
	@TableField("NATCD")
	private String natcd;
	/**
	 * 法定数量
	 */
	@TableField("LAWF_QTY")
	private BigDecimal lawfQty;
	/**
	 * 第二法定数量
	 */
	@TableField("SECD_LAWF_QTY")
	private BigDecimal secdLawfQty;
	/**
	 * 重量比例因子值
	 */
	@TableField("WT_SF_VAL")
	private BigDecimal wtSfVal;
	/**
	 * 报关单商品序号
	 */
	@TableField("ENTRY_GDS_SEQNO")
	private BigDecimal entryGdsSeqno;
	/**
	 * 申请表序号
	 */
	@TableField("APPLY_TB_SEQNO")
	private BigDecimal applyTbSeqno;
	/**
	 * 归类标记代码
	 */
	@TableField("CLY_MARKCD")
	private String clyMarkcd;

	/**
	 * 第一比例因子
	 */
	@TableField("FST_SF_VAL")
	private BigDecimal fstSfVal;
	/**
	 * 第二比例因子
	 */
	@TableField("SECD_SF_VAL")
	private BigDecimal secdSfVal;

	/**
	 * 毛重
	 */
	@TableField("GROSS_WT")
	private BigDecimal grossWt;
	/**
	 * 净重
	 */
	@TableField("NET_WT")
	private BigDecimal netWt;
	/**
	 * 账册备案单耗明细版本号
	 */
	@TableField("UCNS_VERNO")
	private String ucnsVerno;
	/**
	 * 备案编号
	 */
	@TableField("PUTREC_SEQNO")
	private String putrecSeqno;
	/**
	 * 企业备案号
	 */
	/*@TableField("COP_ENT_NO")
	private String copEntNo;*/
	/**
	 * 备注
	 */
	@TableField("RMK")
	private String rmk;
	/**
	 * 申报总金额
	 */
	@TableField("DCL_TOTAL_AMT")
	private BigDecimal dclTotalAmt;


	/**
	 * 单据编号
	 */
	@TableField("SEQ_NO")
	private String seqNo;

	/**
	 * 用途代码
	 */
	@TableField("USE_CD")
	private String useCd;

	/**
	 * 实际过卡数量
	 */
	@TableField("ACTL_PASS_QTY")
	private BigDecimal actlPassQty;

	/**
	 * 核放单已用数量
	 */
	@TableField("PASSPORT_USED_QTY")
	private BigDecimal passportUsedQty;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getBondInvtNo() {
		return bondInvtNo;
	}

	public void setBondInvtNo(String bondInvtNo) {
		this.bondInvtNo = bondInvtNo;
	}

	public BigDecimal getChgTmsCnt() {
		return chgTmsCnt;
	}

	public void setChgTmsCnt(BigDecimal chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}

	public BigDecimal getGdsSeqno() {
		return gdsSeqno;
	}

	public void setGdsSeqno(BigDecimal gdsSeqno) {
		this.gdsSeqno = gdsSeqno;
	}


	public String getGdsMtno() {
		return gdsMtno;
	}

	public void setGdsMtno(String gdsMtno) {
		this.gdsMtno = gdsMtno;
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

	public BigDecimal getUsdStatTotalAmt() {
		return usdStatTotalAmt;
	}

	public void setUsdStatTotalAmt(BigDecimal usdStatTotalAmt) {
		this.usdStatTotalAmt = usdStatTotalAmt;
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

	public BigDecimal getDclQty() {
		return dclQty;
	}

	public void setDclQty(BigDecimal dclQty) {
		this.dclQty = dclQty;
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

	public BigDecimal getLawfQty() {
		return lawfQty;
	}

	public void setLawfQty(BigDecimal lawfQty) {
		this.lawfQty = lawfQty;
	}

	public BigDecimal getSecdLawfQty() {
		return secdLawfQty;
	}

	public void setSecdLawfQty(BigDecimal secdLawfQty) {
		this.secdLawfQty = secdLawfQty;
	}

	public BigDecimal getWtSfVal() {
		return wtSfVal;
	}

	public void setWtSfVal(BigDecimal wtSfVal) {
		this.wtSfVal = wtSfVal;
	}

	public BigDecimal getEntryGdsSeqno() {
		return entryGdsSeqno;
	}

	public void setEntryGdsSeqno(BigDecimal entryGdsSeqno) {
		this.entryGdsSeqno = entryGdsSeqno;
	}

	public BigDecimal getApplyTbSeqno() {
		return applyTbSeqno;
	}

	public void setApplyTbSeqno(BigDecimal applyTbSeqno) {
		this.applyTbSeqno = applyTbSeqno;
	}

	public String getClyMarkcd() {
		return clyMarkcd;
	}

	public void setClyMarkcd(String clyMarkcd) {
		this.clyMarkcd = clyMarkcd;
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

	public BigDecimal getGrossWt() {
		return grossWt;
	}

	public void setGrossWt(BigDecimal grossWt) {
		this.grossWt = grossWt;
	}

	public BigDecimal getNetWt() {
		return netWt;
	}

	public void setNetWt(BigDecimal netWt) {
		this.netWt = netWt;
	}

	public String getUcnsVerno() {
		return ucnsVerno;
	}

	public void setUcnsVerno(String ucnsVerno) {
		this.ucnsVerno = ucnsVerno;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public BigDecimal getDclTotalAmt() {
		return dclTotalAmt;
	}

	public void setDclTotalAmt(BigDecimal dclTotalAmt) {
		this.dclTotalAmt = dclTotalAmt;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getUseCd() {
		return useCd;
	}

	public void setUseCd(String useCd) {
		this.useCd = useCd;
	}

	public BigDecimal getActlPassQty() {
		return actlPassQty;
	}

	public void setActlPassQty(BigDecimal actlPassQty) {
		this.actlPassQty = actlPassQty;
	}

	public BigDecimal getPassportUsedQty() {
		return passportUsedQty;
	}

	public void setPassportUsedQty(BigDecimal passportUsedQty) {
		this.passportUsedQty = passportUsedQty;
	}

	public String getDclUnitnm() {
		return dclUnitnm;
	}

	public void setDclUnitnm(String dclUnitnm) {
		this.dclUnitnm = dclUnitnm;
	}

	public String getLawfUnitnm() {
		return lawfUnitnm;
	}

	public void setLawfUnitnm(String lawfUnitnm) {
		this.lawfUnitnm = lawfUnitnm;
	}

	public String getDclCurrnm() {
		return dclCurrnm;
	}

	public void setDclCurrnm(String dclCurrnm) {
		this.dclCurrnm = dclCurrnm;
	}

	public String getNatnm() {
		return natnm;
	}

	public void setNatnm(String natnm) {
		this.natnm = natnm;
	}

	public String getLvyrlfModenm() {
		return lvyrlfModenm;
	}

	public void setLvyrlfModenm(String lvyrlfModenm) {
		this.lvyrlfModenm = lvyrlfModenm;
	}

	public String getSecdLawfUnitnm() {
		return secdLawfUnitnm;
	}

	public void setSecdLawfUnitnm(String secdLawfUnitnm) {
		this.secdLawfUnitnm = secdLawfUnitnm;
	}

	public String getGdsSpcfModelDesc() {
		return gdsSpcfModelDesc;
	}

	public void setGdsSpcfModelDesc(String gdsSpcfModelDesc) {
		this.gdsSpcfModelDesc = gdsSpcfModelDesc;
	}

	public String getPutrecSeqno() {
		return putrecSeqno;
	}
	public void setPutrecSeqno(String putrecSeqno) {
		this.putrecSeqno = putrecSeqno;
	}
}
