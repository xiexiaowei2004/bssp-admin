package com.powerbridge.bssp.erp.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 核注清单表体-预处理
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-19
 */
@TableName("erp_pre_bond_invt_dt")
public class ErpPreBondInvtDt implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("UID")
	private String uid;
	/**
	 * 清单企业内部编号
	 */
	@TableField("ETPS_INNER_INVT_NO")
	private String etpsInnerInvtNo;
    /**
     * 商品序号
     */
	@TableField("LIST_G_NO")
	private BigDecimal listGNo;
    /**
     * 备案序号
     */
	@TableField("EMS_G_NO")
	private BigDecimal emsGNo;
    /**
     * 商品料号
     */
	@TableField("COP_G_NO")
	private String copGNo;
    /**
     * 商品编码
     */
	@TableField("CODE_TS")
	private String codeTs;
    /**
     * 商品名称
     */
	@TableField("G_NAME")
	private String gName;
    /**
     * 商品规格型号
     */
	@TableField("G_MODEL")
	private String gModel;
    /**
     * 申报计量单位
     */
	@TableField("UNIT")
	private String unit;
    /**
     * 法定计量单位
     */
	@TableField("UNIT_1")
	private String unit1;
    /**
     * 法定第二计量单位
     */
	@TableField("UNIT_2")
	private String unit2;
    /**
     * 产销国(地区)
     */
	@TableField("COUNTRY_CODE")
	private String countryCode;
    /**
     * 企业申报单价
     */
	@TableField("DEC_PRICE")
	private BigDecimal decPrice;
    /**
     * 企业申报总价
     */
	@TableField("DEC_TOTAL")
	private BigDecimal decTotal;
    /**
     * 币制
     */
	@TableField("CURR")
	private String curr;
    /**
     * 法定数量
     */
	@TableField("QTY_1")
	private BigDecimal qty1;
    /**
     * 第二数量
     */
	@TableField("QTY_2")
	private BigDecimal qty2;
    /**
     * 申报数量
     */
	@TableField("QTY")
	private BigDecimal qty;
    /**
     * 法定单位比例
     */
	@TableField("FACTOR_1")
	private BigDecimal factor1;
    /**
     * 第二单位比例
     */
	@TableField("FACTOR_2")
	private BigDecimal factor2;
    /**
     * 重量比例因子
     */
	@TableField("FACTOR_WT")
	private BigDecimal factorWt;
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
     * 用途
     */
	@TableField("USE_TYPE")
	private String useType;
    /**
     * 征免方式
     */
	@TableField("DUTY_MODE")
	private String dutyMode;
    /**
     * 单耗版本号
     */
	@TableField("ENT_VERSION")
	private String entVersion;
    /**
     * 报关单商品序号
     */
	@TableField("ENTRY_GDS_SEQNO")
	private BigDecimal entryGdsSeqno;
    /**
     * 流转申请表序号
     */
	@TableField("APPLY_TB_SEQNO")
	private BigDecimal applyTbSeqno;
    /**
     * 备注
     */
	@TableField("RMK")
	private String rmk;

	/**
	 * 计量单位名称
	 */
	@TableField(exist = false)
	private String unitName;

	/**
	 * 法定单位名称
	 */
	@TableField(exist = false)
	private String unitName1;

	/**
	 * 第二单位名称
	 */
	@TableField(exist = false)
	private String unitName2;

	/**
	 * 产销国(地区)名称
	 */
	@TableField(exist = false)
	private String countryName;

	/**
	 * 币制名称
	 */
	@TableField(exist = false)
	private String currName;

	/**
	 * 征免方式
	 */
	@TableField(exist = false)
	private String dutySpec;


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public BigDecimal getListGNo() {
		return listGNo;
	}

	public void setListGNo(BigDecimal listGNo) {
		this.listGNo = listGNo;
	}

	public BigDecimal getEmsGNo() {
		return emsGNo;
	}

	public void setEmsGNo(BigDecimal emsGNo) {
		this.emsGNo = emsGNo;
	}

	public String getCopGNo() {
		return copGNo;
	}

	public void setCopGNo(String copGNo) {
		this.copGNo = copGNo;
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

	public String getgModel() {
		return gModel;
	}

	public void setgModel(String gModel) {
		this.gModel = gModel;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnit1() {
		return unit1;
	}

	public void setUnit1(String unit1) {
		this.unit1 = unit1;
	}

	public String getUnit2() {
		return unit2;
	}

	public void setUnit2(String unit2) {
		this.unit2 = unit2;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public BigDecimal getDecPrice() {
		return decPrice;
	}

	public void setDecPrice(BigDecimal decPrice) {
		this.decPrice = decPrice;
	}

	public BigDecimal getDecTotal() {
		return decTotal;
	}

	public void setDecTotal(BigDecimal decTotal) {
		this.decTotal = decTotal;
	}

	public String getCurr() {
		return curr;
	}

	public void setCurr(String curr) {
		this.curr = curr;
	}

	public BigDecimal getQty1() {
		return qty1;
	}

	public void setQty1(BigDecimal qty1) {
		this.qty1 = qty1;
	}

	public BigDecimal getQty2() {
		return qty2;
	}

	public void setQty2(BigDecimal qty2) {
		this.qty2 = qty2;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getFactor1() {
		return factor1;
	}

	public void setFactor1(BigDecimal factor1) {
		this.factor1 = factor1;
	}

	public BigDecimal getFactor2() {
		return factor2;
	}

	public void setFactor2(BigDecimal factor2) {
		this.factor2 = factor2;
	}

	public BigDecimal getFactorWt() {
		return factorWt;
	}

	public void setFactorWt(BigDecimal factorWt) {
		this.factorWt = factorWt;
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

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getDutyMode() {
		return dutyMode;
	}

	public void setDutyMode(String dutyMode) {
		this.dutyMode = dutyMode;
	}

	public String getEntVersion() {
		return entVersion;
	}

	public void setEntVersion(String entVersion) {
		this.entVersion = entVersion;
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

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getEtpsInnerInvtNo() {
		return etpsInnerInvtNo;
	}

	public void setEtpsInnerInvtNo(String etpsInnerInvtNo) {
		this.etpsInnerInvtNo = etpsInnerInvtNo;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitName1() {
		return unitName1;
	}

	public void setUnitName1(String unitName1) {
		this.unitName1 = unitName1;
	}

	public String getUnitName2() {
		return unitName2;
	}

	public void setUnitName2(String unitName2) {
		this.unitName2 = unitName2;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCurrName() {
		return currName;
	}

	public void setCurrName(String currName) {
		this.currName = currName;
	}

	public String getDutySpec() {
		return dutySpec;
	}

	public void setDutySpec(String dutySpec) {
		this.dutySpec = dutySpec;
	}
}
