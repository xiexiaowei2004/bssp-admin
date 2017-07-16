package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;


@TableName("cod_cus_userto")
public class CodCusUserto implements Serializable {
  
	 @TableField(exist = false)
	private static final long serialVersionUID = -8324842761590594233L;

	 @TableId(value = "USE_TO_COD")
	private String useToCod;//代码

	 @TableField(value = "USE_TO_NAM")
    private String useToNam;//用途
	 
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
	 
    public String getUseToCod() {
        return useToCod;
    }

    public void setUseToCod(String useToCod) {
        this.useToCod = useToCod == null ? null : useToCod.trim();
    }

    public String getUseToNam() {
        return useToNam;
    }

    public void setUseToNam(String useToNam) {
        this.useToNam = useToNam == null ? null : useToNam.trim();
    }
}