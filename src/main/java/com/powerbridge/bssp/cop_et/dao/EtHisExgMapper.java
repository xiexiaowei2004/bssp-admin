package com.powerbridge.bssp.cop_et.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.powerbridge.bssp.cop_et.entity.EtHisExg;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 联网企业档案库历史表成品 Mapper 接口
 *
 * @author willChen
 * @since 2017-06-01
 */
@Repository("etHisExgMapper")
public interface EtHisExgMapper extends BaseMapper<EtHisExg> {

    List<EtHisExg> selectEtHisExgList(Pagination page, EtHisExg etHisExg);

}