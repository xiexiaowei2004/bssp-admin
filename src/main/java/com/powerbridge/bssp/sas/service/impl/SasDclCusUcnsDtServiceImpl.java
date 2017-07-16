package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclCusUcnsDt;
import com.powerbridge.bssp.sas.dao.SasDclCusUcnsDtMapper;
import com.powerbridge.bssp.sas.service.ISasDclCusUcnsDtService;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务申报单耗正式表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
@Service("sasDclCusUcnsDtService")
public class SasDclCusUcnsDtServiceImpl extends BaseServiceImpl<SasDclCusUcnsDtMapper, SasDclCusUcnsDt> implements ISasDclCusUcnsDtService {
    @Autowired
    private SasDclCusUcnsDtMapper sasDclCusUcnsDtMapper;

    /**
     * 查询备案/分页
     *
     * @param page         分页
     * @param sasDclCusUcnsDt     数据
     * @return Page<SasDclCusUcnsDt>
     */
    public Page<SasDclCusUcnsDt> selectByList(Page<SasDclCusUcnsDt> page, SasDclCusUcnsDt sasDclCusUcnsDt) {
        page.setRecords(sasDclCusUcnsDtMapper.selectByList(page, sasDclCusUcnsDt));
        return page;
    }
}
