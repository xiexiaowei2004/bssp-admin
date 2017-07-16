package com.powerbridge.bssp.sas.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 出入库单表
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
@TableName("sas_stock_bsc")
public class SasStockBsc implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    //列表页面搜索录入日期时使用
    @TableField(exist = false)
    private String decTimeStart;

    @TableField(exist = false)
    private String decTimeEnd;

    //列表页面显示审批标志
    @TableField(exist = false)
    private String emapvMarkNm;

    //列表页面显示单据状态时使用
    @TableField(exist = false)
    private String chkStatusNm;
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
     * 申报类型代码
     */
    @TableField("DCL_TYPECD")
    private String dclTypecd;
    /**
     * 申报表编号
     */
    @NotEmpty(message = "申报表编号不能为空")
    @TableField("SAS_DCL_NO")
    private String sasDclNo;
    /**
     * 出入库单预录入编号
     */
    @NotEmpty(message = "出入库单预录入编号不能为空")
    @TableField("SAS_STOCK_PREENT_NO")
    private String sasStockPreentNo;
    /**
     * 主管关区代码
     */
    @NotEmpty(message = "主管关区不能为空")
    @TableField("MASTER_CUSCD")
    private String masterCuscd;
    /**
     * 出入库单类型
     */
    @NotEmpty(message = "出入库单类型不能为空")
    @TableField("STOCK_TYPECD")
    private String stockTypecd;
    /**
     * 关联核注清单编号
     */
    @TableField("RLT_BOND_INVT_NO")
    private String rltBondInvtNo;
    /**
     * 业务类型代码
     */
    @NotEmpty(message = "业务类型不能为空")
    @TableField("BUSINESS_TYPECD")
    private String businessTypecd;
    /**
     * 集报标志代码
     */
    @TableField("CENTRALIZED_DCL_TYPECD")
    private String centralizedDclTypecd;
    /**
     * 关联出入库单编号
     */
    @TableField("RLT_SAS_STOCK_NO")
    private String rltSasStockNo;
    /**
     * 申请人
     */
    @NotEmpty(message = "申请人不能为空")
    @TableField("DCL_ER")
    private String dclEr;
    /**
     * 申报日期
     */
    @TableField("DCL_TIME")
    private String dclTime;
    /**
     * 件数
     */
    @TableField(value = "PACKAGE_QTY",validate = FieldStrategy.IGNORED)
    private BigDecimal packageQty;
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
     * 包装种类
     */
    @TableField("PACK_TYPE")
    private String packType;
    /**
     * 计征金额
     */
    @TableField("LEVY_BL_AMT")
    private BigDecimal levyBlAmt;
    /**
     * 过卡标志代码
     */
    @TableField("PASS_TYPECD")
    private String passTypecd;
    /**
     * 核放单生成标志代码
     */
    @TableField("PASSPORT_USED_TYPECD")
    private String passportUsedTypecd;
    /**
     * 状态代码
     */
    @TableField("STUCD")
    private String stucd;
    /**
     * 审批标志
     */
    @TableField("EMAPV_MARKCD")
    private String emapvMarkcd;
    /**
     * 审批描述
     */
    @TableField("EMAPV_DESCRIPTION")
    private String emapvDescription;
    /**
     * 所属系统
     */
    @TableField("OWNER_SYSTEM")
    private String ownerSystem;
    /**
     * 备注
     */
    @TableField("RMK")
    private String rmk;
    /**
     * 企业预录入编号
     */
    @NotEmpty(message = "企业预录入编号不能为空")
    @TableField("ETPS_PREENT_NO")
    private String etpsPreentNo;
    /**
     * 企业备案号
     */
    @TableField("COP_ENT_NO")
    private String copEntNo;
    /**
     * 场地代码
     */
    @NotEmpty(message = "场地代码不能为空")
    @TableField("AREA_CODE")
    private String areaCode;
    /**
     * 单据类型
     */
    //@NotEmpty(message = "单据类型不能为空")
    @TableField("DOC_TYPE")
    private String docType;

    /**
     *  单据类型 用于区分物流和加工
     */
    @TableField(exist = false)
    private String busType;

    /**
     * 操作类型
     */
    @NotEmpty(message = "操作类型不能为空")
    @TableField("OPERATE_TYPE")
    private String operateType;
    /**
     * 业务类型
     */
    @TableField("BIZ_TYPE")
    private String bizType;
    /**
     * 单据状态
     */
    @TableField("CHK_STATUS")
    private String chkStatus;
    /**
     * 回执状态
     */
    @TableField(value = "RET_CHANNEL",validate = FieldStrategy.IGNORED)
    private String retChannel;
    /**
     * 录入日期
     */
    @NotEmpty(message = "录入日期不能为空")
    @TableField("DEC_TIME")
    private String decTime;
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
     * 申报（录入）人代码
     */
    @TableField("INPUTER_CODE")
    private String inputerCode;
    /**
     * 申报（录入）人名称
     */
    @TableField("INPUTER_NAME")
    private String inputerName;
    /**
     * 申报（录入）企业代码
     */
    @TableField("INPUT_COP_NO")
    private String inputCopNo;
    /**
     * 申报（录入）企业名称
     */
    @TableField("INPUT_COP_NAME")
    private String inputCopName;
    /**
     * 申报方式(备案/变更)
     */
    @TableField("DECLARE_MARK")
    private String declareMark;
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
     * 备用字段
     */
    @TableField("COL1")
    private String col1;
    @TableField("COL2")
    private String col2;
    @TableField("COL3")
    private String col3;
    @TableField("COL4")
    private BigDecimal col4;
    /**
     * 是否过滤核放单关联单证
     */
    @TableField(exist = false)
    private String isFilterRlt;


    public String getDecTimeStart() {
        return decTimeStart;
    }

    public void setDecTimeStart(String decTimeStart) {
        this.decTimeStart = decTimeStart;
    }

    public String getDecTimeEnd() {
        return decTimeEnd;
    }

    public void setDecTimeEnd(String decTimeEnd) {
        this.decTimeEnd = decTimeEnd;
    }

    public String getEmapvMarkNm() {
        return emapvMarkNm;
    }

    public void setEmapvMarkNm(String emapvMarkNm) {
        this.emapvMarkNm = emapvMarkNm;
    }

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

    public String getDclTypecd() {
        return dclTypecd;
    }

    public void setDclTypecd(String dclTypecd) {
        this.dclTypecd = dclTypecd;
    }

    public String getSasDclNo() {
        return sasDclNo;
    }

    public void setSasDclNo(String sasDclNo) {
        this.sasDclNo = sasDclNo;
    }

    public String getSasStockPreentNo() {
        return sasStockPreentNo;
    }

    public void setSasStockPreentNo(String sasStockPreentNo) {
        this.sasStockPreentNo = sasStockPreentNo;
    }

    public String getMasterCuscd() {
        return masterCuscd;
    }

    public void setMasterCuscd(String masterCuscd) {
        this.masterCuscd = masterCuscd;
    }

    public String getStockTypecd() {
        return stockTypecd;
    }

    public void setStockTypecd(String stockTypecd) {
        this.stockTypecd = stockTypecd;
    }

    public String getRltBondInvtNo() {
        return rltBondInvtNo;
    }

    public void setRltBondInvtNo(String rltBondInvtNo) {
        this.rltBondInvtNo = rltBondInvtNo;
    }

    public String getBusinessTypecd() {
        return businessTypecd;
    }

    public void setBusinessTypecd(String businessTypecd) {
        this.businessTypecd = businessTypecd;
    }

    public String getCentralizedDclTypecd() {
        return centralizedDclTypecd;
    }

    public void setCentralizedDclTypecd(String centralizedDclTypecd) {
        this.centralizedDclTypecd = centralizedDclTypecd;
    }

    public String getRltSasStockNo() {
        return rltSasStockNo;
    }

    public void setRltSasStockNo(String rltSasStockNo) {
        this.rltSasStockNo = rltSasStockNo;
    }

    public String getDclEr() {
        return dclEr;
    }

    public void setDclEr(String dclEr) {
        this.dclEr = dclEr;
    }

    public String getDclTime() {
        return dclTime;
    }

    public void setDclTime(String dclTime) {
        this.dclTime = DateUtil.timeSwitch(dclTime); 
    }

    public BigDecimal getPackageQty() {
        return packageQty;
    }

    public void setPackageQty(BigDecimal packageQty) {
        this.packageQty = packageQty;
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

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }

    public BigDecimal getLevyBlAmt() {
        return levyBlAmt;
    }

    public void setLevyBlAmt(BigDecimal levyBlAmt) {
        this.levyBlAmt = levyBlAmt;
    }

    public String getPassTypecd() {
        return passTypecd;
    }

    public void setPassTypecd(String passTypecd) {
        this.passTypecd = passTypecd;
    }

    public String getPassportUsedTypecd() {
        return passportUsedTypecd;
    }

    public void setPassportUsedTypecd(String passportUsedTypecd) {
        this.passportUsedTypecd = passportUsedTypecd;
    }

    public String getStucd() {
        return stucd;
    }

    public void setStucd(String stucd) {
        this.stucd = stucd;
    }

    public String getEmapvMarkcd() {
        return emapvMarkcd;
    }

    public void setEmapvMarkcd(String emapvMarkcd) {
        this.emapvMarkcd = emapvMarkcd;
    }

    public String getEmapvDescription() {
        return emapvDescription;
    }

    public void setEmapvDescription(String emapvDescription) {
        this.emapvDescription = emapvDescription;
    }

    public String getOwnerSystem() {
        return ownerSystem;
    }

    public void setOwnerSystem(String ownerSystem) {
        this.ownerSystem = ownerSystem;
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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getChkStatus() {
        return chkStatus;
    }

    public void setChkStatus(String chkStatus) {
        this.chkStatus = chkStatus;
    }

    public String getRetChannel() {
        return retChannel;
    }

    public void setRetChannel(String retChannel) {
        this.retChannel = retChannel;
    }

    public String getDecTime() {
        return decTime;
    }

    public void setDecTime(String decTime) {
        this.decTime = DateUtil.timeSwitch(decTime);
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

    public String getInputerCode() {
        return inputerCode;
    }

    public void setInputerCode(String inputerCode) {
        this.inputerCode = inputerCode;
    }

    public String getInputerName() {
        return inputerName;
    }

    public void setInputerName(String inputerName) {
        this.inputerName = inputerName;
    }

    public String getInputCopNo() {
        return inputCopNo;
    }

    public void setInputCopNo(String inputCopNo) {
        this.inputCopNo = inputCopNo;
    }

    public String getInputCopName() {
        return inputCopName;
    }

    public void setInputCopName(String inputCopName) {
        this.inputCopName = inputCopName;
    }

    public String getDeclareMark() {
        return declareMark;
    }

    public void setDeclareMark(String declareMark) {
        this.declareMark = declareMark;
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
        this.createTime = DateUtil.timeSwitch(createTime);
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
        this.updateTime = DateUtil.timeSwitch(updateTime);
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getChkStatusNm() {
        return chkStatusNm;
    }

    public void setChkStatusNm(String chkStatusNm) {
        this.chkStatusNm = chkStatusNm;
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

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = DateUtil.timeSwitch(col3);
    }

    public BigDecimal getCol4() {
        return col4;
    }

    public void setCol4(BigDecimal col4) {
        this.col4 = col4;
    }

    public String getIsFilterRlt() {
        return isFilterRlt;
    }

    public void setIsFilterRlt(String isFilterRlt) {
        this.isFilterRlt = isFilterRlt;
    }
}
