package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclCusAcmpFormDt;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * 业务申报随附单证正式表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
public interface SasDclCusAcmpFormDtMapper extends BaseMapper<SasDclCusAcmpFormDt> {
    /**
     * 查询/分页
     *
     * @param page  分页
     * @param seqNo 数据
     * @param seqNo 数据
     * @return List<SasDclAcmpFormDt>
     */
    List<SasDclCusAcmpFormDt> selectByList(Page<SasDclCusAcmpFormDt> page, String seqNo);
}