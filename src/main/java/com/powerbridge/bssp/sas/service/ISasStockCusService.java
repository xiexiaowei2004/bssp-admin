package com.powerbridge.bssp.sas.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasStockCus;

/**
 * <p>
 * 出入库单表 服务类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
public interface ISasStockCusService extends IService<SasStockCus> {
    /**
     * 查询出入库单正式表/分页
     *
     * @param page        分页
     * @param sasStockCus
     * @return Page<SasStockCus>
     */
    Page<SasStockCus> selectBySasStockCus(Page<SasStockCus> page, SasStockCus sasStockCus);

}
