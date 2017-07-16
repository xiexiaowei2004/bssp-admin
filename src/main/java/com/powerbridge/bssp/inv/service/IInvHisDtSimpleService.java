package com.powerbridge.bssp.inv.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.inv.entity.InvHisDtSimple;

/**
 * <p>
 * 简单加工成品明细 服务类
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface IInvHisDtSimpleService extends IService<InvHisDtSimple> {
    Page<InvHisDtSimple> selectByInvHisDtSimple(Page<InvHisDtSimple>page, InvHisDtSimple invHisDtSimple);
}
