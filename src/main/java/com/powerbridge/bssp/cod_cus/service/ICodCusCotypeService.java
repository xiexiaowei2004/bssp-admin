package com.powerbridge.bssp.cod_cus.service;


import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusCotype;

import java.util.List;

public interface ICodCusCotypeService extends IService<CodCusCotype> {
    public List<CodCusCotype> selectCotypeByList();
    List<CodCusCotype> selectRedisDropDown();

}
