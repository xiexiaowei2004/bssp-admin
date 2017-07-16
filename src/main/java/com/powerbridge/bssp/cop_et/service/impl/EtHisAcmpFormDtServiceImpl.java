package com.powerbridge.bssp.cop_et.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cop_et.dao.EtHisAcmpFormDtMapper;
import com.powerbridge.bssp.cop_et.entity.EtHisAcmpFormDt;
import com.powerbridge.bssp.cop_et.service.IEtHisAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 联网企业档案库随附单证历史表明细 服务实现类
 *
 * @author willChen
 * @since 2017-07-03 10:11:49
 */
@Service("etHisAcmpFormDtService")
public class EtHisAcmpFormDtServiceImpl extends BaseServiceImpl<EtHisAcmpFormDtMapper, EtHisAcmpFormDt> implements IEtHisAcmpFormDtService {

    @Autowired
    private EtHisAcmpFormDtMapper etHisAcmpFormDtMapper;

    /**
     * 根据条件查询表数据
     *
     * @param etHisAcmpFormDt
     * @return
     * @throws Exception
     */
    @Override
    public Page<EtHisAcmpFormDt> selectEtHisAcmpFormDtList(Page<EtHisAcmpFormDt> page, EtHisAcmpFormDt etHisAcmpFormDt) throws Exception {
        page.setRecords(etHisAcmpFormDtMapper.selectEtHisAcmpFormDtList(page, etHisAcmpFormDt));
        return page;
    }
}
