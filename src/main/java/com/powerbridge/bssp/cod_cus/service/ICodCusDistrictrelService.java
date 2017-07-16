package com.powerbridge.bssp.cod_cus.service;


import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusDistrictrel;

public interface ICodCusDistrictrelService extends IService<CodCusDistrictrel> {
    List<CodCusDistrictrel> selectRedisDropDown();

}
