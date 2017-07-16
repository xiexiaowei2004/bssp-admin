package com.powerbridge.bssp.sas.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasStockHis;

import java.util.List;

/**
 * <p>
 * 出入库单表 Mapper 接口
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
public interface SasStockHisMapper extends BaseMapper<SasStockHis> {
    /**
     * 出入库单/分页
     *
     * @param page        分页
     * @param sasStockHis
     * @return List<SasStockHis>
     */
    List<SasStockHis> selectBySasStockHis(Page<SasStockHis> page, SasStockHis sasStockHis);
}