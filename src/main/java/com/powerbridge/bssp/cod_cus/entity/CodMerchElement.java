package com.powerbridge.bssp.cod_cus.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("cod_merch_element")
public class CodMerchElement implements Serializable{
	
	@TableField(exist = false)
	private static final long serialVersionUID = -2424406813828569699L;
	
	@TableField(value="CODE_TS")
	private String codeTs;//商品编码
	
	@TableField(value="S_NUM")
	private String sNum;//要素序号
	
	@TableField(value="ELEMENT")
	private String element;//申报要素
	
	@TableField(value="ELEMENT_NULL")
	private String elementNull;//要素启用标识
	
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
	
	public String getCodeTs() {
		return codeTs;
	}

	public void setCodeTs(String codeTs) {
		this.codeTs = codeTs;
	}

	public String getsNum() {
		return sNum;
	}

	public void setsNum(String sNum) {
		this.sNum = sNum;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getElementNull() {
		return elementNull;
	}

	public void setElementNull(String elementNull) {
		this.elementNull = elementNull;
	}


}
  
    