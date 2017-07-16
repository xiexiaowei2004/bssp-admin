package com.powerbridge.bssp.erp.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.erp.entity.ErpPreBondInvtBsc;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  *  原始清单表头-预处理 Mapper 接口
 * </p>
 *
 * @author lindapeng
 * @since 2017-06-19
 */
@Repository("erpPreBondInvtBscMapper")
public interface ErpPreBondInvtBscMapper extends BaseMapper<ErpPreBondInvtBsc> {
    /**
     * 查询企业原始清单
     *
     * @param page   分页
     * @param erpPreBondInvtBsc 企业原始清单
     * @return List<ErpPreBondInvtBsc>
     */
    List<ErpPreBondInvtBsc> getListData(Page<ErpPreBondInvtBsc> page, ErpPreBondInvtBsc erpPreBondInvtBsc);
}