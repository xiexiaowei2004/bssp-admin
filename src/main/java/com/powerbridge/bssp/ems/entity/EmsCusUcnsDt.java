package com.powerbridge.bssp.ems.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 账册备案正式表单损耗
 * </p>
 *
 * @author jokylao
 * @since 2017-06-05
 */
@TableName("ems_cus_ucns_dt")
public class EmsCusUcnsDt implements Serializable {
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
     * 序号
     */
    @TableField("UCNS_SEQNO")
    private BigDecimal ucnsSeqno;
    /**
     * 成品序号
     */
    @TableField("ENDPRD_SEQNO")
    private BigDecimal endprdSeqno;
    /**
     * 成品料号
     */
    @TableField("ENDPRD_GDS_MTNO")
    private String endprdGdsMtno;
    /**
     * 成品商品编码
     */
    @TableField("ENDPRD_GDECD")
    private String endprdGdecd;
    /**
     * 成品商品名称
     */
    @TableField("ENDPRD_GDS_NM")
    private String endprdGdsNm;
    /**
     * 料件序号
     */
    @TableField("MTPCK_SEQNO")
    private BigDecimal mtpckSeqno;
    /**
     * 料件料号
     */
    @TableField("MTPCK_GDS_MTNO")
    private String mtpckGdsMtno;
    /**
     * 料件商品编码
     */
    @TableField("MTPCK_GDECD")
    private String mtpckGdecd;
    /**
     * 料件商品名称
     */
    @TableField("MTPCK_GDS_NM")
    private String mtpckGdsNm;
    /**
     * 单耗版本号
     */
    @TableField("UCNS_VERNO")
    private String ucnsVerno;
    /**
     * 单耗数量
     */
    @TableField("UCNS_QTY")
    private BigDecimal ucnsQty;
    /**
     * 净耗数量
     */
    @TableField("NET_USEUP_QTY")
    private BigDecimal netUseupQty;
    /**
     * 有形损耗率
     */
    @TableField("TGBL_LOSS_RATE")
    private BigDecimal tgblLossRate;
    /**
     * 无形损耗率
     */
    @TableField("INTGB_LOSS_RATE")
    private BigDecimal intgbLossRate;
    /**
     * 单耗申报状态代码
     */
    @TableField("UCNS_DCL_STUCD")
    private String ucnsDclStucd;
    /**
     * 修改标记代码
     */
    @TableField("MODF_MARKCD")
    private String modfMarkcd;
    /**
     * 保税料件比例
     */
    @TableField("BOND_MTPCK_PRPR")
    private BigDecimal bondMtpckPrpr;
    /**
     * 企业执行标记代码
     */
    @TableField("ETPS_EXE_MARKCD")
    private String etpsExeMarkcd;
    /**
     * 企业预录入编号
     */
    @TableField("ETPS_PREENT_NO")
    private String etpsPreentNo;
    /**
     * 单耗有效期
     */
    @TableField("UCNS_VALID_DATE")
    private String ucnsValidDate;
    /**
     * 申报时间
     */
    @TableField("DCL_TIME")
    private String dclTime;
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

    /**
     * 单耗申报状态名称
     */
    @TableField(exist = false)
    private String ucnsDclStunm;
    /**
     * 修改标记名称
     */
    @TableField(exist = false)
    private String modfMarknm;
    /**
     * 企业执行标记名称
     */
    @TableField(exist = false)
    private String etpsExeMarknm;
    /**
     * 入库时间
     */
    @TableField("INDB_TIME")
    private String indbTime;
    /**
     * 过滤条件，变更选择单耗时用
     */
    @TableField(exist = false)
    private String filterSelect;

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

    public BigDecimal getUcnsSeqno() {
        return ucnsSeqno;
    }

    public void setUcnsSeqno(BigDecimal ucnsSeqno) {
        this.ucnsSeqno = ucnsSeqno;
    }

    public BigDecimal getEndprdSeqno() {
        return endprdSeqno;
    }

    public void setEndprdSeqno(BigDecimal endprdSeqno) {
        this.endprdSeqno = endprdSeqno;
    }

    public String getEndprdGdsMtno() {
        return endprdGdsMtno;
    }

    public void setEndprdGdsMtno(String endprdGdsMtno) {
        this.endprdGdsMtno = endprdGdsMtno;
    }

    public String getEndprdGdecd() {
        return endprdGdecd;
    }

    public void setEndprdGdecd(String endprdGdecd) {
        this.endprdGdecd = endprdGdecd;
    }

    public String getEndprdGdsNm() {
        return endprdGdsNm;
    }

    public void setEndprdGdsNm(String endprdGdsNm) {
        this.endprdGdsNm = endprdGdsNm;
    }

    public BigDecimal getMtpckSeqno() {
        return mtpckSeqno;
    }

    public void setMtpckSeqno(BigDecimal mtpckSeqno) {
        this.mtpckSeqno = mtpckSeqno;
    }

    public String getMtpckGdsMtno() {
        return mtpckGdsMtno;
    }

    public void setMtpckGdsMtno(String mtpckGdsMtno) {
        this.mtpckGdsMtno = mtpckGdsMtno;
    }

    public String getMtpckGdecd() {
        return mtpckGdecd;
    }

    public void setMtpckGdecd(String mtpckGdecd) {
        this.mtpckGdecd = mtpckGdecd;
    }

    public String getMtpckGdsNm() {
        return mtpckGdsNm;
    }

    public void setMtpckGdsNm(String mtpckGdsNm) {
        this.mtpckGdsNm = mtpckGdsNm;
    }

    public String getUcnsVerno() {
        return ucnsVerno;
    }

    public void setUcnsVerno(String ucnsVerno) {
        this.ucnsVerno = ucnsVerno;
    }

    public BigDecimal getUcnsQty() {
        return ucnsQty;
    }

    public void setUcnsQty(BigDecimal ucnsQty) {
        this.ucnsQty = ucnsQty;
    }

    public BigDecimal getNetUseupQty() {
        return netUseupQty;
    }

    public void setNetUseupQty(BigDecimal netUseupQty) {
        this.netUseupQty = netUseupQty;
    }

    public BigDecimal getTgblLossRate() {
        return tgblLossRate;
    }

    public void setTgblLossRate(BigDecimal tgblLossRate) {
        this.tgblLossRate = tgblLossRate;
    }

    public BigDecimal getIntgbLossRate() {
        return intgbLossRate;
    }

    public void setIntgbLossRate(BigDecimal intgbLossRate) {
        this.intgbLossRate = intgbLossRate;
    }

    public String getUcnsDclStucd() {
        return ucnsDclStucd;
    }

    public void setUcnsDclStucd(String ucnsDclStucd) {
        this.ucnsDclStucd = ucnsDclStucd;
    }

    public String getModfMarkcd() {
        return modfMarkcd;
    }

    public void setModfMarkcd(String modfMarkcd) {
        this.modfMarkcd = modfMarkcd;
    }

    public BigDecimal getBondMtpckPrpr() {
        return bondMtpckPrpr;
    }

    public void setBondMtpckPrpr(BigDecimal bondMtpckPrpr) {
        this.bondMtpckPrpr = bondMtpckPrpr;
    }

    public String getEtpsExeMarkcd() {
        return etpsExeMarkcd;
    }

    public void setEtpsExeMarkcd(String etpsExeMarkcd) {
        this.etpsExeMarkcd = etpsExeMarkcd;
    }

    public String getEtpsPreentNo() {
        return etpsPreentNo;
    }

    public void setEtpsPreentNo(String etpsPreentNo) {
        this.etpsPreentNo = etpsPreentNo;
    }

    public String getUcnsValidDate() {
        return ucnsValidDate;
    }

    public void setUcnsValidDate(String ucnsValidDate) {
        this.ucnsValidDate = ucnsValidDate == "" ? null : ucnsValidDate;
    }

    public String getDclTime() {
        return dclTime;
    }

    public void setDclTime(String dclTime) {
        this.dclTime = dclTime;
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

    public String getModfMarknm() {
        return modfMarknm;
    }

    public void setModfMarknm(String modfMarknm) {
        this.modfMarknm = modfMarknm;
    }

    public String getEtpsExeMarknm() {
        return etpsExeMarknm;
    }

    public void setEtpsExeMarknm(String etpsExeMarknm) {
        this.etpsExeMarknm = etpsExeMarknm;
    }

    public String getUcnsDclStunm() {
        return ucnsDclStunm;
    }

    public void setUcnsDclStunm(String ucnsDclStunm) {
        this.ucnsDclStunm = ucnsDclStunm;
    }

    public String getIndbTime() {
        return indbTime;
    }

    public void setIndbTime(String indbTime) {
        this.indbTime = indbTime;
    }

    public String getFilterSelect() {
        return filterSelect;
    }

    public void setFilterSelect(String filterSelect) {
        this.filterSelect = filterSelect;
    }
}
