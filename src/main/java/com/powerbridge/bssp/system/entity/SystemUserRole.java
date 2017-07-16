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
* 类名称：SystemUserRole   
* 类描述：SystemUserRole 表实体类 
* 创建人：simon.xie
* 创建时间：2016年11月13日 下午9:48:11   
* 修改人：simon.xie
* 修改时间：2016年11月13日 下午9:48:11   
* @version
 */
@TableName("system_user_role")
public class SystemUserRole implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 用户角色编号 */
	@TableId(value = "ID", type = IdType.AUTO)
	private Long id;

	/** 角色编号 */
	@TableField(value = "ROLE_ID")
	private Long roleId;

	/** 用户编号 */
	@TableField(value = "USER_ID")
	private Long userId;

	/** 创建时间 */
	@TableField(value = "CREATE_TIME")
	private String createTime;

	/** 创建者 */
	@TableField(value = "CREATE_BY")
	private String createBy;
	
	/** 角色名称 */
	@TableField(value = "ROLE_NAME")
	private String roleName;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
