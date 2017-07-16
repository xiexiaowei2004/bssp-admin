package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasDragcarCusMapper;
import com.powerbridge.bssp.sas.entity.SasDragcarCus;
import com.powerbridge.bssp.sas.service.ISasDragcarCusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 扡卡正式表 服务实现类
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-05
 */
@Service("sasDragcarCusService")
public class SasDragcarCusServiceImpl extends BaseServiceImpl<SasDragcarCusMapper, SasDragcarCus> implements ISasDragcarCusService {
    @Autowired
    private SasDragcarCusMapper sasDragcarCusMapper;
    
    public Page<SasDragcarCus> selectSasDragcarCusList(Page<SasDragcarCus> page, SasDragcarCus sasDragcarCus) {
        page.setRecords(sasDragcarCusMapper.selectSasDragcarCusList(page, sasDragcarCus));
        return page;
    }
}
