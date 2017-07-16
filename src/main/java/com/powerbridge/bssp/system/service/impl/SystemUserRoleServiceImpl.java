package com.powerbridge.bssp.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powerbridge.bssp.system.dao.SystemUserRoleMapper;
import com.powerbridge.bssp.system.entity.SystemUserRole;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.system.service.ISystemUserRoleService;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：SystemUserRoleServiceImpl   
* 类描述：SystemUserRole 表业务逻辑层接口 实现类
 * 创建时间：2017年4月27日 下午10:12:17
 * 修改人：simon.xie
 * 修改时间：2017年4月27日 下午10:12:17
* @version
 */
@Service("systemUserRoleService")
public class SystemUserRoleServiceImpl extends BaseServiceImpl<SystemUserRoleMapper, SystemUserRole> implements ISystemUserRoleService {
	
	@Autowired
	private SystemUserRoleMapper systemUserRoleMapper;
	
	@Override
	public List<SystemUserRole> selectRoleListByAccountId(Long userId) {
		return systemUserRoleMapper.selectRoleListByAccountId(userId);
	}


}