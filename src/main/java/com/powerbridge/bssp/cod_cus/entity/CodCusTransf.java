package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_transf")
public class CodCusTransf implements Serializable {
   
	 @TableField(exist = false)
	private static final long serialVersionUID = -5142433549019911400L;

	 @TableId(value = "TRAF_CODE")
	private String trafCode;//代码

	 @TableField(value = "TRAF_SPEC")
    private String trafSpec;//运输方式
	 
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
	 
    public String getTrafCode() {
        return trafCode;
    }

    public void setTrafCode(String trafCode) {
        this.trafCode = trafCode == null ? null : trafCode.trim();
    }

    public String getTrafSpec() {
        return trafSpec;
    }

    public void setTrafSpec(String trafSpec) {
        this.trafSpec = trafSpec == null ? null : trafSpec.trim();
    }
}