package com.powerbridge.bssp.erp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.erp.entity.ErpPreDtExg;

import java.math.BigDecimal;

/**
 * <p>
 * 料件预录入表 服务类
 * </p>
 *
 * @author lindapeng
 * @since 2017-06-02
 */
public interface IErpPreDtExgService extends IErpBaseService<ErpPreDtExg> {
    /**
     * 查询企业原始单耗列表数据
     *
     * @param page   分页
     * @param erpPreDtExg 企业原始成品
     * @return List<ErpPreDtExg>
     */
    Page<ErpPreDtExg> getListData(Page<ErpPreDtExg> page, ErpPreDtExg erpPreDtExg);
    /**
     * 获取最大的商品序号
     * @return 商品序号
     */
    BigDecimal getMaxGdsSeqno();

    /**
     * 根据序号取成品信息
     *
     * @return 成品序号
     */
    ErpPreDtExg getDataByGdsSeqno(String gdsSeqno);

    /**
     * 取中间表数据
     *
     *
     */
    String getMidExgData();

    /**
     * 将企业原始料件数据写入账册料件
     *
     *
     */
    String insertEmsExgDataByIds(String ids, String emsId);
}

