package com.powerbridge.bssp.saspass.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.saspass.dao.SasPassportDtMapper;
import com.powerbridge.bssp.saspass.dao.SasPassportRltMapper;
import com.powerbridge.bssp.saspass.entity.SasPassportDt;
import com.powerbridge.bssp.saspass.entity.SasPassportRlt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.saspass.dao.SasPassportBscMapper;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import com.powerbridge.bssp.saspass.service.ISasPassportBscService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service("sasPassportBscService")
public class SasPassportBscServiceImpl extends BaseServiceImpl<SasPassportBscMapper, SasPassportBsc> implements ISasPassportBscService {

    @Autowired
    private SasPassportBscMapper sasPassportBscMapper;
    @Autowired
    private SasPassportRltMapper sasPassportRltMapper;
    @Autowired
    private SasPassportDtMapper sasPassportDtMapper;

    @Override
    public SasPassportBsc selectByUid(String uid) {
        return sasPassportBscMapper.selectByUid(uid);
    }

    @Override
    public Page<SasPassportBsc> selectBySasPassportBsc(Page<SasPassportBsc> page, SasPassportBsc sasPassportBsc){
        page.setRecords(sasPassportBscMapper.selectBySasPassportBsc(page, sasPassportBsc));
        return page;
    }

    @Override
    public Boolean deleteBatchSeqNos(List<? extends Serializable> seqNos){
        EntityWrapper<SasPassportBsc> wrapperBsc = new EntityWrapper<SasPassportBsc>();
        wrapperBsc.eq("SEQ_NO",seqNos.get(0));
        Integer bsc = sasPassportBscMapper.delete(wrapperBsc);

        EntityWrapper<SasPassportRlt> wrapperRlt = new EntityWrapper<SasPassportRlt>();
        wrapperRlt.eq("SEQ_NO",seqNos.get(0));
        Integer rlt = sasPassportRltMapper.delete(wrapperRlt);

        EntityWrapper<SasPassportDt> wrapperDt = new EntityWrapper<SasPassportDt>();
        wrapperDt.eq("SEQ_NO",seqNos.get(0));
        Integer dt = sasPassportDtMapper.delete(wrapperDt);

        if (retBool(bsc)/* && retBool(rlt) && retBool(dt)*/){
            return true;
        }else {
            return false;
        }
    }
}
