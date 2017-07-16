package com.powerbridge.bssp.entry.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 随附单证
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-10
 */
@TableName("entry_license_docus")
public class EntryLicenseDocus implements Serializable {

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
     * 单证代码
     */
	@TableField("DOCU_CODE")
	private String docuCode;
    /**
     * 单证编号
     */
	@TableField("CERT_CODE")
	private String certCode;


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

	public String getDocuCode() {
		return docuCode;
	}

	public void setDocuCode(String docuCode) {
		this.docuCode = docuCode;
	}

	public String getCertCode() {
		return certCode;
	}

	public void setCertCode(String certCode) {
		this.certCode = certCode;
	}
}
