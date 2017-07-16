package com.powerbridge.bssp.sas.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * <p>
 * 扡卡备案表
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-05
 */
@TableName("sas_dragcar_cus")
public class SasDragcarCus implements Serializable {

    private static final long serialVersionUID = 1L;
	/**
	 * 预录入编号
	 */
	@TableField("ETPS_PREENT_NO")
	private String etpsPreentNo;
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
	 * 申报类型
	 */
	@TableField("DCL_TYPECD")
	private String dclTypecd;
	/**
	 * 主键
	 */
	@TableId("UID")
	private String uid;
	/**
	 * 变更次数
	 */
	@TableField("CHG_TMS_CNT")
	private Integer chgTmsCnt;
	/**
	 * 车架号
	 */
	@TableField("VEHICLE_FRAME_NO")
	private String vehicleFrameNo;
	/**
	 * 车架重
	 */
	@TableField("VEHICLE_FRAME_WT")
	private BigDecimal vehicleFrameWt;
	/**
	 * 车架类型
	 */
	@TableField("VEHICLE_FRAME_TYPE")
	private String vehicleFrameType;
	/**
	 * 联系人
	 */
	@TableField("CONTACT_ER")
	private String contactEr;
	/**
	 * 联系电话
	 */
	@TableField("CONTACT_TELE")
	private String contactTele;
	/**
	 * 联系地址
	 */
	@TableField("CONTACT_ADDRESS")
	private String contactAddress;
	/**
	 * 所属企业编码
	 */
	@TableField("ETPSNO")
	private String etpsno;
	/**
	 * 所属企业名称
	 */
	@TableField("ETPS_NM")
	private String etpsNm;
	/**
	 * 所属企业社会信用编码
	 */
	@TableField("ETPS_SCCD")
	private String etpsSccd;
	/**
	 * 备注
	 */
	@TableField("RMK")
	private String rmk;
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
	 * 录入日期
	 */
	@TableField("DEC_TIME")
	private String decTime;

	@TableField(exist = false)
	private String decTimeStart;

	@TableField(exist = false)
	private String decTimeEnd;

	@TableField(exist = false)
	private String chkstatusName;

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
		this.dclTime = dclTime;
	}

	public String getDclTypecd() {
		return dclTypecd;
	}

	public void setDclTypecd(String dclTypecd) {
		this.dclTypecd = dclTypecd;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Integer getChgTmsCnt() {
		return chgTmsCnt;
	}

	public void setChgTmsCnt(Integer chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}

	public String getVehicleFrameNo() {
		return vehicleFrameNo;
	}

	public void setVehicleFrameNo(String vehicleFrameNo) {
		this.vehicleFrameNo = vehicleFrameNo;
	}

	public BigDecimal getVehicleFrameWt() {
		return vehicleFrameWt;
	}

	public void setVehicleFrameWt(BigDecimal vehicleFrameWt) {
		this.vehicleFrameWt = vehicleFrameWt;
	}

	public String getVehicleFrameType() {
		return vehicleFrameType;
	}

	public void setVehicleFrameType(String vehicleFrameType) {
		this.vehicleFrameType = vehicleFrameType;
	}

	public String getContactEr() {
		return contactEr;
	}

	public void setContactEr(String contactEr) {
		this.contactEr = contactEr;
	}

	public String getContactTele() {
		return contactTele;
	}

	public void setContactTele(String contactTele) {
		this.contactTele = contactTele;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
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

	public String getChkstatusName() {
		return chkstatusName;
	}

	public void setChkstatusName(String chkstatusName) {
		this.chkstatusName = chkstatusName;
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

	public String getEtpsPreentNo() {
		return etpsPreentNo;
	}

	public void setEtpsPreentNo(String etpsPreentNo) {
		this.etpsPreentNo = etpsPreentNo;
	}
}

