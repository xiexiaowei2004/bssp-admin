package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasFixedCardCus;
import com.powerbridge.bssp.sas.dao.SasFixedCardCusMapper;
import com.powerbridge.bssp.sas.service.ISasFixedCardCusService;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luohongsu
 * @since 2017-07-05
 */
@Service("sasFixedCardCusService")
public class SasFixedCardCusServiceImpl extends BaseServiceImpl<SasFixedCardCusMapper, SasFixedCardCus> implements ISasFixedCardCusService {
    @Autowired
    private SasFixedCardCusMapper sasFixedCardCusMapper;
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page 分页
     * @param sasFixedCardCus
     * @return Page<SasFixedCardCus>
     */
    public Page<SasFixedCardCus> selectByFixedCardCus(Page<SasFixedCardCus> page, SasFixedCardCus sasFixedCardCus){
        page.setRecords(sasFixedCardCusMapper.selectByFixedCardCus(page, sasFixedCardCus));
        return page;
    }
}
