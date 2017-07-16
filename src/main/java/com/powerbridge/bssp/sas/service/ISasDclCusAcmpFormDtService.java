package com.powerbridge.bssp.sas.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasDclCusAcmpFormDt;

/**
 * <p>
 * 业务申报随附单证正式表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
public interface ISasDclCusAcmpFormDtService extends IService<SasDclCusAcmpFormDt> {
    /**
     * 查询/分页
     *
     * @param page      分页
     * @param seqNo     参数数据
     * @return Page<SasDclCusAcmpFormDt>
     */
    Page<SasDclCusAcmpFormDt> selectByList(Page<SasDclCusAcmpFormDt> page, String seqNo);
}
