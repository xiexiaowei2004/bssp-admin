package com.powerbridge.bssp.system.service.impl;

import java.util.List;

import com.powerbridge.bssp.system.dao.SystemUserLoginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powerbridge.bssp.system.entity.SystemUserLoginLog;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.system.service.ISystemUserLoginLogService;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：SystemUserLoginLogServiceImpl   
* 类描述：SystemUserLoginLog 表业务逻辑层接口实现类   
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午11:40:57
* 修改人：simon.xie
* 修改时间：2017年4月27日 下午11:40:57
* @version
 */
@Service("systemUserLoginLogService")
public class SystemUserLoginLogServiceImpl extends BaseServiceImpl<SystemUserLoginLogMapper, SystemUserLoginLog> implements ISystemUserLoginLogService {
	
	@Autowired
	private SystemUserLoginLogMapper systemUserLoginLogMapper;
	
	@Override
	public List<SystemUserLoginLog> selectUserLoginLog(Long userId) {
		return systemUserLoginLogMapper.selectUserLoginLog(userId);
	}


}