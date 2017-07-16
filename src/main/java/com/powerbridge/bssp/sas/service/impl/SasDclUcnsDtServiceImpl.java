package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasDclUcnsDtMapper;
import com.powerbridge.bssp.sas.entity.SasDclUcnsDt;
import com.powerbridge.bssp.sas.service.ISasDclUcnsDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务申报单耗表 服务实现类
 * </p>
 *
 * @author jokylao
 * @since 2017-07-13
 */
@Service("sasDclUcnsDtService")
public class SasDclUcnsDtServiceImpl extends BaseServiceImpl<SasDclUcnsDtMapper, SasDclUcnsDt> implements ISasDclUcnsDtService {

    @Autowired
    private SasDclUcnsDtMapper sasDclUcnsDtMapper;

    /**
     * 查询备案/分页
     *
     * @param page         分页
     * @param sasDclUcnsDt     数据
     * @return Page<SasDclDt>
     */
    public Page<SasDclUcnsDt> selectByList(Page<SasDclUcnsDt> page, SasDclUcnsDt sasDclUcnsDt) {
        page.setRecords(sasDclUcnsDtMapper.selectByList(page, sasDclUcnsDt));
        return page;
    }
}
