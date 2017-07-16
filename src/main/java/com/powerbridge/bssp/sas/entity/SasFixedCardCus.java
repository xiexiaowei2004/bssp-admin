package com.powerbridge.bssp.sas.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author luohongsu
 * @since 2017-07-05
 */
@TableName("sas_fixed_card_cus")
public class SasFixedCardCus implements Serializable {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId("UID")
	private String uid;
	/**
	 * 变更次数
	 */
	@TableField("CHG_TMS_CNT")
	private BigDecimal chgTmsCnt;
	/**
	 * 长期卡备案号
	 */
	@TableField("FIXED_CARD_REGNO")
	private String fixedCardRegno;
	/**
	 * IC物理卡卡号
	 */
	@TableField("IC_PHYSICS_CODE")
	private String icPhysicsCode;
	/**
	 * IC卡编号
	 */
	@TableField("IC_CODE")
	private String icCode;
	/**
	 * IC卡类型
	 */
	@TableField("IC_TYPE")
	private String icType;
	/**
	 * 企业备案号*
	 */
	@TableField("COP_ENT_NO")
	private String copEntNo;
	/**
	 * 领用企业海关代码
	 */
	@TableField("USE_ETPS_CODE")
	private String useEtpsCode;
	/**
	 * 领用企业名称
	 */
	@TableField("USE_ETPS_NAME")
	private String useEtpsName;
	/**
	 * 社会信用代码
	 */
	@TableField("USE_ETPS_SCCD")
	private String useEtpsSccd;
	/**
	 * 有效日期
	 */
	@TableField("VALID_DATE")
	private String validDate;
	/**
	 * 车辆类型
	 */
	@TableField("VEHICLE_TYPECD")
	private String vehicleTypecd;
	/**
	 * 车牌号
	 */
	@TableField("VEHICLE_NO")
	private String vehicleNo;
	/**
	 * 长期卡状态
	 */
	@TableField("FIXED_CARD_STATUS")
	private String fixedCardStatus;
	/**
	 * 备案日期
	 */
	@TableField("REG_DATE")
	private String regDate;
	/**
	 * 备注
	 */
	@TableField("RMK")
	private String rmk;
	/**
	 * 操作日期
	 */
	@TableField("DEC_TIME")
	private String decTime;
	/**
	 * 主管海关
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
	@TableField("RET_CHANNEL")
	private String retChannel;
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
	 * 申报（录入）企业社会信用代码
	 */
	@TableField("INPUT_ETPS_SCCD")
	private String inputEtpsSccd;
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
	 * 车辆分类
	 */
	@TableField("VEHICLE_SORT")
	private String vehicleSort;

	@TableField(exist = false)
	private String regDateEnd;
	@TableField(exist = false)
	private String regDateStart;
	@TableField(exist = false)
	private String validDateStart;
	@TableField(exist = false)
	private String validDateEnd;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public BigDecimal getChgTmsCnt() {
		return chgTmsCnt;
	}

	public void setChgTmsCnt(BigDecimal chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}

	public String getFixedCardRegno() {
		return fixedCardRegno;
	}

	public void setFixedCardRegno(String fixedCardRegno) {
		this.fixedCardRegno = fixedCardRegno;
	}

	public String getIcPhysicsCode() {
		return icPhysicsCode;
	}

	public void setIcPhysicsCode(String icPhysicsCode) {
		this.icPhysicsCode = icPhysicsCode;
	}

	public String getIcCode() {
		return icCode;
	}

	public void setIcCode(String icCode) {
		this.icCode = icCode;
	}

	public String getIcType() {
		return icType;
	}

	public void setIcType(String icType) {
		this.icType = icType;
	}

	public String getCopEntNo() {
		return copEntNo;
	}

	public void setCopEntNo(String copEntNo) {
		this.copEntNo = copEntNo;
	}

	public String getUseEtpsCode() {
		return useEtpsCode;
	}

	public void setUseEtpsCode(String useEtpsCode) {
		this.useEtpsCode = useEtpsCode;
	}

	public String getUseEtpsName() {
		return useEtpsName;
	}

	public void setUseEtpsName(String useEtpsName) {
		this.useEtpsName = useEtpsName;
	}

	public String getUseEtpsSccd() {
		return useEtpsSccd;
	}

	public void setUseEtpsSccd(String useEtpsSccd) {
		this.useEtpsSccd = useEtpsSccd;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getVehicleTypecd() {
		return vehicleTypecd;
	}

	public void setVehicleTypecd(String vehicleTypecd) {
		this.vehicleTypecd = vehicleTypecd;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getFixedCardStatus() {
		return fixedCardStatus;
	}

	public void setFixedCardStatus(String fixedCardStatus) {
		this.fixedCardStatus = fixedCardStatus;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getDecTime() {
		return decTime;
	}

	public void setDecTime(String decTime) {
		this.decTime = decTime;
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
		this.chkTime = chkTime;
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

	public String getInputEtpsSccd() {
		return inputEtpsSccd;
	}

	public void setInputEtpsSccd(String inputEtpsSccd) {
		this.inputEtpsSccd = inputEtpsSccd;
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

	public String getVehicleSort() {return vehicleSort;}

	public void setVehicleSort(String vehicleSort) {this.vehicleSort = vehicleSort;}

	public String getRegDateEnd() {return regDateEnd;}

	public void setRegDateEnd(String regDateEnd) {this.regDateEnd = regDateEnd;}

	public String getRegDateStart() {return regDateStart;}

	public void setRegDateStart(String regDateStart) {this.regDateStart = regDateStart;}

	public String getValidDateStart() {return validDateStart;}

	public void setValidDateStart(String validDateStart) {this.validDateStart = validDateStart;}

	public String getValidDateEnd() {return validDateEnd;}

	public void setValidDateEnd(String validDateEnd) {this.validDateEnd = validDateEnd;}
}
