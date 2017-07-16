package com.powerbridge.bssp.sas.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasStockHis;

/**
 * <p>
 * 出入库单历史记录表 服务类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
public interface ISasStockHisService extends IService<SasStockHis> {
    /**
     * 查询出入库单历史记录/分页
     *
     * @param page        分页
     * @param sasStockHis
     * @return Page<SasStockHis>
     */
    Page<SasStockHis> selectBySasStockHis(Page<SasStockHis> page, SasStockHis sasStockHis);

}
