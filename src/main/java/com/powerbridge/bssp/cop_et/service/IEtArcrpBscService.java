package com.powerbridge.bssp.cop_et.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cop_et.entity.EtArcrpBsc;

/**
 * 联网企业档案库基本表 服务类
 *
 * @author willChen
 * @since 2017-05-22
 */
public interface IEtArcrpBscService extends IService<EtArcrpBsc> {

    /**
     * 根据id删除表数据和子表数据
     *
     * @param etArcrpBsc
     * @return
     */
    Boolean deleteEtArcrpBscById(EtArcrpBsc etArcrpBsc) throws Exception;

    /**
     * 根据条件查询表数据
     *
     * @param page
     * @param etArcrpBsc
     * @return
     * @throws Exception
     */
    Page<EtArcrpBsc> selectEtArcrpBscList(Page<EtArcrpBsc> page, EtArcrpBsc etArcrpBsc) throws Exception;
}
