package com.powerbridge.bssp.sas.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasDclHisDt;

/**
 * <p>
 * 业务申报商品历史表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
public interface ISasDclHisDtService extends IService<SasDclHisDt> {
    /**
     * 查询/分页
     *
     * @param page      分页
     * @param sasDclDt     参数数据
     * @return Page<SasDclHisDt>
     */
    Page<SasDclHisDt> selectByList(Page<SasDclHisDt> page, SasDclHisDt sasDclDt);
}
