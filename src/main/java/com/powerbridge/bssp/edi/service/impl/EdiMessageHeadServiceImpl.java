package com.powerbridge.bssp.edi.service.impl;

import com.powerbridge.bssp.edi.entity.EdiMessageHead;
import com.powerbridge.bssp.edi.dao.EdiMessageHeadMapper;
import com.powerbridge.bssp.edi.service.IEdiMessageHeadService;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 报文头表 服务实现类
 * </p>
 *
 * @author simon.xie
 * @since 2017-06-01
 */
@Service("ediMessageHeadService")
public class EdiMessageHeadServiceImpl extends BaseServiceImpl<EdiMessageHeadMapper, EdiMessageHead> implements IEdiMessageHeadService {
	@Autowired
	private EdiMessageHeadMapper ediMessageHeadMapper;
	
	@Override
	public EdiMessageHead getEdiMessageHead(String seqNo) {
		return ediMessageHeadMapper.getEdiMessageHead(seqNo);
	}
}
