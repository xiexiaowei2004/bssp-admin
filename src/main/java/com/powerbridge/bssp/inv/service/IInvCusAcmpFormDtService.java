package com.powerbridge.bssp.inv.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.inv.entity.InvCusAcmpFormDt;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface IInvCusAcmpFormDtService extends IService<InvCusAcmpFormDt> {
    Page<InvCusAcmpFormDt> selectByInvCusAcmpList(Page<InvCusAcmpFormDt>page, String seqNo);
}
