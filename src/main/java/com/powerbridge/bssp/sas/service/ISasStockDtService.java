package com.powerbridge.bssp.sas.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasStockDt;

/**
 * <p>
 * 出入库商品表 服务类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
public interface ISasStockDtService extends IService<SasStockDt> {
    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<SasStockDt>
     */
    Page<SasStockDt> selectByList(Page<SasStockDt> page, String seqNo);

    /**
     * 查询商品总数
     *
     * @param sasDclNo  申报表编号
     * @param sasDclSeqno 申报序号
     * @return Integer
     */
    Double selectSum(String sasDclNo,int sasDclSeqno);
}
