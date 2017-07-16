package com.powerbridge.bssp.edi.service.impl;


import com.baomidou.mybatisplus.plugins.Page;

import com.powerbridge.bssp.sas.entity.SasDragcarBsc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.edi.dao.EdiRoutingInfoMapper;
import com.powerbridge.bssp.edi.entity.EdiRoutingInfo;
import com.powerbridge.bssp.edi.service.IEdiRoutingInfoService;

@Service("ediRoutingInfoService")
public class EdiRoutingInfoServiceImpl extends BaseServiceImpl<EdiRoutingInfoMapper, EdiRoutingInfo> implements IEdiRoutingInfoService {

    @Autowired
    private EdiRoutingInfoMapper ediRoutingInfoMapper;

    public Page<EdiRoutingInfo> selectEdiRoutingInfoList(Page<EdiRoutingInfo> page, EdiRoutingInfo ediRoutingInfo) {
        page.setRecords(ediRoutingInfoMapper.selectEdiRoutingInfoList(page, ediRoutingInfo));
        return page;

    }


}
