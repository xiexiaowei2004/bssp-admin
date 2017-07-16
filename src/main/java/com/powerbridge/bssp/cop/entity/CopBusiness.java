package com.powerbridge.bssp.cop.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：CopBusiness
 * 类描述：CopBusiness 表实体类
 * 创建人：haihuihuang
 * 创建时间：2017年5月9日 上午10:41:00
 */
@TableName("cop_business")
public class CopBusiness implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "UID")
    private String uid;

    /**
     * 企业备案号
     */
    @TableField(value = "SEQ_NO")
    private String seqNo;

    /**
     * 仓库（工厂）代码
     */
    @TableField(value = "STORE_CODE")
    private String storeCode;

    /**
     * 仓库（工厂）名称
     */
    @TableField(value = "STORE_NAME")
    @NotNull(message = "仓库（工厂）名称不能为空")
    private String storeName;

    /**
     * 仓库类型
     */
    @TableField(value = "STORE_TYPE")
    @NotNull(message = "仓库类型不能为空")
    private String storeType;

    /**
     * 仓库（加工）批准证编号
     */
    @TableField(value = "STORE_LICENCE")
    @NotNull(message = "仓库（加工）批准证编号不能为空")
    private String storeLicence;

    /**
     * 仓库（工厂）面积
     */
    @TableField(value = "STORE_AREA")
    @NotNull(message = "仓库（工厂）面积不能为空")
    private BigDecimal storeArea;

    /**
     * 仓库（工厂）体积
     */
    @TableField(value = "STORE_VOLUME")
    @NotNull(message = "仓库（工厂）体积不能为空")
    private BigDecimal storeVolume;

    /**
     * 仓库（工厂）地址
     */
    @TableField(value = "STORE_ADDRESS")
    @NotNull(message = "仓库（工厂）地址不能为空")
    private String storeAddress;

    /**
     * 储位数量
     */
    @TableField(value = "STORE_NUMBER")
    @NotNull(message = "储位数量不能为空")
    private BigDecimal storeNumber;

    /**
     * 加工范围
     */
    @TableField(value = "PROC_RANG")
    @NotNull(message = "加工范围不能为空")
    private String procRang;

    /**
     * 备注
     */
    @TableField(value = "REMARKS")
    private String remarks;

    /**
     * 创建人
     */
    @TableField(value = "CREATE_BY")
    private String createBy;

    /**
     * 创建人名称
     */
    @TableField(value = "CREATE_NAME")
    private String createName;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME")
    private String createTime;

    /**
     * 修改人
     */
    @TableField(value = "UPDATE_BY")
    private String updateBy;

    /**
     * 修改人名称
     */
    @TableField(value = "UPDATE_NAME")
    private String updateName;


    /**
     * 修改时间
     */
    @TableField(value = "UPDATE_TIME")
    private String updateTime;
    
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

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getStoreLicence() {
        return storeLicence;
    }

    public void setStoreLicence(String storeLicence) {
        this.storeLicence = storeLicence;
    }

    public BigDecimal getStoreArea() {
        return storeArea;
    }

    public void setStoreArea(BigDecimal storeArea) {
        this.storeArea = storeArea;
    }

    public BigDecimal getStoreVolume() {
        return storeVolume;
    }

    public void setStoreVolume(BigDecimal storeVolume) {
        this.storeVolume = storeVolume;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public BigDecimal getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(BigDecimal storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getProcRang() {
        return procRang;
    }

    public void setProcRang(String procRang) {
        this.procRang = procRang;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
}