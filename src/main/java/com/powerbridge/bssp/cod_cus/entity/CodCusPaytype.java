package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_paytype")
public class CodCusPaytype implements Serializable {
    
		@TableField(exist = false)
	private static final long serialVersionUID = 7410725301803319153L;

		@TableId(value = "PAY_TYPE_CO")
	private String payTypeCo;//代码
	
		
		 @TableField(value = "PAY_TYPE_NA")
    private String payTypeNa;//支付方式名称
	
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
    public String getPayTypeCo() {
        return payTypeCo;
    }

    public void setPayTypeCo(String payTypeCo) {
        this.payTypeCo = payTypeCo == null ? null : payTypeCo.trim();
    }

    public String getPayTypeNa() {
        return payTypeNa;
    }

    public void setPayTypeNa(String payTypeNa) {
        this.payTypeNa = payTypeNa == null ? null : payTypeNa.trim();
    }
}