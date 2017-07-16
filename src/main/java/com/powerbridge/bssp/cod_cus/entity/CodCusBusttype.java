package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_busttype")
public class CodCusBusttype implements Serializable {
	
	@TableField(exist = false)
	private static final long serialVersionUID = -4016871245620953812L;

	@TableId(value = "TYPE_CO")
    private String typeCo;//代码

	@TableField(value = "TYPE_NA")
    private String typeNa;//企业类型
	
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

    public String getTypeCo() {
        return typeCo;
    }

    public void setTypeCo(String typeCo) {
        this.typeCo = typeCo == null ? null : typeCo.trim();
    }

    public String getTypeNa() {
        return typeNa;
    }

    public void setTypeNa(String typeNa) {
        this.typeNa = typeNa == null ? null : typeNa.trim();
    }
}