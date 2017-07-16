package com.powerbridge.bssp.cop_et.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cop_et.entity.EtArcrpAcmpFormDt;

/**
 * 联网企业档案库随附单证明细 服务类
 *
 * @author willChen
 * @since 2017-05-22
 */
public interface IEtArcrpAcmpFormDtService extends IService<EtArcrpAcmpFormDt> {

    /**
     * 根据条件查询表数据
     *
     * @param page
     * @param etArcrpAcmpFormDt
     * @return
     * @throws Exception
     */
    Page<EtArcrpAcmpFormDt> selectEtArcrpAcmpFormDtList(Page<EtArcrpAcmpFormDt> page, EtArcrpAcmpFormDt etArcrpAcmpFormDt) throws Exception;

}
