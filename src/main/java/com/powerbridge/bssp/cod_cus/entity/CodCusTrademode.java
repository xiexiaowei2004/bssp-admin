package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_trademode")
public class CodCusTrademode implements Serializable {
	
     @TableField(exist = false)
    private static final long serialVersionUID = 3167698600842221275L;

     @TableField(value = "TRADE_MODE")
	private String tradeMode;//代码

     @TableField(value = "DISTRICT_T")
    private String districtT;//国内地区性质标记

     @TableField(value = "BASIC_IM")
    private String basicIm;//进口适用范围证件

     @TableField(value = "BASIC_EX")
    private String basicEx;//出口适用范围证件

     @TableField(value = "IM_CONTROL")
    private String imControl;//进口特殊监管证件

     @TableField(value = "EX_CONTROL")
    private String exControl;//出口特殊监管证件

     @TableId(value = "PK_SEQ")
    private String pkSeq;//序号
     
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
     
    public String getTradeMode() {
        return tradeMode;
    }

    public void setTradeMode(String tradeMode) {
        this.tradeMode = tradeMode == null ? null : tradeMode.trim();
    }

    public String getDistrictT() {
        return districtT;
    }

    public void setDistrictT(String districtT) {
        this.districtT = districtT == null ? null : districtT.trim();
    }

    public String getBasicIm() {
        return basicIm;
    }

    public void setBasicIm(String basicIm) {
        this.basicIm = basicIm == null ? null : basicIm.trim();
    }

    public String getBasicEx() {
        return basicEx;
    }

    public void setBasicEx(String basicEx) {
        this.basicEx = basicEx == null ? null : basicEx.trim();
    }

    public String getImControl() {
        return imControl;
    }

    public void setImControl(String imControl) {
        this.imControl = imControl == null ? null : imControl.trim();
    }

    public String getExControl() {
        return exControl;
    }

    public void setExControl(String exControl) {
        this.exControl = exControl == null ? null : exControl.trim();
    }

    public String getPkSeq() {
        return pkSeq;
    }

    public void setPkSeq(String pkSeq) {
        this.pkSeq = pkSeq == null ? null : pkSeq.trim();
    }
}