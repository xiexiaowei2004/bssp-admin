package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclUcnsDt;

import java.util.List;

/**
 * <p>
  * 业务申报单耗表 Mapper 接口
 * </p>
 *
 * @author jokylao
 * @since 2017-07-13
 */
public interface SasDclUcnsDtMapper extends BaseMapper<SasDclUcnsDt> {
    /**
     * 查询/分页
     *
     * @param page      分页
     * @param sasDclUcnsDt     参数数据
     * @return Page<SasDclDt>
     */
    List<SasDclUcnsDt> selectByList(Page<SasDclUcnsDt> page, SasDclUcnsDt sasDclUcnsDt);
}