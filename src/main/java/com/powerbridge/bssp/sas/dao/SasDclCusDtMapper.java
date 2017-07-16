package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclCusDt;

import java.util.List;

/**
 * <p>
  * 业务申报商品正式表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
public interface SasDclCusDtMapper extends BaseMapper<SasDclCusDt> {
    /**
     * 查询/分页
     *
     * @param page  分页
     * @param sasDclCusDt 数据
     * @return List<SasDclCusDt>
     */
    List<SasDclCusDt> selectByList(Page<SasDclCusDt> page, SasDclCusDt sasDclCusDt);
}