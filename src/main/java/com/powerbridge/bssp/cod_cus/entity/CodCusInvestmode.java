package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_investmode")
public class CodCusInvestmode implements Serializable{
	
	@TableField(exist = false)
	private static final long serialVersionUID = -2547035869942050331L;
	
	@TableId(value = "INVEST_MODE_CODE")
	private String investModeCode;//投资方式
	
	@TableField(value = "INVEST_MODE_NAME")
    private String investModeName;//投资方式名称
	
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
	
    public String getInvestModeCode() {
        return investModeCode;
    }

    public void setInvestModeCode(String investModeCode) {
        this.investModeCode = investModeCode == null ? null : investModeCode.trim();
    }

    public String getInvestModeName() {
        return investModeName;
    }

    public void setInvestModeName(String investModeName) {
        this.investModeName = investModeName == null ? null : investModeName.trim();
    }
}