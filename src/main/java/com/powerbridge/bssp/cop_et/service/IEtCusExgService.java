package com.powerbridge.bssp.cop_et.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cop_et.entity.EtCusExg;

/**
 * 联网企业档案库正式表成品 服务类
 *
 * @author willChen
 * @since 2017-06-01
 */
public interface IEtCusExgService extends IService<EtCusExg> {

    /**
     * 根据条件查询表数据
     *
     * @param page
     * @param etCusExg
     * @return
     * @throws Exception
     */
    Page<EtCusExg> selectEtCusExgList(Page<EtCusExg> page, EtCusExg etCusExg) throws Exception;

}
