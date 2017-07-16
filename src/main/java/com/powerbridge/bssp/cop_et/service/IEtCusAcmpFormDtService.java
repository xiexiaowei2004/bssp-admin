package com.powerbridge.bssp.cop_et.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cop_et.entity.EtCusAcmpFormDt;

/**
 * 联网企业档案库随附单证正式表明细 服务类
 *
 * @author willChen
 * @since 2017-06-01
 */
public interface IEtCusAcmpFormDtService extends IService<EtCusAcmpFormDt> {

    /**
     * 根据条件查询表数据
     *
     * @param page
     * @param etCusAcmpFormDt
     * @return
     * @throws Exception
     */
    Page<EtCusAcmpFormDt> selectEtCusAcmpFormDtList(Page<EtCusAcmpFormDt> page, EtCusAcmpFormDt etCusAcmpFormDt) throws Exception;

}
