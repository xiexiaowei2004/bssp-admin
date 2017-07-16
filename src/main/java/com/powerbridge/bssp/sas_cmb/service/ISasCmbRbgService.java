package com.powerbridge.bssp.sas_cmb.service;


import com.powerbridge.bssp.sas_cmb.entity.SasCmbRbg;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 耗料单边角料表 服务类
 * </p>
 *
 * @author jokylao
 * @since 2017-06-18
 */
public interface ISasCmbRbgService extends IService<SasCmbRbg> {
    /**
     * 查询耗料单的边角料/分页
     *
     * @param page 分页
     * @param sasCmbRbg
     * @return List<sasCmbRbg>
     */
    Page<SasCmbRbg> selectSasCmbRbgList(Page<SasCmbRbg> page, SasCmbRbg sasCmbRbg);




}
