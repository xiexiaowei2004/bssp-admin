package com.powerbridge.bssp.sas.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasDclHisAcmpFormDt;

/**
 * <p>
 * 业务申报随附单证历史表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
public interface ISasDclHisAcmpFormDtService extends IService<SasDclHisAcmpFormDt> {
    /**
     * 查询/分页
     *
     * @param page      分页
     * @param sasDclHisAcmpFormDt     参数数据
     * @return Page<SasDclHisAcmpFormDt>
     */
    Page<SasDclHisAcmpFormDt> selectByList(Page<SasDclHisAcmpFormDt> page, SasDclHisAcmpFormDt sasDclHisAcmpFormDt);
}
