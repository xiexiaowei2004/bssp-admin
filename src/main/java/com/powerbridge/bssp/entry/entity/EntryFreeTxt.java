package com.powerbridge.bssp.entry.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 自由文本
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-10
 */
@TableName("entry_free_txt")
public class EntryFreeTxt implements Serializable {

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
     * 监管仓号
     */
	@TableField("BON_NO")
	private String bonNo;
    /**
     * 货场代码
     */
	@TableField("CUS_FIE")
	private String cusFie;
    /**
     * 报关员联系方式
     */
	@TableField("DEC_BPNO")
	private String decBpno;
    /**
     * 报关员号
     */
	@TableField("DEC_NO")
	private String decNo;
    /**
     * 关联报关单号
     */
	@TableField("REL_ID")
	private String relId;
    /**
     * 关联备案号
     */
	@TableField("REL_MAN_NO")
	private String relManNo;
    /**
     * 航次号
     */
	@TableField("VOY_NO")
	private String voyNo;


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

	public String getBonNo() {
		return bonNo;
	}

	public void setBonNo(String bonNo) {
		this.bonNo = bonNo;
	}

	public String getCusFie() {
		return cusFie;
	}

	public void setCusFie(String cusFie) {
		this.cusFie = cusFie;
	}

	public String getDecBpno() {
		return decBpno;
	}

	public void setDecBpno(String decBpno) {
		this.decBpno = decBpno;
	}

	public String getDecNo() {
		return decNo;
	}

	public void setDecNo(String decNo) {
		this.decNo = decNo;
	}

	public String getRelId() {
		return relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}

	public String getRelManNo() {
		return relManNo;
	}

	public void setRelManNo(String relManNo) {
		this.relManNo = relManNo;
	}

	public String getVoyNo() {
		return voyNo;
	}

	public void setVoyNo(String voyNo) {
		this.voyNo = voyNo;
	}
}
