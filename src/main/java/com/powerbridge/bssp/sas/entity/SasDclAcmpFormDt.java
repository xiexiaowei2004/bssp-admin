package com.powerbridge.bssp.sas.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 业务申报随附单证表
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
@TableName("sas_dcl_acmp_form_dt")
public class SasDclAcmpFormDt implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = -2406548701963793140L;
	/**
     * 主键
     */
    @TableId("UID")
	private String uid;
    /**
     * 申请表编号
     */
	@TableField("SAS_DCL_NO")
	private String sasDclNo;
    /**
     * 随附单证序号
     */
	@TableField("ACMP_FORM_SEQNO")
	@NotNull(message = "随附单证序号不能为空")
	private Integer acmpFormSeqno;
    /**
     * 变更次数
     */
	@TableField("CHG_TMS_CNT")
	private Integer chgTmsCnt;
    /**
     * 随附单证类型代码
     */
	@TableField("FORM_TYPECD")
	private String formTypecd;
    /**
     * 随附单证编号
     */
	@TableField("FORM_NO")
	private String formNo;
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
     * 备注
     */
	@TableField("RMK")
	private String rmk;
    /**
     * 版本号
     */
	@TableField("VERSION")
	private String version;
    /**
     * 企业预录入编号
     */
	@TableField("ETPS_PREENT_NO")
	@NotEmpty(message = "企业预录入编号不能为空")
	private String etpsPreentNo;
    /**
     * 附件大小(K)
     */
	@TableField("ACMP_FORM_FILE_SIZE")
//	@NotNull(message = "附件大小不能为空")
	private BigDecimal acmpFormFileSize;
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

	public String getSasDclNo() {
		return sasDclNo;
	}

	public void setSasDclNo(String sasDclNo) {
		this.sasDclNo = sasDclNo;
	}

	public Integer getAcmpFormSeqno() {
		return acmpFormSeqno;
	}

	public void setAcmpFormSeqno(Integer acmpFormSeqno) {
		this.acmpFormSeqno = acmpFormSeqno;
	}
	
	

	public Integer getChgTmsCnt() {
		return chgTmsCnt;
	}

	public void setChgTmsCnt(Integer chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}
	
	

	public String getFormTypecd() {
		return formTypecd;
	}

	public void setFormTypecd(String formTypecd) {
		this.formTypecd = formTypecd;
	}

	public String getFormNo() {
		return formNo;
	}

	public void setFormNo(String formNo) {
		this.formNo = formNo;
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

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEtpsPreentNo() {
		return etpsPreentNo;
	}

	public void setEtpsPreentNo(String etpsPreentNo) {
		this.etpsPreentNo = etpsPreentNo;
	}

	public BigDecimal getAcmpFormFileSize() {
		return acmpFormFileSize;
	}

	public void setAcmpFormFileSize(BigDecimal acmpFormFileSize) {
		this.acmpFormFileSize = acmpFormFileSize;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
}
