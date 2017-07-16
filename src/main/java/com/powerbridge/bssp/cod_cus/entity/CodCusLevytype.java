package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_levytype")
public class CodCusLevytype implements Serializable {
	
	@TableField(exist = false)
	private static final long serialVersionUID = 3722698444397116483L;

	@TableId(value = "CUT_MODE")
	private String cutMode;//代码

	@TableField(value = "ABBR_CUT")
    private String abbrCut;//征免性质简称

	@TableField(value = "FULL_CUT")
    private String fullCut;//征免性质全称
	
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
	
    public String getCutMode() {
        return cutMode;
    }

    public void setCutMode(String cutMode) {
        this.cutMode = cutMode == null ? null : cutMode.trim();
    }

    public String getAbbrCut() {
        return abbrCut;
    }

    public void setAbbrCut(String abbrCut) {
        this.abbrCut = abbrCut == null ? null : abbrCut.trim();
    }

    public String getFullCut() {
        return fullCut;
    }

    public void setFullCut(String fullCut) {
        this.fullCut = fullCut == null ? null : fullCut.trim();
    }
}