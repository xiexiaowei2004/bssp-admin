package com.powerbridge.bssp.inv.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.inv.entity.InvHisDecDt;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface IInvHisDecDtService extends IService<InvHisDecDt> {
    Page<InvHisDecDt> selectByHisDeclare(Page<InvHisDecDt> page, InvHisDecDt invHisDecDt);
}
