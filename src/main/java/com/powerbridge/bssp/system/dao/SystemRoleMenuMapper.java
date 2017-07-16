package com.powerbridge.bssp.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.system.entity.SystemRoleMenu;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：SystemRoleMenuMapper   
* 类描述：SystemRoleMenu 表数据访问层接口   
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* 修改人：simon.xie
* 修改时间：2017年4月27日 下午10:12:17
* @version
 */
public interface SystemRoleMenuMapper  extends BaseMapper<SystemRoleMenu> {
	

	/**
	 * 通过角色ID查找权限列表
	 * @param roleIdList 角色ID列表
	 * @return List<SystemRoleMenu> 
	 */
	List<SystemRoleMenu> selectMenuListByRoleId(@Param("roleIdList") List<Long> roleIdList);

}