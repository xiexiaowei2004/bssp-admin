package com.powerbridge.bssp.erp.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.erp.entity.ErpPreDtExg;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository("erpPreDtExgMapper")
public interface ErpPreDtExgMapper extends BaseMapper<ErpPreDtExg> {
    /**
     * 查询企业原始单耗
     *
     * @param page   分页
     * @param erpPreDtExg 企业原始成品
     * @return List<ErpPreDtExg>
     */
    List<ErpPreDtExg> getListData(Page<ErpPreDtExg> page, ErpPreDtExg erpPreDtExg);

    /**
     * 获取最大的商品序号
     *
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
     * 根据组合条件取成品信息
     * @param map 组合条件
     * @return 商品
     */
    List<ErpPreDtExg> getDataByCond(Map<String, Object> map);

    /**
     * 根据组合条件删除成品
     * @param map 组合条件
     */
    void deleteDataByCond(Map<String, Object> map);
}