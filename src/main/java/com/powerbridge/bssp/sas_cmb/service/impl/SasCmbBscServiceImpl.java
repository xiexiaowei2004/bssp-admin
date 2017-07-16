package com.powerbridge.bssp.sas_cmb.service.impl;

import com.powerbridge.bssp.sas_cmb.entity.SasCmbBsc;
import com.powerbridge.bssp.sas_cmb.dao.SasCmbBscMapper;
import com.powerbridge.bssp.sas_cmb.service.ISasCmbBscService;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsBscMapper;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsBsc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 耗料单基本信息 服务实现类
 * </p>
 *
 * @author huanliu
 * @since 2017-06-18
 */
@Service("sasCmbBscService")
public class SasCmbBscServiceImpl extends BaseServiceImpl<SasCmbBscMapper, SasCmbBsc> implements ISasCmbBscService {
    @Autowired
    private SasCmbBscMapper sasCmbBscMapper;

    public Page<SasCmbBsc> selectSasCmbBscList(Page<SasCmbBsc> page, SasCmbBsc sasCmbBsc) {
        page.setRecords(sasCmbBscMapper.selectSasCmbBscList(page, sasCmbBsc));
        return page;

    }


}