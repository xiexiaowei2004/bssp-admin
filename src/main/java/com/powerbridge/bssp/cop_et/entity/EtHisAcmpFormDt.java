package com.powerbridge.bssp.cop_et.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 联网企业档案库随附单证明细历史表
 *
 * @author willChen
 * @since 2017-07-03 09:38:05
 */
@TableName("et_his_acmp_form_dt")
public class EtHisAcmpFormDt implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = -4514091520848483002L;
    /**
     * 主键
     */
    @TableId("UID")
    private String uid;
    /**
     * 联网企业档案库编号
     */
    @TableField("NETWK_ETPS_ARCRP_NO")
    private String netwkEtpsArcrpNo;
    /**
     * 变更次数
     */
    @TableField("CHG_TMS_CNT")
    private Integer chgTmsCnt;
    /**
     * 随附单证序号
     */
    @TableField("ACMP_FORM_SEQNO")
    private Integer acmpFormSeqno;
    /**
     * 随附单证类型代码
     */
    @TableField("ACMP_FORM_TYPECD")
    private String acmpFormTypecd;
    /**
     * 随附单证类型名称
     */
    @TableField("ACMP_FORM_TYPENM")
    private String acmpFormTypenm;
    /**
     * 随附单证编号
     */
    @TableField("ACMP_FORM_NO")
    private String acmpFormNo;
    /**
     * 固定编号
     */
    @TableField("FIXD_NO")
    private String fixdNo;
    /**
     * 随附单证文件名称
     */
    @TableField("ACMP_FORM_FILE_NM")
    private String acmpFormFileNm;
    /**
     * 附件大小(K)
     */
    @TableField("TGBL_LOSS_RATE")
    private Integer tgblLossRate;
    /**
     * 备注
     */
    @TableField("RMK")
    private String rmk;
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
    private Date createTime;
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
    private Date updateTime;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNetwkEtpsArcrpNo() {
        return netwkEtpsArcrpNo;
    }

    public void setNetwkEtpsArcrpNo(String netwkEtpsArcrpNo) {
        this.netwkEtpsArcrpNo = netwkEtpsArcrpNo;
    }

    public Integer getChgTmsCnt() {
        return chgTmsCnt;
    }

    public void setChgTmsCnt(Integer chgTmsCnt) {
        this.chgTmsCnt = chgTmsCnt;
    }

    public Integer getAcmpFormSeqno() {
        return acmpFormSeqno;
    }

    public void setAcmpFormSeqno(Integer acmpFormSeqno) {
        this.acmpFormSeqno = acmpFormSeqno;
    }

    public String getAcmpFormTypecd() {
        return acmpFormTypecd;
    }

    public void setAcmpFormTypecd(String acmpFormTypecd) {
        this.acmpFormTypecd = acmpFormTypecd;
    }

    public String getAcmpFormTypenm() {
        return acmpFormTypenm;
    }

    public void setAcmpFormTypenm(String acmpFormTypenm) {
        this.acmpFormTypenm = acmpFormTypenm;
    }

    public String getAcmpFormNo() {
        return acmpFormNo;
    }

    public void setAcmpFormNo(String acmpFormNo) {
        this.acmpFormNo = acmpFormNo;
    }

    public String getFixdNo() {
        return fixdNo;
    }

    public void setFixdNo(String fixdNo) {
        this.fixdNo = fixdNo;
    }

    public String getAcmpFormFileNm() {
        return acmpFormFileNm;
    }

    public void setAcmpFormFileNm(String acmpFormFileNm) {
        this.acmpFormFileNm = acmpFormFileNm;
    }

    public Integer getTgblLossRate() {
        return tgblLossRate;
    }

    public void setTgblLossRate(Integer tgblLossRate) {
        this.tgblLossRate = tgblLossRate;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
