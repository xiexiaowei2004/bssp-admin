package com.powerbridge.bssp.inv.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.inv.entity.InvCusBsc;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface IInvCusBscService extends IService<InvCusBsc> {
    /**
     * 查询核注清单信息
     *
     * @param page 分页
     * @param InvCusBsc
     * @return Page<InvBsc>
     */
    Page<InvCusBsc> selectByInvCusBsc(Page<InvCusBsc> page, InvCusBsc invCusBsc);
}
