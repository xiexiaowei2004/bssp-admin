package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_paymode")
public class CodCusPaymode implements Serializable {
   
	@TableField(exist = false)
	private static final long serialVersionUID = -574014084110009700L;

	@TableId(value = "PAY_MODE_CODE")
	private String payModeCode;//报税方式

	@TableField(value = "PAY_MODE_NAME")
    private String payModeName;//报税方式名称
	
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
	
    public String getPayModeCode() {
        return payModeCode;
    }

    public void setPayModeCode(String payModeCode) {
        this.payModeCode = payModeCode == null ? null : payModeCode.trim();
    }

    public String getPayModeName() {
        return payModeName;
    }

    public void setPayModeName(String payModeName) {
        this.payModeName = payModeName == null ? null : payModeName.trim();
    }
}