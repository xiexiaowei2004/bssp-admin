package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_invclass")
public class CodCusInvclass implements Serializable{
	
	@TableField(exist = false)
	private static final long serialVersionUID = -9004298358567981218L;
	
	@TableId(value = "INV_CODE")
	private String invCode;//引进方式
	
	@TableField(value = "INV_NAME")
    private String invName;//引进方式名称
	
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
	
    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode == null ? null : invCode.trim();
    }

    public String getInvName() {
        return invName;
    }

    public void setInvName(String invName) {
        this.invName = invName == null ? null : invName.trim();
    }
}