package com.powerbridge.bssp.cod_std.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.sql.Timestamp;

@TableName("cod_std_sprocess_type")
public class CodStdSprocesstype implements Serializable {
	
	@TableField(exist = false)
	private static final long serialVersionUID = -1244491744978695251L;

	@TableId(value = "UID")
    private String uid;//代码

    @TableField(value = "CODE")
    private String code;//加工方式代码

    @TableField(value = "NAME")
    private String name;//加工方式名称

    @TableField(value = "IS_ENABLE")
    private String isEnable;//是否启用

    @TableField(value = "CREATE_BY")
    private String createBy;//创建人

    @TableField(value = "CREATE_TIME")
    private Timestamp createTime;//创建时间

    @TableField(value = "UPDATE_BY")
    private String updateBy;//修改人

    @TableField(value = "UPDATE_TIME")
    private Timestamp updateTime;//修改时间

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

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}