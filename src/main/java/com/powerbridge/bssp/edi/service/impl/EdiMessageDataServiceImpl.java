package com.powerbridge.bssp.edi.service.impl;

import com.powerbridge.bssp.edi.entity.EdiMessageData;
import com.powerbridge.bssp.edi.dao.EdiMessageDataMapper;
import com.powerbridge.bssp.edi.service.IEdiMessageDataService;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 报文数据 服务实现类
 * </p>
 *
 * @author simon.xie
 * @since 2017-06-01
 */
@Service("ediMessageDataService")
public class EdiMessageDataServiceImpl extends BaseServiceImpl<EdiMessageDataMapper, EdiMessageData> implements IEdiMessageDataService {
	
}
