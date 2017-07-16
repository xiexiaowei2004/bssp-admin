package com.powerbridge.bssp.dcr.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 账册核销随附单证明细正式表
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-03
 */
@TableName("dcr_adjacc_cus_acmp_form_dt")
public class DcrAdjaccCusAcmpFormDt implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 6325266718042221435L;
	/**
	 * 主键
	 */
	@TableId("UID")
	private String uid;
	/**
	 * 账册编号
	 */
	@TableField("EMS_NO")
	private String emsNo;
	/**
	 * 报核次数
	 */
	@TableField("CHG_TMS_CNT")
	private Integer chgTmsCnt;
	/**
	 * 随附单证序号
	 */
	@TableField("ACMP_FORM_SEQNO")
	private Integer acmpFormSeqno;
	/**
	 * 随附单证类型代码
	 */
	@TableField("ACMP_FORM_TYPECD")
	private String acmpFormTypecd;
	/**
	 * 随附单证编号
	 */
	@TableField("ACMP_FORM_NO")
	private String acmpFormno;
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
	 * 附件大小(K)
	 */
	@TableField("ACMP_FORM_FILE_SIZE")
	private BigDecimal acmpFormFileSize;
	/**
	 * 备注
	 */
	@TableField("RMK")
	private String rmk;
	/**
	 * 预录入统一编号
	 */
	@TableField("SEQ_NO")
	private String seqNo;


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getEmsNo() {
		return emsNo;
	}

	public void setEmsNo(String emsNo) {
		this.emsNo = emsNo;
	}

	public Integer getChgTmsCnt() {
		return chgTmsCnt;
	}

	public void setChgTmsCnt(Integer chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}

	public Integer getAcmpFormSeqno() {
		return acmpFormSeqno;
	}

	public void setAcmpFormSeqno(Integer acmpFormSeqno) {
		this.acmpFormSeqno = acmpFormSeqno;
	}

	public String getAcmpFormTypecd() {
		return acmpFormTypecd;
	}

	public void setAcmpFormTypecd(String acmpFormTypecd) {
		this.acmpFormTypecd = acmpFormTypecd;
	}

	public String getAcmpFormno() {
		return acmpFormno;
	}

	public void setAcmpFormno(String acmpFormno) {
		this.acmpFormno = acmpFormno;
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

	public BigDecimal getAcmpFormFileSize() {
		return acmpFormFileSize;
	}

	public void setAcmpFormFileSize(BigDecimal acmpFormFileSize) {
		this.acmpFormFileSize = acmpFormFileSize;
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
