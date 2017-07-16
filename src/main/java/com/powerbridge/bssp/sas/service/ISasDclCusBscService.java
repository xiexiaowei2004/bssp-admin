package com.powerbridge.bssp.sas.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasDclCusBsc;

/**
 * <p>
 * 业务申报正式表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
public interface ISasDclCusBscService extends IService<SasDclCusBsc> {
    /**
     * 查询/分页
     *
     * @param page         分页
     * @param sasDclCusBsc 数据
     * @return Page<SasDclCusBsc>
     */
    Page<SasDclCusBsc> selectByList(Page<SasDclCusBsc> page, SasDclCusBsc sasDclCusBsc);

    /**
     * 查询/分页
     *
     * @param page         分页
     * @param SasDclCusBsc 数据
     * @return Page<SasDclBsc>
     */
    Page<SasDclCusBsc> selectBySasDclCusBscList(Page<SasDclCusBsc> page, SasDclCusBsc sasDclCusBsc);
}
