package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclCusUcnsDt;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * 业务申报单耗正式表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
public interface SasDclCusUcnsDtMapper extends BaseMapper<SasDclCusUcnsDt> {
    /**
     * 查询/分页
     *
     * @param page      分页
     * @param sasDclCusUcnsDt     参数数据
     * @return Page<SasDclCusUcnsDt>
     */
    List<SasDclCusUcnsDt> selectByList(Page<SasDclCusUcnsDt> page, SasDclCusUcnsDt sasDclCusUcnsDt);
}