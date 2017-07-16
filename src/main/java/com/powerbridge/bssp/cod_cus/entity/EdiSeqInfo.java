package com.powerbridge.bssp.cod_cus.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("edi_seq_info")
public class EdiSeqInfo implements Serializable{
	
	@TableField(exist = false)
	private static final long serialVersionUID = 2236388790480296329L;
	
	@TableId(value = "UID")
	private String uid;
	
	@TableField(value = "AREA_CODE")
    private String areaCode;   //场所代码
	
	@TableField(value = "DOC_TYPE")
    private String docType;    //单据类型
	
	@TableField(value = "MODEL_NAME")
    private String modelName;   //模块名称
	
	@TableField(value = "DATE_RULE")
    private String dateRule;   //日期规则
	
	@TableField(value = "MASK")
    private String mask;   //项目掩码
	
	@TableField(value = "COUNTER_LENGTH")
    private Integer counterLength;   //计数器位数
	
	@TableField(value = "SERVER_TYPE")
    private String serverType;   //服务类型
	
	@TableField(value = "STATUS")
    private String status;    //是否开启
	
	@TableField(value = "TRANS_MODE")
    private String transMode;   //传输方式
	
	@TableField(value = "REMARKS")
    private String remarks;   //备注
	
	@TableField(value = "CREATE_BY")
    private String createBy;   //创建人代码
	
	@TableField(value = "CREATE_NAME")
    private String createName;   //创建人名称
	
	@TableField(value = "CREATE_TIME")
    private String createTime;   //创建时间
	
	@TableField(value = "UPDATE_BY")
    private String updateBy;  //修改人代码
	
	@TableField(value = "UPDATE_NAME")
    private String updateName;   //修改人名称
	
	@TableField(value = "UPDATE_TIME")
    private String updateTime;   //修改时间
	
	 @TableField(exist = false)
	    private List<EdiSeqList> ediSeqList;
	
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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType == null ? null : docType.trim();
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public String getDateRule() {
        return dateRule;
    }

    public void setDateRule(String dateRule) {
        this.dateRule = dateRule == null ? null : dateRule.trim();
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask == null ? null : mask.trim();
    }

    public Integer getCounterLength() {
        return counterLength;
    }

    public void setCounterLength(Integer counterLength) {
        this.counterLength = counterLength;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType == null ? null : serverType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTransMode() {
        return transMode;
    }

    public void setTransMode(String transMode) {
        this.transMode = transMode == null ? null : transMode.trim();
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

	public List<EdiSeqList> getEdiSeqList() {
		return ediSeqList;
	}

	public void setEdiSeqList(List<EdiSeqList> ediSeqList) {
		this.ediSeqList = ediSeqList;
	}


    
}