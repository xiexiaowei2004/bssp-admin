package com.powerbridge.bssp.cod_std.service;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_std.entity.CodStdExchangeRate;

import java.util.List;

public interface ICodStdExchangeRateService extends IService<CodStdExchangeRate> {
    List<CodStdExchangeRate> selectRedisDropDown();
}
