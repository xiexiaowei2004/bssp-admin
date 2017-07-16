package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasDragcarHisMapper;
import com.powerbridge.bssp.sas.entity.SasDragcarHis;
import com.powerbridge.bssp.sas.service.ISasDragcarHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 扡卡历史表 服务实现类
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-05
 */
@Service("sasDragcarHisService")
public class SasDragcarHisServiceImpl extends BaseServiceImpl<SasDragcarHisMapper, SasDragcarHis> implements ISasDragcarHisService {
    @Autowired
    private SasDragcarHisMapper sasDragcarHisMapper;

    public Page<SasDragcarHis> selectSasDragcarHisList(Page<SasDragcarHis> page, SasDragcarHis sasDragcarHis) {
        page.setRecords(sasDragcarHisMapper.selectSasDragcarHisList(page, sasDragcarHis));
        return page;
    }
}
