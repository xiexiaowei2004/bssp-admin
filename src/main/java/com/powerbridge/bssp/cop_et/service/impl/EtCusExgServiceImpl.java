package com.powerbridge.bssp.cop_et.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cop_et.dao.EtCusExgMapper;
import com.powerbridge.bssp.cop_et.entity.EtCusExg;
import com.powerbridge.bssp.cop_et.service.IEtCusExgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 联网企业档案库正式表成品 服务实现类
 *
 * @author willChen
 * @since 2017-06-01
 */
@Service("etCusExgService")
public class EtCusExgServiceImpl extends BaseServiceImpl<EtCusExgMapper, EtCusExg> implements IEtCusExgService {

    @Autowired
    private EtCusExgMapper etCusExgMapper;

    /**
     * 根据条件查询表数据
     *
     * @param etCusExg
     * @return
     * @throws Exception
     */
    @Override
    public Page<EtCusExg> selectEtCusExgList(Page<EtCusExg> page, EtCusExg etCusExg) throws Exception {
        page.setRecords(etCusExgMapper.selectEtCusExgList(page, etCusExg));
        return page;
    }
}
