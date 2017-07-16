package com.powerbridge.bssp.cop_et.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cop_et.dao.EtCusAcmpFormDtMapper;
import com.powerbridge.bssp.cop_et.entity.EtCusAcmpFormDt;
import com.powerbridge.bssp.cop_et.service.IEtCusAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 联网企业档案库随附单证正式表明细 服务实现类
 *
 * @author willChen
 * @since 2017-06-01
 */
@Service("etCusAcmpFormDtService")
public class EtCusAcmpFormDtServiceImpl extends BaseServiceImpl<EtCusAcmpFormDtMapper, EtCusAcmpFormDt> implements IEtCusAcmpFormDtService {

    @Autowired
    private EtCusAcmpFormDtMapper etCusAcmpFormDtMapper;

    /**
     * 根据条件查询表数据
     *
     * @param etCusAcmpFormDt
     * @return
     * @throws Exception
     */
    @Override
    public Page<EtCusAcmpFormDt> selectEtCusAcmpFormDtList(Page<EtCusAcmpFormDt> page, EtCusAcmpFormDt etCusAcmpFormDt) throws Exception {
        page.setRecords(etCusAcmpFormDtMapper.selectEtCusAcmpFormDtList(page, etCusAcmpFormDt));
        return page;
    }
}
