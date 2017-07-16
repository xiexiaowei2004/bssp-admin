package com.powerbridge.bssp.cop_et.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cop_et.entity.EtHisAcmpFormDt;

/**
 * 联网企业档案库随附单证历史表明细 服务类
 *
 * @author willChen
 * @since 2017-07-03 10:07:28
 */
public interface IEtHisAcmpFormDtService extends IService<EtHisAcmpFormDt> {

    /**
     * 根据条件查询表数据
     *
     * @param page
     * @param etHisAcmpFormDt
     * @return
     * @throws Exception
     */
    Page<EtHisAcmpFormDt> selectEtHisAcmpFormDtList(Page<EtHisAcmpFormDt> page, EtHisAcmpFormDt etHisAcmpFormDt) throws Exception;

}
