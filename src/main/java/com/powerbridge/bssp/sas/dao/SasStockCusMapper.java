package com.powerbridge.bssp.sas.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasStockCus;

import java.util.List;

/**
 * <p>
 * 出入库单表 Mapper 接口
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
public interface SasStockCusMapper extends BaseMapper<SasStockCus> {
    /**
     * 出入库单/分页
     *
     * @param page        分页
     * @param sasStockCus
     * @return List<SasStockCus>
     */
    List<SasStockCus> selectBySasStockCus(Page<SasStockCus> page, SasStockCus sasStockCus);
}