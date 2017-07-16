package com.powerbridge.bssp.dcr.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：DcrChgoffExg
 * 类描述：dcr_chgoff_exg 账册报核成品
 * 创建人：haihuihuang
 * 创建时间：2017年5月22日 上午15:41:00
 */
@TableName("dcr_chgoff_exg")
public class DcrChgoffExg implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = -3158036884296966506L;
    /**
     * 主键
     */
    @TableId("UID")
    private String uid;
    /**
     * 账册编号
     */
    @TableField("EMS_NO")
    @NotEmpty(message = "账册编号不能为空")
    private String emsNo;
    /**
     * 报核次数
     */
    @TableField("CHG_TMS_CNT")
    @NotNull(message = "报核次数不能为空")
    private Integer chgTmsCnt;
    /**
     * 料件成品标记
     */
    @TableField("TYPECD")
    @NotEmpty(message = "料件成品标记")
    private String typecd;
    /**
     * 序号
     */
    @TableField("GDS_SEQNO")
    @NotNull(message = "序号不能为空")
    private Integer gdsSeqno;
    /**
     * 料件备案序号
     */
    @TableField("G_NO")
    @NotNull(message = "料件备案序号不能为空")
    private Integer gNo;
    /**
     * 料号
     */
    @TableField("COP_G_NO")
    private String copGNo;
    /**
     * 商品编码
     */
    @TableField("CODE_TS")
    private String codeTs;
    /**
     * 商品名称
     */
    @TableField("G_NAME")
    private String gName;
    /**
     * 计量单位
     */
    @TableField("UNIT")
    private String unit;
    /**
     * 期初数量
     */
    @TableField("FIRST_QTY")
    private BigDecimal firstQty;
    /**
     * 应剩余数量
     */
    @TableField("SHD_REMAIN_QTY")
    private BigDecimal shdRemainQty;
    /**
     * 应剩余总金额
     */
    @TableField("SHD_REMAIN_TOTAL_AMT")
    private BigDecimal shdRemainTotalAmt;
    /**
     * 实际剩余数量
     */
    @TableField("ACTL_REMAIN_QTY")
    @NotNull(message = "实际剩余数量不能为空")
    private BigDecimal actlRemainQty;
    /**
     * 实际剩余总金额
     */
    @TableField("ACTL_REMAIN_TOTAL_AMT")
    private BigDecimal actlRemainTotalAmt;
    /**
     * 消耗数量
     */
    @TableField("CSM_TOTAL_QTY")
    private BigDecimal csmTotalQty;
    /**
     * 消耗总金额
     */
    @TableField("CSM_TOTAL_AMT")
    private BigDecimal csmTotalAmt;
    /**
     * 进口或出口总数量
     */
    @TableField("IMP_OR_EXP_TOTAL_QTY")
    private BigDecimal impOrExpTotalQty;
    /**
     * 内销数量
     */
    @TableField("SDIN_QTY")
    private Integer sdinQty;
    /**
     * 内销总金额
     */
    @TableField("SDIN_TOTAL_AMT")
    private BigDecimal sdinTotalAmt;
    /**
     * 成品退换出口数量
     */
    @TableField("ENDPRD_RETN_EXCH_EXP_QTY")
    private BigDecimal endprdRetnExchExpQty;
    /**
     * 成品退换进口数量
     */
    @TableField("ENDPRD_RETN_EXCH_IMP_QTY")
    private BigDecimal endprdRetnExchImpQty;
    /**
     * 边角料数量
     */
    @TableField("LFMT_IMPR_QTY")
    private BigDecimal lfmtImprQty;
    /**
     * 边角料总金额
     */
    @TableField("LFMT_IMPR_TOTAL_AMT")
    private BigDecimal lfmtImprTotalAmt;
    /**
     * 深加工结转进出口数量
     */
    @TableField("DPRCS_CRDW_IMPEXP_QTY")
    private BigDecimal dprcsCrdwImpexpQty;
    /**
     * 复出口数量
     */
    @TableField("REEXP_QTY")
    private BigDecimal reexpQty;
    /**
     * 剩余料件转出数量
     */
    @TableField("REMAIN_MTPCK_TRFOUT_QTY")
    private BigDecimal remainMtpckTrfoutQty;
    /**
     * 料件成品销毁数量
     */
    @TableField("MTPCK_ENDPRD_DSTRY_QTY")
    private BigDecimal mtpckEndprdDstryQty;
    /**
     * 边角料销毁数量
     */
    @TableField("LFMT_DSTRY_QTY")
    private BigDecimal lfmtDstryQty;
    /**
     * 核增数量
     */
    @TableField("VRFADD_QTY")
    private BigDecimal vrfaddQty;
    /**
     * 核减数量
     */
    @TableField("VRFRED_QTY")
    private BigDecimal vrfredQty;
    /**
     * 有形损耗数量
     */
    @TableField("TGBL_LOSS_QTY")
    private BigDecimal tgblLossQty;
    /**
     * 无形损耗数量
     */
    @TableField("INTGB_LOSS_QTY")
    private BigDecimal intgbLossQty;
    /**
     * 差异数量
     */
    @TableField("DSML_QTY")
    private BigDecimal dsmlQty;
    /**
     * 差异金额
     */
    @TableField("DSML_AMT")
    private BigDecimal dsmlAmt;
    /**
     * 本期结余数量
     */
    @TableField("TCYCLE_BAL_QTY")
    private BigDecimal tcycleBalQty;
    /**
     * 报关清单平均单价金额
     */
    @TableField("DCLCUS_INVT_AVG_UPRC_AMT")
    private BigDecimal dclcusInvtAvgUprcAmt;
    /**
     * 差异率
     */
    @TableField("DSML_RATE")
    private BigDecimal dsmlRate;
    /**
     * 核销周期初数量
     */
    @TableField("VCLR_PRID_INIT_QTY")
    private BigDecimal vclrPridInitQty;
    /**
     * 第一备用标记代码
     */
    @TableField("FST_SPARE_MARKCD")
    private String fstSpareMarkcd;
    /**
     * 备用金额
     */
    @TableField("SPARE_AMT")
    private BigDecimal spareAmt;
    /**
     * 备用数量
     */
    @TableField("SPARE_QTY")
    private BigDecimal spareQty;
    /**
     * 备注
     */
    @TableField("RMK")
    private String rmk;
    /**
     * 预录入统一编号
     */
    @TableField("SEQ_NO")
    @NotEmpty(message = "预录入统一编号不能为空")
    private String seqNo;

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

    public Integer getChgTmsCnt() {
        return chgTmsCnt;
    }

    public void setChgTmsCnt(Integer chgTmsCnt) {
        this.chgTmsCnt = chgTmsCnt;
    }

    public String getTypecd() {
        return typecd;
    }

    public void setTypecd(String typecd) {
        this.typecd = typecd;
    }

    public Integer getGdsSeqno() {
        return gdsSeqno;
    }

    public void setGdsSeqno(Integer gdsSeqno) {
        this.gdsSeqno = gdsSeqno;
    }

    public Integer getgNo() {
        return gNo;
    }

    public void setgNo(Integer gNo) {
        this.gNo = gNo;
    }

    public String getCopGNo() {
        return copGNo;
    }

    public void setCopGNo(String copGNo) {
        this.copGNo = copGNo;
    }

    public String getCodeTs() {
        return codeTs;
    }

    public void setCodeTs(String codeTs) {
        this.codeTs = codeTs;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getFirstQty() {
        return firstQty;
    }

    public void setFirstQty(BigDecimal firstQty) {
        this.firstQty = firstQty;
    }

    public BigDecimal getShdRemainQty() {
        return shdRemainQty;
    }

    public void setShdRemainQty(BigDecimal shdRemainQty) {
        this.shdRemainQty = shdRemainQty;
    }

    public BigDecimal getShdRemainTotalAmt() {
        return shdRemainTotalAmt;
    }

    public void setShdRemainTotalAmt(BigDecimal shdRemainTotalAmt) {
        this.shdRemainTotalAmt = shdRemainTotalAmt;
    }

    public BigDecimal getActlRemainQty() {
        return actlRemainQty;
    }

    public void setActlRemainQty(BigDecimal actlRemainQty) {
        this.actlRemainQty = actlRemainQty;
    }

    public BigDecimal getActlRemainTotalAmt() {
        return actlRemainTotalAmt;
    }

    public void setActlRemainTotalAmt(BigDecimal actlRemainTotalAmt) {
        this.actlRemainTotalAmt = actlRemainTotalAmt;
    }

    public BigDecimal getCsmTotalQty() {
        return csmTotalQty;
    }

    public void setCsmTotalQty(BigDecimal csmTotalQty) {
        this.csmTotalQty = csmTotalQty;
    }

    public BigDecimal getCsmTotalAmt() {
        return csmTotalAmt;
    }

    public void setCsmTotalAmt(BigDecimal csmTotalAmt) {
        this.csmTotalAmt = csmTotalAmt;
    }

    public BigDecimal getImpOrExpTotalQty() {
        return impOrExpTotalQty;
    }

    public void setImpOrExpTotalQty(BigDecimal impOrExpTotalQty) {
        this.impOrExpTotalQty = impOrExpTotalQty;
    }

    public Integer getSdinQty() {
        return sdinQty;
    }

    public void setSdinQty(Integer sdinQty) {
        this.sdinQty = sdinQty;
    }

    public BigDecimal getSdinTotalAmt() {
        return sdinTotalAmt;
    }

    public void setSdinTotalAmt(BigDecimal sdinTotalAmt) {
        this.sdinTotalAmt = sdinTotalAmt;
    }

    public BigDecimal getEndprdRetnExchExpQty() {
        return endprdRetnExchExpQty;
    }

    public void setEndprdRetnExchExpQty(BigDecimal endprdRetnExchExpQty) {
        this.endprdRetnExchExpQty = endprdRetnExchExpQty;
    }

    public BigDecimal getEndprdRetnExchImpQty() {
        return endprdRetnExchImpQty;
    }

    public void setEndprdRetnExchImpQty(BigDecimal endprdRetnExchImpQty) {
        this.endprdRetnExchImpQty = endprdRetnExchImpQty;
    }

    public BigDecimal getLfmtImprQty() {
        return lfmtImprQty;
    }

    public void setLfmtImprQty(BigDecimal lfmtImprQty) {
        this.lfmtImprQty = lfmtImprQty;
    }

    public BigDecimal getLfmtImprTotalAmt() {
        return lfmtImprTotalAmt;
    }

    public void setLfmtImprTotalAmt(BigDecimal lfmtImprTotalAmt) {
        this.lfmtImprTotalAmt = lfmtImprTotalAmt;
    }

    public BigDecimal getDprcsCrdwImpexpQty() {
        return dprcsCrdwImpexpQty;
    }

    public void setDprcsCrdwImpexpQty(BigDecimal dprcsCrdwImpexpQty) {
        this.dprcsCrdwImpexpQty = dprcsCrdwImpexpQty;
    }

    public BigDecimal getReexpQty() {
        return reexpQty;
    }

    public void setReexpQty(BigDecimal reexpQty) {
        this.reexpQty = reexpQty;
    }

    public BigDecimal getRemainMtpckTrfoutQty() {
        return remainMtpckTrfoutQty;
    }

    public void setRemainMtpckTrfoutQty(BigDecimal remainMtpckTrfoutQty) {
        this.remainMtpckTrfoutQty = remainMtpckTrfoutQty;
    }

    public BigDecimal getMtpckEndprdDstryQty() {
        return mtpckEndprdDstryQty;
    }

    public void setMtpckEndprdDstryQty(BigDecimal mtpckEndprdDstryQty) {
        this.mtpckEndprdDstryQty = mtpckEndprdDstryQty;
    }

    public BigDecimal getLfmtDstryQty() {
        return lfmtDstryQty;
    }

    public void setLfmtDstryQty(BigDecimal lfmtDstryQty) {
        this.lfmtDstryQty = lfmtDstryQty;
    }

    public BigDecimal getVrfaddQty() {
        return vrfaddQty;
    }

    public void setVrfaddQty(BigDecimal vrfaddQty) {
        this.vrfaddQty = vrfaddQty;
    }

    public BigDecimal getVrfredQty() {
        return vrfredQty;
    }

    public void setVrfredQty(BigDecimal vrfredQty) {
        this.vrfredQty = vrfredQty;
    }

    public BigDecimal getTgblLossQty() {
        return tgblLossQty;
    }

    public void setTgblLossQty(BigDecimal tgblLossQty) {
        this.tgblLossQty = tgblLossQty;
    }

    public BigDecimal getIntgbLossQty() {
        return intgbLossQty;
    }

    public void setIntgbLossQty(BigDecimal intgbLossQty) {
        this.intgbLossQty = intgbLossQty;
    }

    public BigDecimal getDsmlQty() {
        return dsmlQty;
    }

    public void setDsmlQty(BigDecimal dsmlQty) {
        this.dsmlQty = dsmlQty;
    }

    public BigDecimal getDsmlAmt() {
        return dsmlAmt;
    }

    public void setDsmlAmt(BigDecimal dsmlAmt) {
        this.dsmlAmt = dsmlAmt;
    }

    public BigDecimal getTcycleBalQty() {
        return tcycleBalQty;
    }

    public void setTcycleBalQty(BigDecimal tcycleBalQty) {
        this.tcycleBalQty = tcycleBalQty;
    }

    public BigDecimal getDclcusInvtAvgUprcAmt() {
        return dclcusInvtAvgUprcAmt;
    }

    public void setDclcusInvtAvgUprcAmt(BigDecimal dclcusInvtAvgUprcAmt) {
        this.dclcusInvtAvgUprcAmt = dclcusInvtAvgUprcAmt;
    }

    public BigDecimal getDsmlRate() {
        return dsmlRate;
    }

    public void setDsmlRate(BigDecimal dsmlRate) {
        this.dsmlRate = dsmlRate;
    }

    public BigDecimal getVclrPridInitQty() {
        return vclrPridInitQty;
    }

    public void setVclrPridInitQty(BigDecimal vclrPridInitQty) {
        this.vclrPridInitQty = vclrPridInitQty;
    }

    public String getFstSpareMarkcd() {
        return fstSpareMarkcd;
    }

    public void setFstSpareMarkcd(String fstSpareMarkcd) {
        this.fstSpareMarkcd = fstSpareMarkcd;
    }

    public BigDecimal getSpareAmt() {
        return spareAmt;
    }

    public void setSpareAmt(BigDecimal spareAmt) {
        this.spareAmt = spareAmt;
    }

    public BigDecimal getSpareQty() {
        return spareQty;
    }

    public void setSpareQty(BigDecimal spareQty) {
        this.spareQty = spareQty;
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
