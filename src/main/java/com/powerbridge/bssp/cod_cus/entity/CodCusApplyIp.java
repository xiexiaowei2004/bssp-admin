package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("cod_cus_apply_ip")
public class CodCusApplyIp implements Serializable {
	@TableField(exist = false)
	private static final long serialVersionUID = -3027379017513719120L;
	
	@TableId(value = "APPLY_ID")
	private String applyId;  //应用id
	
	@TableField(value = "APPLY_IP")
	private String applyIp;  //应用ip
	
	@TableField(value = "CREATE_TIME")
	private String createTime;   //创建时间
	
	@TableField(value = "CREATE_BY")
    private String createBy;   //创建人
	
	@TableField(value = "UPDATE_TIME")
    private String updateTime;   //修改时间
	
	@TableField(value = "UPDATE_BY")
    private String updateBy;   //修改人
	
	@TableField(value = "STATE")
    private Integer state;    //状态：1.正常；0.冻结
	
	@TableField(value = "REMARKS")
    private String remarks;  //备注信息
	
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
	
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getApplyIp() {
		return applyIp;
	}

	public void setApplyIp(String applyIp) {
		this.applyIp = applyIp;
	}

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}