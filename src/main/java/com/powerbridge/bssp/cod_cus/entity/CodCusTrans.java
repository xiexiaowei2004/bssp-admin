package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_trans")
public class CodCusTrans implements Serializable {
	
	@TableField(exist = false)
	private static final long serialVersionUID = 1244491744998695251L;

	@TableId(value = "TRANS_MODE")
	private String transMode;//代码

	 @TableField(value = "TRANS_SPEC")
    private String transSpec;//成交方式
	 
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
	 
    public String getTransMode() {
        return transMode;
    }

    public void setTransMode(String transMode) {
        this.transMode = transMode == null ? null : transMode.trim();
    }

    public String getTransSpec() {
        return transSpec;
    }

    public void setTransSpec(String transSpec) {
        this.transSpec = transSpec == null ? null : transSpec.trim();
    }
}