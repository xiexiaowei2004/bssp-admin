package com.powerbridge.bssp.sas_cmb.service.impl;
import com.powerbridge.bssp.sas_cmb.entity.SasCmbImg;
import com.powerbridge.bssp.sas_cmb.dao.SasCmbImgMapper;
import com.powerbridge.bssp.sas_cmb.service.ISasCmbImgService;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 耗料单料件表 服务实现类
 * </p>
 *
 * @author huanliu
 * @since 2017-06-18
 */
@Service("sasCmbImgService")
public class SasCmbImgServiceImpl extends BaseServiceImpl<SasCmbImgMapper, SasCmbImg> implements ISasCmbImgService {
    @Autowired
    private SasCmbImgMapper sasCmbImgMapper;


    public Page<SasCmbImg> selectSasCmbImgList(Page<SasCmbImg> page, SasCmbImg sasCmbImg){
        page.setRecords(sasCmbImgMapper.selectSasCmbImgList(page, sasCmbImg));
        return page;
    }

}