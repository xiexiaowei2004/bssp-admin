package com.powerbridge.bssp.inv.service.impl;


import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvHisDclStockMapper;
import com.powerbridge.bssp.inv.entity.InvHisDclStock;
import com.powerbridge.bssp.inv.service.IInvHisDclStockService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 清单随附出入库单明细 服务实现类
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
@Service("invHisDclStockService")
public class InvHisDclStockServiceImpl extends BaseServiceImpl<InvHisDclStockMapper, InvHisDclStock> implements IInvHisDclStockService {
	
}
