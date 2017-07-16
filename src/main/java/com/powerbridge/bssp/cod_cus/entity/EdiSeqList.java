package com.powerbridge.bssp.cod_cus.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("edi_seq_list")
public class EdiSeqList implements Serializable{
	
	@TableField(exist = false)
	private static final long serialVersionUID = 8869570512808261774L;
	
	@TableId(value = "UID")
	private String uid;

	@TableField(value = "PARAM_NO")
    private String paramNo;     //参数编号
	
	@TableField(value = "PARAM_NOTE")
    private String paramNote;    //参数说明
	
	@TableField(value = "STATUS")
    private String status;    //是否开启
	
	@TableField(value = "ORDER_NO")
    private Integer orderNo;   //排序
	
	@TableField(value = "REMARKS")
    private String remarks;   //备注

	@TableField(value = "CREATE_BY")
    private String createBy;
	
	@TableField(value = "CREATE_NAME")
    private String createName;
	
	@TableField(value = "CREATE_TIME")
    private String createTime;
	
	@TableField(value = "UPDATE_BY")
    private String updateBy;
	
	@TableField(value = "UPDATE_NAME")
    private String updateName;
	
	@TableField(value = "UPDATE_TIME")
    private String updateTime;
	
	@TableField(value = "MODEL_NAME")
    private String modelName;   //模块名称
	
	@TableField(value = "DATE_RULE")
    private String dateRule;   //日期规则
	
	@TableField(value = "MASK")
    private String mask;   //项目掩码
	
	@TableField(value = "COUNTER_LENGTH")
    private Integer counterLength;   //计数器位数
	
	@TableField(value = "EDI_SEQ_UID")
    private String ediSeqUid;   //外键(单据编号配置表主键)
	

	
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
	
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getParamNo() {
        return paramNo;
    }

    public void setParamNo(String paramNo) {
        this.paramNo = paramNo == null ? null : paramNo.trim();
    }

    public String getParamNote() {
        return paramNote;
    }

    public void setParamNote(String paramNote) {
        this.paramNote = paramNote == null ? null : paramNote.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    
    
    

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getDateRule() {
		return dateRule;
	}

	public void setDateRule(String dateRule) {
		this.dateRule = dateRule;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public Integer getCounterLength() {
		return counterLength;
	}

	public void setCounterLength(Integer counterLength) {
		this.counterLength = counterLength;
	}

	public String getEdiSeqUid() {
		return ediSeqUid;
	}

	public void setEdiSeqUid(String ediSeqUid) {
		this.ediSeqUid = ediSeqUid;
	}

	
}