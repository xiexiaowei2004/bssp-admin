package com.powerbridge.bssp.cop_et.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cop_et.dao.EtHisExgMapper;
import com.powerbridge.bssp.cop_et.entity.EtHisExg;
import com.powerbridge.bssp.cop_et.service.IEtHisExgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 联网企业档案库历史表成品 服务实现类
 *
 * @author willChen
 * @since 2017-07-03 10:13:18
 */
@Service("etHisExgService")
public class EtHisExgServiceImpl extends BaseServiceImpl<EtHisExgMapper, EtHisExg> implements IEtHisExgService {

    @Autowired
    private EtHisExgMapper etHisExgMapper;

    /**
     * 根据条件查询表数据
     *
     * @param etHisExg
     * @return
     * @throws Exception
     */
    @Override
    public Page<EtHisExg> selectEtHisExgList(Page<EtHisExg> page, EtHisExg etHisExg) throws Exception {
        page.setRecords(etHisExgMapper.selectEtHisExgList(page, etHisExg));
        return page;
    }
}
