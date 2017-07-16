package com.powerbridge.bssp.ems.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 账册备案正式表
 * </p>
 *
 * @author jokylao
 * @since 2017-06-02
 */
@TableName("ems_cus_bsc")
public class EmsCusBsc implements Serializable {
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
	 * 预录入编号
	 */
	@TableField("ETPS_PREENT_NO")
	private String etpsPreentNo;
	/**
	 * 主管关区代码
	 */
	@TableField("MASTER_CUSCD")
	private String masterCuscd;
	/**
	 * 经营企业社会信用代码
	 */
	@TableField("BIZOP_ETPS_SCCD")
	private String bizopEtpsSccd;
	/**
	 * 经营企业编号
	 */
	@TableField("BIZOP_ETPSNO")
	private String bizopEtpsno;
	/**
	 * 经营企业名称
	 */
	@TableField("BIZOP_ETPS_NM")
	private String bizopEtpsNm;
	/**
	 * 收发货企业社会信用代码
	 */
	@TableField("RVSNGD_ETPS_SCCD")
	private String rvsngdEtpsSccd;
	/**
	 * 收货企业编号
	 */
	@TableField("RCVGD_ETPSNO")
	private String rcvgdEtpsno;
	/**
	 * 收货企业名称
	 */
	@TableField("RCVGD_ETPS_NM")
	private String rcvgdEtpsNm;
	/**
	 * 申报企业社会信用代码
	 */
	@TableField("DCL_ETPS_SCCD")
	private String dclEtpsSccd;
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
	 * 申报企业类型代码
	 */
	@TableField("DCL_ETPS_TYPECD")
	private String dclEtpsTypecd;
	/**
	 * 账册类型代码
	 */
	@TableField("EMS_TYPECD")
	private String emsTypecd;
	/**
	 * 账册类型
	 */
	@TableField(exist = false)
	private String emsTypenm;
	/**
	 * 申报类型代码
	 */
	@TableField("DCL_TYPECD")
	private String dclTypecd;
	/**
	 * 结束有效日期
	 */
	@TableField("FINISH_VALID_DATE")
	private String finishValidDate;
	/**
	 * 批准证编号
	 */
	@TableField("APCRET_NO")
	private String apcretNo;
	/**
	 * 联网企业档案库编号
	 */
	@TableField("NETWK_ETPS_ARCRP_NO")
	private String netwkEtpsArcrpNo;
	/**
	 * 最大周转金额
	 */
	@TableField("MAX_TOVR_AMT")
	private BigDecimal maxTovrAmt;
	/**
	 * 实际进口总金额
	 */
	@TableField("IMG_AMOUNT")
	private BigDecimal imgAmount;
	/**
	 * 实际出口总金额
	 */
	@TableField("EXG_AMOUNT")
	private BigDecimal exgAmount;
	/**
	 * 料件项数
	 */
	@TableField("IMG_ITEMS")
	private BigDecimal imgItems;
	/**
	 * 成品项数
	 */
	@TableField("EXG_ITEMS")
	private BigDecimal exgItems;
	/**
	 * 申报时间
	 */
	@TableField("DCL_TIME")
	private String dclTime;
	/**
	 * 单耗申报环节代码
	 */
	@TableField("UCNS_DCL_SEGCD")
	private String ucnsDclSegcd;
	/**
	 * 申报标记代码
	 */
	@TableField("DCL_MARKCD")
	private String dclMarkcd;
	/**
	 * 审批状态代码
	 */
	@TableField("EMAPV_STUCD")
	private String emapvStucd;
	/**
	 * 暂停进出口标记代码
	 */
	@TableField("PAUSE_IMPEXP_MARKCD")
	private String pauseImpexpMarkcd;
	/**
	 * 暂停变更标记代码
	 */
	@TableField("PAUSE_CHG_MARKCD")
	private String pauseChgMarkcd;
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
	 * 最近核销时间
	 */
	@TableField("RCNT_VCLR_TIME")
	private String rcntVclrTime;
	/**
	 * 核销申请时间
	 */
	@TableField("VCLR_APPLY_TIME")
	private String vclrApplyTime;
	/**
	 * 核销周期值
	 */
	@TableField("VCLR_PRID_VAL")
	private BigDecimal vclrPridVal;
	/**
	 * 核算标记代码
	 */
	@TableField("ADJACC_MARKCD")
	private String adjaccMarkcd;
	/**
	 * 核算次数
	 */
	@TableField("ADJACC_TMS_CNT")
	private BigDecimal adjaccTmsCnt;
	/**
	 * 单耗版本号控制标志
	 */
	@TableField("UCNS_VERNO_CNTR_FLAG")
	private String ucnsVernoCntrFlag;
	/**
	 * 涉案标记代码
	 */
	@TableField("RCASE_MARKCD")
	private String rcaseMarkcd;
	/**
	 * 账册执行标志
	 */
	@TableField("EMS_EXE_MARK")
	private String emsExeMark;
	/**
	 * 企业具备自核资格标志
	 */
	@TableField("ETPS_POSES_SADJA_QUA_FLAG")
	private String etpsPosesSadjaQuaFlag;
	/**
	 * 备注
	 */
	@TableField("RMK")
	private String rmk;
	/**
	 * 企业备案号
	 */
	@TableField("COP_ENT_NO")
	private String copEntNo;
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
	 * 主管关区名称
	 */
	@TableField(exist = false)
	private String masterCusName;
	/**
	 * 场地代码名称
	 */
	@TableField(exist = false)
	private String areaName;
	/**
	 * 申报类型名称
	 */
	@TableField(exist = false)
	private String dclTypename;
	/**
	 * 单据状态名称
	 */
	@TableField(exist = false)
	private String chkStatusName;
	/**
	 * 录入日期起始
	 */
	@TableField(exist = false)
	private String finishValidDateStart;
	/**
	 * 录入日期终结
	 */
	@TableField(exist = false)
	private String finishValidDateEnd;
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
		this.chgTmsCnt = chgTmsCnt;
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

	public String getBizopEtpsSccd() {
		return bizopEtpsSccd;
	}

	public void setBizopEtpsSccd(String bizopEtpsSccd) {
		this.bizopEtpsSccd = bizopEtpsSccd;
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

	public String getRvsngdEtpsSccd() {
		return rvsngdEtpsSccd;
	}

	public void setRvsngdEtpsSccd(String rvsngdEtpsSccd) {
		this.rvsngdEtpsSccd = rvsngdEtpsSccd;
	}

	public String getRcvgdEtpsno() {
		return rcvgdEtpsno;
	}

	public void setRcvgdEtpsno(String rcvgdEtpsno) {
		this.rcvgdEtpsno = rcvgdEtpsno;
	}

	public String getRcvgdEtpsNm() {
		return rcvgdEtpsNm;
	}

	public void setRcvgdEtpsNm(String rcvgdEtpsNm) {
		this.rcvgdEtpsNm = rcvgdEtpsNm;
	}

	public String getDclEtpsSccd() {
		return dclEtpsSccd;
	}

	public void setDclEtpsSccd(String dclEtpsSccd) {
		this.dclEtpsSccd = dclEtpsSccd;
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

	public String getDclEtpsTypecd() {
		return dclEtpsTypecd;
	}

	public void setDclEtpsTypecd(String dclEtpsTypecd) {
		this.dclEtpsTypecd = dclEtpsTypecd;
	}

	public String getEmsTypecd() {
		return emsTypecd;
	}

	public void setEmsTypecd(String emsTypecd) {
		this.emsTypecd = emsTypecd;
	}

	public String getDclTypecd() {
		return dclTypecd;
	}

	public void setDclTypecd(String dclTypecd) {
		this.dclTypecd = dclTypecd;
	}

	public String getFinishValidDate() {
		return finishValidDate;
	}

	public void setFinishValidDate(String  finishValidDate) {
		this.finishValidDate = finishValidDate==""?null:finishValidDate;
	}

	public String getApcretNo() {
		return apcretNo;
	}

	public void setApcretNo(String apcretNo) {
		this.apcretNo = apcretNo;
	}

	public String getNetwkEtpsArcrpNo() {
		return netwkEtpsArcrpNo;
	}

	public void setNetwkEtpsArcrpNo(String netwkEtpsArcrpNo) {
		this.netwkEtpsArcrpNo = netwkEtpsArcrpNo;
	}

	public BigDecimal getMaxTovrAmt() {
		return maxTovrAmt;
	}

	public void setMaxTovrAmt(BigDecimal maxTovrAmt) {
		this.maxTovrAmt = maxTovrAmt;
	}

	public BigDecimal getImgAmount() {
		return imgAmount;
	}

	public void setImgAmount(BigDecimal imgAmount) {
		this.imgAmount = imgAmount;
	}

	public BigDecimal getExgAmount() {
		return exgAmount;
	}

	public void setExgAmount(BigDecimal exgAmount) {
		this.exgAmount = exgAmount;
	}

	public BigDecimal getImgItems() {
		return imgItems;
	}

	public void setImgItems(BigDecimal imgItems) {
		this.imgItems = imgItems;
	}

	public BigDecimal getExgItems() {
		return exgItems;
	}

	public void setExgItems(BigDecimal exgItems) {
		this.exgItems = exgItems;
	}

	public String getDclTime() {
		return dclTime;
	}

	public void setDclTime(String  dclTime) {
		this.dclTime = dclTime;
	}

	public String getUcnsDclSegcd() {
		return ucnsDclSegcd;
	}

	public void setUcnsDclSegcd(String ucnsDclSegcd) {
		this.ucnsDclSegcd = ucnsDclSegcd;
	}

	public String getDclMarkcd() {
		return dclMarkcd;
	}

	public void setDclMarkcd(String dclMarkcd) {
		this.dclMarkcd = dclMarkcd;
	}

	public String getEmapvStucd() {
		return emapvStucd;
	}

	public void setEmapvStucd(String emapvStucd) {
		this.emapvStucd = emapvStucd;
	}

	public String getPauseImpexpMarkcd() {
		return pauseImpexpMarkcd;
	}

	public void setPauseImpexpMarkcd(String pauseImpexpMarkcd) {
		this.pauseImpexpMarkcd = pauseImpexpMarkcd;
	}

	public String getPauseChgMarkcd() {
		return pauseChgMarkcd;
	}

	public void setPauseChgMarkcd(String pauseChgMarkcd) {
		this.pauseChgMarkcd = pauseChgMarkcd;
	}

	public String getPutrecApprTime() {
		return putrecApprTime;
	}

	public void setPutrecApprTime(String  putrecApprTime) {
		this.putrecApprTime = putrecApprTime==""?null:putrecApprTime;
	}

	public String getChgApprTime() {
		return chgApprTime;
	}

	public void setChgApprTime(String  chgApprTime) {
		this.chgApprTime = chgApprTime==""?null:chgApprTime;
	}

	public String getRcntVclrTime() {
		return rcntVclrTime;
	}

	public void setRcntVclrTime(String  rcntVclrTime) {
		this.rcntVclrTime = rcntVclrTime==""?null:rcntVclrTime;
	}

	public String getVclrApplyTime() {
		return vclrApplyTime;
	}

	public void setVclrApplyTime(String  vclrApplyTime) {
		this.vclrApplyTime = vclrApplyTime==""?null:vclrApplyTime;
	}

	public BigDecimal getVclrPridVal() {
		return vclrPridVal;
	}

	public void setVclrPridVal(BigDecimal vclrPridVal) {
		this.vclrPridVal = vclrPridVal;
	}

	public String getAdjaccMarkcd() {
		return adjaccMarkcd;
	}

	public void setAdjaccMarkcd(String adjaccMarkcd) {
		this.adjaccMarkcd = adjaccMarkcd;
	}

	public BigDecimal getAdjaccTmsCnt() {
		return adjaccTmsCnt;
	}

	public void setAdjaccTmsCnt(BigDecimal adjaccTmsCnt) {
		this.adjaccTmsCnt = adjaccTmsCnt;
	}

	public String getUcnsVernoCntrFlag() {
		return ucnsVernoCntrFlag;
	}

	public void setUcnsVernoCntrFlag(String ucnsVernoCntrFlag) {
		this.ucnsVernoCntrFlag = ucnsVernoCntrFlag;
	}

	public String getRcaseMarkcd() {
		return rcaseMarkcd;
	}

	public void setRcaseMarkcd(String rcaseMarkcd) {
		this.rcaseMarkcd = rcaseMarkcd;
	}

	public String getEmsExeMark() {
		return emsExeMark;
	}

	public void setEmsExeMark(String emsExeMark) {
		this.emsExeMark = emsExeMark;
	}

	public String getEtpsPosesSadjaQuaFlag() {
		return etpsPosesSadjaQuaFlag;
	}

	public void setEtpsPosesSadjaQuaFlag(String etpsPosesSadjaQuaFlag) {
		this.etpsPosesSadjaQuaFlag = etpsPosesSadjaQuaFlag;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
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

	public void setDecTime(String  decTime) {
		this.decTime = decTime;
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

	public void setChkTime(String  chkTime) {
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

	public String getMasterCusName() {
		return masterCusName;
	}

	public void setMasterCusName(String masterCusName) {
		this.masterCusName = masterCusName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getChkStatusName() {
		return chkStatusName;
	}

	public void setChkStatusName(String chkStatusName) {
		this.chkStatusName = chkStatusName;
	}

	public String getDclTypename() {
		return dclTypename;
	}

	public void setDclTypename(String dclTypename) {
		this.dclTypename = dclTypename;
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

	public String getEmsTypenm(){return emsTypenm;}

	public void setEmsTypenm(String emsTypenm) {
		this.emsTypenm = emsTypenm;
	}
}
