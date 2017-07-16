package com.powerbridge.bssp.inv.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.inv.entity.InvCusDt;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface IInvCusDtService extends IService<InvCusDt> {
    Page<InvCusDt> selectByInvCusDt(Page<InvCusDt>page, InvCusDt invCusDt);
}
