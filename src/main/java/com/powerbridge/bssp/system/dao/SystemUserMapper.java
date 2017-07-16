package com.powerbridge.bssp.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.system.entity.QueryUser;
import com.powerbridge.bssp.system.entity.SystemUser;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：SystemUserMapper   
* 类描述：SystemUser 表数据访问层接口   
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* 修改人：simon.xie
* 修改时间：2017年4月27日 下午10:12:17
* @version
 */
public interface SystemUserMapper  extends BaseMapper<SystemUser> {
	
	/**
	 * 根据查找条件查找所有管理员
	 * @param queryUser 查询用户实体类 
	 * @return List<SystemUser> 
	 */
	List<SystemUser> selectAllSystemUser(QueryUser queryUser);
	
	/**
	 * 根据角色ID查找管理员
	 * @param roleId 角色ID
	 * @return List<SystemUser>
	 */
	List<SystemUser> selectSysUserByRoleId(@Param("roleId") Long roleId);
	
	/**
	 * 更新用户信息
	 * @param systemUser 用户信息
	 */
	void updateUserInfo(SystemUser systemUser);
	
}