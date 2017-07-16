package com.powerbridge.bssp.cod_std.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
@TableName("cod_std_bus_type")

public class CodStdBusType  implements Serializable{
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	@TableId(value = "UID")
    private String uid;//UID

    @TableField(value = "CODE")
	private String code; // 编码
	
	@TableField(value = "NAME")
	private String name; // 名称
	
	@TableField(value = "IS_ENABLE")
	private String enable; // 是否启动
	
	@TableField(value = "CREATE_BY")
	private String createBy; // 创建人
	
	@TableField(value = "CREATE_TIME")
	private String createTime; // 创建时间
	
	@TableField(value = "UPDATE_BY")
	private String updateBy; // 修改人
	
	@TableField(value = "UPDATE_TIME")
	private String updateTime; // 修改时间
	
	@TableField(exist = false)
	private String id ;

	@TableField(exist = false)
	private String text ;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

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
        this.uid = uid;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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
		this.updateBy = updateBy;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	
	
	
	
	
	
	
}
