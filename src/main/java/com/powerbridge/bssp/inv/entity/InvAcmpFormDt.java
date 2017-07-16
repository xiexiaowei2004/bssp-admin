package com.powerbridge.bssp.inv.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 保税核注清单随附单证明细表
 * </p>
 *
 * @author zsl
 * @since 2017-06-20
 */
@TableName("inv_acmp_form_dt")
public class InvAcmpFormDt implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId("UID")
	private String uid;
	/**
	 * 保税清单编号
	 */
	@TableField("BOND_INVT_NO")
	private String bondInvtNo;
	/**
	 * 变更次数
	 */
	@TableField("CHG_TMS_CNT")
	private BigDecimal chgTmsCnt;
	/**
	 * 随附单证序号
	 */
	@TableField("ACMP_FORM_SEQNO")
	private Integer acmpFormSeqno;
	/**
	 * 清单商品序号
	 */
	@TableField("INVT_GDS_SEQNO")
	private String invtGdsSeqno;
	/**
	 * 单证类型代码
	 */
	@TableField("FORM_TYPECD")
	private String formTypecd;
	/**
	 * 单证编号
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
	 * 附件大小(K)
	 */
	@TableField("ACMP_FORM_FILE_SIZE")
	private BigDecimal acmpFormFileSize;
	/**
	 * 企业内部清单编号
	 */
	@TableField("ETPS_INNER_INVT_NO")
	private String etpsInnerInvtNo;
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

	public String getBondInvtNo() {
		return bondInvtNo;
	}

	public void setBondInvtNo(String bondInvtNo) {
		this.bondInvtNo = bondInvtNo;
	}

	public BigDecimal getChgTmsCnt() {
		return chgTmsCnt;
	}

	public void setChgTmsCnt(BigDecimal chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}

	public Integer getAcmpFormSeqno() {
		return acmpFormSeqno;
	}

	public void setAcmpFormSeqno(Integer acmpFormSeqno) {
		this.acmpFormSeqno = acmpFormSeqno;
	}

	public String getInvtGdsSeqno() {
		return invtGdsSeqno;
	}

	public void setInvtGdsSeqno(String invtGdsSeqno) {
		this.invtGdsSeqno = invtGdsSeqno;
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

	public BigDecimal getAcmpFormFileSize() {
		return acmpFormFileSize;
	}

	public void setAcmpFormFileSize(BigDecimal acmpFormFileSize) {
		this.acmpFormFileSize = acmpFormFileSize;
	}

	public String getEtpsInnerInvtNo() {
		return etpsInnerInvtNo;
	}

	public void setEtpsInnerInvtNo(String etpsInnerInvtNo) {
		this.etpsInnerInvtNo = etpsInnerInvtNo;
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
