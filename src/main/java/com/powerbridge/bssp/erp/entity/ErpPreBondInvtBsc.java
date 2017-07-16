package com.powerbridge.bssp.erp.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 核注清单表头-预处理
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-19
 */
@TableName("erp_pre_bond_invt_bsc")
public class ErpPreBondInvtBsc implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("UID")
	private String uid;
    /**
     * 清单编号
     */
	@TableField("BOND_INVT_NO")
	private String bondInvtNo;
    /**
     * 账册编号
     */
	@TableField("PUTREC_NO")
	private String putrecNo;
    /**
     * 预录入统一编号
     */
	@TableField("INVT_PREENT_NO")
	private String invtPreentNo;
    /**
     * 清单企业内部编号
     */
	@TableField("ETPS_INNER_INVT_NO")
	private String etpsInnerInvtNo;
    /**
     * 经营单位社会信用代码
     */
	@TableField("BIZOP_ETPS_SCCD")
	private String bizopEtpsSccd;
    /**
     * 经营单位编码
     */
	@TableField("BIZOP_ETPSNO")
	private String bizopEtpsno;
    /**
     * 经营单位名称
     */
	@TableField("BIZOP_ETPS_NM")
	private String bizopEtpsNm;
    /**
     * 收发货单位社会信用代码
     */
	@TableField("RVSNGD_ETPS_SCCD")
	private String rvsngdEtpsSccd;
    /**
     * 收发货单位编码
     */
	@TableField("RCVGD_ETPSNO")
	private String rcvgdEtpsno;
    /**
     * 收发货单位名称
     */
	@TableField("RCVGD_ETPS_NM")
	private String rcvgdEtpsNm;
    /**
     * 申报单位社会信用代码
     */
	@TableField("DCL_ETPS_SCCD")
	private String dclEtpsSccd;
    /**
     * 申报单位编码
     */
	@TableField("DCL_ETPSNO")
	private String dclEtpsno;
    /**
     * 申报单位名称
     */
	@TableField("DCL_ETPS_NM")
	private String dclEtpsNm;
    /**
     * 录入日期
     */
	@TableField("DEC_TIME")
	private String decTime;
    /**
     * 料件/成品标志
     */
	@TableField("MTPCK_ENDPRD_MARKCD")
	private String mtpckEndprdMarkcd;
    /**
     * 监管方式
     */
	@TableField("SUPV_MODECD")
	private String supvModecd;
    /**
     * 运输方式
     */
	@TableField("TRSP_MODECD")
	private String trspModecd;
    /**
     * 进出口标志
     */
	@TableField("IMPEXP_MARKCD")
	private String impexpMarkcd;
    /**
     * 进出口岸
     */
	@TableField("IMPEXP_PORTCD")
	private String impexpPortcd;
    /**
     * 申报地海关
     */
	@TableField("DCL_PLC_CUSCD")
	private String dclPlcCuscd;
    /**
     * 启运运抵国别
     */
	@TableField("NATCD")
	private String natcd;
    /**
     * 申请表编号
     */
	@TableField("APPLY_NO")
	private String applyNo;
    /**
     * 流转类型
     */
	@TableField("LIST_TYPE")
	private String listType;

    /**
     * 报关标志
     */
	@TableField("DCLCUS_FLAG")
	private String dclcusFlag;
    /**
     * 报关类型
     */
	@TableField("DCLCUS_TYPECD")
	private String dclcusTypecd;
    /**
     * 清单类型
     */
	@TableField("BOND_INVT_TYPECD")
	private String bondInvtTypecd;
    /**
     * 报关单类型
     */
	@TableField("DEC_TYPECD")
	private String decTypecd;
    /**
     * 关联清单编号
     */
	@TableField("RLT_INVT_NO")
	private String rltInvtNo;
    /**
     * 关联账册编号
     */
	@TableField("RLT_PUTREC_NO")
	private String rltPutrecNo;
    /**
     * 关联报关单编号
     */
	@TableField("RLT_ENTRY_NO")
	private String rltEntryNo;
    /**
     * 关联报关单经营企业社会信用代码
     */
	@TableField("RLT_ENTRY_BIZOP_ETPS_SCCD")
	private String rltEntryBizopEtpsSccd;
    /**
     * 关联报关单经营单位编码
     */
	@TableField("RLT_ENTRY_BIZOP_ETPSNO")
	private String rltEntryBizopEtpsno;
    /**
     * 关联报关单经营单位名称
     */
	@TableField("RLT_ENTRY_BIZOP_ETPS_NM")
	private String rltEntryBizopEtpsNm;
    /**
     * 关联报关单收发货单位社会信用代码
     */
	@TableField("RLT_ENTRY_RVSNGD_ETPS_SCCD")
	private String rltEntryRvsngdEtpsSccd;
    /**
     * 关联报关单收发货单位编码
     */
	@TableField("RLT_ENTRY_RCVGD_ETPSNO")
	private String rltEntryRcvgdEtpsno;
    /**
     * 关联报关单收发货单位名称
     */
	@TableField("RLT_ENTRY_RCVGD_ETPS_NM")
	private String rltEntryRcvgdEtpsNm;
    /**
     * 关联报关单申报单位社会信用代码
     */
	@TableField("RLT_ENTRY_DCL_ETPS_SCCD")
	private String rltEntryDclEtpsSccd;
    /**
     * 关联报关单申报单位编码
     */
	@TableField("RLT_ENTRY_DCL_ETPSNO")
	private String rltEntryDclEtpsno;
    /**
     * 关联报关单申报单位名称
     */
	@TableField("RLT_ENTRY_DCL_ETPS_NM")
	private String rltEntryDclEtpsNm;
    /**
     * 是否生成清单
     */
	@TableField("SUCCESS_FLAG")
	private String successFlag;
    /**
     * 备注
     */
	@TableField("RMK")
	private String rmk;

	/**
	 * 创建人代码
	 */
	@TableField("CREATE_BY")
	private String createBy;
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
	 * 修改时间
	 */
	@TableField("UPDATE_TIME")
	private String updateTime;

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
	 * 对应报关单申报单位社会统一信用代码
	 */
	@TableField("CORR_ENTRY_DCL_ETPS_SCCD")
	private String corrEntryDclEtpsSccd;
	/**
	 * 对应报关单申报单位代码
	 */
	@TableField("CORR_ENTRY_DCL_ETPSNO")
	private String corrEntryDclEtpsno;
	/**
	 * 对应报关单申报单位名称
	 */
	@TableField("CORR_ENTRY_DCL_ETPS_NM")
	private String corrEntryDclEtpsNm;

	/**
	 * 用于查询  录入日期
	 */
	@TableField(exist = false)
	private String createTimeStart;

	@TableField(exist = false)
	private String createTimeEnd;

	/**
	 * 用于查询  是否已备案
	 */
	@TableField(exist = false)
	private String filterCond;

	/**
	 * 进出口岸名称
	 */
	@TableField(exist = false)
	private String impexpPortcdName;

	/**
	 * 申报地海关名称
	 */
	@TableField(exist = false)
	private String dclPlcCuscdName;

	/**
	 * 监管方式名称
	 */
	@TableField(exist = false)
	private String supvModecdName;



	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getBondInvtNo() {
		return bondInvtNo;
	}

	public void setBondInvtNo(String bondInvtNo) {
		this.bondInvtNo = bondInvtNo;
	}

	public String getPutrecNo() {
		return putrecNo;
	}

	public void setPutrecNo(String putrecNo) {
		this.putrecNo = putrecNo;
	}

	public String getInvtPreentNo() {
		return invtPreentNo;
	}

	public void setInvtPreentNo(String invtPreentNo) {
		this.invtPreentNo = invtPreentNo;
	}

	public String getEtpsInnerInvtNo() {
		return etpsInnerInvtNo;
	}

	public void setEtpsInnerInvtNo(String etpsInnerInvtNo) {
		this.etpsInnerInvtNo = etpsInnerInvtNo;
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

	public String getDecTime() {
		return decTime;
	}

	public void setDecTime(String decTime) {
		this.decTime = decTime;
	}

	public String getMtpckEndprdMarkcd() {
		return mtpckEndprdMarkcd;
	}

	public void setMtpckEndprdMarkcd(String mtpckEndprdMarkcd) {
		this.mtpckEndprdMarkcd = mtpckEndprdMarkcd;
	}

	public String getSupvModecd() {
		return supvModecd;
	}

	public void setSupvModecd(String supvModecd) {
		this.supvModecd = supvModecd;
	}

	public String getTrspModecd() {
		return trspModecd;
	}

	public void setTrspModecd(String trspModecd) {
		this.trspModecd = trspModecd;
	}

	public String getImpexpMarkcd() {
		return impexpMarkcd;
	}

	public void setImpexpMarkcd(String impexpMarkcd) {
		this.impexpMarkcd = impexpMarkcd;
	}

	public String getImpexpPortcd() {
		return impexpPortcd;
	}

	public void setImpexpPortcd(String impexpPortcd) {
		this.impexpPortcd = impexpPortcd;
	}

	public String getDclPlcCuscd() {
		return dclPlcCuscd;
	}

	public void setDclPlcCuscd(String dclPlcCuscd) {
		this.dclPlcCuscd = dclPlcCuscd;
	}

	public String getNatcd() {
		return natcd;
	}

	public void setNatcd(String natcd) {
		this.natcd = natcd;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getDclcusFlag() {
		return dclcusFlag;
	}

	public void setDclcusFlag(String dclcusFlag) {
		this.dclcusFlag = dclcusFlag;
	}

	public String getDclcusTypecd() {
		return dclcusTypecd;
	}

	public void setDclcusTypecd(String dclcusTypecd) {
		this.dclcusTypecd = dclcusTypecd;
	}

	public String getBondInvtTypecd() {
		return bondInvtTypecd;
	}

	public void setBondInvtTypecd(String bondInvtTypecd) {
		this.bondInvtTypecd = bondInvtTypecd;
	}

	public String getDecTypecd() {
		return decTypecd;
	}

	public void setDecTypecd(String decTypecd) {
		this.decTypecd = decTypecd;
	}

	public String getRltInvtNo() {
		return rltInvtNo;
	}

	public void setRltInvtNo(String rltInvtNo) {
		this.rltInvtNo = rltInvtNo;
	}

	public String getRltPutrecNo() {
		return rltPutrecNo;
	}

	public void setRltPutrecNo(String rltPutrecNo) {
		this.rltPutrecNo = rltPutrecNo;
	}

	public String getRltEntryNo() {
		return rltEntryNo;
	}

	public void setRltEntryNo(String rltEntryNo) {
		this.rltEntryNo = rltEntryNo;
	}

	public String getRltEntryBizopEtpsSccd() {
		return rltEntryBizopEtpsSccd;
	}

	public void setRltEntryBizopEtpsSccd(String rltEntryBizopEtpsSccd) {
		this.rltEntryBizopEtpsSccd = rltEntryBizopEtpsSccd;
	}

	public String getRltEntryBizopEtpsno() {
		return rltEntryBizopEtpsno;
	}

	public void setRltEntryBizopEtpsno(String rltEntryBizopEtpsno) {
		this.rltEntryBizopEtpsno = rltEntryBizopEtpsno;
	}

	public String getRltEntryBizopEtpsNm() {
		return rltEntryBizopEtpsNm;
	}

	public void setRltEntryBizopEtpsNm(String rltEntryBizopEtpsNm) {
		this.rltEntryBizopEtpsNm = rltEntryBizopEtpsNm;
	}

	public String getRltEntryRvsngdEtpsSccd() {
		return rltEntryRvsngdEtpsSccd;
	}

	public void setRltEntryRvsngdEtpsSccd(String rltEntryRvsngdEtpsSccd) {
		this.rltEntryRvsngdEtpsSccd = rltEntryRvsngdEtpsSccd;
	}

	public String getRltEntryRcvgdEtpsno() {
		return rltEntryRcvgdEtpsno;
	}

	public void setRltEntryRcvgdEtpsno(String rltEntryRcvgdEtpsno) {
		this.rltEntryRcvgdEtpsno = rltEntryRcvgdEtpsno;
	}

	public String getRltEntryRcvgdEtpsNm() {
		return rltEntryRcvgdEtpsNm;
	}

	public void setRltEntryRcvgdEtpsNm(String rltEntryRcvgdEtpsNm) {
		this.rltEntryRcvgdEtpsNm = rltEntryRcvgdEtpsNm;
	}

	public String getRltEntryDclEtpsSccd() {
		return rltEntryDclEtpsSccd;
	}

	public void setRltEntryDclEtpsSccd(String rltEntryDclEtpsSccd) {
		this.rltEntryDclEtpsSccd = rltEntryDclEtpsSccd;
	}

	public String getRltEntryDclEtpsno() {
		return rltEntryDclEtpsno;
	}

	public void setRltEntryDclEtpsno(String rltEntryDclEtpsno) {
		this.rltEntryDclEtpsno = rltEntryDclEtpsno;
	}

	public String getRltEntryDclEtpsNm() {
		return rltEntryDclEtpsNm;
	}

	public void setRltEntryDclEtpsNm(String rltEntryDclEtpsNm) {
		this.rltEntryDclEtpsNm = rltEntryDclEtpsNm;
	}

	public String getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		if (createTimeStart!=null && !createTimeStart.equals("")) {
			this.createTimeStart = createTimeStart +" 00:00:00";
		}
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		if (createTimeEnd!=null && !createTimeEnd.equals("")) {
			this.createTimeEnd = createTimeEnd +" 23:59:59";
		}
	}

	public String getFilterCond() {
		return filterCond;
	}

	public void setFilterCond(String filterCond) {
		this.filterCond = filterCond;
	}

	public String getImpexpPortcdName() {
		return impexpPortcdName;
	}

	public void setImpexpPortcdName(String impexpPortcdName) {
		this.impexpPortcdName = impexpPortcdName;
	}

	public String getDclPlcCuscdName() {
		return dclPlcCuscdName;
	}

	public void setDclPlcCuscdName(String dclPlcCuscdName) {
		this.dclPlcCuscdName = dclPlcCuscdName;
	}

	public String getSupvModecdName() {
		return supvModecdName;
	}

	public void setSupvModecdName(String supvModecdName) {
		this.supvModecdName = supvModecdName;
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

	public String getCorrEntryDclEtpsSccd() {
		return corrEntryDclEtpsSccd;
	}

	public void setCorrEntryDclEtpsSccd(String corrEntryDclEtpsSccd) {
		this.corrEntryDclEtpsSccd = corrEntryDclEtpsSccd;
	}

	public String getCorrEntryDclEtpsno() {
		return corrEntryDclEtpsno;
	}

	public void setCorrEntryDclEtpsno(String corrEntryDclEtpsno) {
		this.corrEntryDclEtpsno = corrEntryDclEtpsno;
	}

	public String getCorrEntryDclEtpsNm() {
		return corrEntryDclEtpsNm;
	}

	public void setCorrEntryDclEtpsNm(String corrEntryDclEtpsNm) {
		this.corrEntryDclEtpsNm = corrEntryDclEtpsNm;
	}
}
