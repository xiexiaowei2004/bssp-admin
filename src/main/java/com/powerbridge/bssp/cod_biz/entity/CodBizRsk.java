package com.powerbridge.bssp.cod_biz.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：CodBizRsk
 * 类描述：cod_biz_rsk_param 表对应的实体类
 * 创建人：xuzuotao
 * 创建时间：2017年5月9日 上午10:41:00
 */
@TableName("cod_biz_rsk_param")
public class CodBizRsk implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    /**
     * 主键ID
     */
    @TableId(value = "UID")
    private String uid;

    /**
     * 参数序号
     */
    @TableField(value = "ORDER_NO")
    private Integer orderNo;

    /**
     * 参数代码
     */
    @TableField(value = "CODE")
    private String code;

    /**
     * 参数名称
     */
    @TableField(value = "NAME")
    private String name;


    /**
     * 参数值
     */
    @TableField(value = "PARAM_VALUE")
    @NotNull(message = "参数值不能为空")
    @Min(value = 0, message = "参数值最小值为0")
    private BigDecimal paramValue;

    /**
     * 通关业务代码
     */
    @TableField(value = "ClEARANCE_CODE")
    private String clearanceCode;

    /**
     * 参数说明
     */
    @TableField(value = "PARAM_DESCRIPT")
    private String paramDescript;


    /**
     * 关区代码
     */
    @TableField(value = "CUSTOMS_CODE")
    private String customsCode;

    /**
     * 场地代码
     */
    @TableField(value = "AREA_CODE")
    private String areaCode;

    /**
     * 是否启用
     */
    @TableField(value = "IS_ENABLE")
    private String isEnable;

    /**
     * 创建人
     */
    @TableField(value = "CREATE_BY")
    private String createBy;

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
     * 修改时间
     */
    @TableField(value = "UPDATE_TIME")
    private String updateTime;

    @TableField(exist = false)
	private String id ;

	@TableField(exist = false)
	private String text ;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

    
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getParamValue() {
        return paramValue;
    }

    public void setParamValue(BigDecimal paramValue) {
        this.paramValue = paramValue;
    }

    public String getClearanceCode() {
        return clearanceCode;
    }

    public void setClearanceCode(String clearanceCode) {
        this.clearanceCode = clearanceCode;
    }

    public String getParamDescript() {
        return paramDescript;
    }

    public void setParamDescript(String paramDescript) {
        this.paramDescript = paramDescript;
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

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
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
}