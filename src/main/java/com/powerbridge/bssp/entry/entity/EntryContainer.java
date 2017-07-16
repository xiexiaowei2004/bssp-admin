package com.powerbridge.bssp.entry.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 报关单集装箱
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-10
 */
@TableName("entry_container")
public class EntryContainer implements Serializable {

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
     * 集装箱号
     */
	@TableField("CONTAINER_ID")
	private String containerId;
    /**
     * 集装箱规格
     */
	@TableField("CONTAINER_MD")
	private String containerMd;
    /**
     * 集装箱自重
     */
	@TableField("CONTAINER_WT")
	private BigDecimal containerWt;


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

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public String getContainerMd() {
		return containerMd;
	}

	public void setContainerMd(String containerMd) {
		this.containerMd = containerMd;
	}

	public BigDecimal getContainerWt() {
		return containerWt;
	}

	public void setContainerWt(BigDecimal containerWt) {
		this.containerWt = containerWt;
	}
}
