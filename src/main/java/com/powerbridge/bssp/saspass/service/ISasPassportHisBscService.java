package com.powerbridge.bssp.saspass.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import com.powerbridge.bssp.saspass.entity.SasPassportHisBsc;

import java.io.Serializable;
import java.util.List;

public interface ISasPassportHisBscService extends IService<SasPassportHisBsc> {

    /**
     * 根据ID查询
     * @param uid
     * @return
     */
    public SasPassportHisBsc selectByUid(String uid);

    /**
     * 分页查询
     * @param page
     * @param sasPassportHisBsc
     * @return
     */
    public Page<SasPassportHisBsc> selectBySasPassportHisBsc(Page<SasPassportHisBsc> page, SasPassportHisBsc sasPassportHisBsc);
}
