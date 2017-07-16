package com.powerbridge.bssp.dcr.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：DcrChgoffInvtLt
 * 类描述：dcr_chgoff_invt_lt 账册报核清单
 * 创建人：haihuihuang
 * 创建时间：2017年5月22日 上午15:41:00
 */
@TableName("dcr_chgoff_invt_lt")
public class DcrChgoffInvtLt implements Serializable {

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
     * 报核清单编号
     */
//    @TableField("DCR_INVT_NO")
//    @NotEmpty(message = "报核清单编号不能为空")
    // @Size(min = 1,max = 64,message = "报核清单编号精度超出限制")
//    private String dcrInvtNo;
    /**
     * 保税清单编号
     */
    @TableField("BOND_INVT_NO")
    @NotEmpty(message = "报核清单编号不能为空")
    private String bondInvtNo;
    /**
     * 序号
     */
    @TableField("L_NO")
    @NotNull(message = "序号不能为空")
    private Integer lNo;
    /**
     * 进出口标志
     */
    @TableField("IE_FLAG")
    @NotEmpty(message = "进出口标志不能为空")
    private String ieFlag;
    /**
     * 申报类型
     */
    @TableField("DCL_TYPECD")
    @NotEmpty(message = "申报类型不能为空")
    private String dclTypecd;
    /**
     * 预录入统一编号
     */
    @TableField("SEQ_NO")
    @NotEmpty(message = "预录入统一编号不能为空")
    // @Size(min = 1,max = 32,message = "预录入统一编号超出限制")
    private String seqNo;
    /**
     * 修改标记代码
     */
    @TableField("MODF_MARKCD")
    @NotEmpty(message = "修改标记代码不能为空")
    private String modfMarkcd;
    /**
     * 备注
     */
    @TableField("RMK")
    // @Size(max = 512, message = "备注精度超出限制")
    private String rmk;
    /**
     *  用于查询子表聚合存放总数
     */
    @TableField(exist = false)
    private Integer total;
    /**
     * 主管关区
     */
//    @TableField("MASTER_CUSCD")
//    private String masterCuscd;
    /**
     * 场地代码
     */
//    @TableField("AREA_CODE")
//    private String areaCode;

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

    public String getBondInvtNo() {
        return bondInvtNo;
    }

    public void setBondInvtNo(String bondInvtNo) {
        this.bondInvtNo = bondInvtNo;
    }

    public Integer getlNo() {
        return lNo;
    }

    public void setlNo(Integer lNo) {
        this.lNo = lNo;
    }

    public String getIeFlag() {
        return ieFlag;
    }

    public void setIeFlag(String ieFlag) {
        this.ieFlag = ieFlag;
    }

    public String getDclTypecd() {
        return dclTypecd;
    }

    public void setDclTypecd(String dclTypecd) {
        this.dclTypecd = dclTypecd;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getModfMarkcd() {
        return modfMarkcd;
    }

    public void setModfMarkcd(String modfMarkcd) {
        this.modfMarkcd = modfMarkcd;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
