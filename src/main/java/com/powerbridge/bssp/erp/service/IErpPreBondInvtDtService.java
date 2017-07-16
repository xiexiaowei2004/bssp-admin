package com.powerbridge.bssp.erp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.erp.entity.ErpPreBondInvtDt;

/**
 * <p>
 * 核注清单表体-预处理 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-19
 */
public interface IErpPreBondInvtDtService extends IErpBaseService<ErpPreBondInvtDt> {
    /**
     * 查询企业原始清单商品列表数据
     *
     * @param page   分页
     * @param erpPreBondInvtDt 企业原始清单商品
     * @return List<ErpPreBondInvtBsc>
     */
    Page<ErpPreBondInvtDt> getListData(Page<ErpPreBondInvtDt> page, ErpPreBondInvtDt erpPreBondInvtDt);
    /**
     * 根据账册料件或成品生成清单商品数据
     *
     * @param id 账册料件或成品id（目前只支持单个id）
     * @param mtpckEndprdMarkcd 进出口标志
     * @param etpsInnerInvtNo 清单企业内部编号
     * @return List<ErpPreBondInvtBsc>
     */
    String addPreBondInvtDtByEmsList(String id, String mtpckEndprdMarkcd, String etpsInnerInvtNo);
}
