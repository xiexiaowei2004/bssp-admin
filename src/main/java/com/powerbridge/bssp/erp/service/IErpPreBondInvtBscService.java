package com.powerbridge.bssp.erp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.util.json.JSONException;
import com.powerbridge.bssp.erp.entity.ErpPreBondInvtBsc;

import java.util.List;

/**
 * <p>
 * 核注清单表头-预处理 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-19
 */
public interface IErpPreBondInvtBscService extends IErpBaseService<ErpPreBondInvtBsc> {
    /**
     * 查询企业原始清单列表数据
     *
     * @param page   分页
     * @param erpPreBondInvtBsc 企业原始清单
     * @return List<ErpPreBondInvtBsc>
     */
    Page<ErpPreBondInvtBsc> getListData(Page<ErpPreBondInvtBsc> page, ErpPreBondInvtBsc erpPreBondInvtBsc);

    /**
     * 删除企业原始清单
     *
     * @param  id
     * @return Boolean
     */
    Boolean deletePreBondInvt(String  id);

    /**
     * 从清单中间表取数据插入企业原始清单
     *
     * @return String
     */
    String getMidData();

    /**
     * 生成核注清单
     *
     * @return String
     */
    String genBondInvt(String id);
}
