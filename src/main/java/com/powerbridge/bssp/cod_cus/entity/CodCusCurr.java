package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_curr")
public class CodCusCurr implements Serializable {
	
	@TableField(exist = false)
	private static final long serialVersionUID = 7383513681240355270L;
	
	@TableId(value = "CURR_CODE")
	private String currCode;//代码
	
	@TableField(value = "CURR_SYMB")
	private String currSymb;//英文币值
	
	@TableField(value = "CURR_NAME")
	 private String currName;//币值
	
	@TableField(value = "IS_MAINTAIN")
	private String isMaintain;//
	
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
	
    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode == null ? null : currCode.trim();
    }

    public String getCurrSymb() {
        return currSymb;
    }

    public void setCurrSymb(String currSymb) {
        this.currSymb = currSymb == null ? null : currSymb.trim();
    }

    public String getCurrName() {
        return currName;
    }

    public void setCurrName(String currName) {
        this.currName = currName == null ? null : currName.trim();
    }

    public String getIsMaintain() {
        return isMaintain;
    }

    public void setIsMaintain(String isMaintain) {
        this.isMaintain = isMaintain == null ? null : isMaintain.trim();
    }
}