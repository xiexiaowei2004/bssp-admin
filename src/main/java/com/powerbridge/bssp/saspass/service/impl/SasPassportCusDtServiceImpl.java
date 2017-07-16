package com.powerbridge.bssp.saspass.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.saspass.dao.SasPassportCusDtMapper;
import com.powerbridge.bssp.saspass.dao.SasPassportDtMapper;
import com.powerbridge.bssp.saspass.entity.SasPassportCusDt;
import com.powerbridge.bssp.saspass.entity.SasPassportDt;
import com.powerbridge.bssp.saspass.service.ISasPassportCusDtService;
import com.powerbridge.bssp.saspass.service.ISasPassportDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sasPassportCusDtService")
public class SasPassportCusDtServiceImpl extends BaseServiceImpl<SasPassportCusDtMapper, SasPassportCusDt> implements ISasPassportCusDtService {

	@Autowired
    private SasPassportCusDtMapper sasPassportCusDtMapper;

	@Override
	public SasPassportCusDt selectByUid(String id){
        return sasPassportCusDtMapper.selectByUid(id);
    }

    @Override
    public Page<SasPassportCusDt> selectBySasPassportCusDt(Page<SasPassportCusDt> page, SasPassportCusDt sasPassportCusDt){
        page.setRecords(sasPassportCusDtMapper.selectBySasPassportCusDt(page, sasPassportCusDt));
        return page;
    }
}
