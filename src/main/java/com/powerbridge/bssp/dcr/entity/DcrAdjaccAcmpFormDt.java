package com.powerbridge.bssp.dcr.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 项目名称：bssp Maven Webapp
 * 类名称：DcrAdjaccAcmpFormDt
 * 类描述：dcr_adjacc_acmp_form_dt 账册核销随附单证明细
 * 创建人：haihuihuang
 * 创建时间：2017年5月22日 上午15:41:00
 */
@TableName("dcr_adjacc_acmp_form_dt")
public class DcrAdjaccAcmpFormDt implements Serializable {

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
    @NotEmpty(message = "账册编号不能为空")
    private String emsNo;
    /**
     * 报核次数
     */
    @TableField("CHG_TMS_CNT")
    @NotNull(message = "报核次数不能为空")
    private Integer chgTmsCnt;
    /**
     * 随附单证序号
     */
    @TableField("ACMP_FORM_SEQNO")
    @NotNull(message = "随附单证序号不能为空")
    private Integer acmpFormSeqno;
    /**
     * 随附单证类型代码
     */
    @TableField("ACMP_FORM_TYPECD")
    @NotEmpty(message = "随附单证类型代码不能为空")
    private String acmpFormTypecd;
    /**
     * 随附单证编号
     */
    @TableField("ACMP_FORM_NO")
    @NotEmpty(message = "随附单证编号不能为空")
    private String acmpFormno;
    /**
     * 固定编号
     */
    @TableField("FIXD_NO")
    private String fixdNo;
    /**
     * 随附单证文件名称
     */
    @TableField("ACMP_FORM_FILE_NM")
    @NotEmpty(message = "随附单证文件名称不能为空")
    private String acmpFormFileNm;
    /**
     * 附件大小(K)
     */
    @TableField("ACMP_FORM_FILE_SIZE")
    private BigDecimal acmpFormFileSize;
    /**
     * 备注
     */
    @TableField("RMK")
    private String rmk;
    /**
     * 预录入统一编号
     */
    @TableField("SEQ_NO")
    @NotEmpty(message = "预录入统一编号不能为空")
    private String seqNo;


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

    public String getAcmpFormno() {
        return acmpFormno;
    }

    public void setAcmpFormno(String acmpFormno) {
        this.acmpFormno = acmpFormno;
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

    public BigDecimal getAcmpFormFileSize() {
        return acmpFormFileSize;
    }

    public void setAcmpFormFileSize(BigDecimal acmpFormFileSize) {
        this.acmpFormFileSize = acmpFormFileSize;
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
}
