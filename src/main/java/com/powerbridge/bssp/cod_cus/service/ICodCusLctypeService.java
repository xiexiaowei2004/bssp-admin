package com.powerbridge.bssp.cod_cus.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusLctype;

public interface ICodCusLctypeService extends IService<CodCusLctype> {
    List<CodCusLctype> selectRedisDropDown();

}
