package com.powerbridge.bssp.inv.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.inv.entity.InvDtSimple;

/**
 * <p>
 * 简单加工成品明细 服务类
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface IInvDtSimpleService extends IService<InvDtSimple> {
    Page<InvDtSimple> selectByInvDtSimple(Page<InvDtSimple>page, InvDtSimple invDtSimple);
}
