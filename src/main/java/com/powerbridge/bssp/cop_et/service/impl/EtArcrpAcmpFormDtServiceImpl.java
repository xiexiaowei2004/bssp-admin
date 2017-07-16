package com.powerbridge.bssp.cop_et.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cop_et.dao.EtArcrpAcmpFormDtMapper;
import com.powerbridge.bssp.cop_et.entity.EtArcrpAcmpFormDt;
import com.powerbridge.bssp.cop_et.service.IEtArcrpAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 联网企业档案库随附单证明细 服务实现类
 *
 * @author willChen
 * @since 2017-05-22
 */
@Service("etArcrpAcmpFormDtService")
public class EtArcrpAcmpFormDtServiceImpl extends BaseServiceImpl<EtArcrpAcmpFormDtMapper, EtArcrpAcmpFormDt> implements IEtArcrpAcmpFormDtService {

    @Autowired
    private EtArcrpAcmpFormDtMapper etArcrpAcmpFormDtMapper;

    /**
     * 根据条件查询表数据
     *
     * @param etArcrpAcmpFormDt
     * @return
     * @throws Exception
     */
    @Override
    public Page<EtArcrpAcmpFormDt> selectEtArcrpAcmpFormDtList(Page<EtArcrpAcmpFormDt> page, EtArcrpAcmpFormDt etArcrpAcmpFormDt) throws Exception {
        page.setRecords(etArcrpAcmpFormDtMapper.selectEtArcrpAcmpFormDtList(page, etArcrpAcmpFormDt));
        return page;
    }
}
