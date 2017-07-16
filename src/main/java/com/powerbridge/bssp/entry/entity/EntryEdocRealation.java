package com.powerbridge.bssp.entry.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 电子随附单据关联关系信息
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-10
 */
@TableName("entry_edoc_realation")
public class EntryEdocRealation implements Serializable {

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
     * 随附单据编号
     */
	@TableField("EDOC_ID")
	private String edocId;
    /**
     * 随附单证类别
     */
	@TableField("EDOC_CODE")
	private String edocCode;
    /**
     * 随附单据格式类型
     */
	@TableField("EDOC_FOMAT_TYPE")
	private String edocFomatType;
    /**
     * 操作说明
     */
	@TableField("OP_NOTE")
	private String opNote;
    /**
     * 随附单据文件名称
     */
	@TableField("EDOC_COP_ID")
	private String edocCopId;
    /**
     * 所属单位海关编号
     */
	@TableField("EDOC_OWNER_CODE")
	private String edocOwnerCode;
    /**
     * 签名单位代码
     */
	@TableField("SIGN_UNIT")
	private String signUnit;
    /**
     * 签名时间
     */
	@TableField("SIGN_TIME")
	private String signTime;
    /**
     * 所属单位名称
     */
	@TableField("EDOC_OWNER_NAME")
	private String edocOwnerName;


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

	public String getEdocId() {
		return edocId;
	}

	public void setEdocId(String edocId) {
		this.edocId = edocId;
	}

	public String getEdocCode() {
		return edocCode;
	}

	public void setEdocCode(String edocCode) {
		this.edocCode = edocCode;
	}

	public String getEdocFomatType() {
		return edocFomatType;
	}

	public void setEdocFomatType(String edocFomatType) {
		this.edocFomatType = edocFomatType;
	}

	public String getOpNote() {
		return opNote;
	}

	public void setOpNote(String opNote) {
		this.opNote = opNote;
	}

	public String getEdocCopId() {
		return edocCopId;
	}

	public void setEdocCopId(String edocCopId) {
		this.edocCopId = edocCopId;
	}

	public String getEdocOwnerCode() {
		return edocOwnerCode;
	}

	public void setEdocOwnerCode(String edocOwnerCode) {
		this.edocOwnerCode = edocOwnerCode;
	}

	public String getSignUnit() {
		return signUnit;
	}

	public void setSignUnit(String signUnit) {
		this.signUnit = signUnit;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getEdocOwnerName() {
		return edocOwnerName;
	}

	public void setEdocOwnerName(String edocOwnerName) {
		this.edocOwnerName = edocOwnerName;
	}
}
