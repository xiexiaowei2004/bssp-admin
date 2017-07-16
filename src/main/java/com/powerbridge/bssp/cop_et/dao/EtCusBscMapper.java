package com.powerbridge.bssp.cop_et.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.powerbridge.bssp.cop_et.entity.EtCusBsc;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 联网企业档案库正式表 Mapper 接口
 * @author ${author}
 * @since 2017-06-01
 */
@Repository("etCusBscMapper")
public interface EtCusBscMapper extends BaseMapper<EtCusBsc> {

    List<EtCusBsc> selectEtCusBscList(Pagination page, EtCusBsc etCusBsc);
}