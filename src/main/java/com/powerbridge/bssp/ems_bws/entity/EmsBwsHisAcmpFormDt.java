
package com.powerbridge.bssp.ems_bws.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 物流账册随附单证明细
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
@TableName("ems_bws_his_acmp_form_dt")
public class EmsBwsHisAcmpFormDt implements Serializable {
	@TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("UID")
	private String uid;
    /**
     * 账册编号
     */
	@TableField("BOP_NO")
	private String bopNo;
    /**
     * 变更次数
     */
	@TableField("CHG_TMS_CNT")
	private BigDecimal chgTmsCnt;
    /**
     * 随附单证序号
     */
	@TableField("ACMP_FORM_SEQNO")
	private BigDecimal acmpFormSeqno;
    /**
     * 随附单证类型代码
     */
	@TableField("ACMP_FORM_TYPECD")
	private String acmpFormTypecd;
    /**
     * 随附单证编号
     */
	@TableField("ACMP_FORM_NO")
	private String acmpFormNo;
    /**
     * 固定编号
     */
	@TableField("FIXD_NO")
	private String fixdNo;
    /**
     * 随附单证文件名称
     */
	@TableField("ACMP_FORM_FILE_NM")
	private String acmpFormFileNm;
    /**
     * 企业预录入编号 
     */
	@TableField("ETPS_PREENT_NO")
	private String etpsPreentNo;
    /**
     * 备注
     */
	@TableField("RMK")
	private String rmk;
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

	public String getBopNo() {
		return bopNo;
	}

	public void setBopNo(String bopNo) {
		this.bopNo = bopNo;
	}

	public BigDecimal getChgTmsCnt() {
		return chgTmsCnt;
	}

	public void setChgTmsCnt(BigDecimal chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}

	public BigDecimal getAcmpFormSeqno() {
		return acmpFormSeqno;
	}

	public void setAcmpFormSeqno(BigDecimal acmpFormSeqno) {
		this.acmpFormSeqno = acmpFormSeqno;
	}

	public String getAcmpFormTypecd() {
		return acmpFormTypecd;
	}

	public void setAcmpFormTypecd(String acmpFormTypecd) {
		this.acmpFormTypecd = acmpFormTypecd;
	}

	public String getAcmpFormNo() {
		return acmpFormNo;
	}

	public void setAcmpFormNo(String acmpFormNo) {
		this.acmpFormNo = acmpFormNo;
	}

	public String getFixdNo() {
		return fixdNo;
	}

	public void setFixdNo(String fixdNo) {
		this.fixdNo = fixdNo;
	}

	public String getAcmpFormFileNm() {
		return acmpFormFileNm;
	}

	public void setAcmpFormFileNm(String acmpFormFileNm) {
		this.acmpFormFileNm = acmpFormFileNm;
	}

	public String getEtpsPreentNo() {
		return etpsPreentNo;
	}

	public void setEtpsPreentNo(String etpsPreentNo) {
		this.etpsPreentNo = etpsPreentNo;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	

}
