package com.powerbridge.bssp.saspass.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.saspass.entity.SasPassportDt;

public interface ISasPassportDtService extends IService<SasPassportDt> {

    public SasPassportDt selectByUid(String id);

    public Page<SasPassportDt> selectBySasPassportDt(Page<SasPassportDt> page, SasPassportDt sasPassportDt);
}
