package com.powerbridge.bssp.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.system.entity.SystemRole;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：ISystemRoleService   
* 类描述：SystemRole 表业务逻辑层接口   
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* 修改人：simon.xie
* 修改时间：2017年4月27日 下午10:12:17
* @version
 */
public interface ISystemRoleService extends IService<SystemRole> {
	
	/**
	 * 查询角色列表
	 * @return List<SystemRole>
	 */
	List<SystemRole> selectRoleList();
	
	/**
	 * 查询角色列表及数量
	 * @return List<SystemRole>
	 */
	List<SystemRole> selectRoleAndNumber();

}