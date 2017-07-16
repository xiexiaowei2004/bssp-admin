package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_portlin")
public class CodCusPortlin implements Serializable{
	
	 @TableField(exist = false)
	private static final long serialVersionUID = 117788214209183475L;

	 @TableField(value = "PORT_C_COD")
	private String portCCod;//港口中文

	 @TableField(value = "PORT_E_COD")
    private String portECod;//港口英文

	 @TableField(value = "PORT_LINE")
    private String portLine;//港口航线

	 @TableField(value = "PORT_COUNT")
    private String portCount;//

	 @TableField(value = "PORT_CODE")
    private String portCode;//

	 @TableField(value = "QUARANTINE")
    private String quarantine;//

	 @TableId(value = "PK_SEQ")
    private BigDecimal pkSeq;//序号
	 
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
	 
    public String getPortCCod() {
        return portCCod;
    }

    public void setPortCCod(String portCCod) {
        this.portCCod = portCCod == null ? null : portCCod.trim();
    }

    public String getPortECod() {
        return portECod;
    }

    public void setPortECod(String portECod) {
        this.portECod = portECod == null ? null : portECod.trim();
    }

    public String getPortLine() {
        return portLine;
    }

    public void setPortLine(String portLine) {
        this.portLine = portLine == null ? null : portLine.trim();
    }

    public String getPortCount() {
        return portCount;
    }

    public void setPortCount(String portCount) {
        this.portCount = portCount == null ? null : portCount.trim();
    }

    public String getPortCode() {
        return portCode;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode == null ? null : portCode.trim();
    }

    public String getQuarantine() {
        return quarantine;
    }

    public void setQuarantine(String quarantine) {
        this.quarantine = quarantine == null ? null : quarantine.trim();
    }

    public BigDecimal getPkSeq() {
        return pkSeq;
    }

    public void setPkSeq(BigDecimal pkSeq) {
        this.pkSeq = pkSeq;
    }
}