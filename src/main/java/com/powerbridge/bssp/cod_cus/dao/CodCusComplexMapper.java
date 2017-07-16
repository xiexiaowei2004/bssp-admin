package com.powerbridge.bssp.cod_cus.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.cod_cus.entity.CodCusComplex;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("codCusComplexMapper")
public interface CodCusComplexMapper extends BaseMapper<CodCusComplex> {
    List<CodCusComplex> selectRedisDropDown();

    /**
     * 查询表表/分页
     *
     * @param page          分页
     * @param codCusComplex
     * @return List<EmsCusBsc>
     */
    List<CodCusComplex> selectComplexList(Page<CodCusComplex> page, CodCusComplex codCusComplex);
    /**
     * 查询商品编码是否存在
     * @param gdecd
     * @return
     */
    String selectCountByGdecd(String gdecd);
}