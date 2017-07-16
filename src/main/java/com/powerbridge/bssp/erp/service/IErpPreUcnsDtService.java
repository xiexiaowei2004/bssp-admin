package com.powerbridge.bssp.erp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.erp.entity.ErpPreUcnsDt;

/**
 * <p>
 * Bom预录入表 服务类
 * </p>
 *
 * @author lindapeng
 * @since 2017-06-02
 */
public interface IErpPreUcnsDtService extends IErpBaseService<ErpPreUcnsDt> {
    /**
     * 查询企业原始单耗列表数据
     *
     * @param page   分页
     * @param erpPreUcnsDt 企业原始单耗
     * @return List<ErpPreUcnsDt>
     */
    Page<ErpPreUcnsDt> getListData(Page<ErpPreUcnsDt> page, ErpPreUcnsDt erpPreUcnsDt);

    /**
     * 根据id取单耗
     *
     * @param uid
     * @return List<CopEnt>
     */
    ErpPreUcnsDt getDataById(String uid);

    /**
     * 取中间表数据
     *
     *
     */
    String getMidUcnsData();

    /**
     * 将企业原始单耗数据写入账册料件
     *
     *
     */
    String insertEmsUcnsDataByIds(String ids, String emsId);
}