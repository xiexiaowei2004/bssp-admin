package com.powerbridge.bssp.entry.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 进口/出口报关单表体
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-10
 */
@TableName("entry_list")
public class EntryList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableField("UID")
	private String uid;
    /**
     * 数据中心统一编号
     */
	@TableField("SEQ_NO")
	private String seqNo;
    /**
     * 预录入编号
     */
	@TableField("ETPS_PREENT_NO")
	private String etpsPreentNo;
    /**
     * 归类标志
     */
	@TableField("CLASS_MARK")
	private String classMark;
    /**
     * 商品编号
     */
	@TableField("CODE_T_S")
	private String codeTS;
    /**
     * 备案序号
     */
	@TableField("CONTR_ITEM")
	private BigDecimal contrItem;
    /**
     * 申报单价
     */
	@TableField("DECL_PRICE")
	private BigDecimal declPrice;
    /**
     * 征减免税方式
     */
	@TableField("DUTY_MODE")
	private String dutyMode;
    /**
     * 申报计量单位与法定单位比例因子
     */
	@TableField("FACTOR")
	private BigDecimal factor;
    /**
     * 商品规格、型号
     */
	@TableField("G_MODEL")
	private String gModel;
    /**
     * 商品名称
     */
	@TableField("G_NAME")
	private String gName;
    /**
     * 商品序号
     */
	@TableField("G_NO")
	private BigDecimal gNo;
    /**
     * 原产地
     */
	@TableField("ORIGIN_COUNTRY")
	private String originCountry;
    /**
     * 成交币制
     */
	@TableField("TRADE_CURR")
	private String tradeCurr;
    /**
     * 申报总价
     */
	@TableField("DECL_TOTAL")
	private BigDecimal declTotal;
    /**
     * 申报数量
     */
	@TableField("G_QTY")
	private BigDecimal gQty;
    /**
     * 第一法定数量
     */
	@TableField("FIRST_QTY")
	private BigDecimal firstQty;
    /**
     * 第二法定数量
     */
	@TableField("SECOND_QTY")
	private BigDecimal secondQty;
    /**
     * 申报计量单位
     */
	@TableField("G_UNIT")
	private String gUnit;
    /**
     * 第一计量单位
     */
	@TableField("FIRST_UNIT")
	private String firstUnit;
    /**
     * 第二计量单位
     */
	@TableField("SECOND_UNIT")
	private String secondUnit;
    /**
     * 用途/生产厂家
     */
	@TableField("USE_TO")
	private String useTo;
    /**
     * 工缴费
     */
	@TableField("WORK_USD")
	private BigDecimal workUsd;
    /**
     * 货号
     */
	@TableField("EXG_NO")
	private String exgNo;
    /**
     * 版本号
     */
	@TableField("EXG_VERSION")
	private BigDecimal exgVersion;
    /**
     * 最终目的国(地区)
     */
	@TableField("DESTINATION_COUNTRY")
	private String destinationCountry;


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getEtpsPreentNo() {
		return etpsPreentNo;
	}

	public void setEtpsPreentNo(String etpsPreentNo) {
		this.etpsPreentNo = etpsPreentNo;
	}

	public String getClassMark() {
		return classMark;
	}

	public void setClassMark(String classMark) {
		this.classMark = classMark;
	}

	public String getCodeTS() {
		return codeTS;
	}

	public void setCodeTS(String codeTS) {
		this.codeTS = codeTS;
	}

	public BigDecimal getContrItem() {
		return contrItem;
	}

	public void setContrItem(BigDecimal contrItem) {
		this.contrItem = contrItem;
	}

	public BigDecimal getDeclPrice() {
		return declPrice;
	}

	public void setDeclPrice(BigDecimal declPrice) {
		this.declPrice = declPrice;
	}

	public String getDutyMode() {
		return dutyMode;
	}

	public void setDutyMode(String dutyMode) {
		this.dutyMode = dutyMode;
	}

	public BigDecimal getFactor() {
		return factor;
	}

	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}

	public String getgModel() {
		return gModel;
	}

	public void setgModel(String gModel) {
		this.gModel = gModel;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public BigDecimal getgNo() {
		return gNo;
	}

	public void setgNo(BigDecimal gNo) {
		this.gNo = gNo;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getTradeCurr() {
		return tradeCurr;
	}

	public void setTradeCurr(String tradeCurr) {
		this.tradeCurr = tradeCurr;
	}

	public BigDecimal getDeclTotal() {
		return declTotal;
	}

	public void setDeclTotal(BigDecimal declTotal) {
		this.declTotal = declTotal;
	}

	public BigDecimal getgQty() {
		return gQty;
	}

	public void setgQty(BigDecimal gQty) {
		this.gQty = gQty;
	}

	public BigDecimal getFirstQty() {
		return firstQty;
	}

	public void setFirstQty(BigDecimal firstQty) {
		this.firstQty = firstQty;
	}

	public BigDecimal getSecondQty() {
		return secondQty;
	}

	public void setSecondQty(BigDecimal secondQty) {
		this.secondQty = secondQty;
	}

	public String getgUnit() {
		return gUnit;
	}

	public void setgUnit(String gUnit) {
		this.gUnit = gUnit;
	}

	public String getFirstUnit() {
		return firstUnit;
	}

	public void setFirstUnit(String firstUnit) {
		this.firstUnit = firstUnit;
	}

	public String getSecondUnit() {
		return secondUnit;
	}

	public void setSecondUnit(String secondUnit) {
		this.secondUnit = secondUnit;
	}

	public String getUseTo() {
		return useTo;
	}

	public void setUseTo(String useTo) {
		this.useTo = useTo;
	}

	public BigDecimal getWorkUsd() {
		return workUsd;
	}

	public void setWorkUsd(BigDecimal workUsd) {
		this.workUsd = workUsd;
	}

	public String getExgNo() {
		return exgNo;
	}

	public void setExgNo(String exgNo) {
		this.exgNo = exgNo;
	}

	public BigDecimal getExgVersion() {
		return exgVersion;
	}

	public void setExgVersion(BigDecimal exgVersion) {
		this.exgVersion = exgVersion;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}
}
