package com.powerbridge.bssp.sas_cmb.service.impl;

import com.powerbridge.bssp.sas_cmb.entity.SasCmbInvt;
import com.powerbridge.bssp.sas_cmb.dao.SasCmbInvtMapper;
import com.powerbridge.bssp.sas_cmb.service.ISasCmbInvtService;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 耗料单清单列表 服务实现类
 * </p>
 *
 * @author huanliu
 * @since 2017-06-18
 */
@Service("sasCmbInvtService")
public class SasCmbInvtServiceImpl extends BaseServiceImpl<SasCmbInvtMapper,SasCmbInvt> implements ISasCmbInvtService {
    @Autowired
    private SasCmbInvtMapper sasCmbInvtMapper;


    public Page<SasCmbInvt> selectSasCmbInvtList(Page<SasCmbInvt> page, SasCmbInvt sasCmbInvt){
        page.setRecords(sasCmbInvtMapper.selectSasCmbInvtList(page, sasCmbInvt));
        return page;
    }


}