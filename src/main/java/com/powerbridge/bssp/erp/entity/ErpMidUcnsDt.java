package com.powerbridge.bssp.erp.entity;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * Bom中间表
 * </p>
 *
 * @author lindapeng
 * @since 2017-06-02
 */
@TableName("erp_mid_ucns_dt")
public class ErpMidUcnsDt implements Serializable {

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
	 * 单耗申报状态代码
	 */
	@TableField("UCNS_DCL_STUCD")
	private String ucnsDclStucd;


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

	public String getUcnsDclStucd() {
		return ucnsDclStucd;
	}

	public void setUcnsDclStucd(String ucnsDclStucd) {
		this.ucnsDclStucd = ucnsDclStucd;
	}
}
