package com.powerbridge.bssp.cop_et.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cop_et.dao.EtHisBscMapper;
import com.powerbridge.bssp.cop_et.entity.EtHisBsc;
import com.powerbridge.bssp.cop_et.service.IEtHisBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 联网企业档案库历史表 服务实现类
 *
 * @author willChen
 * @since 2017-07-03 10:11:14
 */
@Service("etHisBscService")
public class EtHisBscServiceImpl extends BaseServiceImpl<EtHisBscMapper, EtHisBsc> implements IEtHisBscService {

    @Autowired
    private EtHisBscMapper etHisBscMapper;

    /**
     * 根据条件查询表数据
     *
     * @param etHisBsc
     * @return
     * @throws Exception
     */
    @Override
    public Page<EtHisBsc> selectEtHisBscList(Page<EtHisBsc> page, EtHisBsc etHisBsc) throws Exception {
        page.setRecords(etHisBscMapper.selectEtHisBscList(page, etHisBsc));
        return page;
    }
}
