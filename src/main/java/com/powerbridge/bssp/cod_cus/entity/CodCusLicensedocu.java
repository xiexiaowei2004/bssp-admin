package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_licensedocu")
public class CodCusLicensedocu implements Serializable {
   
	@TableField(exist = false)
	private static final long serialVersionUID = -2764089073107087670L;

	@TableId(value = "DOCU_CODE")
	private String docuCode;//代码

	@TableField(value = "DOCU_NAME")
    private String docuName;//许可证件名称
	
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
	
    public String getDocuCode() {
        return docuCode;
    }

    public void setDocuCode(String docuCode) {
        this.docuCode = docuCode == null ? null : docuCode.trim();
    }

    public String getDocuName() {
        return docuName;
    }

    public void setDocuName(String docuName) {
        this.docuName = docuName == null ? null : docuName.trim();
    }
}