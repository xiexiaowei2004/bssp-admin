package com.powerbridge.bssp.ems_bws.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 物流账册正式表明细
 * </p>
 *
 * @author jokylao
 * @since 2017-06-09
 */
@TableName("ems_bws_dt")
public class EmsBwsDt implements Serializable {
	@TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("UID")
	private String uid;
    /**
     * 仓库账册号
     */
	@TableField("BWS_NO")
	private String bwsNo;
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
     * 最近入仓(核增）日期
     */
	@TableField("IN_DATE")
	private String inDate;
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
     * 商品规格型号
     */
	@TableField("GDS_SPCF_MODEL_DESC")
	private String gdsSpcfModelDesc;
    /**
     * 国别代码
     */
	@TableField("NATCD")
	private String natcd;
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
     * 平均美元单价
     */
	@TableField("AVG_PRICE")
	private BigDecimal avgPrice;
    /**
     * 库存美元总价
     */
	@TableField("TOTAL_AMT")
	private BigDecimal totalAmt;
    /**
     * 入仓数量
     */
	@TableField("IN_QTY")
	private BigDecimal inQty;
    /**
     * 入仓法定数量
     */
	@TableField("IN_LAWF_QTY")
	private BigDecimal inLawfQty;
    /**
     * 第二入仓法定数量
     */
	@TableField("IN_SECD_LAWF_QTY")
	private BigDecimal inSecdLawfQty;
    /**
     * 实增数量
     */
	@TableField("ACTL_INC_QTY")
	private BigDecimal actlIncQty;
    /**
     * 实减数量
     */
	@TableField("ACTL_REDC_QTY")
	private BigDecimal actlRedcQty;
    /**
     * 预增数量
     */
	@TableField("PREVD_INC_QTY")
	private BigDecimal prevdIncQty;
    /**
     * 预减数量
     */
	@TableField("PREVD_REDC_QTY")
	private BigDecimal prevdRedcQty;
    /**
     * 最近出仓(区）日期
     */
	@TableField("OUT_DATE")
	private String outDate;
    /**
     * 存储(监管）期限
     */
	@TableField("LIMIT_DATE")
	private String limitDate;
    /**
     * 设备入区方式代码
     */
	@TableField("IN_TYPE")
	private String inType;
    /**
     * 记账清单编号
     */
	@TableField("INVT_NO")
	private String invtNo;
    /**
     * 记账清单商品序号
     */
	@TableField("INVT_G_NO")
	private BigDecimal invtGNo;
    /**
     * 海关执行标记代码
     */
	@TableField("CUSM_EXE_MARKCD")
	private String cusmExeMarkcd;
    /**
     * 备注
     */
	@TableField("RMK")
	private String rmk;
    /**
     * 修改标记
     */
	@TableField("MODF_MARKCD")
	private String modfMarkcd;
    /**
     * 企业预录入编号 
     */
	@TableField("ETPS_PREENT_NO")
	private String etpsPreentNo;
    /**
     * 单据编号
     */
	@TableField("SEQ_NO")
	private String seqNo;


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getBwsNo() {
		return bwsNo;
	}

	public void setBwsNo(String bwsNo) {
		this.bwsNo = bwsNo;
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

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
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

	public String getGdsSpcfModelDesc() {
		return gdsSpcfModelDesc;
	}

	public void setGdsSpcfModelDesc(String gdsSpcfModelDesc) {
		this.gdsSpcfModelDesc = gdsSpcfModelDesc;
	}

	public String getNatcd() {
		return natcd;
	}

	public void setNatcd(String natcd) {
		this.natcd = natcd;
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

	public BigDecimal getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getInQty() {
		return inQty;
	}

	public void setInQty(BigDecimal inQty) {
		this.inQty = inQty;
	}

	public BigDecimal getInLawfQty() {
		return inLawfQty;
	}

	public void setInLawfQty(BigDecimal inLawfQty) {
		this.inLawfQty = inLawfQty;
	}

	public BigDecimal getInSecdLawfQty() {
		return inSecdLawfQty;
	}

	public void setInSecdLawfQty(BigDecimal inSecdLawfQty) {
		this.inSecdLawfQty = inSecdLawfQty;
	}

	public BigDecimal getActlIncQty() {
		return actlIncQty;
	}

	public void setActlIncQty(BigDecimal actlIncQty) {
		this.actlIncQty = actlIncQty;
	}

	public BigDecimal getActlRedcQty() {
		return actlRedcQty;
	}

	public void setActlRedcQty(BigDecimal actlRedcQty) {
		this.actlRedcQty = actlRedcQty;
	}

	public BigDecimal getPrevdIncQty() {
		return prevdIncQty;
	}

	public void setPrevdIncQty(BigDecimal prevdIncQty) {
		this.prevdIncQty = prevdIncQty;
	}

	public BigDecimal getPrevdRedcQty() {
		return prevdRedcQty;
	}

	public void setPrevdRedcQty(BigDecimal prevdRedcQty) {
		this.prevdRedcQty = prevdRedcQty;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}

	public String getInType() {
		return inType;
	}

	public void setInType(String inType) {
		this.inType = inType;
	}

	public String getInvtNo() {
		return invtNo;
	}

	public void setInvtNo(String invtNo) {
		this.invtNo = invtNo;
	}

	public BigDecimal getInvtGNo() {
		return invtGNo;
	}

	public void setInvtGNo(BigDecimal invtGNo) {
		this.invtGNo = invtGNo;
	}

	public String getCusmExeMarkcd() {
		return cusmExeMarkcd;
	}

	public void setCusmExeMarkcd(String cusmExeMarkcd) {
		this.cusmExeMarkcd = cusmExeMarkcd;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getModfMarkcd() {
		return modfMarkcd;
	}

	public void setModfMarkcd(String modfMarkcd) {
		this.modfMarkcd = modfMarkcd;
	}

	public String getEtpsPreentNo() {
		return etpsPreentNo;
	}

	public void setEtpsPreentNo(String etpsPreentNo) {
		this.etpsPreentNo = etpsPreentNo;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

}

