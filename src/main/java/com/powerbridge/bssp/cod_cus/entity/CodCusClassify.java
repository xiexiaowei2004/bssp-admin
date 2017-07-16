package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_classify")
public class CodCusClassify implements Serializable {
	
	@TableField(exist = false)
	private static final long serialVersionUID = -3872818749464525655L;

	@TableId(value = "PK_SEQ")
    private Double pkSeq;//序号
	
	@TableField(value = "G_NAME")
    private String gName;//商品名称
	
	@TableField(value = "KEY_NAME")
    private String keyName;//商品英文名称
	
	@TableField(value = "CLASS_SPEC")
    private String classSpec;//归类描述
	
	@TableField(value = "CODE_T")
    private String codeT;//商品编码
	
	@TableField(value = "CODE_S")
    private String codeS;//扩展编码
	
	@TableField(value = "CLASS_NO")
    private String classNo;//归类号
	
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

    public Double getPkSeq() {
        return pkSeq;
    }

    public void setPkSeq(Double pkSeq) {
        this.pkSeq = pkSeq;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName == null ? null : gName.trim();
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName == null ? null : keyName.trim();
    }

    public String getClassSpec() {
        return classSpec;
    }

    public void setClassSpec(String classSpec) {
        this.classSpec = classSpec == null ? null : classSpec.trim();
    }

    public String getCodeT() {
        return codeT;
    }

    public void setCodeT(String codeT) {
        this.codeT = codeT == null ? null : codeT.trim();
    }

    public String getCodeS() {
        return codeS;
    }

    public void setCodeS(String codeS) {
        this.codeS = codeS == null ? null : codeS.trim();
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo == null ? null : classNo.trim();
    }
}