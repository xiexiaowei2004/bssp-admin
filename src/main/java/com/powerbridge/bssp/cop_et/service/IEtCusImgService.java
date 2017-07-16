package com.powerbridge.bssp.cop_et.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cop_et.entity.EtCusImg;

/**
 * 联网企业档案库正式表料件 服务类
 *
 * @author willChen
 * @since 2017-06-01
 */
public interface IEtCusImgService extends IService<EtCusImg> {

    /**
     * 根据条件查询表数据
     *
     * @param page
     * @param etCusImg
     * @return
     * @throws Exception
     */
    Page<EtCusImg> selectEtCusImgList(Page<EtCusImg> page, EtCusImg etCusImg) throws Exception;
}
