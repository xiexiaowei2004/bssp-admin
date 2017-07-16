package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasFixedCardBsc;
import com.powerbridge.bssp.sas.dao.SasFixedCardBscMapper;
import com.powerbridge.bssp.sas.service.ISasFixedCardBscService;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luohongsu
 * @since 2017-07-05
 */
@Service("sasFixedCardBscService")
public class SasFixedCardBscServiceImpl extends BaseServiceImpl<SasFixedCardBscMapper, SasFixedCardBsc> implements ISasFixedCardBscService {
    @Autowired
    private SasFixedCardBscMapper sasFixedCardBscMapper;
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page 分页
     * @param sasFixedCardBsc
     * @return Page<SasFixedCardBsc>
     */
    public Page<SasFixedCardBsc> selectByFixedCardBsc(Page<SasFixedCardBsc> page, SasFixedCardBsc sasFixedCardBsc){
        page.setRecords(sasFixedCardBscMapper.selectByFixedCardBsc(page, sasFixedCardBsc));
        return page;
    }

    @Override
    public SasFixedCardBsc selectByIcCode(String icCode) {
        return sasFixedCardBscMapper.selectByIcCode(icCode);
    }

}
