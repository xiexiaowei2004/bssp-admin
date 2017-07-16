package com.powerbridge.bssp.sas.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasStockHisDt;

/**
 * <p>
 * 出入库商品历史表 服务类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
public interface ISasStockHisDtService extends IService<SasStockHisDt> {
    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<SasStockHisDt>
     */
    Page<SasStockHisDt> selectByList(Page<SasStockHisDt> page, String seqNo);

}
