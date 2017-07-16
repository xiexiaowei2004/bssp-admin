package com.powerbridge.bssp.inv.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 保税核注清单表 
 * </p>
 *
 * @author zsl
 * @since 2017-06-03
 */
@TableName("inv_bsc")
public class InvBsc implements Serializable{

    private static final long serialVersionUID = 1L; 

    /**
     * 主键
     */
    @TableId("UID")
	private String uid;
	//列表页面搜索录入日期时使用
	@TableField(exist = false)
	private String decTimeStart;

	@TableField(exist = false)
	private String decTimeEnd;

	//列表页面显示审批标志
	@TableField(exist = false)
	private String emapvMarknm;

	//核扣标志名称
	@TableField(exist = false)
	private String vrfdedMarkcdnm;

	//出入区标识
	@TableField(exist = false)
	private String iEFlagnm;

	//清单类型
	@TableField(exist = false)
	private String bondInvtTypecdnm;

	//清单状态
	@TableField(exist = false)
	private String invtStucdnm;

	//单据状态
	@TableField(exist = false)
	private String chkStatusnm;

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

	/**
	 * 是否过滤核放单关联单证
	 */
	@TableField(exist = false)
	private String isFilterRlt;

	/**
	 * 审批标志代码
	 */
	@TableField("EMAPV_MARKCD")
	private String emapvMarkcd;

    /**
     * 保税清单编号
     */
	@TableField("BOND_INVT_NO")
	private String bondInvtNo;
    /**
     * 变更次数
     */
	@TableField("CHG_TMS_CNT")
	private BigDecimal chgTmsCnt;
    /**
     * 清单预录入统一编号
     */
	@TableField("INVT_PREENT_NO")
	private String invtPreentNo;
    /**
     * 备案编号
     */
	@TableField("PUTREC_NO")
	private String putrecNo;
    /**
     * 企业内部清单编号
     */
	@TableField("ETPS_INNER_INVT_NO")
	private String etpsInnerInvtNo;
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
     * 清单申报日期
     */
	@TableField("INVT_DCL_TIME")
	private String invtDclTime;
    /**
     * 报关单申报时间
     */
	@TableField("ENTRY_DCL_TIME")
	private String entryDclTime;
    /**
     * 报关单编号
     */
	@TableField("ENTRY_NO")
	private String entryNo;
    /**
     * 关联清单编号
     */
	@TableField("RLT_INVT_NO")
	private String rltInvtNo;
    /**
     * 关联备案编号
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
     * 关联报关单经营企业编号
     */
	@TableField("RLT_ENTRY_BIZOP_ETPSNO")
	private String rltEntryBizopEtpsno;
    /**
     * 关联报关单经营企业名称
     */
	@TableField("RLT_ENTRY_BIZOP_ETPS_NM")
	private String rltEntryBizopEtpsNm;
    /**
     * 进出口口岸代码
     */
	@TableField("IMPEXP_PORTCD")
	private String impexpPortcd;
    /**
     * 申报地关区代码
     */
	@TableField("DCL_PLC_CUSCD")
	private String dclPlcCuscd;
    /**
     * 申报地关区
     */
	@TableField("DCL_PLC_CUSNAME")
	private String dclPlcCusname;
    /**
     * 进出口标记代码
     */
	@TableField("IMPEXP_MARKCD")
	private String impexpMarkcd;
    /**
     * 料件成品标记代码
     */
	@TableField("MTPCK_ENDPRD_MARKCD")
	private String mtpckEndprdMarkcd;
    /**
     * 监管方式代码
     */
	@TableField("SUPV_MODECD")
	private String supvModecd;
    /**
     * 运输方式代码
     */
	@TableField("TRSP_MODECD")
	private String trspModecd;
    /**
     * 申请编号
     */
	@TableField("APPLY_NO")
	private String applyNo;
    /**
     * 是否报关标志
     */
	@TableField("DCLCUS_FLAG")
	private String dclcusFlag;
    /**
     * 报关类型代码
     */
	@TableField("DCLCUS_TYPECD")
	private String dclcusTypecd;
    /**
     * 预核扣时间
     */
	@TableField("PREVD_TIME")
	private String prevdTime;
    /**
     * 正式核扣时间
     */
	@TableField("FORMAL_VRFDED_TIME")
	private String formalVrfdedTime;
    /**
     * 清单进出卡口状态代码
     */
	@TableField("INVT_IOCHKPT_STUCD")
	private String invtIochkptStucd;
    /**
     * 核扣标记代码
     */
	@TableField("VRFDED_MARKCD")
	private String vrfdedMarkcd;
    /**
     * 清单状态代码
     */
	@TableField("INVT_STUCD")
	private String invtStucd;
    /**
     * 核扣方式代码
     */
	@TableField("VRFDED_MODECD")
	private String vrfdedModecd;
    /**
     * 核算代码
     */
	@TableField("DU_CODE")
	private String duCode;
    /**
     * 清单类型代码
     */
	@TableField("BOND_INVT_TYPECD")
	private String bondInvtTypecd;
    /**
     * 报关状态代码
     */
	@TableField("ENTRY_STUCD")
	private String entryStucd;
    /**
     * 报关状态
     */
	@TableField("ENTRY_STUNAME")
	private String entryStuname;
    /**
     * 核放单生成标志代码
     */
	@TableField("PASSPORT_USED_TYPECD")
	private String passportUsedTypecd;
    /**
     * 归并方式代码
     */
	@TableField("ORG_FLAG")
	private String orgFlag;
    /**
     * 出入区标识
     */
	@TableField("I_E_FLAG")
	private String iEFlag;

	/**
	 * 进出菜单标志
	 */
	@TableField("I_E_FLAG1")
	private String iEFlag1;

    /**
     * 出入区方式
     */
	@TableField("I_E_WAY")
	private String iEWay;
    /**
     * 单据类型
     */
	@TableField("BILL_TYPE")
	private String billType;
    /**
     * 企业报核编号
     */
	@TableField("ENT_VERI_NO")
	private String entVeriNo;
    /**
     * 关联报关单收发货单位社会统一信用代码
     */
	@TableField("RLT_ENTRY_RVSNGD_ETPS_SCCD")
	private String rltEntryRvsngdEtpsSccd;
    /**
     * 关联报关单海关收发货单位编码
     */
	@TableField("RLT_ENTRY_RCVGD_ETPSNO")
	private String rltEntryRcvgdEtpsno;
    /**
     * 关联报关单收发货单位名称
     */
	@TableField("RLT_ENTRY_RCVGD_ETPS_NM")
	private String rltEntryRcvgdEtpsNm;
    /**
     * 关联报关单申报单位社会统一信用代码
     */
	@TableField("RLT_ENTRY_DCL_ETPS_SCCD")
	private String rltEntryDclEtpsSccd;
    /**
     * 关联报关单海关申报单位编码
     */
	@TableField("RLT_ENTRY_DCL_ETPSNO")
	private String rltEntryDclEtpsno;
    /**
     * 关联报关单申报单位名称
     */
	@TableField("RLT_ENTRY_DCL_ETPS_NM")
	private String rltEntryDclEtpsNm;
    /**
     * 流转类型
     */
	@TableField("LIST_TYPE")
	private String listType;
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
     * 报关单类型
     */
	@TableField("DEC_TYPE")
	private String decType;
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
     * 主管关区代码
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
     * 申报（录入）企业统一社会信用代码
     */
	@TableField("INPUT_ETPS_SCCD")
	private String inputEtpsSccd;
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
     * 国别代码
     */
	@TableField("NATCD")
	private String natcd;

	/**
	 * 备用1
	 */
	@TableField("PARAM1")
	private String param1;

	/**
	 * 备用2
	 */
	@TableField("PARAM2")
	private String param2;

	/**
	 * 备用3
	 */
	@TableField("PARAM3")
	private BigDecimal param3;

	/**
	 * 备用4
	 */
	@TableField("PARAM4")
	private String param4;

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

	public BigDecimal getChgTmsCnt() {
		return chgTmsCnt;
	}

	public void setChgTmsCnt(BigDecimal chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}

	public String getInvtPreentNo() {
		return invtPreentNo;
	}

	public void setInvtPreentNo(String invtPreentNo) {
		this.invtPreentNo = invtPreentNo;
	}

	public String getPutrecNo() {
		return putrecNo;
	}

	public void setPutrecNo(String putrecNo) {
		this.putrecNo = putrecNo;
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

	public String getInvtDclTime() {
		return invtDclTime;
	}

	public void setInvtDclTime(String invtDclTime) {
		this.invtDclTime=StringUtil.isEmpty(invtDclTime)?null:invtDclTime;
	}

	public String getEntryDclTime() {
		return entryDclTime;
	}

	public void setEntryDclTime(String entryDclTime) {
		this.entryDclTime=StringUtil.isEmpty(entryDclTime)?null:entryDclTime;
	}

	public String getEntryNo() {
		return entryNo;
	}

	public void setEntryNo(String entryNo) {
		this.entryNo = entryNo;
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

	public String getDclPlcCusname() {
		return dclPlcCusname;
	}

	public void setDclPlcCusname(String dclPlcCusname) {
		this.dclPlcCusname = dclPlcCusname;
	}

	public String getImpexpMarkcd() {
		return impexpMarkcd;
	}

	public void setImpexpMarkcd(String impexpMarkcd) {
		this.impexpMarkcd = impexpMarkcd;
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

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
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

	public String getPrevdTime() {
		return prevdTime;
	}

	public void setPrevdTime(String prevdTime) {
		this.prevdTime=StringUtil.isEmpty(prevdTime)?null:prevdTime;
	}

	public String getFormalVrfdedTime() {
		return formalVrfdedTime;
	}

	public void setFormalVrfdedTime(String formalVrfdedTime) {
		//this.formalVrfdedTime = formalVrfdedTime;
		this.formalVrfdedTime=StringUtil.isEmpty(formalVrfdedTime)?null:formalVrfdedTime;
	}

	public String getInvtIochkptStucd() {
		return invtIochkptStucd;
	}

	public void setInvtIochkptStucd(String invtIochkptStucd) {
		this.invtIochkptStucd = invtIochkptStucd;
	}

	public String getVrfdedMarkcd() {
		return vrfdedMarkcd;
	}

	public void setVrfdedMarkcd(String vrfdedMarkcd) {
		this.vrfdedMarkcd = vrfdedMarkcd;
	}

	public String getInvtStucd() {
		return invtStucd;
	}

	public void setInvtStucd(String invtStucd) {
		this.invtStucd = invtStucd;
	}

	public String getVrfdedModecd() {
		return vrfdedModecd;
	}

	public void setVrfdedModecd(String vrfdedModecd) {
		this.vrfdedModecd = vrfdedModecd;
	}

	public String getDuCode() {
		return duCode;
	}

	public void setDuCode(String duCode) {
		this.duCode = duCode;
	}

	public String getBondInvtTypecd() {
		return bondInvtTypecd;
	}

	public void setBondInvtTypecd(String bondInvtTypecd) {
		this.bondInvtTypecd = bondInvtTypecd;
	}

	public String getEntryStucd() {
		return entryStucd;
	}

	public void setEntryStucd(String entryStucd) {
		this.entryStucd = entryStucd;
	}

	public String getEntryStuname() {
		return entryStuname;
	}

	public void setEntryStuname(String entryStuname) {
		this.entryStuname = entryStuname;
	}

	public String getPassportUsedTypecd() {
		return passportUsedTypecd;
	}

	public void setPassportUsedTypecd(String passportUsedTypecd) {
		this.passportUsedTypecd = passportUsedTypecd;
	}

	public String getOrgFlag() {
		return orgFlag;
	}

	public void setOrgFlag(String orgFlag) {
		this.orgFlag = orgFlag;
	}

	public String getiEFlag() {
		return iEFlag;
	}

	public void setiEFlag(String iEFlag) {
		this.iEFlag = iEFlag;
	}

	public String getiEWay() {
		return iEWay;
	}

	public void setiEWay(String iEWay) {
		this.iEWay = iEWay;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getEntVeriNo() {
		return entVeriNo;
	}

	public void setEntVeriNo(String entVeriNo) {
		this.entVeriNo = entVeriNo;
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

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
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

	public String getDecType() {
		return decType;
	}

	public void setDecType(String decType) {
		this.decType = decType;
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

	public String getDecTime() {
		return decTime;
	}

	public void setDecTime(String decTime) {
		this.decTime=StringUtil.isEmpty(decTime)?null:decTime;

	}

	public String getChkTime() {
		return chkTime;
	}

	public void setChkTime(String chkTime) {
		this.chkTime=StringUtil.isEmpty(chkTime)?null:chkTime;
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

	public String getInputEtpsSccd() {
		return inputEtpsSccd;
	}

	public void setInputEtpsSccd(String inputEtpsSccd) {
		this.inputEtpsSccd = inputEtpsSccd;
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
		return  createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime=StringUtil.isEmpty(createTime)?null:createTime;
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
		this.updateTime=StringUtil.isEmpty(updateTime)?null:updateTime;
	}

	public String getNatcd() {
		return natcd;
	}

	public void setNatcd(String natcd) {
		this.natcd = natcd;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public BigDecimal getParam3() {
		return param3;
	}

	public void setParam3(BigDecimal param3) {
		this.param3 = param3;
	}

	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 =  DateUtil.timeSwitch(param4);
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

	public String getEmapvMarkcd() {
		return emapvMarkcd;
	}

	public void setEmapvMarkcd(String emapvMarkcd) {
		this.emapvMarkcd = emapvMarkcd;
	}

	public String getEmapvMarknm() {
		return emapvMarknm;
	}

	public void setEmapvMarknm(String emapvMarknm) {
		this.emapvMarknm = emapvMarknm;
	}

	public String getiEFlagnm() {
		return iEFlagnm;
	}

	public void setiEFlagnm(String iEFlagnm) {
		this.iEFlagnm = iEFlagnm;
	}

	public String getBondInvtTypecdnm() {
		return bondInvtTypecdnm;
	}

	public void setBondInvtTypecdnm(String bondInvtTypecdnm) {
		this.bondInvtTypecdnm = bondInvtTypecdnm;
	}

	public String getInvtStucdnm() {
		return invtStucdnm;
	}

	public void setInvtStucdnm(String invtStucdnm) {
		this.invtStucdnm = invtStucdnm;
	}

	public String getChkStatusnm() {
		return chkStatusnm;
	}

	public void setChkStatusnm(String chkStatusnm) {
		this.chkStatusnm = chkStatusnm;
	}

	public String getiEFlag1() {
		return iEFlag1;
	}

	public void setiEFlag1(String iEFlag1) {
		this.iEFlag1 = iEFlag1;
	}

	public String getIsFilterRlt() {
		return isFilterRlt;
	}

	public void setIsFilterRlt(String isFilterRlt) {
		this.isFilterRlt = isFilterRlt;
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

	public String getVrfdedMarkcdnm() {
		return vrfdedMarkcdnm;
	}

	public void setVrfdedMarkcdnm(String vrfdedMarkcdnm) {
		this.vrfdedMarkcdnm = vrfdedMarkcdnm;
	}
}
