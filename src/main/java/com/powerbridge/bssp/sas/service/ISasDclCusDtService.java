package com.powerbridge.bssp.sas.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasDclCusDt;

/**
 * <p>
 * 业务申报商品正式表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
public interface ISasDclCusDtService extends IService<SasDclCusDt> {
    /**
     * 查询/分页
     *
     * @param page      分页
     * @param sasDclDt     参数数据
     * @return Page<SasDclDt>
     */
    Page<SasDclCusDt> selectByList(Page<SasDclCusDt> page, SasDclCusDt sasDclDt);
}
