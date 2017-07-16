package com.powerbridge.bssp.edi.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.edi.dao.EdiMessageLogMapper;
import com.powerbridge.bssp.edi.entity.EdiMessageLog;
import com.powerbridge.bssp.edi.service.IEdiMessageLogService;
import org.springframework.stereotype.Service;

/**
 * Created by 宋轲 on 2017/6/1.
 */
@Service("ediMessageLogService")
public class EdiMessageLogServiceImpl extends BaseServiceImpl<EdiMessageLogMapper,EdiMessageLog> implements IEdiMessageLogService {
}
