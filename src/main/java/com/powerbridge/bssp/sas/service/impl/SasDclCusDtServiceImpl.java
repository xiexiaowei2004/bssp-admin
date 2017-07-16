package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclCusDt;
import com.powerbridge.bssp.sas.dao.SasDclCusDtMapper;
import com.powerbridge.bssp.sas.service.ISasDclCusDtService;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务申报商品正式表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
@Service("sasDclCusDtService")
public class SasDclCusDtServiceImpl extends BaseServiceImpl<SasDclCusDtMapper, SasDclCusDt> implements ISasDclCusDtService {

    @Autowired
    private SasDclCusDtMapper sasDclCusDtMapper;

    /**
     * 查询备案/分页
     *
     * @param page         分页
     * @param sasDclCusDt     数据
     * @return Page<SasDclDt>
     */
    public Page<SasDclCusDt> selectByList(Page<SasDclCusDt> page, SasDclCusDt sasDclCusDt) {
        page.setRecords(sasDclCusDtMapper.selectByList(page, sasDclCusDt));
        return page;
    }
}
