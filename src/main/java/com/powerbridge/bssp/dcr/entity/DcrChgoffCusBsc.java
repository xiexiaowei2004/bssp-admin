package com.powerbridge.bssp.dcr.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 账册报核正式表
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-03
 */
@TableName("dcr_chgoff_cus_bsc")
public class DcrChgoffCusBsc implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = -8327505969302039943L;
	/**
	 * 申报类型名称 用于列表显示
	 */
	@TableField(exist = false)
	private String chgoffTypeNm;

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
	 * 账册编号
	 */
	@TableField("EMS_NO")
	private String emsNo;
	/**
	 *  企业预录入编号
	 */
	@TableField("ETPS_PREENT_NO")
	private String etpsPreentNo;
	/**
	 * 报核次数
	 */
	@TableField("CHG_TMS_CNT")
	private Integer chgTmsCnt;
	/**
	 * 申报类型
	 */
	@TableField("CHGOFF_TYPECD")
	private String chgoffTypecd;
	/**
	 * 报核截止时间
	 */
	@TableField("CHGOFF_DUE_TIME")
	private String chgoffDueTime;
	/**
	 * 报核开始时间
	 */
	@TableField("CHGOFF_BEGIN_TIME")
	private String chgoffBeginTime;
	/**
	 * 经营单位社会信用代码
	 */
	@TableField("BIZOP_ETPS_SCCD")
	private String bizopEtpsSccd;
	/**
	 * 经营单位编号
	 */
	@TableField("BIZOP_ETPSNO")
	private String bizopEtpsno;
	/**
	 * 经营单位名称
	 */
	@TableField("BIZOP_ETPS_NM")
	private String bizopEtpsNm;
	/**
	 * 加工单位社会信用代码
	 */
	@TableField("RVSNGD_ETPS_SCCD")
	private String rvsngdEtpsSccd;
	/**
	 * 加工单位编号
	 */
	@TableField("RCVGD_ETPSNO")
	private String rcvgdEtpsno;
	/**
	 * 加工单位名称
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
	 * 进口报核清单总份数
	 */
	@TableField("IMP_INVT_TOTAL_CNT")
	private Integer impInvtTotalCnt;
	/**
	 * 出口报核清单总份数
	 */
	@TableField("EXP_INVT_TOTAL_CNT")
	private Integer expInvtTotalCnt;
	/**
	 * 报核料件总数
	 */
	@TableField("CHGOFF_MTPCK_TOTAL_CNT")
	private Integer chgoffMtpckTotalCnt;
	/**
	 * 报核成品总数
	 */
	@TableField("CHGOFF_ENDPRD_TOTAL_CNT")
	private Integer chgoffEndprdTotalCnt;
	/**
	 * 报核边角料总数
	 */
	@TableField("CHGOFF_LFMT_TOTAL_CNT")
	private Integer chgoffLfmtTotalCnt;
	/**
	 * 报核残次品总数
	 */
	@TableField("CHGOFF_IMPR_TOTAL_CNT")
	private Integer chgoffImprTotalCnt;
	/**
	 * 进口料件总金额
	 */
	@TableField("IMP_MTPCK_TOTAL_AMT")
	private BigDecimal impMtpckTotalAmt;
	/**
	 * 出口成品总金额
	 */
	@TableField("EXP_ENDPRD_TOTAL_AMT")
	private BigDecimal expEndprdTotalAmt;
	/**
	 * 录入关员编号
	 */
	@TableField("INPUT_CUSRNO")
	private String inputCusrno;
	/**
	 * 报核申报时间
	 */
	@TableField("CHGOFF_DCL_TIME")
	private String chgoffDclTime;
	/**
	 * 第一备用标记代码
	 */
	@TableField("FST_SPARE_MARKCD")
	private String fstSpareMarkcd;
	/**
	 * 备用金额
	 */
	@TableField("SPARE_AMT")
	private BigDecimal spareAmt;
	/**
	 * 备用数量
	 */
	@TableField("SPARE_QTY")
	private BigDecimal spareQty;
	/**
	 * 报核标记代码
	 */
	@TableField("CHGOFF_MARKCD")
	private String chgoffMarkcd;
	/**
	 * 审批标记代码
	 */
	@TableField("EMAPV_MARKCD")
	private String emapvMarkcd;
	/**
	 * 处理标记代码
	 */
	@TableField("DSPS_MARKCD")
	private String dspsMarkcd;
	/**
	 * 申报来源标记
	 */
	@TableField("DCL_SOURCE_MARKCD")
	private String dclSourceMarkcd;
	/**
	 * 备注
	 */
	@TableField("RMK")
	private String rmk;
	/**
	 * 预录入统一编号
	 */
	@TableField("SEQ_NO")
	private String seqNo;
	/**
	 * 主管关区
	 */
	@TableField("MASTER_CUSCD")
	private String masterCuscd;
	/**
	 * 场地代码
	 */
	@TableField("AREA_CODE")
	private String areaCode;
	/**
	 * 场地名称
	 */
//    @TableField("AREA_NAME")
//    private String areaName;
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
	 * 创建人
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
	 * 修改人
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
	 * 报核申报时间 查询
	 */
	@TableField(exist = false)
	private String chgoffDclTimeStart;

	@TableField(exist = false)
	private String chgoffDclTimeEnd;

	/**
	 * 操作时间时间 查询
	 */
	@TableField(exist = false)
	private String decTimeStart;

	@TableField(exist = false)
	private String decTimeEnd;

	/**
	 *  业务类型 用于菜单区分是单据类型
	 */
	@TableField(exist = false)
	private String busType;

	/**
	 * 账册报核成品集合
	 */
	@TableField(exist = false)
	private List<DcrChgoffExg> dcrChgoffExg;

	/**
	 * 账册报核成品集合
	 */
	@TableField(exist = false)
	private List<DcrChgoffImg> dcrChgoffImg;

	/**
	 * 账册报核清单集合
	 */
	@TableField(exist = false)
	private List<DcrChgoffInvtLt> dcrChgoffInvtLt;

	/**
	 * 账册核销随附单证明细集合
	 */
	@TableField(exist = false)
	private List<DcrAdjaccAcmpFormDt> dcrAdjaccAcmpFormDt;



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

	public Integer getChgTmsCnt() {
		return chgTmsCnt;
	}

	public void setChgTmsCnt(Integer chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}

	public String getChgoffTypecd() {
		return chgoffTypecd;
	}

	public void setChgoffTypecd(String chgoffTypecd) {
		this.chgoffTypecd = chgoffTypecd;
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

	public Integer getImpInvtTotalCnt() {
		return impInvtTotalCnt;
	}

	public void setImpInvtTotalCnt(Integer impInvtTotalCnt) {
		this.impInvtTotalCnt = impInvtTotalCnt;
	}

	public Integer getExpInvtTotalCnt() {
		return expInvtTotalCnt;
	}

	public void setExpInvtTotalCnt(Integer expInvtTotalCnt) {
		this.expInvtTotalCnt = expInvtTotalCnt;
	}

	public Integer getChgoffMtpckTotalCnt() {
		return chgoffMtpckTotalCnt;
	}

	public void setChgoffMtpckTotalCnt(Integer chgoffMtpckTotalCnt) {
		this.chgoffMtpckTotalCnt = chgoffMtpckTotalCnt;
	}

	public Integer getChgoffEndprdTotalCnt() {
		return chgoffEndprdTotalCnt;
	}

	public void setChgoffEndprdTotalCnt(Integer chgoffEndprdTotalCnt) {
		this.chgoffEndprdTotalCnt = chgoffEndprdTotalCnt;
	}

	public Integer getChgoffLfmtTotalCnt() {
		return chgoffLfmtTotalCnt;
	}

	public void setChgoffLfmtTotalCnt(Integer chgoffLfmtTotalCnt) {
		this.chgoffLfmtTotalCnt = chgoffLfmtTotalCnt;
	}

	public Integer getChgoffImprTotalCnt() {
		return chgoffImprTotalCnt;
	}

	public void setChgoffImprTotalCnt(Integer chgoffImprTotalCnt) {
		this.chgoffImprTotalCnt = chgoffImprTotalCnt;
	}

	public BigDecimal getImpMtpckTotalAmt() {
		return impMtpckTotalAmt;
	}

	public void setImpMtpckTotalAmt(BigDecimal impMtpckTotalAmt) {
		this.impMtpckTotalAmt = impMtpckTotalAmt;
	}

	public BigDecimal getExpEndprdTotalAmt() {
		return expEndprdTotalAmt;
	}

	public void setExpEndprdTotalAmt(BigDecimal expEndprdTotalAmt) {
		this.expEndprdTotalAmt = expEndprdTotalAmt;
	}

	public String getInputCusrno() {
		return inputCusrno;
	}

	public void setInputCusrno(String inputCusrno) {
		this.inputCusrno = inputCusrno;
	}

	public String getFstSpareMarkcd() {
		return fstSpareMarkcd;
	}

	public void setFstSpareMarkcd(String fstSpareMarkcd) {
		this.fstSpareMarkcd = fstSpareMarkcd;
	}

	public BigDecimal getSpareAmt() {
		return spareAmt;
	}

	public void setSpareAmt(BigDecimal spareAmt) {
		this.spareAmt = spareAmt;
	}

	public BigDecimal getSpareQty() {
		return spareQty;
	}

	public void setSpareQty(BigDecimal spareQty) {
		this.spareQty = spareQty;
	}

	public String getChgoffMarkcd() {
		return chgoffMarkcd;
	}

	public void setChgoffMarkcd(String chgoffMarkcd) {
		this.chgoffMarkcd = chgoffMarkcd;
	}

	public String getEmapvMarkcd() {
		return emapvMarkcd;
	}

	public void setEmapvMarkcd(String emapvMarkcd) {
		this.emapvMarkcd = emapvMarkcd;
	}

	public String getDspsMarkcd() {
		return dspsMarkcd;
	}

	public void setDspsMarkcd(String dspsMarkcd) {
		this.dspsMarkcd = dspsMarkcd;
	}

	public String getDclSourceMarkcd() {
		return dclSourceMarkcd;
	}

	public void setDclSourceMarkcd(String dclSourceMarkcd) {
		this.dclSourceMarkcd = dclSourceMarkcd;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
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

	public String getInputerCode() {
		return inputerCode;
	}

	public void setInputerCode(String inputerCode) {
		this.inputerCode = inputerCode;
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

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getChgoffDueTime() {
		return chgoffDueTime;
	}

	public void setChgoffDueTime(String chgoffDueTime) {
		if("".equals(chgoffDueTime)){
			chgoffDueTime = null;
		}
		this.chgoffDueTime = chgoffDueTime;
	}

	public String getChgoffBeginTime() {
		return chgoffBeginTime;
	}

	public void setChgoffBeginTime(String chgoffBeginTime) {
		if("".equals(chgoffBeginTime)){
			chgoffBeginTime = null;
		}
		this.chgoffBeginTime = chgoffBeginTime;
	}

	public String getChgoffDclTime() {
		return chgoffDclTime;
	}

	public void setChgoffDclTime(String chgoffDclTime) {
		if ("".equals(chgoffDclTime)){
			chgoffDclTime = null;
		}
		this.chgoffDclTime = chgoffDclTime;
	}

	public String getDecTime() {
		return decTime;
	}

	public void setDecTime(String decTime) {
		if("".equals(decTime)){
			decTime = null;
		}
		this.decTime = decTime;
	}

	public String getChkTime() {
		return chkTime;
	}

	public void setChkTime(String chkTime) {
		this.chkTime = chkTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getChgoffDclTimeStart() {
		return chgoffDclTimeStart;
	}

	public void setChgoffDclTimeStart(String chgoffDclTimeStart) {
		this.chgoffDclTimeStart = chgoffDclTimeStart;
	}

	public String getChgoffDclTimeEnd() {
		return chgoffDclTimeEnd;
	}

	public void setChgoffDclTimeEnd(String chgoffDclTimeEnd) {
		this.chgoffDclTimeEnd = chgoffDclTimeEnd;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public List<DcrChgoffExg> getDcrChgoffExg() {
		return dcrChgoffExg;
	}

	public void setDcrChgoffExg(List<DcrChgoffExg> dcrChgoffExg) {
		this.dcrChgoffExg = dcrChgoffExg;
	}

	public List<DcrChgoffImg> getDcrChgoffImg() {
		return dcrChgoffImg;
	}

	public void setDcrChgoffImg(List<DcrChgoffImg> dcrChgoffImg) {
		this.dcrChgoffImg = dcrChgoffImg;
	}

	public List<DcrChgoffInvtLt> getDcrChgoffInvtLt() {
		return dcrChgoffInvtLt;
	}

	public void setDcrChgoffInvtLt(List<DcrChgoffInvtLt> dcrChgoffInvtLt) {
		this.dcrChgoffInvtLt = dcrChgoffInvtLt;
	}

	public List<DcrAdjaccAcmpFormDt> getDcrAdjaccAcmpFormDt() {
		return dcrAdjaccAcmpFormDt;
	}

	public void setDcrAdjaccAcmpFormDt(List<DcrAdjaccAcmpFormDt> dcrAdjaccAcmpFormDt) {
		this.dcrAdjaccAcmpFormDt = dcrAdjaccAcmpFormDt;
	}

	public String getInputerName() {
		return inputerName;
	}

	public void setInputerName(String inputerName) {
		this.inputerName = inputerName;
	}

	public String getEtpsPreentNo() {
		return etpsPreentNo;
	}

	public void setEtpsPreentNo(String etpsPreentNo) {
		this.etpsPreentNo = etpsPreentNo;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
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

	public String getChgoffTypeNm() {
		return chgoffTypeNm;
	}

	public void setChgoffTypeNm(String chgoffTypeNm) {
		this.chgoffTypeNm = chgoffTypeNm;
	}

	public String getChkStatusNm() {
		return chkStatusNm;
	}

	public void setChkStatusNm(String chkStatusNm) {
		this.chkStatusNm = chkStatusNm;
	}

}
