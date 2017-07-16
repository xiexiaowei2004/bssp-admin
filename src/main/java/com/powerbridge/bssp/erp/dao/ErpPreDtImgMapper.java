package com.powerbridge.bssp.erp.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.erp.entity.ErpPreDtImg;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository("erpPreDtImgMapper")
public interface ErpPreDtImgMapper extends BaseMapper<ErpPreDtImg> {
    /**
     * 获取最大的商品序号
     *
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
     * 根据组合条件取料件信息
     * @param map 组合条件
     * @return 商品
     */
    List<ErpPreDtImg> getDataByCond(Map<String, Object> map);

    /**
     * 根据组合条件删除料件
     * @param map 组合条件
     */
    void deleteDataByCond(Map<String, Object> map);

    /**
     * 查询企业原始单耗
     *
     * @param page   分页
     * @param erpPreDtImg 企业原始料件
     * @return List<ErpPreDtImg>
     */
    List<ErpPreDtImg> getListData(Page<ErpPreDtImg> page, ErpPreDtImg erpPreDtImg);
}