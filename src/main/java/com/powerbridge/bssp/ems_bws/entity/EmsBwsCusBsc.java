package com.powerbridge.bssp.ems_bws.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;

/**
 * <p>
 * 物流账册正式表
 * </p>
 *
 * @author jokylao
 * @since 2017-06-09
 */
@TableName("ems_bws_cus_bsc")
public class  EmsBwsCusBsc implements Serializable {
	@TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("UID")
	private String uid;
    /**
     * 仓库账册号
     */
	@TableField("BWS_NO")
	private String bwsNo;
    /**
     * 变更次数
     */
	@TableField("CHG_TMS_CNT")
	private BigDecimal chgTmsCnt;
    /**
     * 统一编号
     */
	@TableField("ETPS_PREENT_NO")
	private String etpsPreentNo;
    /**
     * 申报类型代码
     */
	@TableField("DCL_TYPECD")
	private String dclTypecd;
    /**
     * 区域场所类别
     */
	@TableField("BWL_TYPECD")
	private String bwlTypecd;
    /**
     * 主管海关代码
     */
	@TableField("MASTER_CUSCD")
	private String masterCuscd;
    /**
     * 经营企业编码
     */
	@TableField("BIZOP_ETPSNO")
	private String bizopEtpsno;
    /**
     * 经营企业名称
     */
	@TableField("BIZOP_ETPS_NM")
	private String bizopEtpsNm;
    /**
     * 经营企业社会信用代码
     */
	@TableField("BIZOP_ETPS_SCCD")
	private String bizopEtpsSccd;
    /**
     * 仓库编号
     */
	@TableField("HOUSE_NO")
	private String houseNo;
    /**
     * 仓库名称
     */
	@TableField("HOUSE_NM")
	private String houseNm;
    /**
     * 申报企业编号
     */
	@TableField("DCL_ETPSNO")
	private String dclEtpsno;
    /**
     * 申报企业名称
     */
	@TableField("DCL_ETPS_NM")
	private String dclEtpsNm;
    /**
     * 申报企业社会信用代码
     */
	@TableField("DCL_ETPS_SCCD")
	private String dclEtpsSccd;
    /**
     * 申报单位类型代码
     */
	@TableField("DCL_ETPS_TYPECD")
	private String dclEtpsTypecd;
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
     * 企业类型代码
     */
	@TableField("HOUSE_TYPECD")
	private String houseTypecd;
    /**
     * 仓库面积
     */
	@TableField("HOUSE_AREA")
	private BigDecimal houseArea;
    /**
     * 仓库容积
     */
	@TableField("HOUSE_VOLUME")
	private BigDecimal houseVolume;
    /**
     * 仓库地址
     */
	@TableField("HOUSE_ADDRESS")
	private String houseAddress;
    /**
     * 申报日期
     */
	@TableField("DCL_TIME")
	private String dclTime;
    /**
     * 录入日期
     */
	@TableField("DEC_TIME")
	private String decTime;
	 /**
     * 录入日期起始
     */
    @TableField(exist = false)
    private String decTimeStart;
    /**
     * 录入日期终结
     */
    @TableField(exist = false)
    private String decTimeEnd;
    

    /**
     * 退税标志代码
     */
	@TableField("TAX_TYPECD")
	private String taxTypecd;
    /**
     * 备案批准时间
     */
	@TableField("PUTREC_APPR_TIME")
	private String putrecApprTime;
    /**
     * 变更批准时间
     */
	@TableField("CHG_APPR_TIME")
	private String chgApprTime;
    /**
     * 结束有效日期
     */
	@TableField("FINISH_VALID_DATE")
	private String finishValidDate;
	/**
     * 结束有效日期起始
     */
    @TableField(exist = false)
    private String finishValidDateStart;
    /**
     * 结束有效日期终结
     */
    @TableField(exist = false)
    private String finishValidDateEnd;
    /**
     * 暂停变更标记代码
     */
	@TableField("PAUSE_CHG_MARKCD")
	private String pauseChgMarkcd;
    /**
     * 审核状态代码
     */
	@TableField("EMAPV_STUCD")
	private String emapvStucd;
    /**
     * 申报标志代码
     */
	@TableField("DCL_MARKCD")
	private String dclMarkcd;
    /**
     * 记账模式代码
     */
	@TableField("APPEND_TYPECD")
	private String appendTypecd;
    /**
     * 备注
     */
	@TableField("RMK")
	private String rmk;
    /**
     * 所属系统
     */
	@TableField("OWNER_SYSTEM")
	private String ownerSystem;
    /**
     * 企业备案号
     */
	@TableField("COP_ENT_NO")
	private String copEntNo;
    /**
     * 申报方式(备案/变更)
     */
	@TableField("DECLARE_MARK")
	private String declareMark;
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
	@TableField("RET_CHANNEL")
	private String retChannel;
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
     * 申报（录入）企业代码
     */
	@TableField("INPUT_COP_NO")
	private String inputCopNo;

	public String getInputEtpsSccd() {
		return inputEtpsSccd;
	}

	public void setInputEtpsSccd(String inputEtpsSccd) {
		this.inputEtpsSccd = inputEtpsSccd;
	}

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
	 * 单据状态名称
	 */
	@TableField( exist = false)
	private String chkStatusName;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getBwsNo() {
		return bwsNo;
	}

	public void setBwsNo(String bwsNo) {
		this.bwsNo = bwsNo;
	}

	public BigDecimal getChgTmsCnt() {
		return chgTmsCnt;
	}

	public void setChgTmsCnt(BigDecimal chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}

	public String getEtpsPreentNo() {
		return etpsPreentNo;
	}

	public void setEtpsPreentNo(String etpsPreentNo) {
		this.etpsPreentNo = etpsPreentNo;
	}

	public String getDclTypecd() {
		return dclTypecd;
	}

	public void setDclTypecd(String dclTypecd) {
		this.dclTypecd = dclTypecd;
	}

	public String getBwlTypecd() {
		return bwlTypecd;
	}

	public void setBwlTypecd(String bwlTypecd) {
		this.bwlTypecd = bwlTypecd;
	}

	public String getMasterCuscd() {
		return masterCuscd;
	}

	public void setMasterCuscd(String masterCuscd) {
		this.masterCuscd = masterCuscd;
	}

	public String getBizopEtpsno() {
		return bizopEtpsno;
	}

	public void setBizopEtpsno(String bizopEtpsno) {
		this.bizopEtpsno = bizopEtpsno;
	}

	public String getBizopEtpsNm() {
		return bizopEtpsNm;
	}

	public void setBizopEtpsNm(String bizopEtpsNm) {
		this.bizopEtpsNm = bizopEtpsNm;
	}

	public String getBizopEtpsSccd() {
		return bizopEtpsSccd;
	}

	public void setBizopEtpsSccd(String bizopEtpsSccd) {
		this.bizopEtpsSccd = bizopEtpsSccd;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getHouseNm() {
		return houseNm;
	}

	public void setHouseNm(String houseNm) {
		this.houseNm = houseNm;
	}

	public String getDclEtpsno() {
		return dclEtpsno;
	}

	public void setDclEtpsno(String dclEtpsno) {
		this.dclEtpsno = dclEtpsno;
	}

	public String getDclEtpsNm() {
		return dclEtpsNm;
	}

	public void setDclEtpsNm(String dclEtpsNm) {
		this.dclEtpsNm = dclEtpsNm;
	}

	public String getDclEtpsSccd() {
		return dclEtpsSccd;
	}

	public void setDclEtpsSccd(String dclEtpsSccd) {
		this.dclEtpsSccd = dclEtpsSccd;
	}

	public String getDclEtpsTypecd() {
		return dclEtpsTypecd;
	}

	public void setDclEtpsTypecd(String dclEtpsTypecd) {
		this.dclEtpsTypecd = dclEtpsTypecd;
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

	public String getHouseTypecd() {
		return houseTypecd;
	}

	public void setHouseTypecd(String houseTypecd) {
		this.houseTypecd = houseTypecd;
	}

	public BigDecimal getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(BigDecimal houseArea) {
		this.houseArea = houseArea;
	}

	public BigDecimal getHouseVolume() {
		return houseVolume;
	}

	public void setHouseVolume(BigDecimal houseVolume) {
		this.houseVolume = houseVolume;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public String getDclTime() {
		return dclTime;
	}

	public void setDclTime(String dclTime) {
		this.dclTime = DateUtil.timeSwitch(dclTime);
	}



	public String getTaxTypecd() {
		return taxTypecd;
	}

	public void setTaxTypecd(String taxTypecd) {
		this.taxTypecd = taxTypecd;
	}

	public String getPutrecApprTime() {
		return putrecApprTime;
	}

	public void setPutrecApprTime(String putrecApprTime) {
		this.putrecApprTime = DateUtil.timeSwitch(putrecApprTime);
	}

	public String getChgApprTime() {
		return chgApprTime;
	}

	public void setChgApprTime(String chgApprTime) {
		this.chgApprTime = DateUtil.timeSwitch(chgApprTime);
	}

	public String getFinishValidDate() {
		return finishValidDate;
	}

	public void setFinishValidDate(String finishValidDate) {
		this.finishValidDate = DateUtil.timeSwitch(finishValidDate);
	}

	public String getPauseChgMarkcd() {
		return pauseChgMarkcd;
	}

	public void setPauseChgMarkcd(String pauseChgMarkcd) {
		this.pauseChgMarkcd = pauseChgMarkcd;
	}

	public String getEmapvStucd() {
		return emapvStucd;
	}

	public void setEmapvStucd(String emapvStucd) {
		this.emapvStucd = emapvStucd;
	}

	public String getDclMarkcd() {
		return dclMarkcd;
	}

	public void setDclMarkcd(String dclMarkcd) {
		this.dclMarkcd = dclMarkcd;
	}

	public String getAppendTypecd() {
		return appendTypecd;
	}

	public void setAppendTypecd(String appendTypecd) {
		this.appendTypecd = appendTypecd;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getOwnerSystem() {
		return ownerSystem;
	}

	public void setOwnerSystem(String ownerSystem) {
		this.ownerSystem = ownerSystem;
	}

	public String getCopEntNo() {
		return copEntNo;
	}

	public void setCopEntNo(String copEntNo) {
		this.copEntNo = copEntNo;
	}

	public String getDeclareMark() {
		return declareMark;
	}

	public void setDeclareMark(String declareMark) {
		this.declareMark = declareMark;
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

	

	public String getFinishValidDateStart() {
		return finishValidDateStart;
	}

	public void setFinishValidDateStart(String finishValidDateStart) {
		this.finishValidDateStart = finishValidDateStart;
	}

	public String getFinishValidDateEnd() {
		return finishValidDateEnd;
	}

	public void setFinishValidDateEnd(String finishValidDateEnd) {
		this.finishValidDateEnd = finishValidDateEnd;
	}

	public String getDecTime() {
		return decTime;
	}

	public void setDecTime(String decTime) {
		this.decTime = DateUtil.timeSwitch(decTime);
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

	public String getChkStatusName() {
		return chkStatusName;
	}

	public void setChkStatusName(String chkStatusName) {
		this.chkStatusName = chkStatusName;
	}
}
