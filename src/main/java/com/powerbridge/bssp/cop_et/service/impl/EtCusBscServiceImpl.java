package com.powerbridge.bssp.cop_et.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cop_et.dao.EtCusBscMapper;
import com.powerbridge.bssp.cop_et.entity.EtCusBsc;
import com.powerbridge.bssp.cop_et.service.IEtCusBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 联网企业档案库正式表 服务实现类
 *
 * @author willChen
 * @since 2017-06-01
 */
@Service("etCusBscService")
public class EtCusBscServiceImpl extends BaseServiceImpl<EtCusBscMapper, EtCusBsc> implements IEtCusBscService {

    @Autowired
    private EtCusBscMapper etCusBscMapper;

    /**
     * 根据条件查询表数据
     *
     * @param etCusBsc
     * @return
     * @throws Exception
     */
    @Override
    public Page<EtCusBsc> selectEtCusBscList(Page<EtCusBsc> page, EtCusBsc etCusBsc) throws Exception {
        page.setRecords(etCusBscMapper.selectEtCusBscList(page, etCusBsc));
        return page;
    }
}
