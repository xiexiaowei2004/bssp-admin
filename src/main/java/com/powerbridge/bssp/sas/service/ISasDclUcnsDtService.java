package com.powerbridge.bssp.sas.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasDclUcnsDt;

/**
 * <p>
 * 业务申报单耗表 服务类
 * </p>
 *
 * @author jokylao
 * @since 2017-07-13
 */
public interface ISasDclUcnsDtService extends IService<SasDclUcnsDt> {
    /**
     * 查询/分页
     *
     * @param page      分页
     * @param sasDclUcnsDt     参数数据
     * @return Page<SasDclDt>
     */
    Page<SasDclUcnsDt> selectByList(Page<SasDclUcnsDt> page, SasDclUcnsDt sasDclUcnsDt);
}
