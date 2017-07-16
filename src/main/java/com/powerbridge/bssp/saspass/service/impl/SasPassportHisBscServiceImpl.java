package com.powerbridge.bssp.saspass.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.saspass.dao.SasPassportBscMapper;
import com.powerbridge.bssp.saspass.dao.SasPassportDtMapper;
import com.powerbridge.bssp.saspass.dao.SasPassportHisBscMapper;
import com.powerbridge.bssp.saspass.dao.SasPassportRltMapper;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import com.powerbridge.bssp.saspass.entity.SasPassportDt;
import com.powerbridge.bssp.saspass.entity.SasPassportHisBsc;
import com.powerbridge.bssp.saspass.entity.SasPassportRlt;
import com.powerbridge.bssp.saspass.service.ISasPassportBscService;
import com.powerbridge.bssp.saspass.service.ISasPassportHisBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service("sasPassportHisBscService")
public class SasPassportHisBscServiceImpl extends BaseServiceImpl<SasPassportHisBscMapper, SasPassportHisBsc> implements ISasPassportHisBscService {

    @Autowired
    private SasPassportHisBscMapper SasPassportHisBscMapper;

    @Override
    public SasPassportHisBsc selectByUid(String uid) {
        return SasPassportHisBscMapper.selectByUid(uid);
    }

    @Override
    public Page<SasPassportHisBsc> selectBySasPassportHisBsc(Page<SasPassportHisBsc> page, SasPassportHisBsc sasPassportHisBsc){
        page.setRecords(SasPassportHisBscMapper.selectBySasPassportHisBsc(page, sasPassportHisBsc));
        return page;
    }
}
