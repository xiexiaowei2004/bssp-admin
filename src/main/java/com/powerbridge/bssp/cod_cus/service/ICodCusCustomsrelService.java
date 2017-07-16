package com.powerbridge.bssp.cod_cus.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusCustomsrel;


public interface ICodCusCustomsrelService extends IService<CodCusCustomsrel> {
    List<CodCusCustomsrel> selectRedisDropDown();

}
