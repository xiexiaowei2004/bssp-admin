package com.powerbridge.bssp.cop_et.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.powerbridge.bssp.cop_et.entity.EtCusAcmpFormDt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 联网企业档案库随附单证正式表明细 Mapper 接口
 * @author ${author}
 * @since 2017-06-01
 */
@Repository("etCusAcmpFormDtMapper")
public interface EtCusAcmpFormDtMapper extends BaseMapper<EtCusAcmpFormDt> {

    List<EtCusAcmpFormDt> selectEtCusAcmpFormDtList(Pagination page, EtCusAcmpFormDt etCusAcmpFormDt);

}