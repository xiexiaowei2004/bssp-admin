package com.powerbridge.bssp.sas.service;

import com.powerbridge.bssp.sas.entity.SasFixedCardCus;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luohongsu
 * @since 2017-07-05
 */
public interface ISasFixedCardCusService extends IService<SasFixedCardCus> {
    /**
     * 查询长期卡备案信息申请表/分页
     *
     * @param page 分页
     * @param sasFixedCardCus
     * @return Page<SasFixedCardCus>
     */
    Page<SasFixedCardCus> selectByFixedCardCus(Page<SasFixedCardCus> page, SasFixedCardCus sasFixedCardCus);
}
