package com.powerbridge.bssp.cod_std.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdExchangeRate;

import java.util.List;

public interface CodStdExchangeRateMapper extends BaseMapper<CodStdExchangeRate> {
    List<CodStdExchangeRate> selectRedisDropDown();
    
}
