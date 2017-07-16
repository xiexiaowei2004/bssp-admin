package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclHisBsc;

import java.util.List;

/**
 * <p>
  * 业务申报历史表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
public interface SasDclHisBscMapper extends BaseMapper<SasDclHisBsc> {
    /**
     * 查询/分页
     *
     * @param page         分页
     * @param sasDclHisBsc  数据
     * @return List<SasDclHisBsc>
     */
    List<SasDclHisBsc> selectByList(Page<SasDclHisBsc> page, SasDclHisBsc sasDclHisBsc);
}