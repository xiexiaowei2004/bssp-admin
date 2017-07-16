package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_trade")
public class CodCusTrade implements Serializable {
	
    
	 @TableField(exist = false)
	private static final long serialVersionUID = -2210675346925477988L;

	 @TableId(value = "TRADE_MODE")
	private String tradeMode;//代码

	 @TableField(value = "ABBR_TRADE")
    private String abbrTrade;//监管方式

	 @TableField(value = "FULL_TRADE")
    private String fullTrade;//监管方式全称
	 
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

    public String getAbbrTrade() {
        return abbrTrade;
    }

    public void setAbbrTrade(String abbrTrade) {
        this.abbrTrade = abbrTrade == null ? null : abbrTrade.trim();
    }

    public String getFullTrade() {
        return fullTrade;
    }

    public void setFullTrade(String fullTrade) {
        this.fullTrade = fullTrade == null ? null : fullTrade.trim();
    }
}