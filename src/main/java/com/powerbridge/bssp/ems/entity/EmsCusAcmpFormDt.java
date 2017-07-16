package com.powerbridge.bssp.ems.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 账册备案正式表随附单证
 * </p>
 *
 * @author simon.xie
 * @since 2017-05-23
 */
@TableName("ems_cus_acmp_form_dt")
public class EmsCusAcmpFormDt implements Serializable {
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
	@TableField("EMS_NO")
	private String emsNo;
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
	 * 随附单证类型名称
	 */
	@TableField(exist = false)
	private String acmpFormTypenm;
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
	 * 附件大小(K)
	 */
	@TableField("ACMP_FORM_FILE_SIZE")
	private BigDecimal acmpFormFileSize;
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
	/**
	 * 创建人代码
	 */
	@TableField("CREATE_BY")
	private String createBy;
	/**
	 * 创建人名称
	 */
	@TableField("CREATE_NAME")
	private String createName;
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
	 * 修改人名称
	 */
	@TableField("UPDATE_NAME")
	private String updateName;
	/**
	 * 修改时间
	 */
	@TableField("UPDATE_TIME")
	private String updateTime;


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

	public String getAcmpFormTypenm() {
		return acmpFormTypenm;
	}

	public void setAcmpFormTypenm(String acmpFormTypenm) {
		this.acmpFormTypenm = acmpFormTypenm;
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

	public BigDecimal getAcmpFormFileSize() {
		return acmpFormFileSize;
	}

	public void setAcmpFormFileSize(BigDecimal acmpFormFileSize) {
		this.acmpFormFileSize = acmpFormFileSize;
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
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

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
