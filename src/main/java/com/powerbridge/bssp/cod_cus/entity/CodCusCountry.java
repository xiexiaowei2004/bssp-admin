package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_country")
public class
CodCusCountry implements Serializable {
	
	@TableField(exist = false)
	private static final long serialVersionUID = -3344351544596168711L;

	@TableId(value = "COUNTRY_CO")
    private String countryCo;//代码
	
	@TableField(value = "COUNTRY_EN")
    private String countryEn;//英文名称
	
	@TableField(value = "COUNTRY_NA")
    private String countryNa;//中文名称
	
	@TableField(value = "EXAM_MARK")
    private String examMark;//优普税率
	
	@TableField(value = "HIGH_LOW")
    private String highLow;//
	
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

    public String getCountryCo() {
        return countryCo;
    }

    public void setCountryCo(String countryCo) {
        this.countryCo = countryCo == null ? null : countryCo.trim();
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn == null ? null : countryEn.trim();
    }

    public String getCountryNa() {
        return countryNa;
    }

    public void setCountryNa(String countryNa) {
        this.countryNa = countryNa == null ? null : countryNa.trim();
    }

    public String getExamMark() {
        return examMark;
    }

    public void setExamMark(String examMark) {
        this.examMark = examMark == null ? null : examMark.trim();
    }

    public String getHighLow() {
        return highLow;
    }

    public void setHighLow(String highLow) {
        this.highLow = highLow == null ? null : highLow.trim();
    }
}