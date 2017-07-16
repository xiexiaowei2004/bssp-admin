package com.powerbridge.bssp.inv.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.inv.entity.InvAcmpFormDt;

/**
 * <p>
 * 保税核注清单随附单证明细表 服务类
 * </p>
 *
 * @author zsl
 * @since 2017-06-20
 */
public interface IInvAcmpFormDtService extends IService<InvAcmpFormDt> {

    Page<InvAcmpFormDt> selectByInvAcmpList(Page<InvAcmpFormDt>page,String seqNo);
	
}
