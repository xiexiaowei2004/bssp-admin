package com.powerbridge.bssp.sas.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasDclCusBsc;
import com.powerbridge.bssp.sas.entity.SasDclHisBsc;

/**
 * <p>
 * 业务申报历史表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
public interface ISasDclHisBscService extends IService<SasDclHisBsc> {
    /**
     * 查询/分页
     *
     * @param page         分页
     * @param sasDclHisBsc 数据
     * @return Page<SasDclHisBsc>
     */
    Page<SasDclHisBsc> selectByList(Page<SasDclHisBsc> page, SasDclHisBsc sasDclHisBsc);
}
