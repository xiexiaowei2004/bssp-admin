package com.powerbridge.bssp.cod_std.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_std.dao.CodStdExchangeRateMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdExchangeRate;
import com.powerbridge.bssp.cod_std.service.ICodStdExchangeRateService;

import java.util.List;

@Service("codStdExchangeRateService")
public class CodStdExchangeRateServiceImpl extends BaseServiceImpl<CodStdExchangeRateMapper, CodStdExchangeRate> implements ICodStdExchangeRateService {


    @Autowired
    private CodStdExchangeRateMapper codStdExchangeRateMapper;

    public List<CodStdExchangeRate> selectRedisDropDown() {
        return codStdExchangeRateMapper.selectRedisDropDown();
    }
}
