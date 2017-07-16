package com.powerbridge.bssp.cod_cus.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusLevytype;

public interface ICodCusLevytypeService extends IService<CodCusLevytype> {
    List<CodCusLevytype> selectRedisDropDown();

}
