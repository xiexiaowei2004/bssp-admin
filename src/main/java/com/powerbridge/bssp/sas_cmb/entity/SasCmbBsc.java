package com.powerbridge.bssp.sas_cmb.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 耗料单基本信息
 * </p>
 *
 * @author jokylao
 * @since 2017-06-18
 */
@TableName("sas_cmb_bsc")
public class SasCmbBsc implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("UID")
    private String uid;
    /**
     * 耗料单编号
     */
    @TableField("CMB_NO")
    private String cmbNo;
    /**
     * 变更次数
     */
    @TableField("CHG_TMS_CNT")
    private BigDecimal chgTmsCnt;
    /**
     * 耗料单预录入编号
     */
    @TableField("SAS_CMB_PREENT_NO")
    private String sasCmbPreentNo;
    /**
     * 申报类型
     */
    @TableField("DCL_TYPECD")
    private String dclTypecd;
    /**
     * 耗料单类型
     */
    @TableField("CM_TYPECD")
    private String cmTypecd;
    /**
     * 账册编号
     */
    @TableField("EMS_NO")
    private String emsNo;
    /**
     * 主管海关
     */
    @TableField("MASTER_CUSCD")
    private String masterCuscd;
    /**
     * 耗料单开始时间
     */
    @TableField("CM_BEGIN_TIME")
    private String cmBeginTime;
    /**
     * 耗料单截止时间
     */
    @TableField("CM_END_TIME")
    private String cmEndTime;
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
     * 审批标记
     */
    @TableField("EMAPV_STUCD")
    private String emapvStucd;
    /**
     * 备案审批时间
     */
    @TableField("PUTREC_APPR_TIME")
    private String putrecApprTime;
    /**
     * 变更审批时间
     */
    @TableField("CHG_APPR_TIME")
    private String chgApprTime;
    /**
     * 申报时间
     */
    @TableField("DCL_TIME")
    private String dclTime;

    @TableField(exist = false)
    private String dclTimeStart;

    @TableField(exist = false)
    private String dclTimeEnd;

    /**
     * 申报来源
     */
    @TableField("DCL_MARKCD")
    private String dclMarkcd;
    /**
     * 状态
     */
    @TableField("STUCD")
    private String stucd;
    /**
     * 备注
     */
    @TableField("RMK")
    private String rmk;
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

    @TableField(exist = false)
    private String chkstatusName;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCmbNo() {
        return cmbNo;
    }

    public void setCmbNo(String cmbNo) {
        this.cmbNo = cmbNo;
    }

    public BigDecimal getChgTmsCnt() {
        return chgTmsCnt;
    }

    public void setChgTmsCnt(BigDecimal chgTmsCnt) {
        this.chgTmsCnt = chgTmsCnt;
    }

    public String getSasCmbPreentNo() {
        return sasCmbPreentNo;
    }

    public void setSasCmbPreentNo(String sasCmbPreentNo) {
        this.sasCmbPreentNo = sasCmbPreentNo;
    }

    public String getDclTypecd() {
        return dclTypecd;
    }

    public void setDclTypecd(String dclTypecd) {
        this.dclTypecd = dclTypecd;
    }

    public String getCmTypecd() {
        return cmTypecd;
    }

    public void setCmTypecd(String cmTypecd) {
        this.cmTypecd = cmTypecd;
    }

    public String getEmsNo() {
        return emsNo;
    }

    public void setEmsNo(String emsNo) {
        this.emsNo = emsNo;
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

    public String getStucd() {
        return stucd;
    }

    public void setStucd(String stucd) {
        this.stucd = stucd;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
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

    public String getCmBeginTime() {
        return cmBeginTime;
    }

    public void setCmBeginTime(String cmBeginTime){
        if ("".equals(cmBeginTime)) {
            this.cmBeginTime = null;
        } else {
            this.cmBeginTime = cmBeginTime;
        }
    }

    public String getCmEndTime() {
        return cmEndTime;
    }

    public void setCmEndTime(String cmEndTime) {
        if ("".equals(cmEndTime)) {
            this.cmEndTime = null;
        } else {
            this.cmEndTime = cmEndTime;
        }
    }

    public String getPutrecApprTime() {
        return putrecApprTime;
    }

    public void setPutrecApprTime(String putrecApprTime) {
        if ("".equals(putrecApprTime)) {
            this.putrecApprTime = null;
        } else {
            this.putrecApprTime = putrecApprTime;
        }
    }

    public String getChgApprTime() {
        return chgApprTime;
    }

    public void setChgApprTime(String chgApprTime) {
        if ("".equals(chgApprTime)) {
            this.chgApprTime = null;
        } else {
            this.chgApprTime = chgApprTime;
        }
    }
    public String getDclTime() {
        return dclTime;
    }

    public void setDclTime(String dclTime) {
        if ("".equals(dclTime)) {
            this.dclTime = null;
        } else {
            this.dclTime = dclTime;
        }
    }
    public String getDecTime() {
        return decTime;
    }

    public void setDecTime(String decTime) {
          if ("".equals(decTime)) {
            this.decTime = null;
        } else {
            this.decTime = decTime;
        }
    }

    public String getChkTime() {
        return chkTime;
    }

    public void setChkTime(String chkTime) {
        if ("".equals(chkTime)) {
            this.chkTime = null;
        } else {
            this.chkTime = chkTime;
        }
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {

        if ("".equals(createTime)) {
            this.createTime = null;
        } else {
            this.createTime = createTime;
        }
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {

        if ("".equals(updateTime)) {
            this.updateTime = null;
        } else {
            this.updateTime = updateTime;
        }
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

	public String getChkstatusName() {
		return chkstatusName;
	}

	public void setChkstatusName(String chkstatusName) {
		this.chkstatusName = chkstatusName;
	}




}
