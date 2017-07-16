package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_disttype")

public class CodCusDisttype implements Serializable {
	
	@TableField(exist = false)
	private static final long serialVersionUID = -4022954689300935689L;

	@TableId(value = "DISTRICT_T")
	private String districtT;//类别代码
	
	@TableField(value = "DIST_TYPE")
    private String distType;//类别名称
	
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
	
    public String getDistrictT() {
        return districtT;
    }

    public void setDistrictT(String districtT) {
        this.districtT = districtT == null ? null : districtT.trim();
    }

    public String getDistType() {
        return distType;
    }

    public void setDistType(String distType) {
        this.distType = distType == null ? null : distType.trim();
    }
}