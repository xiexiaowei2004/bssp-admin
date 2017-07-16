package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_districtrel")
public class CodCusDistrictrel implements Serializable {
	
	@TableField(exist = false)
	private static final long serialVersionUID = 4480238792248077626L;
	
	@TableId(value = "DISTRICT_CODE")
	private String districtCode;//地区代码
	
	@TableField(value = "DISTRICT_NAME")
    private String districtName;//地区名称
	
	@TableField(value = "DISTRICT_TYPE")
    private String districtType;//地区类别
	
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
	
    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode == null ? null : districtCode.trim();
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public String getDistrictType() {
        return districtType;
    }

    public void setDistrictType(String districtType) {
        this.districtType = districtType == null ? null : districtType.trim();
    }
}