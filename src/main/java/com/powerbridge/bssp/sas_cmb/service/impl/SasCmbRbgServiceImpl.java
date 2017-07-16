package com.powerbridge.bssp.sas_cmb.service.impl;

import com.powerbridge.bssp.sas_cmb.entity.SasCmbRbg;
import com.powerbridge.bssp.sas_cmb.dao.SasCmbRbgMapper;
import com.powerbridge.bssp.sas_cmb.service.ISasCmbRbgService;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsAcmpFormDtMapper;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsAcmpFormDt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 耗料单边角料表 服务实现类
 * </p>
 *
 * @author jokylao
 * @since 2017-06-18
 */
@Service("sasCmbRbgService")
public class SasCmbRbgServiceImpl extends BaseServiceImpl<SasCmbRbgMapper, SasCmbRbg> implements ISasCmbRbgService {
    @Autowired
    private SasCmbRbgMapper sasCmbRbgMapper;


    public Page<SasCmbRbg> selectSasCmbRbgList(Page<SasCmbRbg> page, SasCmbRbg sasCmbRbg){
        page.setRecords(sasCmbRbgMapper.selectSasCmbRbgList(page, sasCmbRbg));
        return page;
    }




}