package com.powerbridge.bssp.sas.service;

import com.powerbridge.bssp.sas.entity.SasFixedCardBsc;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luohongsu
 * @since 2017-07-05
 */
public interface ISasFixedCardBscService extends IService<SasFixedCardBsc> {
    /**
     * 查询长期卡备案信息申请表/分页
     *
     * @param page 分页
     * @param sasFixedCardBsc
     * @return Page<SasFixedCardBsc>
     */
    Page<SasFixedCardBsc> selectByFixedCardBsc(Page<SasFixedCardBsc> page, SasFixedCardBsc sasFixedCardBsc);

    /**
     * 查询icCode
     * @return Page<SasFixedCardBsc>
     */
    SasFixedCardBsc selectByIcCode(String icCode);
}
