package com.powerbridge.bssp.inv.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.inv.entity.InvHisBsc;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface IInvHisBscService extends IService<InvHisBsc> {
    Page<InvHisBsc> selectByInvHisBsc(Page<InvHisBsc> page, InvHisBsc invHisBsc);
}
