package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_customsfec")
public class CodCusCustomsfec implements Serializable {
	
	@TableField(exist = false)
	private static final long serialVersionUID = 4071023857638355697L;
	
	@TableId(value = "CUSTOMS_CODE")
	private String customsCode;//代码
	
	@TableField(value = "CUSTOMS_NAME")
    private String customsName;//关区名称
	
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
	
    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode == null ? null : customsCode.trim();
    }

    public String getCustomsName() {
        return customsName;
    }

    public void setCustomsName(String customsName) {
        this.customsName = customsName == null ? null : customsName.trim();
    }
}