package com.powerbridge.bssp.saspass.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.saspass.dao.SasPassportCusBscMapper;
import com.powerbridge.bssp.saspass.entity.SasPassportCusBsc;
import com.powerbridge.bssp.saspass.service.ISasPassportCusBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sasPassportCusBscService")
public class SasPassportCusBscServiceImpl extends BaseServiceImpl<SasPassportCusBscMapper, SasPassportCusBsc> implements ISasPassportCusBscService {

    @Autowired
    private SasPassportCusBscMapper sasPassportCusBscMapper;

    @Override
    public SasPassportCusBsc selectByUid(String uid) {
        return sasPassportCusBscMapper.selectByUid(uid);
    }

    @Override
    public Page<SasPassportCusBsc> selectBySasPassportCusBsc(Page<SasPassportCusBsc> page, SasPassportCusBsc sasPassportCusBsc){
        page.setRecords(sasPassportCusBscMapper.selectBySasPassportCusBsc(page, sasPassportCusBsc));
        return page;
    }
}
