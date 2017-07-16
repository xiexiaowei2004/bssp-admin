package com.powerbridge.bssp.erp.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.erp.entity.ErpPreBondInvtDt;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
  * 核注清单表体-预处理 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-19
 */
@Repository("erpPreBondInvtDtMapper")
public interface ErpPreBondInvtDtMapper extends BaseMapper<ErpPreBondInvtDt> {
    /**
     * 查询企业原始清单商品
     *
     * @param page   分页
     * @param erpPreBondInvtDt 企业原始清单商品
     * @return List<ErpPreBondInvtDt>
     */
    List<ErpPreBondInvtDt> getListData(Page<ErpPreBondInvtDt> page, ErpPreBondInvtDt erpPreBondInvtDt);

    /**
     * 获取最大的商品序号
     *@param etpsInnerInvtNo   清单企业内部编号
     * @return 商品序号
     */
    BigDecimal getMaxListGNo(String etpsInnerInvtNo);
}