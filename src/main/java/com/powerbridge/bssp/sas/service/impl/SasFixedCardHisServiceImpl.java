package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasFixedCardHis;
import com.powerbridge.bssp.sas.dao.SasFixedCardHisMapper;
import com.powerbridge.bssp.sas.service.ISasFixedCardHisService;
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
@Service("sasFixedCardHisService")
public class SasFixedCardHisServiceImpl extends BaseServiceImpl<SasFixedCardHisMapper, SasFixedCardHis> implements ISasFixedCardHisService {
    @Autowired
    private SasFixedCardHisMapper sasFixedCardHisMapper;
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page 分页
     * @param sasFixedCardHis
     * @return Page<sasFixedCardHis>
     */
    public Page<SasFixedCardHis> selectByFixedCardHis(Page<SasFixedCardHis> page, SasFixedCardHis sasFixedCardHis){
        page.setRecords(sasFixedCardHisMapper.selectByFixedCardHis(page, sasFixedCardHis));
        return page;
    }
}
