package com.powerbridge.bssp.sas.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasDragcarCus;

/**
 * <p>
 * 扡卡正式表 服务类
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-05
 */
public interface ISasDragcarCusService extends IService<SasDragcarCus> {
    /**
     * 查询/分页
     *
     * @param page         分页
     * @param sasDragcarCus 数据
     * @return Page<sasDragcarCus>
     */
    Page<SasDragcarCus> selectSasDragcarCusList(Page<SasDragcarCus> page, SasDragcarCus sasDragcarCus);
}
