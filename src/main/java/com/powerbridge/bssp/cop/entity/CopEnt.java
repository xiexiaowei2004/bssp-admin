package com.powerbridge.bssp.cop.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：CopEnt
 * 类描述：CopEnt 表实体类
 * 创建人：haihuihuang
 * 创建时间：2017年5月9日 上午10:41:00
 */
@TableName("cop_ent")
public class CopEnt implements Serializable {

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
    @NotEmpty(message = "企业备案号不能为空")
    private String seqNo;

    /**
     * 企业备案编码
     */
    @TableField(value = "TRADE_CODE")
    @NotEmpty(message = "企业备案编码不能为空")
    @Size(min = 1, max = 10,message = "企业备案编码长度应在1至10之间")
    private String tradeCode;

    /**
     * 社会信用代码
     */
    @TableField(value = "COP_GB_CODE")
    @NotEmpty(message = "社会信用代码不能为空")
    @Size(min = 1, max = 18,message = "社会信用代码长度应在1至18之间")
    private String copGbCode;

    /**
     * 企业名称
     */
    @TableField(value = "ENT_NAME")
    @NotEmpty(message = "企业名称不能为空")
    @Length(min = 1, max = 128,message = "企业名称长度应在1至128之间")
    private String entName;

    /**
     * 企业地址
     */
    @TableField(value = "ENT_ADDR")
    @NotEmpty(message = "企业地址不能为空")
    @Length(min = 1, max = 128,message = "企业地址长度应在1至128之间")
    private String entAddr;

    /**
     * 主管海关
     */
    @TableField(value = "CUSTOMS_CODE")
    @NotEmpty(message = "主管海关不能为空")
    private String customsCode;

    /**
     * 主管海关名称
     */
    @TableField(value = "CUSTOMS_NAME")
    private String customsName;

    /**
     * 场地代码
     */
    @TableField(value = "AREA_CODE")
    @NotEmpty(message = "监管场所不能为空")
    private String areaCode;

    /**
     * 场地代码名称
     */
    @TableField(value = "AREA_NAME")
    private String areaName;

    /**
     * 企业性质
     */
    @TableField(value = "ENT_PROPERTY")
    @NotEmpty(message = "企业性质不能为空")
    private String entProperty;

    /**
     * 企业性质名称
     */
    @TableField(value = "ENT_PROPERTY_NAME")
    private String entPropertyName;

    /**
     * 企业类型
     */
    @TableField(value = "ENT_TYPE")
    @NotEmpty(message = "企业类型不能为空")
    private String entType;

    /**
     * 企业类型名称
     */
    @TableField(value = "ENT_TYPE_Name")
    private String entTypeName;

    /**
     * 法人代表
     */
    @TableField(value = "LAW_MAN")
    @NotEmpty(message = "法人代表不能为空")
    @Length(min = 1, max = 30)
    private String lawMan;

    /**
     * 注册资本
     */
    @TableField(value = "REG_FUND")
    @Digits(integer = 18, fraction = 5)
    private BigDecimal regFund;

    /**
     * 联系电话
     */
    @TableField(value = "TEL_CO")
    @NotEmpty(message = "联系电话不能为空")
    @Length(min = 1, max = 30)
    private String telCo;

    /**
     * 联系人
     */
    @TableField(value = "CONTAC_CO")
    @NotEmpty(message = "联系人不能为空")
    @Length(min = 1, max = 30)
    private String contacCo;

    /**
     * 经营范围
     */
    @TableField(value = "TRADE_AREA")
    @NotEmpty(message = "经营范围不能为空")
    @Length(min = 1, max = 255)
    private String tradeArea;

    /**
     * 备案日期
     */
    @TableField(value = "REG_DATE")
    private String regDate;

    /**
     * 有效期
     */
    @TableField(value = "VALID_DATE")

    private String validDate;

    /**
     * 有效标识
     */
    @TableField(value = "VALID_FLAG")
    @NotEmpty(message = "有效标识不能为空")
    private String validFlag;

    /**
     * 有效标识名称
     */
    @TableField(value = "VALID_FLAG_NAME")
    private String validFlagName;

    /**
     * 备注
     */
    @TableField(value = "REMARKS")
    @Length(min = 0, max = 255)
    private String remarks;

    /**
     * 申报人
     */
    @TableField(value = "INPUTER_CODE")
    private String inputerCode;

    /**
     * 申报人名称
     */
    @TableField(value = "INPUTER_NAME")
    private String inputerName;

    /**
     * 申报企业
     */
    @TableField(value = "INPUT_COP_NO")
    private String inputCopNo;

    /**
     * 申报企业名称
     */
    @TableField(value = "INPUT_COP_NAME")
    private String inputCopName;

    /**
     * 单据类型
     */
    @TableField("DOC_TYPE")
    private String docType;
    /**
     * 业务类型
     */

    @TableField("BIZ_TYPE")
    private String bizType;

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


    /**
     * 用于查询  备案日期
     */
    @TableField(exist = false)
    private String regDateStart;

    @TableField(exist = false)
    private String regDateEnd;

    /**
     * 用于查询 有效期
     */
    @TableField(exist = false)
    private String validDateStart;

    @TableField(exist = false)
    private String validDateEnd;
    
    @TableField(exist = false)
    @Valid //级联验证
    private List<CopBusiness> copBusiness;

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

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public String getCopGbCode() {
        return copGbCode;
    }

    public void setCopGbCode(String copGbCode) {
        this.copGbCode = copGbCode;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getEntAddr() {
        return entAddr;
    }

    public void setEntAddr(String entAddr) {
        this.entAddr = entAddr;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getEntProperty() {
        return entProperty;
    }

    public void setEntProperty(String entProperty) {
        this.entProperty = entProperty;
    }

    public String getEntType() {
        return entType;
    }

    public void setEntType(String entType) {
        this.entType = entType;
    }

    public String getLawMan() {
        return lawMan;
    }

    public void setLawMan(String lawMan) {
        this.lawMan = lawMan;
    }

    public BigDecimal getRegFund() {
        return regFund;
    }

    public void setRegFund(BigDecimal regFund) {
        this.regFund = regFund;
    }

    public String getTelCo() {
        return telCo;
    }

    public void setTelCo(String telCo) {
        this.telCo = telCo;
    }

    public String getContacCo() {
        return contacCo;
    }

    public void setContacCo(String contacCo) {
        this.contacCo = contacCo;
    }

    public String getTradeArea() {
        return tradeArea;
    }

    public void setTradeArea(String tradeArea) {
        this.tradeArea = tradeArea;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
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

    public String getRegDateStart() {
        return regDateStart;
    }

    public void setRegDateStart(String regDateStart) {
        this.regDateStart = regDateStart;
    }

    public String getRegDateEnd() {
        return regDateEnd;
    }

    public void setRegDateEnd(String regDateEnd) {
        this.regDateEnd = regDateEnd;
    }

    public String getCustomsName() {
        return customsName;
    }

    public void setCustomsName(String customsName) {
        this.customsName = customsName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getEntPropertyName() {
        return entPropertyName;
    }

    public void setEntPropertyName(String entPropertyName) {
        this.entPropertyName = entPropertyName;
    }

    public String getEntTypeName() {
        return entTypeName;
    }

    public void setEntTypeName(String entTypeName) {
        this.entTypeName = entTypeName;
    }

    public String getValidFlagName() {
        return validFlagName;
    }

    public void setValidFlagName(String validFlagName) {
        this.validFlagName = validFlagName;
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

    public String getInputCopName() {
        return inputCopName;
    }

    public void setInputCopName(String inputCopName) {
        this.inputCopName = inputCopName;
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

	public List<CopBusiness> getCopBusiness() {
		return copBusiness;
	}

	public void setCopBusiness(List<CopBusiness> copBusiness) {
		this.copBusiness = copBusiness;
	}

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getValidDateStart() {return validDateStart;}

    public String getValidDateEnd() {return validDateEnd;}

    public void setValidDateStart(String validDateStart) {this.validDateStart = validDateStart;}

    public void setValidDateEnd(String validDateEnd) {this.validDateEnd = validDateEnd;}
}