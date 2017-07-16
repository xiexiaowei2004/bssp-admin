package com.powerbridge.bssp.sas_cmb.service;

import com.powerbridge.bssp.sas_cmb.entity.SasCmbBsc;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 耗料单基本信息 服务类
 * </p>
 *
 * @author huanliu
 * @since 2017-06-18
 */
public interface ISasCmbBscService extends IService<SasCmbBsc> {
    /**
     * 查询耗料单/分页
     *
     * @param page 分页
     * @param sasCmbBsc
     * @return Page<SasCmbBsc>
     */
    Page<SasCmbBsc> selectSasCmbBscList(Page<SasCmbBsc> page, SasCmbBsc sasCmbBsc);

}
