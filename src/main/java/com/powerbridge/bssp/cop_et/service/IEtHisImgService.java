package com.powerbridge.bssp.cop_et.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cop_et.entity.EtHisImg;

/**
 * 联网企业档案库历史表料件 服务类
 *
 * @author willChen
 * @since 2017-07-03 10:09:50
 */
public interface IEtHisImgService extends IService<EtHisImg> {

    /**
     * 根据条件查询表数据
     *
     * @param page
     * @param etHisImg
     * @return
     * @throws Exception
     */
    Page<EtHisImg> selectEtHisImgList(Page<EtHisImg> page, EtHisImg etHisImg) throws Exception;
}
