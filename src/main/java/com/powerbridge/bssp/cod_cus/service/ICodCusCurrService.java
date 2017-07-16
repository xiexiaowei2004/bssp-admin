package com.powerbridge.bssp.cod_cus.service;


import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusCurr;

public interface ICodCusCurrService extends IService<CodCusCurr> {
    List<CodCusCurr> selectRedisDropDown();

}
