package com.powerbridge.bssp.cod_cus.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusCountry;
 
public interface ICodCusCountryService extends IService<CodCusCountry> {
    List<CodCusCountry> selectRedisDropDown();

}
