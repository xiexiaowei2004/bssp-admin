package com.powerbridge.bssp.cop_et.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.powerbridge.bssp.cop_et.entity.EtHisAcmpFormDt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 联网企业档案库随附单证历史表明细 Mapper 接口
 *
 * @author willChen
 * @since 2017-07-03 10:00:06
 */
@Repository("ethisAcmpFormDtMapper")
public interface EtHisAcmpFormDtMapper extends BaseMapper<EtHisAcmpFormDt> {

    List<EtHisAcmpFormDt> selectEtHisAcmpFormDtList(Pagination page, EtHisAcmpFormDt etHisAcmpFormDt);

}