package com.powerbridge.bssp.sas.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 宋轲
 */
@TableName(value = "sas_vehicle_i_reg")
public class SasVehicleIReg implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "UID")
    private String uid;

    /**
     * 车牌号
     */
    @TableField(value = "VEHICLE_NO")
    private String vehicleNo;

    /**
     * 车牌类型
     */
    @TableField(value = "PLATE_TYPECD")
    private String plateTypecd;

    /**
     * 车辆类型
     */

    @TableField(value = "VEHICLE_TYPECD")
    private String vehicleTypecd;

    /**
     * 通关业务类型
     */

    @TableField(value = "ClEARANCE_TYPE")
    private String clearanceType;

    /**
     * 车辆入区登记编号
     */

    @TableField(value = "ETPS_PREENT_NO")
    private String etpsPreentNo;

    /**
     * 车辆备案标识
     */

    @TableField(value = "VEHICLE_REG_FLAG")
    private String vehicleRegFlag;

    /**
     * 车辆自重
     */

    @TableField(value = "VEHICLE_WT")
    private BigDecimal vehicleWt;

    /**
     * 电子车牌
     */

    @TableField(value = "VEHICLE_IC_NO")
    private String vehicleIcNo;

    /**
     * IC卡编号
     */

    @TableField(value = "IC_NO")
    private String icNo;

    /**
     * IC卡类型
     */

    @TableField(value = "IC_TYPECD")
        private String icTypecd;

    /**
     * 运输类型代码
     */

    @TableField(value = "TRANS_TYPECD")
    private String transTypecd;

    /**
     * 入区登记日期
     */

    @TableField(value = "I_REG_DATE")
    private String iRegDate;

    /**
     * 本次有效日期
     */

    @TableField(value = "THIS_VALIDATE_DATE")
    private String thisValidateDate;

    /**
     * 入区运输单类型代码
     */

    @TableField(value = "I_TRANS_TYPE_CODE")
    private String iTransTypeCode;

    /**
     * 入区运输单编号
     */

    @TableField(value = "I_TRANS_NO")
    private String iTransNo;

    /**
     * 入区运输单编号1
     */

    @TableField(value = "I_TRANS_NO1")
    private String iTransNo1;

    /**
     * 入区车架编号
     */

    @TableField(value = "I_VEHICLE_FRAME_NO")
    private String iVehicleFrameNo;

    /**
     * 入区车架重量
     */

    @TableField(value = "I_VEHICLE_FRAME_WT")
    private BigDecimal iVehicleFrameWt;

    /**
     * 入区区内企业代码
     */

    @TableField(value = "I_AREAIN_ETPSNO")
    private String iAreainEtpsno;

    /**
     * 入区场地代码
     */

    @TableField(value = "I_AREA_CODE")
    private String iAreaCode;

    /**
     * 入区企业备案号
     */

    @TableField(value = "I_COP_ENT_NO")
    private String iCopEntNo;

    /**
     * 入区流向
     */

    @TableField(value = "I_FLOW")
        private String iFlow;

    /**
     * 入区核放单审批标识
     */

    @TableField(value = "I_CHK_EMAPV_MARKCD")
    private String iChkEmapvMarkcd;

    /**
     * 收卡时间
     */

    @TableField(value = "IC_RECYCE_TIME")
    private String icRecyceTime;

    /**
     * 出区运输单类型代码
     */

    @TableField(value = "E_TRANS_TYPE")
    private String eTransType;

    /**
     * 出区运输单编号
     */

    @TableField(value = "E_TRANS_NO")
    private String eTransNo;

    /**
     * 出区运输单编号1
     */

    @TableField(value = "E_TRANS_NO1")
    private String eTransNo1;

    /**
     * 出区车架重量
     */

    @TableField(value = "E_VEHICLE_FRAME_WT")
    private BigDecimal eVehicleFrameWt;

    /**
     * 出区区内企业代码
     */

    @TableField(value = "E_AREAIN_ETPSNO")
    private String eAreainEtpsno;

    /**
     * 出区场地代码
     */

    @TableField(value = "E_AREA_CODE")
    private String eAreaCode;

    /**
     * 出区企业备案号
     */

    @TableField(value = "EI_COP_ENT_NO")
    private String eiCopEntNo;

    /**
     * 出区流向
     */

    @TableField(value = "E_FLOW")
    private String eFlow;

    /**
     * 出区核放单审批标识
     */

    @TableField(value = "E_CHK_EMAPV_MARKCD")
    private String eChkEmapvMarkcd;

    /**
     * 作业单标识代码
     */

    @TableField(value = "WORK_FLAG_CODE")
    private String workFlagCode;

    /**
     * 入区区内企业名称
     */

    @TableField(value = "I_AREAIN_ETPSNM")
    private String iAreainEtpsnm;

    /**
     * 出区车架编号
     */

    @TableField(value = "E_VEHICLE_FRAME_NO")
    private String eVehicleFrameNo;

    /**
     * 出区区内企业名称
     */

    @TableField(value = "E_AREAIN_ETPSNM")
    private String eAreainEtpsnm;

    /**
     * 备注
     */

    @TableField(value = "RMK")
    private String rmk;

    /**
     * 主管关区
     */

    @TableField(value = "MASTER_CUSCD")
    private String masterCuscd;

    /**
     * 场地代码
     */

    @TableField(value = "AREA_CODE")
    private String areaCode;

    /**
     * 业务类型
     */

    @TableField(value = "BIZ_TYPE")
    private String bizType;

    /**
     * 单据状态
     */

    @TableField(value = "CHK_STATUS")
    private String chkStatus;

    /**
     * 单据类别
     */

    @TableField(value = "DOC_TYPE")
    private String docType;

    /**
     * 回执状态
     */

    @TableField(value = "RET_CHANNEL")
    private String retChannel;

    /**
     * 录入日期
     */

    @TableField(value = "DEC_TIME")
    private String decTime;

    /**
     * 审批时间
     */

    @TableField(value = "CHK_TIME")
    private String chkTime;

    /**
     * 单据编号
     */

    @TableField(value = "SEQ_NO")
    private String seqNo;

    /**
     * 申报类型代码
     */

    @TableField(value = "DCl_TYPECD")
    private String dclTypecd;

    /**
     * 申报（录入）人代码
     */

    @TableField(value = "INPUTER_CODE")
    private String inputerCode;

    /**
     * 申报（录入）人名称
     */

    @TableField(value = "INPUTER_NAME")
    private String inputerName;

    /**
     * 申报（录入）企业代码
     */

    @TableField(value = "INPUT_COP_NO")
    private String inputCopNo;

    /**
     * 申报（录入）企业名称
     */

    @TableField(value = "INPUT_COP_NAME")
    private String inputCopName;

    /**
     * 创建人代码
     */

    @TableField(value = "CREATE_BY")
    private String createBy;

    /**
     * 创建人名称
     */

    @TableField(value = "CREATE_NAME")
    private String createName;

    /**
     * 创建时间
     */

    @TableField(value = "CREATE_TIME")
    private String createTime;

    /**
     * 修改人代码
     */

    @TableField(value = "UPDATE_BY")
    private String updateBy;

    /**
     * 修改人名称
     */

    @TableField(value = "UPDATE_NAME")
    private String updateName;

    /**
     * 修改时间
     */

    @TableField(value = "UPDATE_TIME")
    private String updateTime;

    @TableField(exist = false)
    private String iRegDateStart;

    @TableField(exist = false)
    private String iRegDateEnd;


    private static final long serialVersionUID = 1L;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getPlateTypecd() {
        return plateTypecd;
    }

    public void setPlateTypecd(String plateTypecd) {
        this.plateTypecd = plateTypecd;
    }

    public String getVehicleTypecd() {
        return vehicleTypecd;
    }

    public void setVehicleTypecd(String vehicleTypecd) {
        this.vehicleTypecd = vehicleTypecd;
    }

    public String getClearanceType() {
        return clearanceType;
    }

    public void setClearanceType(String clearanceType) {
        this.clearanceType = clearanceType;
    }

    public String getEtpsPreentNo() {
        return etpsPreentNo;
    }

    public void setEtpsPreentNo(String etpsPreentNo) {
        this.etpsPreentNo = etpsPreentNo;
    }

    public String getVehicleRegFlag() {
        return vehicleRegFlag;
    }

    public void setVehicleRegFlag(String vehicleRegFlag) {
        this.vehicleRegFlag = vehicleRegFlag;
    }

    public BigDecimal getVehicleWt() {
        return vehicleWt;
    }

    public void setVehicleWt(BigDecimal vehicleWt) {
        this.vehicleWt = vehicleWt;
    }

    public String getVehicleIcNo() {
        return vehicleIcNo;
    }

    public void setVehicleIcNo(String vehicleIcNo) {
        this.vehicleIcNo = vehicleIcNo;
    }

    public String getIcNo() {
        return icNo;
    }

    public void setIcNo(String icNo) {
        this.icNo = icNo;
    }

    public String getIcTypecd() {
        return icTypecd;
    }

    public void setIcTypecd(String icTypecd) {
        this.icTypecd = icTypecd;
    }

    public String getTransTypecd() {
        return transTypecd;
    }

    public void setTransTypecd(String transTypecd) {
        this.transTypecd = transTypecd;
    }

    public String getiTransTypeCode() {
        return iTransTypeCode;
    }

    public void setiTransTypeCode(String iTransTypeCode) {
        this.iTransTypeCode = iTransTypeCode;
    }

    public String getiTransNo() {
        return iTransNo;
    }

    public void setiTransNo(String iTransNo) {
        this.iTransNo = iTransNo;
    }

    public String getiTransNo1() {
        return iTransNo1;
    }

    public void setiTransNo1(String iTransNo1) {
        this.iTransNo1 = iTransNo1;
    }

    public String getiVehicleFrameNo() {
        return iVehicleFrameNo;
    }

    public void setiVehicleFrameNo(String iVehicleFrameNo) {
        this.iVehicleFrameNo = iVehicleFrameNo;
    }

    public BigDecimal getiVehicleFrameWt() {
        return iVehicleFrameWt;
    }

    public void setiVehicleFrameWt(BigDecimal iVehicleFrameWt) {
        this.iVehicleFrameWt = iVehicleFrameWt;
    }

    public String getiAreainEtpsno() {
        return iAreainEtpsno;
    }

    public void setiAreainEtpsno(String iAreainEtpsno) {
        this.iAreainEtpsno = iAreainEtpsno;
    }

    public String getiAreaCode() {
        return iAreaCode;
    }

    public void setiAreaCode(String iAreaCode) {
        this.iAreaCode = iAreaCode;
    }

    public String getiCopEntNo() {
        return iCopEntNo;
    }

    public void setiCopEntNo(String iCopEntNo) {
        this.iCopEntNo = iCopEntNo;
    }

    public String getiFlow() {
        return iFlow;
    }

    public void setiFlow(String iFlow) {
        this.iFlow = iFlow;
    }

    public String getiChkEmapvMarkcd() {
        return iChkEmapvMarkcd;
    }

    public void setiChkEmapvMarkcd(String iChkEmapvMarkcd) {
        this.iChkEmapvMarkcd = iChkEmapvMarkcd;
    }

    public String getIcRecyceTime() {
        return icRecyceTime;
    }

    public void setIcRecyceTime(String icRecyceTime) {
        this.icRecyceTime = icRecyceTime;
    }

    public String geteTransType() {
        return eTransType;
    }

    public void seteTransType(String eTransType) {
        this.eTransType = eTransType;
    }

    public String geteTransNo() {
        return eTransNo;
    }

    public void seteTransNo(String eTransNo) {
        this.eTransNo = eTransNo;
    }

    public String geteTransNo1() {
        return eTransNo1;
    }

    public void seteTransNo1(String eTransNo1) {
        this.eTransNo1 = eTransNo1;
    }

    public BigDecimal geteVehicleFrameWt() {
        return eVehicleFrameWt;
    }

    public void seteVehicleFrameWt(BigDecimal eVehicleFrameWt) {
        this.eVehicleFrameWt = eVehicleFrameWt;
    }

    public String geteAreainEtpsno() {
        return eAreainEtpsno;
    }

    public void seteAreainEtpsno(String eAreainEtpsno) {
        this.eAreainEtpsno = eAreainEtpsno;
    }

    public String geteAreaCode() {
        return eAreaCode;
    }

    public void seteAreaCode(String eAreaCode) {
        this.eAreaCode = eAreaCode;
    }

    public String getEiCopEntNo() {
        return eiCopEntNo;
    }

    public void setEiCopEntNo(String eiCopEntNo) {
        this.eiCopEntNo = eiCopEntNo;
    }

    public String geteFlow() {
        return eFlow;
    }

    public void seteFlow(String eFlow) {
        this.eFlow = eFlow;
    }

    public String geteChkEmapvMarkcd() {
        return eChkEmapvMarkcd;
    }

    public void seteChkEmapvMarkcd(String eChkEmapvMarkcd) {
        this.eChkEmapvMarkcd = eChkEmapvMarkcd;
    }

    public String getWorkFlagCode() {
        return workFlagCode;
    }

    public void setWorkFlagCode(String workFlagCode) {
        this.workFlagCode = workFlagCode;
    }

    public String getiAreainEtpsnm() {
        return iAreainEtpsnm;
    }

    public void setiAreainEtpsnm(String iAreainEtpsnm) {
        this.iAreainEtpsnm = iAreainEtpsnm;
    }

    public String geteVehicleFrameNo() {
        return eVehicleFrameNo;
    }

    public void seteVehicleFrameNo(String eVehicleFrameNo) {
        this.eVehicleFrameNo = eVehicleFrameNo;
    }

    public String geteAreainEtpsnm() {
        return eAreainEtpsnm;
    }

    public void seteAreainEtpsnm(String eAreainEtpsnm) {
        this.eAreainEtpsnm = eAreainEtpsnm;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
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

    public String getDclTypecd() {
        return dclTypecd;
    }

    public void setDclTypecd(String dclTypecd) {
        this.dclTypecd = dclTypecd;
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

    public String getiRegDate() {
        return iRegDate;
    }

    public void setiRegDate(String iRegDate) {
        this.iRegDate = iRegDate;
    }

    public String getThisValidateDate() {
        return thisValidateDate;
    }

    public void setThisValidateDate(String thisValidateDate) {
        this.thisValidateDate = thisValidateDate;
    }

    public String getiRegDateStart() {
        return iRegDateStart;
    }

    public void setiRegDateStart(String iRegDateStart) {
        this.iRegDateStart = iRegDateStart;
    }

    public String getiRegDateEnd() {
        return iRegDateEnd;
    }

    public void setiRegDateEnd(String iRegDateEnd) {
        this.iRegDateEnd = iRegDateEnd;
    }
}