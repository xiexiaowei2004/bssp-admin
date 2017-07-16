package com.powerbridge.bssp.erp.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.erp.entity.ErpPreUcnsDt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("erpPreUcnsDtMapper")
public interface ErpPreUcnsDtMapper extends BaseMapper<ErpPreUcnsDt> {
    /**
     * 查询企业原始单耗
     *
     * @param page   分页
     * @param erpPreUcnsDt 企业原始单耗
     * @return List<ErpPreUcnsDt>
     */
    List<ErpPreUcnsDt> getListData(Page<ErpPreUcnsDt> page, ErpPreUcnsDt erpPreUcnsDt);

    /**
     * 根据id取单耗
     *
     * @param uid
     * @return ErpPreUcnsDt
     */
    ErpPreUcnsDt getDataById(String uid);

    /**
     * 根据组合条件取单耗信息
     * @param map 组合条件
     * @return 商品
     */
    List<ErpPreUcnsDt> getDataByCond(Map<String, Object> map);

    /**
     * 根据组合条件删除料件
     * @param map 组合条件
     */
    void deleteDataByCond(Map<String, Object> map);
}