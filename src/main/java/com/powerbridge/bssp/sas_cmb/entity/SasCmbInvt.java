package com.powerbridge.bssp.sas_cmb.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 耗料单清单列表
 * </p>
 *
 * @author jokylao
 * @since 2017-06-18
 */
@TableName("sas_cmb_invt")
public class SasCmbInvt implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("UID")
    private String uid;
    /**
     * 耗料单编号
     */
    @TableField("CMB_NO")
    private String cmbNo;
    /**
     * 变更次数
     */
    @TableField("CHG_TMS_CNT")
    private BigDecimal chgTmsCnt;
    /**
     * 保税清单编号
     */
    @TableField("BOND_INVT_NO")
    private String bondInvtNo;
    /**
     * 备注
     */
    @TableField("RMK")
    private String rmk;
    /**
     * 修改标志代码
     */
    @TableField("MODF_MARKCD")
    private String modfMarkcd;
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

    public String getCmbNo() {
        return cmbNo;
    }

    public void setCmbNo(String cmbNo) {
        this.cmbNo = cmbNo;
    }

    public BigDecimal getChgTmsCnt() {
        return chgTmsCnt;
    }

    public void setChgTmsCnt(BigDecimal chgTmsCnt) {
        this.chgTmsCnt = chgTmsCnt;
    }

    public String getBondInvtNo() {
        return bondInvtNo;
    }

    public void setBondInvtNo(String bondInvtNo) {
        this.bondInvtNo = bondInvtNo;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    public String getModfMarkcd() {
        return modfMarkcd;
    }

    public void setModfMarkcd(String modfMarkcd) {
        this.modfMarkcd = modfMarkcd;
    }

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}



}
