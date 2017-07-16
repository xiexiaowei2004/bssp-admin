package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclHisDt;
import com.powerbridge.bssp.sas.dao.SasDclHisDtMapper;
import com.powerbridge.bssp.sas.service.ISasDclHisDtService;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务申报商品历史表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
@Service("sasDclHisDtService")
public class SasDclHisDtServiceImpl extends BaseServiceImpl<SasDclHisDtMapper, SasDclHisDt> implements ISasDclHisDtService {

    @Autowired
    private SasDclHisDtMapper sasDclHisDtMapper;

    /**
     * 查询备案/分页
     *
     * @param page         分页
     * @param sasDclHisDt     数据
     * @return Page<SasDclDt>
     */
    public Page<SasDclHisDt> selectByList(Page<SasDclHisDt> page, SasDclHisDt sasDclHisDt) {
        page.setRecords(sasDclHisDtMapper.selectByList(page, sasDclHisDt));
        return page;
    }
}
