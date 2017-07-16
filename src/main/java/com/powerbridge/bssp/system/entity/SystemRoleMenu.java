package com.powerbridge.bssp.system.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：SystemRoleMenu   
* 类描述：SystemRoleMenu 表实体类   
* 创建人：simon.xie
* 创建时间：2016年11月13日 下午10:41:36   
* 修改人：simon.xie
* 修改时间：2016年11月13日 下午10:41:36   
* @version
 */
@TableName("system_role_menu")
public class SystemRoleMenu implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 角色权限编号 */
	@TableId(value = "ID", type = IdType.AUTO)
	private Long id;

	/** 角色编号 */
	@TableField(value = "ROLE_ID")
	private Long roleId;

	/** 权限编号 */
	@TableField(value = "MENU_ID")
	private Long menuId;
	
	/** 权限标识 */
	@TableField(exist = false)
	private String permission;

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

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}
