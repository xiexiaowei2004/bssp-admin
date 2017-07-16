package com.powerbridge.bssp.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powerbridge.bssp.system.dao.SystemRoleMenuMapper;
import com.powerbridge.bssp.system.entity.SystemRoleMenu;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.system.service.ISystemRoleMenuService;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：SystemRoleMenuServiceImpl   
* 类描述：SystemRoleMenu 表业务逻辑层接口实现类   
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午11:40:57
* 修改人：simon.xie
* 修改时间：2017年4月27日 下午11:40:57
* @version
 */
@Service("systemRoleMenuService")
public class SystemRoleMenuServiceImpl extends BaseServiceImpl<SystemRoleMenuMapper, SystemRoleMenu> implements ISystemRoleMenuService {
	
	@Autowired
	private SystemRoleMenuMapper systemRoleMenuMapper;
	
	@Override
	public List<SystemRoleMenu> selectMenuListByRoleId(List<Long> roleIdList) {
		return systemRoleMenuMapper.selectMenuListByRoleId(roleIdList);
	}


}