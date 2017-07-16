package com.powerbridge.bssp.inv.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.inv.entity.InvHisDt;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface IInvHisDtService extends IService<InvHisDt> {
    Page<InvHisDt> selectByInvHisDt(Page<InvHisDt>page, InvHisDt invHisDt);
}
