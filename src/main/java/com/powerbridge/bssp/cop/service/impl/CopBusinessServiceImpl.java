package com.powerbridge.bssp.cop.service.impl;


import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cop.dao.CopBusinessMapper;
import com.powerbridge.bssp.cop.entity.CopBusiness;
import com.powerbridge.bssp.cop.service.ICopBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：CopBusinessServiceImpl
 * 类描述：CopBusiness 表业务逻辑层接口 实现类
 * 创建时间：2017年5月9日 下午10:51:17
 */
@Service("copBusinessService")
public class CopBusinessServiceImpl extends BaseServiceImpl<CopBusinessMapper, CopBusiness> implements ICopBusinessService {

}
