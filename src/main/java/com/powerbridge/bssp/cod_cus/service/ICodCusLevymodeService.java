package com.powerbridge.bssp.cod_cus.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusLevymode;

public interface ICodCusLevymodeService extends IService<CodCusLevymode> {
    List<CodCusLevymode> selectRedisDropDown();

}
