package com.powerbridge.bssp.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.system.entity.SystemUserRole;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：SystemUserRoleMapper   
* 类描述：SystemUserRole 表数据访问层接口   
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* @version
 */
public interface SystemUserRoleMapper  extends BaseMapper<SystemUserRole> {
	
	/**
	 * 通过账号ID查找角色列表
	 * @param accountId 账号Id
	 * @return List<SystemUserRole> 
	 */
	List<SystemUserRole> selectRoleListByAccountId(@Param("userId") Long userId);
	
	/**
	 * 插入用户角色记录 
	 * @param systemUserRoles 角色列表
	 */
	void insertUserRoles(@Param("systemUserRoles") List<SystemUserRole> systemUserRoles);
}