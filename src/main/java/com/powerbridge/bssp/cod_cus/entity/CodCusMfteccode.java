package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;


@TableName("cod_cus_mfteccode")
public class CodCusMfteccode implements Serializable{
	

	@TableField(exist = false)
	private static final long serialVersionUID = 5133023441450398834L;

	@TableId(value = "MFTEC_CODE")
	private String mftecCode;//外经贸委代码

	@TableField(value = "MFTEC_NAME")
    private String mftecName;//外经贸委代码名称
	
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
	
    public String getMftecCode() {
        return mftecCode;
    }

    public void setMftecCode(String mftecCode) {
        this.mftecCode = mftecCode == null ? null : mftecCode.trim();
    }

    public String getMftecName() {
        return mftecName;
    }

    public void setMftecName(String mftecName) {
        this.mftecName = mftecName == null ? null : mftecName.trim();
    }
}