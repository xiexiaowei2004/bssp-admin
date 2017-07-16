package com.powerbridge.bssp.cop_et.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.powerbridge.bssp.cop_et.entity.EtArcrpAcmpFormDt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 联网企业档案库随附单证明细 Mapper 接口
 *
 * @author willChen
 * @since 2017-05-22
 */
@Repository("etArcrpAcmpFormDtMapper")
public interface EtArcrpAcmpFormDtMapper extends BaseMapper<EtArcrpAcmpFormDt> {

    List<EtArcrpAcmpFormDt> selectEtArcrpAcmpFormDtList(Pagination page, EtArcrpAcmpFormDt etArcrpAcmpFormDt);

}