package com.powerbridge.bssp.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.system.entity.SystemUserRole;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：ISystemUserRoleService   
* 类描述：SystemUserRole 表业务逻辑层接口   
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* 修改人：simon.xie
* 修改时间：2017年4月27日 下午10:12:17
* @version
 */
public interface ISystemUserRoleService extends IService<SystemUserRole> {

	/**
	 * 通过账号ID查找角色列表
	 * @param accountId 账号Id
	 * @return
	 */
	List<SystemUserRole> selectRoleListByAccountId(Long userId);
}