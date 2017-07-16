package com.powerbridge.bssp.saspass.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.saspass.dao.SasPassportHisDtMapper;
import com.powerbridge.bssp.saspass.entity.SasPassportHisDt;
import com.powerbridge.bssp.saspass.service.ISasPassportHisDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sasPassportHisDtService")
public class SasPassportHisDtServiceImpl extends BaseServiceImpl<SasPassportHisDtMapper, SasPassportHisDt> implements ISasPassportHisDtService {

	@Autowired
    private SasPassportHisDtMapper sasPassportHisDtMapper;

	@Override
	public SasPassportHisDt selectByUid(String id){
        return sasPassportHisDtMapper.selectByUid(id);
    }

    @Override
    public Page<SasPassportHisDt> selectBySasPassportHisDt(Page<SasPassportHisDt> page, SasPassportHisDt sasPassportHisDt){
        page.setRecords(sasPassportHisDtMapper.selectBySasPassportHisDt(page, sasPassportHisDt));
        return page;
    }
}
