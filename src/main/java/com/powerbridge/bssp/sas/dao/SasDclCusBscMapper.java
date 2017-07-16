package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclCusBsc;

import java.util.List;

/**
 * <p>
  * 业务申报正式表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
public interface SasDclCusBscMapper extends BaseMapper<SasDclCusBsc> {
    /**
     * 查询/分页
     *
     * @param page         分页
     * @param sasDclCusBsc  数据
     * @return List<SasDclCusBsc>
     */
    List<SasDclCusBsc> selectByList(Page<SasDclCusBsc> page, SasDclCusBsc sasDclCusBsc);

    List<SasDclCusBsc> selectBySasDclCusBscList(Page<SasDclCusBsc> page, SasDclCusBsc sasDclCusBsc);
}