package com.powerbridge.bssp.sas_cmb.service;

import com.powerbridge.bssp.sas_cmb.entity.SasCmbInvt;
import com.powerbridge.bssp.sas_cmb.entity.SasCmbRbg;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 耗料单清单列表 服务类
 * </p>
 *
 * @author huanliu
 * @since 2017-06-18
 */
public interface ISasCmbInvtService extends IService<SasCmbInvt> {
    /**
     * 查询耗料单的边角料/分页
     *
     * @param page 分页
     * @param sasCmbInvt
     * @return List<sasCmbInvt>
     */
    Page<SasCmbInvt> selectSasCmbInvtList(Page<SasCmbInvt> page, SasCmbInvt sasCmbInvt);


}
