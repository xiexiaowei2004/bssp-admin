package com.powerbridge.bssp.cop_et.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.powerbridge.bssp.cop_et.entity.EtArcrpBsc;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 联网企业档案库基本表 Mapper 接口
 * @author ${author}
 * @since 2017-05-22
 */
@Repository("etArcrpBscMapper")
public interface EtArcrpBscMapper extends BaseMapper<EtArcrpBsc> {

    List<EtArcrpBsc> selectEtArcrpBscList(Pagination page, EtArcrpBsc etArcrpBsc);
}