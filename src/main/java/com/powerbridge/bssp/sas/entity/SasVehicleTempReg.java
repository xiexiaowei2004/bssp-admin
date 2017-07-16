package com.powerbridge.bssp.sas.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author
 */
@TableName(value = "sas_vehicle_temp_reg")
public class SasVehicleTempReg implements Serializable {
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
     * 企业社会信用代码
     */
    @TableField(value = "ETPS_SCCD")
    private String etpsSccd;

    /**
     * 车辆备案号
     */
    @TableField(value = "VEHICLE_I_REGNO")
    private String vehicleIRegno;

    /**
     * 车主或车属企业名称
     */
    @TableField(value = "VEHICLE_ENT_NAME")
    private String vehicleEntName;

    /**
     * 载重量
     */
    @TableField(value = "VEHICLE_LOAD_WT")
    private BigDecimal vehicleLoadWt;

    /**
     * 车辆自重
     */

    @TableField(value = "VEHICLE_WT")
    private BigDecimal vehicleWt;

    /**
     * 备注
     */
    @TableField(value = "RMK")
    private String rmk;

    /**
     * 主管海关
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

    public String getEtpsSccd() {
        return etpsSccd;
    }

    public void setEtpsSccd(String etpsSccd) {
        this.etpsSccd = etpsSccd;
    }

    public String getVehicleIRegno() {
        return vehicleIRegno;
    }

    public void setVehicleIRegno(String vehicleIRegno) {
        this.vehicleIRegno = vehicleIRegno;
    }

    public String getVehicleEntName() {
        return vehicleEntName;
    }

    public void setVehicleEntName(String vehicleEntName) {
        this.vehicleEntName = vehicleEntName;
    }

    public BigDecimal getVehicleLoadWt() {
        return vehicleLoadWt;
    }

    public void setVehicleLoadWt(BigDecimal vehicleLoadWt) {
        this.vehicleLoadWt = vehicleLoadWt;
    }

    public BigDecimal getVehicleWt() {
        return vehicleWt;
    }

    public void setVehicleWt(BigDecimal vehicleWt) {
        this.vehicleWt = vehicleWt;
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
}