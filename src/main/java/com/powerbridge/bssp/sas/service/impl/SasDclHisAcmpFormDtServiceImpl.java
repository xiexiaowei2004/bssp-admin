package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclHisAcmpFormDt;
import com.powerbridge.bssp.sas.dao.SasDclHisAcmpFormDtMapper;
import com.powerbridge.bssp.sas.service.ISasDclHisAcmpFormDtService;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务申报随附单证历史表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
@Service("sasDclHisAcmpFormDtService")
public class SasDclHisAcmpFormDtServiceImpl extends BaseServiceImpl<SasDclHisAcmpFormDtMapper, SasDclHisAcmpFormDt> implements ISasDclHisAcmpFormDtService {

    @Autowired
    private SasDclHisAcmpFormDtMapper sasDclHisAcmpFormDtMapper;

    /**
     * 查询备案/分页
     *
     * @param page         分页
     * @param sasDclHisAcmpFormDt   参数数据
     * @return Page<SasDclAcmpFormDt>
     */
    public Page<SasDclHisAcmpFormDt> selectByList(Page<SasDclHisAcmpFormDt> page, SasDclHisAcmpFormDt sasDclHisAcmpFormDt) {
        page.setRecords(sasDclHisAcmpFormDtMapper.selectByList(page, sasDclHisAcmpFormDt));
        return page;
    }

}
