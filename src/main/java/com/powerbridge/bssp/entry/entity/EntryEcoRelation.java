package com.powerbridge.bssp.entry.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;


/**
 * <p>
 * 原产地证明表体
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-10
 */
@TableName("entry_eco_relation")
public class EntryEcoRelation implements Serializable {

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
     * 单据类型
     */
	@TableField("CERT_TYPE")
	private String certType;
    /**
     * 单据证书号
     */
	@TableField("ECO_CERT_NO")
	private String ecoCertNo;
    /**
     * 报关单商品项号
     */
	@TableField("DEC_G_NO")
	private String decGNo;
    /**
     * 原产地证书单证项号
     */
	@TableField("ECO_G_NO")
	private String ecoGNo;
    /**
     * 扩展字段
     */
	@TableField("EXTEND_FILED")
	private String extendFiled;


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

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getEcoCertNo() {
		return ecoCertNo;
	}

	public void setEcoCertNo(String ecoCertNo) {
		this.ecoCertNo = ecoCertNo;
	}

	public String getDecGNo() {
		return decGNo;
	}

	public void setDecGNo(String decGNo) {
		this.decGNo = decGNo;
	}

	public String getEcoGNo() {
		return ecoGNo;
	}

	public void setEcoGNo(String ecoGNo) {
		this.ecoGNo = ecoGNo;
	}

	public String getExtendFiled() {
		return extendFiled;
	}

	public void setExtendFiled(String extendFiled) {
		this.extendFiled = extendFiled;
	}

}
