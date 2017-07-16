package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_cotype")
public class CodCusCotype implements Serializable {
	
	@TableField(exist = false)
	private static final long serialVersionUID = -6133386096275392390L;

	@TableId(value = "CO_OWNER")
    private String coOwner;//代码
	
	@TableField(value = "CO_OWNERSH")
    private String coOwnersh;//企业性质
	
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

    public String getCoOwner() {
        return coOwner;
    }

    public void setCoOwner(String coOwner) {
        this.coOwner = coOwner == null ? null : coOwner.trim();
    }

    public String getCoOwnersh() {
        return coOwnersh;
    }

    public void setCoOwnersh(String coOwnersh) {
        this.coOwnersh = coOwnersh == null ? null : coOwnersh.trim();
    }
}