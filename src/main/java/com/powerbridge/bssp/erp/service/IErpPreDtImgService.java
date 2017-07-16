package com.powerbridge.bssp.erp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.erp.entity.ErpPreDtImg;

import java.math.BigDecimal;

/**
 * <p>
 * 成品预录入表 服务类
 * </p>
 *
 * @author lindapeng
 * @since 2017-06-02
 */
public interface IErpPreDtImgService extends IErpBaseService<ErpPreDtImg> {
    /**
     * 查询企业原始单耗列表数据
     *
     * @param page   分页
     * @param erpPreDtImg 企业原始料件
     * @return List<ErpPreDtImg>
     */
    Page<ErpPreDtImg> getListData(Page<ErpPreDtImg> page, ErpPreDtImg erpPreDtImg);
    /**
     * 获取最大的商品序号
     * @return 商品序号
     */
    BigDecimal getMaxGdsSeqno();

    /**
     * 根据序号取料件信息
     *
     * @return 料件序号
     */
    ErpPreDtImg getDataByGdsSeqno(String gdsSeqno);

    /**
     * 取中间表数据
     *
     *
     */
    String getMidImgData ();

    /**
     * 将企业原始料件数据写入账册料件
     *
     *
     */
    String insertEmsImgDataByIds(String ids, String emsId);
}