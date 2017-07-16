package com.powerbridge.bssp.cop_et.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.powerbridge.bssp.cop_et.entity.EtHisBsc;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 联网企业档案库历史表 Mapper 接口
 *
 * @author willChen
 * @since 2017-07-03 10:02:46
 */
@Repository("etHisBscMapper")
public interface EtHisBscMapper extends BaseMapper<EtHisBsc> {

    List<EtHisBsc> selectEtHisBscList(Pagination page, EtHisBsc etHisBsc);

}