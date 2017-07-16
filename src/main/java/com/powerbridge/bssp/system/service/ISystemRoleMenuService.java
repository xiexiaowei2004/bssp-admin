package com.powerbridge.bssp.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.system.entity.SystemRoleMenu;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：ISystemRoleMenuService   
* 类描述：SystemRoleMenu 表业务逻辑层接口   
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* @version
 */
public interface ISystemRoleMenuService extends IService<SystemRoleMenu> {
	
	/**
	 * 通过角色ID查找权限列表
	 * @param roleIdList 角色ID列表
	 * @return List<SystemRoleMenu> 
	 */
	List<SystemRoleMenu> selectMenuListByRoleId(List<Long> roleIdList);

}