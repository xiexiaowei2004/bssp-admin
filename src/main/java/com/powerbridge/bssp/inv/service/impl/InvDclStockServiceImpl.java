package com.powerbridge.bssp.inv.service.impl;


import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvDclStockMapper;
import com.powerbridge.bssp.inv.entity.InvDclStock;
import com.powerbridge.bssp.inv.service.IInvDclStockService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 清单随附出入库单明细 服务实现类
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
@Service("invDclStockService")
public class InvDclStockServiceImpl extends BaseServiceImpl<InvDclStockMapper, InvDclStock> implements IInvDclStockService {
	
}
