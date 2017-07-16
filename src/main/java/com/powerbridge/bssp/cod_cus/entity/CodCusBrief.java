package com.powerbridge.bssp.cod_cus.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("cod_cus_brief")
public class CodCusBrief implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(value = "REG_CO_CGAC")
    private String regCoCgac; // 企业海关代码

    @TableField(value = "ENT_NAME")
    private String entName; // 企业名称

    @TableField(value = "CHK_ANNUAL")
    private String chkAnnual; //是否年检
    
    
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

    public String getRegCoCgac() {
        return regCoCgac;
    }

    public void setRegCoCgac(String regCoCgac) {
        this.regCoCgac = regCoCgac == null ? null : regCoCgac.trim();
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName == null ? null : entName.trim();
    }

    public String getChkAnnual() {
        return chkAnnual;
    }

    public void setChkAnnual(String chkAnnual) {
        this.chkAnnual = chkAnnual == null ? null : chkAnnual.trim();
    }
}