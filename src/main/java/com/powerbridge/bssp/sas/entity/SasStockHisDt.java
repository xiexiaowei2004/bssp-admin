package com.powerbridge.bssp.sas.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 出入库商品历史记录表
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
@TableName("sas_stock_his_dt")
public class SasStockHisDt implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    //当前业务申报表某个申报商品的总数
    @TableField(exist = false)
    private BigDecimal total;
    /**
     * 主键
     */
    @TableId("UID")
    private String uid;
    /**
     * 出入库单编号
     */
    @TableField("SAS_STOCK_NO")
    private String sasStockNo;
    /**
     * 变更次数
     */
    @TableField("CHG_TMS_CNT")
    private Integer chgTmsCnt;
    /**
     * 商品序号
     */
    @TableField("SAS_STOCK_SEQNO")
    private Integer sasStockSeqno;
    /**
     * 申报表编号
     */
    @TableField("SAS_DCL_SEQNO")
    private Integer sasDclSeqno;
    /**
     * 备案序号
     */
    @TableField("ORIACT_GDS_SEQNO")
    private Integer oriactGdsSeqno;
    /**
     * 商品料号
     */
    @TableField("GDS_MTNO")
    private String gdsMtno;
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
     * 商品规格型号
     */
    @TableField("GDS_SPCF_MODEL_DESC")
    private String gdsSpcfModelDesc;
    /**
     * 申报计量单位代码
     */
    @TableField("DCL_UNITCD")
    private String dclUnitcd;
    /**
     * 法定计量单位代码
     */
    @TableField("LAWF_UNITCD")
    private String lawfUnitcd;
    /**
     * 法定第二计量代码
     */
    @TableField("SECD_LAWF_UNITCD")
    private String secdLawfUnitcd;
    /**
     * 国别代码
     */
    @TableField("NATCD")
    private String natcd;
    /**
     * 申报单价
     */
    @TableField("DCL_UPRC_AMT")
    private BigDecimal dclUprcAmt;
    /**
     * 申报总价
     */
    @TableField(value = "DCL_TOTAL_AMT",validate = FieldStrategy.IGNORED)
    private BigDecimal dclTotalAmt;
    /**
     * 美元统计总价
     */
    @TableField(value = "USD_STAT_TOTAL_AMT",validate = FieldStrategy.IGNORED)
    private BigDecimal usdStatTotalAmt;
    /**
     * 申报币制代码
     */
    @TableField("DCL_CURRCD")
    private String dclCurrcd;
    /**
     * 法定数量
     */
    @TableField(value = "LAWF_QTY",validate = FieldStrategy.IGNORED)
    private BigDecimal lawfQty;
    /**
     * 第二法定数量
     */
    @TableField(value = "SECD_LAWF_QTY",validate = FieldStrategy.IGNORED)
    private BigDecimal secdLawfQty;
    /**
     * 重量比例因子
     */
    @TableField(value = "WT_SF_VAL",validate = FieldStrategy.IGNORED)
    private BigDecimal wtSfVal;
    /**
     * 第一比例因子
     */
    @TableField(value = "FST_SF_VAL",validate = FieldStrategy.IGNORED)
    private BigDecimal fstSfVal;
    /**
     * 第二比例因子
     */
    @TableField(value = "SECD_SF_VAL",validate = FieldStrategy.IGNORED)
    private BigDecimal secdSfVal;
    /**
     * 申报数量
     */
    @TableField("DCL_QTY")
    private BigDecimal dclQty;
    /**
     * 毛重
     */
    @TableField(value = "GROSS_WT",validate = FieldStrategy.IGNORED)
    private BigDecimal grossWt;
    /**
     * 净重
     */
    @TableField(value = "NET_WT",validate = FieldStrategy.IGNORED)
    private BigDecimal netWt;
    /**
     * 用途代码
     */
    @TableField("USE_CD")
    private String useCd;
    /**
     * 征免方式代码
     */
    @TableField("LVYRLF_MODECD")
    private String lvyrlfModecd;
    /**
     * 单耗版本号
     */
    @TableField("UCNS_VERNO")
    private String ucnsVerno;
    /**
     * 归类标志代码
     */
    @TableField("CLY_MARKCD")
    private String clyMarkcd;
    /**
     * 实际过卡数量
     */
    @TableField("ACTL_PASS_QTY")
    private BigDecimal actlPassQty;
    /**
     * 核放单已用数量
     */
    @TableField("PASSPORT_USED_QTY")
    private BigDecimal passportUsedQty;
    /**
     * 退换数量
     */
    @TableField("REPLACE_QTY")
    private BigDecimal replaceQty;
    /**
     * 关联商品序号
     */
    @TableField("RLT_GDS_SEQNO")
    private String rltGdsSeqno;
    /**
     * 备注
     */
    @TableField("RMK")
    private String rmk;
    /**
     * 企业预录入编号
     */
    @TableField("ETPS_PREENT_NO")
    private String etpsPreentNo;
    /**
     * 企业备案号
     */
    @TableField("COP_ENT_NO")
    private String copEntNo;
    /**
     * 出入库单类型
     */
    @TableField("STOCK_TYPECD")
    private String stockTypecd;
    /**
     * 料件成品标志代码
     */
    @TableField("MTPCK_ENDPRD_TYPECD")
    private String mtpckEndprdTypecd;
    /**
     * 操作类型
     */
    @TableField("OPERATE_TYPE")
    private String operateType;
    /**
     * 单据编号
     */
    @TableField("SEQ_NO")
    private String seqNo;
    /**
     * 审批时间
     */
    @TableField("CHK_TIME")
    private String chkTime;
    /**
     * 备用4
     */
    @TableField("COL4")
    private Double col4;
    /**
     * 备用3
     */
    @TableField("COL3")
    private String col3;
    /**
     * 备用1
     */
    @TableField("COL1")
    private String col1;
    /**
     * 备用2
     */
    @TableField("COL2")
    private String col2;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSasStockNo() {
        return sasStockNo;
    }

    public void setSasStockNo(String sasStockNo) {
        this.sasStockNo = sasStockNo;
    }

    public Integer getChgTmsCnt() {
        return chgTmsCnt;
    }

    public void setChgTmsCnt(Integer chgTmsCnt) {
        this.chgTmsCnt = chgTmsCnt;
    }

    public Integer getSasStockSeqno() {
        return sasStockSeqno;
    }

    public void setSasStockSeqno(Integer sasStockSeqno) {
        this.sasStockSeqno = sasStockSeqno;
    }

    public Integer getSasDclSeqno() {
        return sasDclSeqno;
    }

    public void setSasDclSeqno(Integer sasDclSeqno) {
        this.sasDclSeqno = sasDclSeqno;
    }

    public Integer getOriactGdsSeqno() {
        return oriactGdsSeqno;
    }

    public void setOriactGdsSeqno(Integer oriactGdsSeqno) {
        this.oriactGdsSeqno = oriactGdsSeqno;
    }

    public String getGdsMtno() {
        return gdsMtno;
    }

    public void setGdsMtno(String gdsMtno) {
        this.gdsMtno = gdsMtno;
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

    public String getLawfUnitcd() {
        return lawfUnitcd;
    }

    public void setLawfUnitcd(String lawfUnitcd) {
        this.lawfUnitcd = lawfUnitcd;
    }

    public String getSecdLawfUnitcd() {
        return secdLawfUnitcd;
    }

    public void setSecdLawfUnitcd(String secdLawfUnitcd) {
        this.secdLawfUnitcd = secdLawfUnitcd;
    }

    public String getNatcd() {
        return natcd;
    }

    public void setNatcd(String natcd) {
        this.natcd = natcd;
    }

    public BigDecimal getDclUprcAmt() {
        return dclUprcAmt;
    }

    public void setDclUprcAmt(BigDecimal dclUprcAmt) {
        this.dclUprcAmt = dclUprcAmt;
    }

    public BigDecimal getDclTotalAmt() {
        return dclTotalAmt;
    }

    public void setDclTotalAmt(BigDecimal dclTotalAmt) {
        this.dclTotalAmt = dclTotalAmt;
    }

    public BigDecimal getUsdStatTotalAmt() {
        return usdStatTotalAmt;
    }

    public void setUsdStatTotalAmt(BigDecimal usdStatTotalAmt) {
        this.usdStatTotalAmt = usdStatTotalAmt;
    }

    public String getDclCurrcd() {
        return dclCurrcd;
    }

    public void setDclCurrcd(String dclCurrcd) {
        this.dclCurrcd = dclCurrcd;
    }

    public BigDecimal getLawfQty() {
        return lawfQty;
    }

    public void setLawfQty(BigDecimal lawfQty) {
        this.lawfQty = lawfQty;
    }

    public BigDecimal getSecdLawfQty() {
        return secdLawfQty;
    }

    public void setSecdLawfQty(BigDecimal secdLawfQty) {
        this.secdLawfQty = secdLawfQty;
    }

    public BigDecimal getWtSfVal() {
        return wtSfVal;
    }

    public void setWtSfVal(BigDecimal wtSfVal) {
        this.wtSfVal = wtSfVal;
    }

    public BigDecimal getFstSfVal() {
        return fstSfVal;
    }

    public void setFstSfVal(BigDecimal fstSfVal) {
        this.fstSfVal = fstSfVal;
    }

    public BigDecimal getSecdSfVal() {
        return secdSfVal;
    }

    public void setSecdSfVal(BigDecimal secdSfVal) {
        this.secdSfVal = secdSfVal;
    }

    public BigDecimal getDclQty() {
        return dclQty;
    }

    public void setDclQty(BigDecimal dclQty) {
        this.dclQty = dclQty;
    }

    public BigDecimal getGrossWt() {
        return grossWt;
    }

    public void setGrossWt(BigDecimal grossWt) {
        this.grossWt = grossWt;
    }

    public BigDecimal getNetWt() {
        return netWt;
    }

    public void setNetWt(BigDecimal netWt) {
        this.netWt = netWt;
    }

    public String getUseCd() {
        return useCd;
    }

    public void setUseCd(String useCd) {
        this.useCd = useCd;
    }

    public String getLvyrlfModecd() {
        return lvyrlfModecd;
    }

    public void setLvyrlfModecd(String lvyrlfModecd) {
        this.lvyrlfModecd = lvyrlfModecd;
    }

    public String getUcnsVerno() {
        return ucnsVerno;
    }

    public void setUcnsVerno(String ucnsVerno) {
        this.ucnsVerno = ucnsVerno;
    }

    public String getClyMarkcd() {
        return clyMarkcd;
    }

    public void setClyMarkcd(String clyMarkcd) {
        this.clyMarkcd = clyMarkcd;
    }

    public BigDecimal getActlPassQty() {
        return actlPassQty;
    }

    public void setActlPassQty(BigDecimal actlPassQty) {
        this.actlPassQty = actlPassQty;
    }

    public BigDecimal getPassportUsedQty() {
        return passportUsedQty;
    }

    public void setPassportUsedQty(BigDecimal passportUsedQty) {
        this.passportUsedQty = passportUsedQty;
    }

    public BigDecimal getReplaceQty() {
        return replaceQty;
    }

    public void setReplaceQty(BigDecimal replaceQty) {
        this.replaceQty = replaceQty;
    }

    public String getRltGdsSeqno() {
        return rltGdsSeqno;
    }

    public void setRltGdsSeqno(String rltGdsSeqno) {
        this.rltGdsSeqno = rltGdsSeqno;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    public String getEtpsPreentNo() {
        return etpsPreentNo;
    }

    public void setEtpsPreentNo(String etpsPreentNo) {
        this.etpsPreentNo = etpsPreentNo;
    }

    public String getCopEntNo() {
        return copEntNo;
    }

    public void setCopEntNo(String copEntNo) {
        this.copEntNo = copEntNo;
    }

    public String getStockTypecd() {
        return stockTypecd;
    }

    public void setStockTypecd(String stockTypecd) {
        this.stockTypecd = stockTypecd;
    }

    public String getMtpckEndprdTypecd() {
        return mtpckEndprdTypecd;
    }

    public void setMtpckEndprdTypecd(String mtpckEndprdTypecd) {
        this.mtpckEndprdTypecd = mtpckEndprdTypecd;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getChkTime() {
        return chkTime;
    }

    public void setChkTime(String chkTime) {
        this.chkTime = DateUtil.timeSwitch(chkTime);
    }

    public Double getCol4() {
        return col4;
    }

    public void setCol4(Double col4) {
        this.col4 = col4;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = DateUtil.timeSwitch(col3);
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
