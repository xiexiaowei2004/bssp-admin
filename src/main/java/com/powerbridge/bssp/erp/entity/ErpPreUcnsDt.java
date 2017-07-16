package com.powerbridge.bssp.erp.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * Bom预录入表
 * </p>
 *
 * @author lindapeng
 * @since 2017-06-02
 */
@TableName("erp_pre_ucns_dt")
public class ErpPreUcnsDt implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("UID")
	private String uid;
    /**
     * 成品序号
     */
	@TableField("ENDPRD_SEQNO")
	private BigDecimal endprdSeqno;
    /**
     * 料件序号
     */
	@TableField("MTPCK_SEQNO")
	private BigDecimal mtpckSeqno;
    /**
     * 单耗版本号
     */
	@TableField("UCNS_VERNO")
	private String ucnsVerno;
    /**
     * 单耗数量
     */
	@TableField("UCNS_QTY")
	private BigDecimal ucnsQty;
    /**
     * 净耗数量
     */
	@TableField("NET_USEUP_QTY")
	private BigDecimal netUseupQty;
    /**
     * 有形损耗率
     */
	@TableField("TGBL_LOSS_RATE")
	private BigDecimal tgblLossRate;
    /**
     * 无形损耗率
     */
	@TableField("INTGB_LOSS_RATE")
	private BigDecimal intgbLossRate;
    /**
     * 保税料件比例
     */
	@TableField("BOND_MTPCK_PRPR")
	private BigDecimal bondMtpckPrpr;
    /**
     * 是否生成备案
     */
	@TableField("SUCCESS_FLAG")
	private String successFlag;
    /**
     * 备注
     */
	@TableField("RMK")
	private String rmk;
    /**
     * 创建人代码
     */
	@TableField("CREATE_BY")
	private String createBy;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private String createTime;
    /**
     * 修改人代码
     */
	@TableField("UPDATE_BY")
	private String updateBy;
    /**
     * 修改时间
     */
	@TableField("UPDATE_TIME")
	private String updateTime;

	/**
	 * 用于查询  录入日期
	 */
	@TableField(exist = false)
	private String createTimeStart;

	/**
	 * 用于查询  成品料号
	 */
	@TableField(exist = false)
	private String exgDdsMtno;

	@TableField(exist = false)
	private String createTimeEnd;

	@TableField(exist = false)
	private String filterCond;


	/**
	 * 用于查询  成品名称
	 */
	@TableField(exist = false)
	private String exgGdsNm;

	/**
	 * 用于查询  成品商品编码
	 */
	@TableField(exist = false)
	private String exgGdecd;

	/**
	 * 用于查询  料件料号
	 */
	@TableField(exist = false)
	private String imgDdsMtno;

	/**
	 * 用于查询  料件名称
	 */
	@TableField(exist = false)
	private String imgGdsNm;

	/**
	 * 用于查询  料件商品编码
	 */
	@TableField(exist = false)
	private String imgGdecd;

	/**
	 * 申报（录入）企业代码
	 */
	@TableField("INPUT_COP_NO")
	private String inputCopNo;
	/**
	 * 申报（录入）企业社会信用代码
	 */
	@TableField("INPUT_ETPS_SCCD")
	private String inputEtpsSccd;
	/**
	 * 申报（录入）企业名称
	 */
	@TableField("INPUT_COP_NAME")
	private String inputCopName;

	/**
	 * 修改标记
	 */
	@TableField("MODF_MARKCD")
	private String modfMarkcd;

	/**
	 * 单耗申报状态
	 */
	@TableField("UCNS_DCL_STUCD")
	private String ucnsDclStucd;

	public String getExgDdsMtno() {
		return exgDdsMtno;
	}

	public void setExgDdsMtno(String exgDdsMtno) {
		this.exgDdsMtno = exgDdsMtno;
	}

	public String getFilterCond() {
		return filterCond;
	}

	public void setFilterCond(String filterCond) {
		this.filterCond = filterCond;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public BigDecimal getEndprdSeqno() {
		return endprdSeqno;
	}

	public void setEndprdSeqno(BigDecimal endprdSeqno) {
		this.endprdSeqno = endprdSeqno;
	}

	public BigDecimal getMtpckSeqno() {
		return mtpckSeqno;
	}

	public void setMtpckSeqno(BigDecimal mtpckSeqno) {
		this.mtpckSeqno = mtpckSeqno;
	}

	public String getUcnsVerno() {
		return ucnsVerno;
	}

	public void setUcnsVerno(String ucnsVerno) {
		this.ucnsVerno = ucnsVerno;
	}

	public BigDecimal getUcnsQty() {
		return ucnsQty;
	}

	public void setUcnsQty(BigDecimal ucnsQty) {
		this.ucnsQty = ucnsQty;
	}

	public BigDecimal getNetUseupQty() {
		return netUseupQty;
	}

	public void setNetUseupQty(BigDecimal netUseupQty) {
		this.netUseupQty = netUseupQty;
	}

	public BigDecimal getTgblLossRate() {
		return tgblLossRate;
	}

	public void setTgblLossRate(BigDecimal tgblLossRate) {
		this.tgblLossRate = tgblLossRate;
	}

	public BigDecimal getIntgbLossRate() {
		return intgbLossRate;
	}

	public void setIntgbLossRate(BigDecimal intgbLossRate) {
		this.intgbLossRate = intgbLossRate;
	}

	public BigDecimal getBondMtpckPrpr() {
		return bondMtpckPrpr;
	}

	public void setBondMtpckPrpr(BigDecimal bondMtpckPrpr) {
		this.bondMtpckPrpr = bondMtpckPrpr;
	}

	public String getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
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

	public String getExgGdsNm() {
		return exgGdsNm;
	}

	public void setExgGdsNm(String exgGdsNm) {
		this.exgGdsNm = exgGdsNm;
	}

	public String getExgGdecd() {
		return exgGdecd;
	}

	public void setExgGdecd(String exgGdecd) {
		this.exgGdecd = exgGdecd;
	}

	public String getImgDdsMtno() {
		return imgDdsMtno;
	}

	public void setImgDdsMtno(String imgDdsMtno) {
		this.imgDdsMtno = imgDdsMtno;
	}

	public String getImgGdsNm() {
		return imgGdsNm;
	}

	public void setImgGdsNm(String imgGdsNm) {
		this.imgGdsNm = imgGdsNm;
	}

	public String getImgGdecd() {
		return imgGdecd;
	}

	public void setImgGdecd(String imgGdecd) {
		this.imgGdecd = imgGdecd;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		if (createTimeStart!=null && !createTimeStart.equals("")) {
			this.createTimeStart = createTimeStart +" 00:00:00";
		}
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		if (createTimeEnd!=null && !createTimeEnd.equals("")) {
			this.createTimeEnd = createTimeEnd +" 23:59:59";
		}
	}

	public String getInputCopNo() {
		return inputCopNo;
	}

	public void setInputCopNo(String inputCopNo) {
		this.inputCopNo = inputCopNo;
	}

	public String getInputEtpsSccd() {
		return inputEtpsSccd;
	}

	public void setInputEtpsSccd(String inputEtpsSccd) {
		this.inputEtpsSccd = inputEtpsSccd;
	}

	public String getInputCopName() {
		return inputCopName;
	}

	public void setInputCopName(String inputCopName) {
		this.inputCopName = inputCopName;
	}

	public String getModfMarkcd() {
		return modfMarkcd;
	}

	public void setModfMarkcd(String modfMarkcd) {
		this.modfMarkcd = modfMarkcd;
	}

	public String getUcnsDclStucd() {
		return ucnsDclStucd;
	}

	public void setUcnsDclStucd(String ucnsDclStucd) {
		this.ucnsDclStucd = ucnsDclStucd;
	}
}
