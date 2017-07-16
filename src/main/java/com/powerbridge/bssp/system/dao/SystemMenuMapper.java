package com.powerbridge.bssp.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.system.entity.SystemMenu;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：SystemMenuMapper   
* 类描述：SystemMenu 表数据访问层接口  
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* 修改人：simon.xie
* 修改时间：2017年4月27日 下午10:12:17
* @version
 */
public interface SystemMenuMapper  extends BaseMapper<SystemMenu> {
	
	/**
	 * 查询系统目录
	 * @param status 状态
	 * @param menuType 权限类型
	 * @return List<SystemMenu>
	 */
	List<SystemMenu> selectSystemMenu(@Param("status") Integer status, @Param("menuType") Integer menuType);
	
	/**
	 * 根据角色查询系统目录
	 * @param status 状态
	 * @param menuType 权限类型
	 * @return List<SystemMenu>
	 */
	List<SystemMenu> selectSystemMenuByRole(@Param("status") Integer status, @Param("menuType") Integer menuType,@Param("roleIdList") List<Long> roleIdList);

}