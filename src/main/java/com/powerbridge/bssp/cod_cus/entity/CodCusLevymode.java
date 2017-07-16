package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_levymode")
public class CodCusLevymode implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = -6771536297068423768L;

	@TableId(value = "DUTY_MODE")
    private String dutyMode;//代码

	@TableField(value = "DUTY_SPEC")
    private String dutySpec;//征免方式
	
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
	
    public String getDutyMode() {
        return dutyMode;
    }

    public void setDutyMode(String dutyMode) {
        this.dutyMode = dutyMode == null ? null : dutyMode.trim();
    }

    public String getDutySpec() {
        return dutySpec;
    }

    public void setDutySpec(String dutySpec) {
        this.dutySpec = dutySpec == null ? null : dutySpec.trim();
    }
}