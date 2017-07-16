package com.powerbridge.bssp.system.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：SystemRole   
* 类描述：SystemRole 表实体类   
* 创建人：simon.xie
* 创建时间：2016年11月13日 下午9:52:18    
* @version
 */
@TableName("system_role")
public class SystemRole implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 角色编号 */
	@TableId(value = "ID", type = IdType.AUTO)
	private Long id;

	/** 角色名称 */
	@TableField(value = "ROLE_NAME")
	private String roleName;

	/** 系统数据：1.是，只有超级管理员能修改；0.否，拥有角色修改人员的权限能都修改 */
	@TableField(value = "IS_SYSTEM")
	private Integer isSystem;

	/** 状态：1.正常；0.冻结 */
	@TableField(value = "STATUS")
	private Integer status;

	/** 创建时间 */
	@TableField(value = "CREATE_TIME")
	private String createTime;

	/** 创建者 */
	@TableField(value = "CREATE_BY")
	private String createBy;

	/** 更新时间 */
	@TableField(value = "UPDATE_TIME")
	private String updateTime;

	/** 更新者 */
	@TableField(value = "UPDATE_BY")
	private String updateBy;

	/** 备注信息 */
	@TableField(value = "REMARKS")
	private String remarks;

	/** 角色类型 */
	@TableField(value = "ROLE_TYPE")
	private Integer roleType;

	/** 企业编号 */
	@TableField(value = "TRADE_CODE")
	private String tradeCode;
	
    /**角色人数*/
	@TableField(exist = false)
    private Integer number;

    public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getIsSystem() {
		return this.isSystem;
	}

	public void setIsSystem(Integer isSystem) {
		this.isSystem = isSystem;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}


	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
}
