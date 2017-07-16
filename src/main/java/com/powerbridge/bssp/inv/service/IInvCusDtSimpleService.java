package com.powerbridge.bssp.inv.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.inv.entity.InvCusDtSimple;

/**
 * <p>
 * 简单加工成品明细 服务类
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface IInvCusDtSimpleService extends IService<InvCusDtSimple> {
    Page<InvCusDtSimple> selectByInvCusDtSimple(Page<InvCusDtSimple>page, InvCusDtSimple invCusDtSimple);
}
