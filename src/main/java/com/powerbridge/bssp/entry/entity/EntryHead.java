package com.powerbridge.bssp.entry.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 报关单表头
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-10
 */
@TableName("entry_head")
public class EntryHead implements Serializable {

    private static final long serialVersionUID = 1L;
	/**
	 * 操作时间开始
	 */
	@TableField(exist = false)
	private String updateTimeStart;
	/**
	 * 操作时间结束
	 */
	@TableField(exist = false)
	private String updateTimeEnd;

	/**
	 * 申报时间开始
	 */
	@TableField(exist = false)
	private String dclTimeStart;
	/**
	 * 申报时间结束
	 */
	@TableField(exist = false)
	private String dclTimeEnd;

    /**
     * 主键
     */
	@TableField("UID")
	private String uid;
    /**
     * 数据中心统一编号
     */
	@TableField("SEQ_NO")
	private String seqNo;
    /**
     * 预录入编号
     */
	@TableField("ETPS_PREENT_NO")
	private String etpsPreentNo;
    /**
     * 进出口标志
     */
	@TableField("I_E_FLAG")
	private String iEFlag;
    /**
     * EDI申报备注
     */
	@TableField("TYPE")
	private String type;
    /**
     * 申报单位代码
     */
	@TableField("AGENT_CODE")
	private String agentCode;
    /**
     * 申报单位名称
     */
	@TableField("AGENT_NAME")
	private String agentName;
    /**
     * 批准文号
     */
	@TableField("APPR_NO")
	private String apprNo;
    /**
     * 提单号
     */
	@TableField("BILL_NO")
	private String billNo;
    /**
     * 合同号
     */
	@TableField("CONTR_NO")
	private String contrNo;
    /**
     * 征免性质
     */
	@TableField("CUT_MODE")
	private String cutMode;
    /**
     * 装货港
     */
	@TableField("DISTINATE_PORT")
	private String distinatePort;
    /**
     * 境内目的地
     */
	@TableField("DISTRICT_CODE")
	private String districtCode;
    /**
     * 运费币制
     */
	@TableField("FEE_CURR")
	private String feeCurr;
    /**
     * 运费标记
     */
	@TableField("FEE_MARK")
	private String feeMark;
    /**
     * 运费／率
     */
	@TableField("FEE_RATE")
	private BigDecimal feeRate;
    /**
     * 毛重
     */
	@TableField("GROSS_WET")
	private BigDecimal grossWet;
    /**
     * 进出口日期
     */
	@TableField("I_E_DATE")
	private String iEDate;
    /**
     * 进出口岸
     */
	@TableField("I_E_PORT")
	private String iEPort;
    /**
     * 征税比例（进口用）
     */
	@TableField("IN_RATIO")
	private BigDecimal inRatio;
    /**
     * 保险费币制
     */
	@TableField("INSUR_CURR")
	private String insurCurr;
    /**
     * 保险费标记
     */
	@TableField("INSUR_MARK")
	private String insurMark;
    /**
     * 保险费／率
     */
	@TableField("INSUR_RATE")
	private BigDecimal insurRate;
    /**
     * 许可证编号
     */
	@TableField("LICENSE_NO")
	private String licenseNo;
    /**
     * 备案号
     */
	@TableField("MANUAL_NO")
	private String manualNo;
    /**
     * 净重
     */
	@TableField("NET_WT")
	private BigDecimal netWt;
    /**
     * 备注
     */
	@TableField("NOTE_S")
	private String noteS;
    /**
     * 杂费币制
     */
	@TableField("OTHER_CURR")
	private String otherCurr;
    /**
     * 杂费标志
     */
	@TableField("OTHER_MARK")
	private String otherMark;
    /**
     * 杂费／率
     */
	@TableField("OTHER_RATE")
	private BigDecimal otherRate;
    /**
     * 货主单位代码
     */
	@TableField("OWNER_CODE")
	private String ownerCode;
    /**
     * 货主单位名称
     */
	@TableField("OWNER_NAME")
	private String ownerName;
    /**
     * 件数
     */
	@TableField("PACK_NO")
	private BigDecimal packNo;
    /**
     * 结汇方式（出口用）
     */
	@TableField("PAY_WAY")
	private String payWay;
    /**
     * 纳税单位
     */
	@TableField("PAYMENT_MARK")
	private String paymentMark;
    /**
     * 经营单位编号
     */
	@TableField("TRADE_CODE")
	private String tradeCode;
    /**
     * 贸易国别（运抵国）
     */
	@TableField("TRADE_COUNTRY")
	private String tradeCountry;
    /**
     * 贸易方式
     */
	@TableField("TRADE_MODE")
	private String tradeMode;
    /**
     * 经营单位名称
     */
	@TableField("TRADE_NAME")
	private String tradeName;
    /**
     * 运输方式代码
     */
	@TableField("TRAF_MODE")
	private String trafMode;
    /**
     * 运输工具代码及名称
     */
	@TableField("TRAF_NAME")
	private String trafName;
    /**
     * 成交方式
     */
	@TableField("TRANS_MODE")
	private String transMode;
    /**
     * 包装种类
     */
	@TableField("WRAP_TYPE")
	private String wrapType;
    /**
     * 海关编号
     */
	@TableField("ENTRY_ID")
	private String entryId;
    /**
     * 报关标志
     */
	@TableField("EDI_ID")
	private String ediId;
    /**
     * 风险评估参数
     */
	@TableField("RISK")
	private String risk;
    /**
     * 打印日期
     */
	@TableField("P_DATE")
	private String pDate;
    /**
     * 录入员IC卡号
     */
	@TableField("TYPIST_NO")
	private String typistNo;
    /**
     * 宁波通关申请单号
     */
	@TableField("TGD_NO")
	private String tgdNo;
    /**
     * 数据来源
     */
	@TableField("DATA_SOURCE")
	private String dataSource;
    /**
     * 报关/转关关系标志
     */
	@TableField("DECL_TRN_REL")
	private String declTrnRel;
    /**
     * 担保验放标志
     */
	@TableField("CHK_SURETY")
	private String chkSurety;
    /**
     * 备案清单类型
     */
	@TableField("BILL_TYPE")
	private String billType;
    /**
     * 申报代码统一编码
     */
	@TableField("AGENT_CODE_SCC")
	private String agentCodeScc;
    /**
     * 货主单位统一编码
     */
	@TableField("OWNER_CODE_SCC")
	private String ownerCodeScc;
    /**
     * 经营单位统一编码
     */
	@TableField("TRADE_CODE_SCC")
	private String tradeCodeScc;
    /**
     * 录入单位统一编码
     */
	@TableField("COP_CODE_SCC")
	private String copCodeScc;
    /**
     * 承诺事项
     */
	@TableField("PROMISE_ITMES")
	private String promiseItmes;
    /**
     * 贸易场地编码
     */
	@TableField("TRADE_AREA_CODE")
	private String tradeAreaCode;
    /**
     * 主管海关（申报地海关）
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
     * 申报类型代码
     */
	@TableField("DCL_TYPECD")
	private String dclTypecd;
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
	 * 申报时间
	 */
	@TableField("DCL_TIME")
	private String dclTime;

	/**
	 * 报关单类型
	 */
	@TableField("ENTRY_TYPE")
	private String entryType;

	/**
	 * 报关员
	 */
	@TableField("DECLARANT")
	private String declarant;

	/**
	 * 关联报关单
	 */
	@TableField("RELATE_ENTRY")
	private String relateEntry;

	/**
	 * 关联备案
	 */
	@TableField("RELATE_REG")
	private String relateReg;

	/**
	 * 货场代码
	 */
	@TableField("WAREHOSE_NO")
	private String warehoseNo;

	/**
	 * 监管仓号
	 */
	@TableField("SUPERVISE_HOUSE_NO")
	private String superviseHouseNo;

	/**
	 * 随附单证
	 */
	@TableField("ACMP_FORM")
	private String acmpForm;

	public String getUpdateTimeStart() {
		return updateTimeStart;
	}

	public void setUpdateTimeStart(String updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	public String getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(String updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getDclTimeStart() {
		return dclTimeStart;
	}

	public void setDclTimeStart(String dclTimeStart) {
		this.dclTimeStart = dclTimeStart;
	}

	public String getDclTimeEnd() {
		return dclTimeEnd;
	}

	public void setDclTimeEnd(String dclTimeEnd) {
		this.dclTimeEnd = dclTimeEnd;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getEtpsPreentNo() {
		return etpsPreentNo;
	}

	public void setEtpsPreentNo(String etpsPreentNo) {
		this.etpsPreentNo = etpsPreentNo;
	}

	public String getiEFlag() {
		return iEFlag;
	}

	public void setiEFlag(String iEFlag) {
		this.iEFlag = iEFlag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getApprNo() {
		return apprNo;
	}

	public void setApprNo(String apprNo) {
		this.apprNo = apprNo;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getContrNo() {
		return contrNo;
	}

	public void setContrNo(String contrNo) {
		this.contrNo = contrNo;
	}

	public String getCutMode() {
		return cutMode;
	}

	public void setCutMode(String cutMode) {
		this.cutMode = cutMode;
	}

	public String getDistinatePort() {
		return distinatePort;
	}

	public void setDistinatePort(String distinatePort) {
		this.distinatePort = distinatePort;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getFeeCurr() {
		return feeCurr;
	}

	public void setFeeCurr(String feeCurr) {
		this.feeCurr = feeCurr;
	}

	public String getFeeMark() {
		return feeMark;
	}

	public void setFeeMark(String feeMark) {
		this.feeMark = feeMark;
	}

	public BigDecimal getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}

	public BigDecimal getGrossWet() {
		return grossWet;
	}

	public void setGrossWet(BigDecimal grossWet) {
		this.grossWet = grossWet;
	}

	public String getiEDate() {
		return iEDate;
	}

	public void setiEDate(String iEDate) {
		this.iEDate = iEDate;
	}

	public String getiEPort() {
		return iEPort;
	}

	public void setiEPort(String iEPort) {
		this.iEPort = iEPort;
	}

	public BigDecimal getInRatio() {
		return inRatio;
	}

	public void setInRatio(BigDecimal inRatio) {
		this.inRatio = inRatio;
	}

	public String getInsurCurr() {
		return insurCurr;
	}

	public void setInsurCurr(String insurCurr) {
		this.insurCurr = insurCurr;
	}

	public String getInsurMark() {
		return insurMark;
	}

	public void setInsurMark(String insurMark) {
		this.insurMark = insurMark;
	}

	public BigDecimal getInsurRate() {
		return insurRate;
	}

	public void setInsurRate(BigDecimal insurRate) {
		this.insurRate = insurRate;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getManualNo() {
		return manualNo;
	}

	public void setManualNo(String manualNo) {
		this.manualNo = manualNo;
	}

	public BigDecimal getNetWt() {
		return netWt;
	}

	public void setNetWt(BigDecimal netWt) {
		this.netWt = netWt;
	}

	public String getNoteS() {
		return noteS;
	}

	public void setNoteS(String noteS) {
		this.noteS = noteS;
	}

	public String getOtherCurr() {
		return otherCurr;
	}

	public void setOtherCurr(String otherCurr) {
		this.otherCurr = otherCurr;
	}

	public String getOtherMark() {
		return otherMark;
	}

	public void setOtherMark(String otherMark) {
		this.otherMark = otherMark;
	}

	public BigDecimal getOtherRate() {
		return otherRate;
	}

	public void setOtherRate(BigDecimal otherRate) {
		this.otherRate = otherRate;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public BigDecimal getPackNo() {
		return packNo;
	}

	public void setPackNo(BigDecimal packNo) {
		this.packNo = packNo;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getPaymentMark() {
		return paymentMark;
	}

	public void setPaymentMark(String paymentMark) {
		this.paymentMark = paymentMark;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getTradeCountry() {
		return tradeCountry;
	}

	public void setTradeCountry(String tradeCountry) {
		this.tradeCountry = tradeCountry;
	}

	public String getTradeMode() {
		return tradeMode;
	}

	public void setTradeMode(String tradeMode) {
		this.tradeMode = tradeMode;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getTrafMode() {
		return trafMode;
	}

	public void setTrafMode(String trafMode) {
		this.trafMode = trafMode;
	}

	public String getTrafName() {
		return trafName;
	}

	public void setTrafName(String trafName) {
		this.trafName = trafName;
	}

	public String getTransMode() {
		return transMode;
	}

	public void setTransMode(String transMode) {
		this.transMode = transMode;
	}

	public String getWrapType() {
		return wrapType;
	}

	public void setWrapType(String wrapType) {
		this.wrapType = wrapType;
	}

	public String getEntryId() {
		return entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	public String getEdiId() {
		return ediId;
	}

	public void setEdiId(String ediId) {
		this.ediId = ediId;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getpDate() {
		return pDate;
	}

	public void setpDate(String pDate) {
		this.pDate = pDate;
	}

	public String getTypistNo() {
		return typistNo;
	}

	public void setTypistNo(String typistNo) {
		this.typistNo = typistNo;
	}

	public String getTgdNo() {
		return tgdNo;
	}

	public void setTgdNo(String tgdNo) {
		this.tgdNo = tgdNo;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getDeclTrnRel() {
		return declTrnRel;
	}

	public void setDeclTrnRel(String declTrnRel) {
		this.declTrnRel = declTrnRel;
	}

	public String getChkSurety() {
		return chkSurety;
	}

	public void setChkSurety(String chkSurety) {
		this.chkSurety = chkSurety;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getAgentCodeScc() {
		return agentCodeScc;
	}

	public void setAgentCodeScc(String agentCodeScc) {
		this.agentCodeScc = agentCodeScc;
	}

	public String getOwnerCodeScc() {
		return ownerCodeScc;
	}

	public void setOwnerCodeScc(String ownerCodeScc) {
		this.ownerCodeScc = ownerCodeScc;
	}

	public String getTradeCodeScc() {
		return tradeCodeScc;
	}

	public void setTradeCodeScc(String tradeCodeScc) {
		this.tradeCodeScc = tradeCodeScc;
	}

	public String getCopCodeScc() {
		return copCodeScc;
	}

	public void setCopCodeScc(String copCodeScc) {
		this.copCodeScc = copCodeScc;
	}

	public String getPromiseItmes() {
		return promiseItmes;
	}

	public void setPromiseItmes(String promiseItmes) {
		this.promiseItmes = promiseItmes;
	}

	public String getTradeAreaCode() {
		return tradeAreaCode;
	}

	public void setTradeAreaCode(String tradeAreaCode) {
		this.tradeAreaCode = tradeAreaCode;
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

	public String getDclTypecd() {
		return dclTypecd;
	}

	public void setDclTypecd(String dclTypecd) {
		this.dclTypecd = dclTypecd;
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

	public String getDclTime() {
		return dclTime;
	}

	public void setDclTime(String dclTime) {
		this.dclTime = dclTime;
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public String getDeclarant() {
		return declarant;
	}

	public void setDeclarant(String declarant) {
		this.declarant = declarant;
	}

	public String getRelateEntry() {
		return relateEntry;
	}

	public void setRelateEntry(String relateEntry) {
		this.relateEntry = relateEntry;
	}

	public String getRelateReg() {
		return relateReg;
	}

	public void setRelateReg(String relateReg) {
		this.relateReg = relateReg;
	}

	public String getWarehoseNo() {
		return warehoseNo;
	}

	public void setWarehoseNo(String warehoseNo) {
		this.warehoseNo = warehoseNo;
	}

	public String getSuperviseHouseNo() {
		return superviseHouseNo;
	}

	public void setSuperviseHouseNo(String superviseHouseNo) {
		this.superviseHouseNo = superviseHouseNo;
	}

	public String getAcmpForm() {
		return acmpForm;
	}

	public void setAcmpForm(String acmpForm) {
		this.acmpForm = acmpForm;
	}
}
