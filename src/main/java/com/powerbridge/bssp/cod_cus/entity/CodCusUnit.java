package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_unit")
public class CodCusUnit implements Serializable {
    
	  @TableField(exist = false)
	private static final long serialVersionUID = 7124868637950223349L;

	  @TableField(value = "CONV_CODE")
	private String convCode;//对应统计计量单位代码

	  @TableField(value = "CONV_RATIO")
    private String convRatio;//计量单位

	  @TableId(value = "UNIT_CODE")
    private String unitCode;//计量单位代码

	  @TableField(value = "UNIT_NAME")
    private String unitName;//换算率
	  
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
	  
    public String getConvCode() {
        return convCode;
    }

    public void setConvCode(String convCode) {
        this.convCode = convCode == null ? null : convCode.trim();
    }

    public String getConvRatio() {
        return convRatio;
    }

    public void setConvRatio(String convRatio) {
        this.convRatio = convRatio == null ? null : convRatio.trim();
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode == null ? null : unitCode.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }
}