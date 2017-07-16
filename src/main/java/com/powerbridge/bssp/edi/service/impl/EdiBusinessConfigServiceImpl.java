package com.powerbridge.bssp.edi.service.impl;

import com.powerbridge.bssp.edi.entity.EdiBusinessConfig;
import com.powerbridge.bssp.edi.dao.EdiBusinessConfigMapper;
import com.powerbridge.bssp.edi.service.IEdiBusinessConfigService;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 单据配置表 服务实现类
 * </p>
 *
 * @author simon.xie
 * @since 2017-06-10
 */
@Service("ediBusinessConfigService")
public class EdiBusinessConfigServiceImpl extends BaseServiceImpl<EdiBusinessConfigMapper, EdiBusinessConfig> implements IEdiBusinessConfigService {
	
}
