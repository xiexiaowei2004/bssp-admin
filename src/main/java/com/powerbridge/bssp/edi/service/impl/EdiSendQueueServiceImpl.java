package com.powerbridge.bssp.edi.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.edi.dao.EdiSendQueueMapper;
import com.powerbridge.bssp.edi.entity.EdiSendQueue;
import com.powerbridge.bssp.edi.service.IEdiSendQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 单据队列表 服务实现类
 * </p>
 *
 * @author simon.xie
 * @since 2017-05-20
 */
@Service("ediSendQueueService")
public class EdiSendQueueServiceImpl extends BaseServiceImpl<EdiSendQueueMapper, EdiSendQueue> implements IEdiSendQueueService {
	
	@Autowired
	private EdiSendQueueMapper ediSendQueueMapper;
	
	@Override
	public int insertCirclationInfoBatch(List<EdiSendQueue> ediSendQueueList) {
		return ediSendQueueMapper.insertCirclationInfoBatch(ediSendQueueList);
	}
	
}
