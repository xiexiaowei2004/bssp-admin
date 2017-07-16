package com.powerbridge.bssp.ems_bws.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsCusBsc;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsHisBsc;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("emsBwsHisBscMapper")
public interface EmsBwsHisBscMapper extends BaseMapper<EmsBwsHisBsc> {
    /**
     * 查询物流账册备案历史表/分页
     *
     * @param page 分页
     * @param emsBwsHisBsc
     * @return List<EmsBwsCusBsc>
     */
    List<EmsBwsHisBsc> selectEmsBwsHisBscList(Page<EmsBwsHisBsc> page, EmsBwsHisBsc emsBwsHisBsc);
}