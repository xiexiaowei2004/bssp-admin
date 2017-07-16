package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclHisAcmpFormDt;

import java.util.List;

/**
 * <p>
  * 业务申报随附单证历史表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
public interface SasDclHisAcmpFormDtMapper extends BaseMapper<SasDclHisAcmpFormDt> {
    /**
     * 查询/分页
     *
     * @param page  分页
     * @param sasDclHisAcmpFormDt 数据
     * @return List<SasDclAcmpFormDt>
     */
    List<SasDclHisAcmpFormDt> selectByList(Page<SasDclHisAcmpFormDt> page, SasDclHisAcmpFormDt sasDclHisAcmpFormDt);
}