package com.powerbridge.bssp.edi.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.edi.dao.EdiSendLogMapper;
import com.powerbridge.bssp.edi.entity.EdiSendLog;
import com.powerbridge.bssp.edi.service.IEdiSendLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 单据队列日志表 服务实现类
 * </p>
 *
 * @author simon.xie
 * @since 2017-05-20
 */
@Service("ediSendLogService")
public class EdiSendLogServiceImpl extends BaseServiceImpl<EdiSendLogMapper, EdiSendLog> implements IEdiSendLogService {

}
