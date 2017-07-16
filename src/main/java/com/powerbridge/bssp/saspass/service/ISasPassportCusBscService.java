package com.powerbridge.bssp.saspass.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import com.powerbridge.bssp.saspass.entity.SasPassportCusBsc;

import java.io.Serializable;
import java.util.List;

public interface ISasPassportCusBscService extends IService<SasPassportCusBsc> {

    /**
     * 根据ID查询
     * @param uid
     * @return
     */
    public SasPassportCusBsc selectByUid(String uid);

    /**
     * 分页查询
     * @param page
     * @param sasPassportCusBsc
     * @return
     */
    public Page<SasPassportCusBsc> selectBySasPassportCusBsc(Page<SasPassportCusBsc> page, SasPassportCusBsc sasPassportCusBsc);
}
