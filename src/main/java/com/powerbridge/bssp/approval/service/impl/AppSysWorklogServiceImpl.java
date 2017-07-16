package com.powerbridge.bssp.approval.service.impl;

import com.powerbridge.bssp.approval.dao.AppSysWorklogMapper;
import com.powerbridge.bssp.approval.entity.AppSysWorklog;
import com.powerbridge.bssp.approval.service.IAppSysWorklogService;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：IAppSysWorklogService
 * 类描述：AppSysWorklog 表业务逻辑层接口
 * 创建人：haihuihuang
 * 创建时间：2017年5月9日 下午10:12:17
 */
@Service("appSysWorklogService")
public class AppSysWorklogServiceImpl extends BaseServiceImpl<AppSysWorklogMapper, AppSysWorklog> implements IAppSysWorklogService {

}
