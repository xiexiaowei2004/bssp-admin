package com.powerbridge.bssp.sas.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 车辆信息备案申请表
 * </p>
 *
 * @author xuzuotao
 * @since 2017-05-22
 */
@TableName("sas_vehicle_his")
public class SasVehicleHis implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    //列表页面搜索录入日期时使用
    @TableField(exist = false)
    private String decTimeStart;

    @TableField(exist = false)
    private String decTimeEnd;

    //列表页面搜素车属企业时使用
    @TableField(exist = false)
    private String vehicleEtps;

    //列表页面显示单据状态时使用
    @TableField(exist = false)
    private String chkStatusNm;

    //列表页面显示主管关区时使用
    @TableField(exist = false)
    private String masterCudNm;

    /**
     * 主键
     */
    @TableId("UID")
    private String uid;
    /**
     * 车牌号
     */
    @NotEmpty(message = "车牌号不可为空")
    @TableField("VEHICLE_NO")

    private String vehicleNo;
    /**
     * 变更次数
     */
    @TableField("CHG_TMS_CNT")
    private Integer chgTmsCnt;
    /**
     * 车辆类型
     */
    @NotEmpty(message = "车辆类型必须选择")
    @TableField("VEHICLE_TYPECD")
    private String vehicleTypecd;
    /**
     * 申报端统一编号
     */
    @NotEmpty(message = "申报端统一编号不能为空")
    @TableField("DCL_PREENT_NO")
    private String dclPreentNo;
    /**
     * 申报类型代码
     */
    @TableField("DCl_TYPECD")
    private String dclTypecd;
    /**
     * 监管车海关编码
     */
    @TableField("VEHICLE_CUS_NO")
    private String vehicleCusNo;
    /**
     * 所属企业编码
     */
    @TableField("ETPSNO")
    private String etpsno;
    /**
     * 所属企业名称
     */
    @NotEmpty(message = "所属企业名称不可为空")
    @TableField("ETPS_NM")
    private String etpsNm;
    /**
     * 所属企业社会信用编码
     */
    @TableField("ETPS_SCCD")
    private String etpsSccd;
    /**
     * 车辆自重
     */
    @NotNull(message = "车辆自重不能为空")
    @TableField(value = "VEHICLE_WT",validate = FieldStrategy.IGNORED)
    private BigDecimal vehicleWt;
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
     * 备案审批时间
     */
    @TableField("PUTREC_EMAPV_TIME")
    private String putrecEmapvTime;
    /**
     * 变更审批时间
     */
    @TableField("CHG_EMAPV_TIME")
    private String chgEmapvTime;
    /**
     * 审批标志
     */
    @TableField("EMAPV_MARKCD")
    private String emapvMarkcd;
    /**
     * 所属系统
     */
    @TableField("OWNER_SYSTEM")
    private String ownerSystem;
    /**
     * 有效标识
     */
    @TableField("VALID_FLAG")
    private String validFlag;
    /**
     * 车牌类型
     */
    @NotEmpty(message = "车牌类型不能为空")
    @TableField("PLATE_TYPECD")
    private String plateTypecd;
    /**
     * 第二车牌号
     */
    @TableField("SEC_VEHICLE_IC_NO")
    private String secVehicleIcNo;
    /**
     * 状态
     */
    @TableField("STUCD")
    private String stucd;
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
     * 主管关区
     */
    @TableField("MASTER_CUSCD")
    private String masterCuscd;
    /**
     * 场地代码
     */
    @TableField("AREA_CODE")
    private String areaCode;
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
     * 单据类别
     */
    @TableField("DOC_TYPE")
    private String docType;
    /**
     * 回执状态
     */
    @TableField(value = "RET_CHANNEL",validate = FieldStrategy.IGNORED)
    private String retChannel;
    /**
     * 录入日期
     */
    @TableField("DEC_TIME")
    private String decTime;

    /**
     * 审批时间
     */
    @TableField("CHK_TIME")
    private String chkTime;
    /**
     * 单据编号
     */
    @NotEmpty(message = "单据编号不能为空")
    @TableField("SEQ_NO")
    private String seqNo;
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
     * 创建人代码
     */
    @TableField("CREATE_BY")
    private String createBy;

    /**
     * 创建人名称
     */
    @TableField(value = "CREATE_NAME")
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
    @TableField(value = "UPDATE_NAME")
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


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVehicleNo() {
        return vehicleNo.replaceAll("\\s*", "");
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Integer getChgTmsCnt() {
        return chgTmsCnt;
    }

    public void setChgTmsCnt(Integer chgTmsCnt) {
        this.chgTmsCnt = chgTmsCnt;
    }

    public String getVehicleTypecd() {
        return vehicleTypecd;
    }

    public void setVehicleTypecd(String vehicleTypecd) {
        this.vehicleTypecd = vehicleTypecd;
    }

    public String getDclPreentNo() {
        return dclPreentNo;
    }

    public void setDclPreentNo(String dclPreentNo) {
        this.dclPreentNo = dclPreentNo;
    }

    public String getDclTypecd() {
        return dclTypecd;
    }

    public void setDclTypecd(String dclTypecd) {
        this.dclTypecd = dclTypecd;
    }

    public String getVehicleCusNo() {
        return vehicleCusNo;
    }

    public void setVehicleCusNo(String vehicleCusNo) {
        this.vehicleCusNo = vehicleCusNo;
    }

    public String getEtpsno() {
        return etpsno;
    }

    public void setEtpsno(String etpsno) {
        this.etpsno = etpsno;
    }

    public String getEtpsNm() {
        return etpsNm;
    }

    public void setEtpsNm(String etpsNm) {
        this.etpsNm = etpsNm;
    }

    public String getEtpsSccd() {
        return etpsSccd;
    }

    public void setEtpsSccd(String etpsSccd) {
        this.etpsSccd = etpsSccd;
    }

    public BigDecimal getVehicleWt() {
        return vehicleWt;
    }

    public void setVehicleWt(BigDecimal vehicleWt) {
        this.vehicleWt = vehicleWt;
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
        this.dclTime = dclTime == "" ? null : dclTime;
    }

    public String getPutrecEmapvTime() {
        return putrecEmapvTime;
    }

    public void setPutrecEmapvTime(String putrecEmapvTime) {
        this.putrecEmapvTime = putrecEmapvTime;
    }

    public String getChgEmapvTime() {
        return chgEmapvTime;
    }

    public void setChgEmapvTime(String chgEmapvTime) {
        this.chgEmapvTime = chgEmapvTime;
    }

    public String getEmapvMarkcd() {
        return emapvMarkcd;
    }

    public void setEmapvMarkcd(String emapvMarkcd) {
        this.emapvMarkcd = emapvMarkcd;
    }

    public String getOwnerSystem() {
        return ownerSystem;
    }

    public void setOwnerSystem(String ownerSystem) {
        this.ownerSystem = ownerSystem;
    }

    public String getPlateTypecd() {
        return plateTypecd;
    }

    public void setPlateTypecd(String plateTypecd) {
        this.plateTypecd = plateTypecd;
    }

    public String getSecVehicleIcNo() {
        return secVehicleIcNo;
    }

    public void setSecVehicleIcNo(String secVehicleIcNo) {
        this.secVehicleIcNo = secVehicleIcNo;
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

    public String getMasterCuscd() {
        return masterCuscd;
    }

    public void setMasterCuscd(String masterCuscd) {
        this.masterCuscd = masterCuscd;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
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

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
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
        this.decTime = decTime;
    }

    public String getChkTime() {
        return chkTime;
    }

    public void setChkTime(String chkTime) {
        this.chkTime = chkTime;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

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

    public String getVehicleEtps() {
        return vehicleEtps;
    }

    public void setVehicleEtps(String vehicleEtps) {
        this.vehicleEtps = vehicleEtps;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getChkStatusNm() {
        return chkStatusNm;
    }

    public void setChkStatusNm(String chkStatusNm) {
        this.chkStatusNm = chkStatusNm;
    }

    public String getMasterCudNm() {
        return masterCudNm;
    }

    public void setMasterCudNm(String masterCudNm) {
        this.masterCudNm = masterCudNm;
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
        this.col3 = col3;
    }

    public BigDecimal getCol4() {
        return col4;
    }

    public void setCol4(BigDecimal col4) {
        this.col4 = col4;
    }

    public String getStucd() {
        return stucd;
    }

    public void setStucd(String stucd) {
        this.stucd = stucd;
    }
}
