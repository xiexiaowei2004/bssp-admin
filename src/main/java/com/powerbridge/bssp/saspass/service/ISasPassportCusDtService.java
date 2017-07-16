package com.powerbridge.bssp.saspass.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.saspass.entity.SasPassportCusDt;
import com.powerbridge.bssp.saspass.entity.SasPassportDt;

public interface ISasPassportCusDtService extends IService<SasPassportCusDt> {

    public SasPassportCusDt selectByUid(String id);

    public Page<SasPassportCusDt> selectBySasPassportCusDt(Page<SasPassportCusDt> page, SasPassportCusDt sasPassportCusDt);
}
