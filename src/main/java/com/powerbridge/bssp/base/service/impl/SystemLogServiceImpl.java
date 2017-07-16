package com.powerbridge.bssp.base.service.impl;

import com.powerbridge.bssp.base.dao.SystemLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powerbridge.bssp.base.entity.SystemLog;
import com.powerbridge.bssp.base.service.ISystemLogService;
/**
 * 
 * @ClassName SystemLogServiceImpl
 * @Description 用户行为日志
 * @author Simon.xie
 * @Date 2017年5月3日 下午9:47:54
 * @version 1.0.0
 */
@Service("systemLogService")
public class SystemLogServiceImpl extends BaseServiceImpl<SystemLogMapper, SystemLog> implements ISystemLogService {
    @Autowired
    public SystemLogMapper systemLogMapper;
}
