package com.powerbridge.bssp.system.service;

import java.util.List;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.system.entity.SystemUserLoginLog;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：ISystemUserLoginLogService   
* 类描述：SystemUserLoginLog 表业务逻辑层接口   
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* 修改人：simon.xie
* 修改时间：2017年4月27日 下午10:12:17s
* @version
 */
public interface ISystemUserLoginLogService extends IService<SystemUserLoginLog> {
	
	/**
	 * 根据用户ID查询用户登录日志
	 * @param userId 用户ID
	 * @return List<SystemUserLoginLog>
	 */
	List<SystemUserLoginLog> selectUserLoginLog(Long userId);


}