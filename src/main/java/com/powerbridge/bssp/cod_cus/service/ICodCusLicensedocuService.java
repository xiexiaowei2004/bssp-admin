package com.powerbridge.bssp.cod_cus.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusLicensedocu;


public interface ICodCusLicensedocuService extends IService<CodCusLicensedocu> {
    List<CodCusLicensedocu> selectRedisDropDown();

}
