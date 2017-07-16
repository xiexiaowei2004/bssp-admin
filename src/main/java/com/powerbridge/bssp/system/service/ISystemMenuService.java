package com.powerbridge.bssp.system.service;

import java.util.List;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.system.entity.SystemMenu;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：ISystemMenuService   
* 类描述：SystemMenu 表业务逻辑层接口   
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* 修改人：simon.xie
* 修改时间：2017年4月27日 下午10:12:17
* @version
 */
public interface ISystemMenuService extends IService<SystemMenu> {

	/**
	 * 查询系统目录,网站目录列表
	 * @return List<SystemMenu>
	 */
	List<SystemMenu> selectSystemMenu();
	/**根据角色获取系统目录
	 * @return List<SystemMenu>
	 */
	List<SystemMenu> selectSystemMenuByRole(List<Long> roleIdList);

}