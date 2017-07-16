package com.powerbridge.bssp.ems.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * <p>
 * 账册备案申请料件
 * </p>
 *
 * @author jokylao
 * @since 2017-06-03
 */
@TableName("ems_putrec_exg")
public class EmsPutrecExg implements Serializable  {
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
     * 商品序号
     */
    @NotEmpty(message = "商品序号不能为空")
    @TableField("GDS_SEQNO")
    private BigDecimal gdsSeqno;
    /**
     * 料件成品类型代码
     */
    @TableField("MTPCK_ENDPRD_TYPECD")
    private String mtpckEndprdTypecd;
    /**
     * 商品料号
     */
    @NotEmpty(message = "商品料号不能为空")
    @TableField("GDS_MTNO")
    private String gdsMtno;
    /**
     * 商品编码
     */
    @NotEmpty(message = "商品编码不能为空")
    @TableField("GDECD")
    private String gdecd;
    /**
     * 商品名称
     */
    @NotEmpty(message = "商品名称不能为空")
    @TableField("GDS_NM")
    private String gdsNm;
    /**
     * 商品规格型号描述
     */
    @TableField("ENDPRD_GDS_SPCF_MODEL_DESC")
    private String endprdGdsSpcfModelDesc;
    /**
     * 申报计量单位代码
     */
    @NotEmpty(message = "申报计量单位不能为空")
    @TableField("DCL_UNITCD")
    private String dclUnitcd;
    /**
     * 申报计量单位名称
     */
    @TableField(exist = false)
    private String dclUnitnm;
    /**
     * 法定计量单位代码
     */
    @TableField("LAWF_UNITCD")
    private String lawfUnitcd;
    /**
     * 法定计量单位名称
     */
    @TableField(exist = false)
    private String lawfUnitnm;
    /**
     * 第二法定计量单位代码
     */
    @TableField("SECD_LAWF_UNITCD")
    private String secdLawfUnitcd;
    /**
     * 申报单价金额
     */
    @TableField(value = "DCL_UPRC_AMT",validate = FieldStrategy.IGNORED)
    private BigDecimal dclUprcAmt;
    /**
     * 申报币制代码
     */
    @TableField("DCL_CURRCD")
    private String dclCurrcd;
    /**
     * 申报数量
     */
    @TableField(value = "DCL_QTY",validate = FieldStrategy.IGNORED)
    private BigDecimal dclQty;
    /**
     * 征减免方式代码
     */
    @TableField("LVYRLF_MODECD")
    private String lvyrlfModecd;
    /**
     * 数量控制标记代码
     */
    @TableField("QTY_CNTR_MARKCD")
    private String qtyCntrMarkcd;
    /**
     * 主料标记代码
     */
    @NotEmpty(message = "主料标记不能为空")
    @TableField("ADJMTR_MARKCD")
    private String adjmtrMarkcd;
    /**
     * 修改标记代码
     */
    @TableField("MODF_MARKCD")
    private String modfMarkcd;
    /**
     * 企业执行标记代码
     */
    @NotEmpty(message = "企业执行标记不能为空")
    @TableField("ETPS_EXE_MARKCD")
    private String etpsExeMarkcd;
    /**
     * 海关执行标记代码
     */
    @TableField("CUSM_EXE_MARKCD")
    private String cusmExeMarkcd;
    /**
     * 批准最大余数量
     */
    @TableField("APPR_MAX_SURP_QTY")
    private BigDecimal apprMaxSurpQty;
    /**
     * 核销周期初始数量
     */
    @TableField("VCLR_PRID_INIT_QTY")
    private BigDecimal vclrPridInitQty;
    /**
     * 单耗质疑标志代码
     */
    @TableField("UCNS_TQSN_FLAG")
    private String ucnsTqsnFlag;
    /**
     * 磋商标志
     */
    @TableField("CSTTN_FLAG")
    private String csttnFlag;
    /**
     * 企业预录入编号
     */
    @TableField("ETPS_PREENT_NO")
    private String etpsPreentNo;
    /**
     * 备注
     */
    @TableField("RMK")
    private String rmk;
    /**
     * 单据编号
     */
    @NotEmpty(message = "单据编号不能为空")
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
     * 辅料标记名称
     */
    @TableField(exist = false)
    private String adjmtrMarknm;
    /**
     * 记账清单编号
     */
    @TableField("INVT_NO")
    private String invtNo;
    /**
     *记账清单商品序号
     */
    @TableField("INVT_G_NO")
    private BigDecimal invtGNo;

    /**
     * 申报币制名称
     */
    @TableField(exist = false)
    private String dclCurrnm;

    /**
     * 入库时间
     */
    @TableField("INDB_TIME")
    private String indbTime;
    /**
     * 过滤条件，变更选择商品时用
     */
    @TableField(exist = false)
    private String filterSelect;

    public String getInvtNo() {
        return invtNo;
    }

    public void setInvtNo(String invtNo) {
        this.invtNo = invtNo;
    }

    public BigDecimal getInvtGNo() {
        return invtGNo;
    }

    public void setInvtGNo(BigDecimal invtGNo) {
        this.invtGNo = invtGNo;
    }

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
        this.chgTmsCnt = chgTmsCnt == null ? new BigDecimal(0) : chgTmsCnt;
    }

    public BigDecimal getGdsSeqno() {
        return gdsSeqno;
    }

    public void setGdsSeqno(BigDecimal gdsSeqno) {
        this.gdsSeqno = gdsSeqno;
    }

    public String getMtpckEndprdTypecd() {
        return mtpckEndprdTypecd;
    }

    public void setMtpckEndprdTypecd(String mtpckEndprdTypecd) {
        this.mtpckEndprdTypecd = mtpckEndprdTypecd;
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

    public String getEndprdGdsSpcfModelDesc() {
        return endprdGdsSpcfModelDesc;
    }

    public void setEndprdGdsSpcfModelDesc(String endprdGdsSpcfModelDesc) {
        this.endprdGdsSpcfModelDesc = endprdGdsSpcfModelDesc;
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

    public BigDecimal getDclUprcAmt() {
        return dclUprcAmt;
    }

    public void setDclUprcAmt(BigDecimal dclUprcAmt) {
        this.dclUprcAmt = dclUprcAmt;
    }

    public String getDclCurrcd() {
        return dclCurrcd;
    }

    public void setDclCurrcd(String dclCurrcd) {
        this.dclCurrcd = dclCurrcd;
    }

    public BigDecimal getDclQty() {
        return dclQty;
    }

    public void setDclQty(BigDecimal dclQty) {
        this.dclQty = dclQty;
    }

    public String getLvyrlfModecd() {
        return lvyrlfModecd;
    }

    public void setLvyrlfModecd(String lvyrlfModecd) {
        this.lvyrlfModecd = lvyrlfModecd;
    }

    public String getQtyCntrMarkcd() {
        return qtyCntrMarkcd;
    }

    public void setQtyCntrMarkcd(String qtyCntrMarkcd) {
        this.qtyCntrMarkcd = qtyCntrMarkcd;
    }

    public String getAdjmtrMarkcd() {
        return adjmtrMarkcd;
    }

    public void setAdjmtrMarkcd(String adjmtrMarkcd) {
        this.adjmtrMarkcd = adjmtrMarkcd;
    }

    public String getModfMarkcd() {
        return modfMarkcd;
    }

    public void setModfMarkcd(String modfMarkcd) {
        this.modfMarkcd = modfMarkcd;
    }

    public String getEtpsExeMarkcd() {
        return etpsExeMarkcd;
    }

    public void setEtpsExeMarkcd(String etpsExeMarkcd) {
        this.etpsExeMarkcd = etpsExeMarkcd;
    }

    public String getCusmExeMarkcd() {
        return cusmExeMarkcd;
    }

    public void setCusmExeMarkcd(String cusmExeMarkcd) {
        this.cusmExeMarkcd = cusmExeMarkcd;
    }

    public BigDecimal getApprMaxSurpQty() {
        return apprMaxSurpQty;
    }

    public void setApprMaxSurpQty(BigDecimal apprMaxSurpQty) {
        this.apprMaxSurpQty = apprMaxSurpQty;
    }

    public BigDecimal getVclrPridInitQty() {
        return vclrPridInitQty;
    }

    public void setVclrPridInitQty(BigDecimal vclrPridInitQty) {
        this.vclrPridInitQty = vclrPridInitQty;
    }

    public String getUcnsTqsnFlag() {
        return ucnsTqsnFlag;
    }

    public void setUcnsTqsnFlag(String ucnsTqsnFlag) {
        this.ucnsTqsnFlag = ucnsTqsnFlag;
    }

    public String getCsttnFlag() {
        return csttnFlag;
    }

    public void setCsttnFlag(String csttnFlag) {
        this.csttnFlag = csttnFlag;
    }

    public String getEtpsPreentNo() {
        return etpsPreentNo;
    }

    public void setEtpsPreentNo(String etpsPreentNo) {
        this.etpsPreentNo = etpsPreentNo;
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

    public String getAdjmtrMarknm() {
        return adjmtrMarknm;
    }

    public String getDclUnitnm() {
        return dclUnitnm;
    }

    public void setDclUnitnm(String dclUnitnm) {
        this.dclUnitnm = dclUnitnm;
    }

    public String getLawfUnitnm() {
        return lawfUnitnm;
    }

    public void setLawfUnitnm(String lawfUnitnm) {
        this.lawfUnitnm = lawfUnitnm;
    }

    public void setDclCurrnm(String dclCurrnm) {
        this.dclCurrnm = dclCurrnm;
    }

    public String getDclCurrnm() {
        return dclCurrnm;
    }

    public void setAdjmtrMarknm(String adjmtrMarknm) {
        this.adjmtrMarknm = adjmtrMarknm;
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
