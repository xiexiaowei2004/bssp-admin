package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;


@TableName("cod_cus_wrap")
public class CodCusWrap implements Serializable{

	  @TableField(exist = false)
	private static final long serialVersionUID = 7225671188511920801L;

	  @TableField (value = "WRAP_NAME")
	private String wrapName;//包装方式

	  @TableId(value = "WRAP_TYPE")
    private String wrapType;//代码

	  @TableField(value = "LICENSEDUCO_CODE")
    private String licenseducoCode;//
	  
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
	  
    public String getWrapName() {
        return wrapName;
    }

    public void setWrapName(String wrapName) {
        this.wrapName = wrapName == null ? null : wrapName.trim();
    }

    public String getWrapType() {
        return wrapType;
    }

    public void setWrapType(String wrapType) {
        this.wrapType = wrapType == null ? null : wrapType.trim();
    }

    public String getLicenseducoCode() {
        return licenseducoCode;
    }

    public void setLicenseducoCode(String licenseducoCode) {
        this.licenseducoCode = licenseducoCode == null ? null : licenseducoCode.trim();
    }
}