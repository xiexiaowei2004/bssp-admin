package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclHisDt;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * 业务申报商品历史表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
public interface SasDclHisDtMapper extends BaseMapper<SasDclHisDt> {
    /**
     * 查询/分页
     *
     * @param page  分页
     * @param sasDclHisDt 数据
     * @return List<SasDclHisDt>
     */
    List<SasDclHisDt> selectByList(Page<SasDclHisDt> page, SasDclHisDt sasDclHisDt);
}