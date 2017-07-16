package com.powerbridge.bssp.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.system.entity.SystemUserLoginLog;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：SystemUserLoginLogMapper   
* 类描述：SystemUserLoginLog 表数据访问层接口   
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* 修改人：simon.xie
* 修改时间：2017年4月27日 下午10:12:17
* @version
 */
public interface SystemUserLoginLogMapper  extends BaseMapper<SystemUserLoginLog> {

	/**
	 * 根据用户ID查找用户日志
	 * @param userId 用户ID
	 * @return List<SystemUserLoginLog> 
	 */
	List<SystemUserLoginLog> selectUserLoginLog(@Param("userId") Long userId);

}