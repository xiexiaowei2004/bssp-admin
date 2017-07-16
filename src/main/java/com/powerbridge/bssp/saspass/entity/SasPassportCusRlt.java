package com.powerbridge.bssp.saspass.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName(value = "sas_passport_cus_rlt")
public class SasPassportCusRlt implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 主键
     *
     * @pdOid c6b37573-9b0f-42e1-abab-32ec40b9abdd
     */
    @TableId(value = "UID")
    private String uid;
    /**
     * 单据编号
     *
     * @pdOid 600795ad-7f62-4bb2-8ff8-233d63b6e27d
     */
    @TableField(value = "SEQ_NO")
    private String seqNo;
    /**
     * 核放单编号
     *
     * @pdOid 0b75271f-9f37-4257-860b-5e3e8caf5763
     */
    @TableField(value = "PASSPORT_NO")
    private String passportNo;
    /**
     * 关联单证类型
     *
     * @pdOid 7aa62c39-b88b-49ee-a008-c44126260af8
     */
    @TableField(value = "RLT_TB_TYPECD")
    private String rltTbTypecd;
    /**
     * 关联单证编号
     *
     * @pdOid 7b2aa871-e92d-4304-aefb-5b3a3eb3015d
     */
    @TableField(value = "RLT_NO")
    private String rltNo;
    /**
     * 企业预录入编号
     *
     * @pdOid f1b950f6-9cac-40e7-918d-96ac31d26612
     */
    @TableField(value = "ETPS_PREENT_NO")
    private String etpsPreentNo;
    /**
     * 关联单证企业备案号
     *
     * @pdOid e63241c6-3bd8-4d49-813e-47f63ab0af77
     */
    @TableField(value = "COP_ENT_NO")
    private String copEntNo;
    /**
     * 关联单证经营企业名称
     *
     * @pdOid f0eba3fe-ca26-4cbe-9c15-e9b905a647a3
     */
    @TableField(value = "BIZOP_ETPS_NM")
    private String bizopEtpsNm;
    /**
     * 创建人代码
     *
     * @pdOid 1c828cd7-3c62-4334-b595-49e45fa2df17
     */
    @TableField(value = "CREATE_BY")
    private String createBy;
    /**
     * 创建人名称
     *
     * @pdOid aa1b5ac2-6981-4086-acf8-660619fb6b5c
     */
    @TableField(value = "CREATE_NAME")
    private String createName;
    /**
     * 创建时间
     *
     * @pdOid 4cd5a358-9b61-49b1-8fd5-129399180130
     */
    @TableField(value = "CREATE_TIME")
    private String createTime;
    /**
     * 修改人代码
     *
     * @pdOid 4fc0b0c7-2c35-4394-a01d-f3f17a44d0e2
     */
    @TableField(value = "UPDATE_BY")
    private String updateBy;
    /**
     * 修改人名称
     *
     * @pdOid 79f2f157-ab1f-4d74-99b2-ef9d819f68a9
     */
    @TableField(value = "UPDATE_NAME")
    private String updateName;
    /**
     * 修改时间
     *
     * @pdOid d447d6a8-1111-4113-ac29-7f67defd88de
     */
    @TableField(value = "UPDATE_TIME")
    private String updateTime;

    /**
     * 变更次数
     */
    @TableField(value = "CHG_TMS_CNT")
    public Integer chgTmsCnt;

    /**
     * 管理单证类型名称
     */
    @TableField(exist = false)
    private String rltTbTypecdName;


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

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getRltTbTypecd() {
        return rltTbTypecd;
    }

    public void setRltTbTypecd(String rltTbTypecd) {
        this.rltTbTypecd = rltTbTypecd;
    }

    public String getRltNo() {
        return rltNo;
    }

    public void setRltNo(String rltNo) {
        this.rltNo = rltNo;
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

    public String getBizopEtpsNm() {
        return bizopEtpsNm;
    }

    public void setBizopEtpsNm(String bizopEtpsNm) {
        this.bizopEtpsNm = bizopEtpsNm;
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

    public String getRltTbTypecdName() {
        return rltTbTypecdName;
    }

    public void setRltTbTypecdName(String rltTbTypecdName) {
        this.rltTbTypecdName = rltTbTypecdName;
    }

    public Integer getChgTmsCnt() {
        return chgTmsCnt;
    }

    public void setChgTmsCnt(Integer chgTmsCnt) {
        this.chgTmsCnt = chgTmsCnt;
    }
}
