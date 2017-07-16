package com.powerbridge.bssp.cop_et.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.powerbridge.bssp.cop_et.entity.EtCusExg;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 联网企业档案库正式表成品 Mapper 接口
 *
 * @author willChen
 * @since 2017-06-01
 */
@Repository("etCusExgMapper")
public interface EtCusExgMapper extends BaseMapper<EtCusExg> {

    List<EtCusExg> selectEtCusExgList(Pagination page, EtCusExg etCusExg);

    /**
     * 获取最大的成品序号
     *
     * @param seqNo 统一编号
     * @return
     */
    Integer getMaxGdsSeqno(String seqNo);
}