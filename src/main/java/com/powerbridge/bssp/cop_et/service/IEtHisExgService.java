package com.powerbridge.bssp.cop_et.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cop_et.entity.EtHisExg;

/**
 * 联网企业档案库历史表成品 服务类
 *
 * @author willChen
 * @since 2017-07-03 10:09:45
 */
public interface IEtHisExgService extends IService<EtHisExg> {

    /**
     * 根据条件查询表数据
     *
     * @param page
     * @param etHisExg
     * @return
     * @throws Exception
     */
    Page<EtHisExg> selectEtHisExgList(Page<EtHisExg> page, EtHisExg etHisExg) throws Exception;

}
