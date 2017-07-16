package com.powerbridge.bssp.sas.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 业务申报单耗表
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
@TableName("sas_dcl_ucns_dt")
public class SasDclUcnsDt implements Serializable {

	private static final long serialVersionUID = -1284170622713125925L;
	/**
     * 主键
     */
    @TableId("UID")
	private String uid;
    /**
     * 申报表编号
     */
	@TableField("SAS_DCL_NO")
	private String sasDclNo;
    /**
     * 变更次数
     */
	@TableField("CHG_TMS_CNT")
	private Integer chgTmsCnt;
    /**
     * 成品申报序号
     */
	@TableField("ENDPRD_SEQNO")
	@NotNull(message = "成品申报序号不能为空")
	private Integer endprdSeqno;
    /**
     * 料件申报序号
     */
	@TableField("MTPCK_SEQNO")
	@NotNull(message = "料件申报序号不能为空")
	private Integer mtpckSeqno;
    /**
     * 净耗数量
     */
	@TableField("NET_USEUP_QTY")
	@NotNull(message = "净耗数量不能为空")
	private BigDecimal netUseupQty;
    /**
     * 损耗率
     */
	@TableField("LOSS_RATE")
	@NotNull(message = "损耗率不能为空")
//	@Digits(integer = 10, fraction =  9,message = "损耗率精度超出限制")
	private BigDecimal lossRate;
    /**
     * 修改标志代码
     */
	@TableField("MODF_MARKCD")
	private String modfMarkcd;
	/**
	 * 修改标志名称
	 */
	@TableField(exist = false)
	private String modfMarknm;
    /**
     * 企业预录入编号
     */
	@TableField("ETPS_PREENT_NO")
	@NotEmpty(message = "企业预录入编号不能为空")
	private String etpsPreentNo;
    /**
     * 单据编号
     */
	@TableField("SEQ_NO")
	private String seqNo;

	/**
	 * 备用
	 */
	@TableField(value="COL1")
	private String col1;

	/**
	 * 备用
	 */
	@TableField(value="COL2")
	private String col2;

	/**
	 * 备用
	 */
	@TableField(value="COL3")
	private String col3;

	/**
	 * 备用
	 */
	@TableField(value="COL4")
	private Double col4;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSasDclNo() {
		return sasDclNo;
	}

	public void setSasDclNo(String sasDclNo) {
		this.sasDclNo = sasDclNo;
	}

	public Integer getChgTmsCnt() {
		return chgTmsCnt;
	}

	public void setChgTmsCnt(Integer chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}

	public Integer getEndprdSeqno() {
		return endprdSeqno;
	}

	public void setEndprdSeqno(Integer endprdSeqno) {
		this.endprdSeqno = endprdSeqno;
	}

	public Integer getMtpckSeqno() {
		return mtpckSeqno;
	}

	public void setMtpckSeqno(Integer mtpckSeqno) {
		this.mtpckSeqno = mtpckSeqno;
	}

	public BigDecimal getNetUseupQty() {
		return netUseupQty;
	}

	public void setNetUseupQty(BigDecimal netUseupQty) {
		this.netUseupQty = netUseupQty;
	}

	public BigDecimal getLossRate() {
		return lossRate;
	}

	public void setLossRate(BigDecimal lossRate) {
		this.lossRate = lossRate;
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

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 =  DateUtil.timeSwitch(col3);
	}

	public Double getCol4() {
		return col4;
	}

	public void setCol4(Double col4) {
		this.col4 = col4;
	}

	public String getModfMarknm() {
		return modfMarknm;
	}

	public void setModfMarknm(String modfMarknm) {
		this.modfMarknm = modfMarknm;
	}
}
