package com.powerbridge.bssp.sas.service;

import com.powerbridge.bssp.sas.entity.SasFixedCardHis;
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
public interface ISasFixedCardHisService extends IService<SasFixedCardHis> {
    /**
     * 查询长期卡备案信息申请表/分页
     *
     * @param page 分页
     * @param sasFixedCardHis
     * @return Page<SasFixedCardHis>
     */
    Page<SasFixedCardHis> selectByFixedCardHis(Page<SasFixedCardHis> page, SasFixedCardHis sasFixedCardHis);
}
