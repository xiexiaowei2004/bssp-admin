package com.powerbridge.bssp.cod_cus.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusDisttype;

public interface ICodCusDisttypeService extends IService<CodCusDisttype> {
    List<CodCusDisttype> selectRedisDropDown();

}
