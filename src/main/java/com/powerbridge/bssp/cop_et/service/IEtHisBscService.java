package com.powerbridge.bssp.cop_et.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cop_et.entity.EtHisBsc;

/**
 * 联网企业档案库历史表 服务类
 *
 * @author willChen
 * @since 2017-07-03 10:07:34
 */
public interface IEtHisBscService extends IService<EtHisBsc> {
    /**
     * 根据条件查询表数据
     *
     * @param page
     * @param etHisBsc
     * @return
     * @throws Exception
     */
    Page<EtHisBsc> selectEtHisBscList(Page<EtHisBsc> page, EtHisBsc etHisBsc) throws Exception;
}
