package com.powerbridge.bssp.sas.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasStockDt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 出入库商品表 Mapper 接口
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
public interface SasStockDtMapper extends BaseMapper<SasStockDt> {
    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return List<SasDclDt>
     */
    List<SasStockDt> selectByList(Page<SasStockDt> page, String seqNo);
    /**
     * 查询商品总数
     *
     * @param sasDclNo  申报表编号
     * @param sasDclSeqno 申报序号
     * @return Integer
     */
    Double selectSum(@Param("sasDclNo")String sasDclNo, @Param("sasDclSeqno") int sasDclSeqno);
}