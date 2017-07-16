package com.powerbridge.bssp.cod_cus.service;


import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusComplex;

public interface ICodCusComplexService extends IService<CodCusComplex> {
    List<CodCusComplex> selectRedisDropDown();
    /**
     * 查询表表/分页
     *
     * @param page 分页
     * @param emsCusBsc
     * @return List<EmsCusBsc>
     */
    Page<CodCusComplex> selectComplexList(Page<CodCusComplex> page, CodCusComplex emsCusBsc);
    /**
     * 查询商品编码是否存在
     * @param gdecd
     * @return
     */
    String selectCountByGdecd(String gdecd);
}
