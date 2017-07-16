package com.powerbridge.bssp.cod_cus.service;


import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusBusttype;

import java.util.List;

public interface ICodCusBusttypeService extends IService<CodCusBusttype> {
    public List<CodCusBusttype> selectBusttypeByList();
    List<CodCusBusttype> selectRedisDropDown();

}
