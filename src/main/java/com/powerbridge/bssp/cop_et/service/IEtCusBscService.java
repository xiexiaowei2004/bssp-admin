package com.powerbridge.bssp.cop_et.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cop_et.entity.EtCusBsc;

/**
 * 联网企业档案库正式表 服务类
 *
 * @author willChen
 * @since 2017-06-01
 */
public interface IEtCusBscService extends IService<EtCusBsc> {
    /**
     * 根据条件查询表数据
     *
     * @param page
     * @param etCusBsc
     * @return
     * @throws Exception
     */
    Page<EtCusBsc> selectEtCusBscList(Page<EtCusBsc> page, EtCusBsc etCusBsc) throws Exception;
}
