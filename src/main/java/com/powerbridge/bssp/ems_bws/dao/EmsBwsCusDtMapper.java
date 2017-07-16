package com.powerbridge.bssp.ems_bws.dao;


import com.powerbridge.bssp.ems_bws.entity.EmsBwsCusDt;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * <p>
  * 物流账册正式表明细 Mapper 接口
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
@Repository("emsBwsCusDtMapper")
public interface EmsBwsCusDtMapper extends BaseMapper<EmsBwsCusDt> {

    List<EmsBwsCusDt> selectEmsBwsCusDtList(Pagination page, EmsBwsCusDt emsBwsCusDt);

    /**
     * 获取最大的料件序号
     * @param etArcrpNo 档案库编号
     * @return
     */
   // Integer getMaxGdsSeqno(String etArcrpNo);
}