package com.powerbridge.bssp.ems_bws.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsHisDt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * 物流账册正式表明细 Mapper 接口
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
@Repository("emsBwsHisDtMapper")
public interface EmsBwsHisDtMapper extends BaseMapper<EmsBwsHisDt> {
    /**
     * 查询物流账册备案历史表明细/分页
     *
     * @param page 分页
     * @param emsBwsHisDt
     * @return List<EmsBwsHisDt>
     */
    List<EmsBwsHisDt> selectEmsBwsHisDtList(Pagination page, EmsBwsHisDt emsBwsHisDt);
}