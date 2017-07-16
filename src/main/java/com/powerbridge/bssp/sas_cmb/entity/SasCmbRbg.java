package com.powerbridge.bssp.sas_cmb.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 耗料单边角料表
 * </p>
 *
 * @author jokylao
 * @since 2017-06-18
 */
@TableName("sas_cmb_rbg")
public class SasCmbRbg implements Serializable{

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
     * 料件序号
     */
    @TableField("MTPCK_SEQNO")
    private BigDecimal mtpckSeqno;
    /**
     * 商品编码
     */
    @TableField("GDECD")
    private String gdecd;
    /**
     * 商品名称
     */
    @TableField("GDS_NM")
    private String gdsNm;
    /**
     * 规格型号
     */
    @TableField("GDS_SPCF_MODEL_DESC")
    private String gdsSpcfModelDesc;
    /**
     * 计量单位代码
     */
    @TableField("DCL_UNITCD")
    private String dclUnitcd;
    /**
     * 申报数量
     */
    @TableField("DCL_QTY")
    private BigDecimal dclQty;
    /**
     * 修改标志代码
     */
    @TableField("MODF_MARKCD")
    private String modfMarkcd;
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

    public BigDecimal getMtpckSeqno() {
        return mtpckSeqno;
    }

    public void setMtpckSeqno(BigDecimal mtpckSeqno) {
        this.mtpckSeqno = mtpckSeqno;
    }

    public String getGdecd() {
        return gdecd;
    }

    public void setGdecd(String gdecd) {
        this.gdecd = gdecd;
    }

    public String getGdsNm() {
        return gdsNm;
    }

    public void setGdsNm(String gdsNm) {
        this.gdsNm = gdsNm;
    }

    public String getGdsSpcfModelDesc() {
        return gdsSpcfModelDesc;
    }

    public void setGdsSpcfModelDesc(String gdsSpcfModelDesc) {
        this.gdsSpcfModelDesc = gdsSpcfModelDesc;
    }

    public String getDclUnitcd() {
        return dclUnitcd;
    }

    public void setDclUnitcd(String dclUnitcd) {
        this.dclUnitcd = dclUnitcd;
    }

    public BigDecimal getDclQty() {
        return dclQty;
    }

    public void setDclQty(BigDecimal dclQty) {
        this.dclQty = dclQty;
    }

    public String getModfMarkcd() {
        return modfMarkcd;
    }

    public void setModfMarkcd(String modfMarkcd) {
        this.modfMarkcd = modfMarkcd;
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
