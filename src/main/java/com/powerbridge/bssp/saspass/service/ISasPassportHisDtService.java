package com.powerbridge.bssp.saspass.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.saspass.entity.SasPassportDt;
import com.powerbridge.bssp.saspass.entity.SasPassportHisDt;

public interface ISasPassportHisDtService extends IService<SasPassportHisDt> {

    public SasPassportHisDt selectByUid(String id);

    public Page<SasPassportHisDt> selectBySasPassportHisDt(Page<SasPassportHisDt> page, SasPassportHisDt sasPassportHisDt);
}
