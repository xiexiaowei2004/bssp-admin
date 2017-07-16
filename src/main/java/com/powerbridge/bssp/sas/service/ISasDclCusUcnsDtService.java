package com.powerbridge.bssp.sas.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasDclCusUcnsDt;

/**
 * <p>
 * 业务申报单耗正式表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
public interface ISasDclCusUcnsDtService extends IService<SasDclCusUcnsDt> {
    /**
     * 查询/分页
     *
     * @param page      分页
     * @param sasDclCusUcnsDt     参数数据
     * @return Page<SasDclCusUcnsDt>
     */
    Page<SasDclCusUcnsDt> selectByList(Page<SasDclCusUcnsDt> page, SasDclCusUcnsDt sasDclCusUcnsDt);
}
