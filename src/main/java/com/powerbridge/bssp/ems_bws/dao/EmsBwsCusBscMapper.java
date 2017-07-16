package com.powerbridge.bssp.ems_bws.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsCusBsc;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("emsBwsCusBscMapper")
public interface EmsBwsCusBscMapper extends BaseMapper<EmsBwsCusBsc> {
    /**
     * 查询加工账册备案申请表/分页
     *
     * @param page 分页
     * @param emsBwsCusBsc
     * @return List<EmsBwsCusBsc>
     */
    List<EmsBwsCusBsc> selectEmsBwsCusBscList(Page<EmsBwsCusBsc> page, EmsBwsCusBsc emsBwsCusBsc);
    /**
     * 查询账册备案正式账册编号
     *
     * @return List<EmsBwsCusBsc>
     */
    List<EmsBwsCusBsc> selectBwsNoList(EmsBwsCusBsc emsBwsCusBsc);
}