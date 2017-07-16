package com.powerbridge.bssp.sas_cmb.service;

import com.powerbridge.bssp.sas_cmb.entity.SasCmbImg;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 耗料单料件表 服务类
 * </p>
 *
 * @author huanliu
 * @since 2017-06-18
 */
public interface ISasCmbImgService extends IService<SasCmbImg> {
    /**
     * 查询耗料单的边角料/分页
     *
     * @param page 分页
     * @param sasCmbImg
     * @return List<sasCmbImg>
     */
    Page<SasCmbImg> selectSasCmbImgList(Page<SasCmbImg> page, SasCmbImg sasCmbImg);


}
