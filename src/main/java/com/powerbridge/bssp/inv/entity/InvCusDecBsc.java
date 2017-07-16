package com.powerbridge.bssp.inv.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
@TableName("inv_cus_dec_bsc")
public class InvCusDecBsc implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableField("UID")
	private String uid;
    /**
     * 清单预录入统一编号
     */
	@TableField("SEQ_NO")
	private String seqNo;
    /**
     * 变更次数
     */
	@TableField("CHG_TMS_CNT")
	private BigDecimal chgTmsCnt;
    /**
     * 报关单统一编号
     */
	@TableField("DEC_SEQ_NO")
	private String decSeqNo;
    /**
     * 备案编号
     */
	@TableField("PUTREC_NO")
	private String putrecNo;
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
     * 录入单位统一社会信用代码
     */
	@TableField("INPUT_ETPS_SCCD")
	private String inputEtpsSccd;
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
     * 进出口标记代码
     */
	@TableField("IMPEXP_MARKCD")
	private String impexpMarkcd;
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
     * 报关单类型代码
     */
	@TableField("DEC_TYPECD")
	private String decTypecd;
    /**
     * 备注
     */
	@TableField("RMK")
	private String rmk;
    /**
     * 是否有生成报关单
     */
	@TableField("CREATE_FLAG")
	private String createFlag;
    /**
     * 入库时间
     */
	@TableField("ADD_TIME")
	private String addTime;


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

	public BigDecimal getChgTmsCnt() {
		return chgTmsCnt;
	}

	public void setChgTmsCnt(BigDecimal chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}

	public String getDecSeqNo() {
		return decSeqNo;
	}

	public void setDecSeqNo(String decSeqNo) {
		this.decSeqNo = decSeqNo;
	}

	public String getPutrecNo() {
		return putrecNo;
	}

	public void setPutrecNo(String putrecNo) {
		this.putrecNo = putrecNo;
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

	public String getImpexpMarkcd() {
		return impexpMarkcd;
	}

	public void setImpexpMarkcd(String impexpMarkcd) {
		this.impexpMarkcd = impexpMarkcd;
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

	public String getDecTypecd() {
		return decTypecd;
	}

	public void setDecTypecd(String decTypecd) {
		this.decTypecd = decTypecd;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getCreateFlag() {
		return createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}
