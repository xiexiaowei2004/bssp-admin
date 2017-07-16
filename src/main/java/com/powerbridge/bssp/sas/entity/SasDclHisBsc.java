package com.powerbridge.bssp.sas.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;

import java.io.Serializable;

/**
 * <p>
 * 业务申报历史表
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
@TableName("sas_dcl_his_bsc")
public class SasDclHisBsc implements Serializable {

    private static final long serialVersionUID = 1L;

	//列表页面搜素区内企业时使用
	@TableField(exist = false)
	private String areainEtps;

	/**
	 * 申报类型名称 用于列表显示
	 */
	@TableField(exist = false)
	private String dclTypeNm;

	/**
	 * 单据状态名称 用于列表显示
	 */
	@TableField(exist = false)
	private String chkStatusNm;

	/**
	 * 主键
	 */
	@TableId("UID")
	private String uid;
	/**
	 * 申报表编号
	 */
	@TableField("SAS_DCL_NO")
	private String sasDclNo;
	/**
	 * 变更次数
	 */
	@TableField("CHG_TMS_CNT")
	private Integer chgTmsCnt;
	/**
	 * 主管关区代码
	 */
	@TableField("MASTER_CUSCD")
	private String masterCuscd;
	/**
	 * 申报表预录入编号
	 */
	@TableField("SAS_DCL_PREENT_NO")
	private String sasDclPreentNo;
	/**
	 * 申报类型代码
	 */
	@TableField("DCL_TYPECD")
	private String dclTypecd;

	/**
	 * 业务类型代码
	 */
	@TableField("BUSINESS_TYPECD")
	private String businessTypecd;
	/**
	 * 货物流向代码
	 */
	@TableField("DIRECTION_TYPECD")
	private String directionTypecd;
	/**
	 * 区内账册编号
	 */
	@TableField("AREAIN_ORIACT_NO")
	private String areainOriactNo;
	/**
	 * 区外账册编号
	 */
	@TableField("AREAOUT_ORIACT_NO")
	private String areaoutOriactNo;
	/**
	 * 区内企业编码
	 */
	@TableField("AREAIN_ETPSNO")
	private String areainEtpsno;
	/**
	 * 区内企业名称
	 */
	@TableField("AREAIN_ETPS_NM")
	private String areainEtpsNm;
	/**
	 * 区内企业社会信用代码
	 */
	@TableField("AREAIN_ETPS_SCCD")
	private String areainEtpsSccd;
	/**
	 * 区外企业编码
	 */
	@TableField("AREAOUT_ETPSNO")
	private String areaoutEtpsno;
	/**
	 * 区外企业名称
	 */
	@TableField("AREAOUT_ETPS_NM")
	private String areaoutEtpsNm;
	/**
	 * 区外企业社会信用代码
	 */
	@TableField("AREAOUT_ETPS_SCCD")
	private String areaoutEtpsSccd;
	/**
	 * 保证金征收单编号
	 */
	@TableField("DPST_LEVY_BL_NO")
	private String dpstLevyBlNo;
	/**
	 * 有效期
	 */
	@TableField("VALID_TIME")
	private String validTime;
	/**
	 * 展示地
	 */
	@TableField("EXHIBITION_PLACE")
	private String exhibitionPlace;
	/**
	 * 申请人
	 */
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
	 * 结案审批时间
	 */
	@TableField("CLS_CASE_TIME")
	private String clsCaseTime;
	/**
	 * 审批标志代码
	 */
	@TableField("EMAPV_MARKCD")
	private String emapvMarkcd;
	/**
	 * 申报表状态代码
	 */
	@TableField("DCL_TB_STUCD")
	private String dclTbStucd;
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
	@TableField("ETPS_PREENT_NO")
	private String etpsPreentNo;
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
	 * 单据类型
	 */
	@TableField("DOC_TYPE")
	private String docType;
	/**
	 * 单据状态
	 */
	@TableField("CHK_STATUS")
	private String chkStatus;
	/**
	 * 回执状态
	 */
	@TableField("RET_CHANNEL")
	private String retChannel;
	/**
	 * 录入单位代码
	 */
	@TableField("INPUT_COP_NO")
	private String inputCopNo;
	/**
	 * 录入单位名称
	 */
	@TableField("INPUT_COP_NAME")
	private String inputCopName;
	/**
	 * 操作员名称
	 */
	@TableField("INPUTER_NAME")
	private String inputerName;
	/**
	 * 操作员代码
	 */
	@TableField("INPUTER_CODE")
	private String inputerCode;
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
	 * 业务类型
	 */
	@TableField("BIZ_TYPE")
	private String bizType;
	/**
	 * 审批描述
	 */
	@TableField("EMAPV_DESCRIPTION")
	private String emapvDescription;

	/**
	 * 备用
	 */
	@TableField(value="COL1")
	private String col1;

	/**
	 * 备用
	 */
	@TableField(value="COL2")
	private String col2;

	/**
	 * 备用
	 */
	@TableField(value="COL3")
	private String col3;

	/**
	 * 备用
	 */
	@TableField(value="COL4")
	private Double col4;

	/**
	 *  业务类型 用于菜单区分是单据类型
	 */
	@TableField(exist = false)
	private String busType;

	/**
	 * 用于查询 有效期
	 */
	@TableField(exist = false)
	private String validTimeStart;

	@TableField(exist = false)
	private String validTimeEnd;

	/**
	 * 用于查询 录入日期
	 */
	@TableField(exist = false)
	private String decTimeStart;

	@TableField(exist = false)
	private String decTimeEnd;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSasDclNo() {
		return sasDclNo;
	}

	public void setSasDclNo(String sasDclNo) {
		this.sasDclNo = sasDclNo;
	}

	public Integer getChgTmsCnt() {
		return chgTmsCnt;
	}

	public void setChgTmsCnt(Integer chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}


	public String getMasterCuscd() {
		return masterCuscd;
	}

	public void setMasterCuscd(String masterCuscd) {
		this.masterCuscd = masterCuscd;
	}

	public String getSasDclPreentNo() {
		return sasDclPreentNo;
	}

	public void setSasDclPreentNo(String sasDclPreentNo) {
		this.sasDclPreentNo = sasDclPreentNo;
	}

	public String getDclTypecd() {
		return dclTypecd;
	}

	public void setDclTypecd(String dclTypecd) {
		this.dclTypecd = dclTypecd;
	}

	public String getBusinessTypecd() {
		return businessTypecd;
	}

	public void setBusinessTypecd(String businessTypecd) {
		this.businessTypecd = businessTypecd;
	}

	public String getDirectionTypecd() {
		return directionTypecd;
	}

	public void setDirectionTypecd(String directionTypecd) {
		this.directionTypecd = directionTypecd;
	}

	public String getAreainOriactNo() {
		return areainOriactNo;
	}

	public void setAreainOriactNo(String areainOriactNo) {
		this.areainOriactNo = areainOriactNo;
	}

	public String getAreaoutOriactNo() {
		return areaoutOriactNo;
	}

	public void setAreaoutOriactNo(String areaoutOriactNo) {
		this.areaoutOriactNo = areaoutOriactNo;
	}

	public String getAreainEtpsno() {
		return areainEtpsno;
	}

	public void setAreainEtpsno(String areainEtpsno) {
		this.areainEtpsno = areainEtpsno;
	}

	public String getAreainEtpsNm() {
		return areainEtpsNm;
	}

	public void setAreainEtpsNm(String areainEtpsNm) {
		this.areainEtpsNm = areainEtpsNm;
	}

	public String getAreainEtpsSccd() {
		return areainEtpsSccd;
	}

	public void setAreainEtpsSccd(String areainEtpsSccd) {
		this.areainEtpsSccd = areainEtpsSccd;
	}

	public String getAreaoutEtpsno() {
		return areaoutEtpsno;
	}

	public void setAreaoutEtpsno(String areaoutEtpsno) {
		this.areaoutEtpsno = areaoutEtpsno;
	}

	public String getAreaoutEtpsNm() {
		return areaoutEtpsNm;
	}

	public void setAreaoutEtpsNm(String areaoutEtpsNm) {
		this.areaoutEtpsNm = areaoutEtpsNm;
	}

	public String getAreaoutEtpsSccd() {
		return areaoutEtpsSccd;
	}

	public void setAreaoutEtpsSccd(String areaoutEtpsSccd) {
		this.areaoutEtpsSccd = areaoutEtpsSccd;
	}

	public String getDpstLevyBlNo() {
		return dpstLevyBlNo;
	}

	public void setDpstLevyBlNo(String dpstLevyBlNo) {
		this.dpstLevyBlNo = dpstLevyBlNo;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = DateUtil.timeSwitch(validTime);
	}

	public String getExhibitionPlace() {
		return exhibitionPlace;
	}

	public void setExhibitionPlace(String exhibitionPlace) {
		this.exhibitionPlace = exhibitionPlace;
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

	public String getPutrecEmapvTime() {
		return putrecEmapvTime;
	}

	public void setPutrecEmapvTime(String putrecEmapvTime) {
		this.putrecEmapvTime = DateUtil.timeSwitch(putrecEmapvTime);
	}

	public String getChgEmapvTime() {
		return chgEmapvTime;
	}

	public void setChgEmapvTime(String chgEmapvTime) {
		this.chgEmapvTime = DateUtil.timeSwitch(chgEmapvTime);
	}

	public String getClsCaseTime() {
		return clsCaseTime;
	}

	public void setClsCaseTime(String clsCaseTime) {
		this.clsCaseTime = DateUtil.timeSwitch(clsCaseTime);
	}

	public String getEmapvMarkcd() {
		return emapvMarkcd;
	}

	public void setEmapvMarkcd(String emapvMarkcd) {
		this.emapvMarkcd = emapvMarkcd;
	}

	public String getDclTbStucd() {
		return dclTbStucd;
	}

	public void setDclTbStucd(String dclTbStucd) {
		this.dclTbStucd = dclTbStucd;
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

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
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

	public String getInputerName() {
		return inputerName;
	}

	public void setInputerName(String inputerName) {
		this.inputerName = inputerName;
	}

	public String getInputerCode() {
		return inputerCode;
	}

	public void setInputerCode(String inputerCode) {
		this.inputerCode = inputerCode;
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
		this.chkTime = chkTime;
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

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getAreainEtps() {
		return areainEtps;
	}

	public void setAreainEtps(String areainEtps) {
		this.areainEtps = areainEtps;
	}

	public String getValidTimeStart() {
		return validTimeStart;
	}

	public void setValidTimeStart(String validTimeStart) {
		this.validTimeStart = validTimeStart;
	}

	public String getValidTimeEnd() {
		return validTimeEnd;
	}

	public void setValidTimeEnd(String validTimeEnd) {
		this.validTimeEnd = validTimeEnd;
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

	public String getEmapvDescription() {
		return emapvDescription;
	}

	public void setEmapvDescription(String emapvDescription) {
		this.emapvDescription = emapvDescription;
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

	public Double getCol4() {
		return col4;
	}

	public void setCol4(Double col4) {
		this.col4 = col4;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getDclTypeNm() {
		return dclTypeNm;
	}

	public void setDclTypeNm(String dclTypeNm) {
		this.dclTypeNm = dclTypeNm;
	}

	public String getChkStatusNm() {
		return chkStatusNm;
	}

	public void setChkStatusNm(String chkStatusNm) {
		this.chkStatusNm = chkStatusNm;
	}

}
