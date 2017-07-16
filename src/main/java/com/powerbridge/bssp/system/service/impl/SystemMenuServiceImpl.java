package com.powerbridge.bssp.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.powerbridge.bssp.system.dao.SystemMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.system.entity.SystemMenu;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.system.service.ISystemMenuService;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：SystemMenuServiceImpl   
* 类描述：SystemMenu 表业务逻辑层接口实现类   
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* 修改人：simon.xie
* 修改时间：2017年4月27日 下午10:12:17
* @version
 */
@Service("systemMenuService")
public class SystemMenuServiceImpl extends BaseServiceImpl<SystemMenuMapper, SystemMenu> implements ISystemMenuService {
	
	@Autowired
	private SystemMenuMapper systemMenuMaper;
	
	@Override
	public List<SystemMenu> selectSystemMenu() {
		List<SystemMenu> systemMenus = new ArrayList<SystemMenu>();
		// 查询一级目录
		List<SystemMenu> parentMenuList = systemMenuMaper.selectSystemMenu(1, 1);
		// 查询二级目录
		List<SystemMenu> childMenuList = systemMenuMaper.selectSystemMenu(1, 2);
		
		// 获取根级权限的子级权限
		for (SystemMenu parentMenu : parentMenuList) {
			recursionMenu(systemMenus, childMenuList, parentMenu);
		}
		return systemMenus;
	}
	@Override
	public List<SystemMenu> selectSystemMenuByRole(List<Long> roleIdList){
		List<SystemMenu> systemMenus = new ArrayList<SystemMenu>();
		// 查询一级目录
		List<SystemMenu> parentMenuList = systemMenuMaper.selectSystemMenuByRole(1, 1,roleIdList);
		// 查询二级目录
		List<SystemMenu> childMenuList = systemMenuMaper.selectSystemMenuByRole(1, 2,roleIdList);
		
		// 获取根级权限的子级权限
		for (SystemMenu parentMenu : parentMenuList) {
			recursionMenu(systemMenus, childMenuList, parentMenu);
		}
		return systemMenus;
	}
	/**
	 * 递归得到每个权限的子级权限
	 * @param systemMenus 处理后的目录列表
	 * @param childMenuList  二级目录列表
	 * @param parentMenu 当前一级目录
	 */
	private void recursionMenu(List<SystemMenu> systemMenus, List<SystemMenu> childMenuList, SystemMenu parentMenu){
		List<SystemMenu> childMenus = new ArrayList<SystemMenu>();
		for(SystemMenu menu : childMenuList){
			if (parentMenu.getId().equals(menu.getParentId())) {
				childMenus.add(menu);
			}
		}
		parentMenu.setChildMenuList(childMenus);
		systemMenus.add(parentMenu);
	}
}