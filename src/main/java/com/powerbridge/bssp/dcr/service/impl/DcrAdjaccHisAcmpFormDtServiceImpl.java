package com.powerbridge.bssp.dcr.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.dcr.dao.DcrAdjaccHisAcmpFormDtMapper;
import com.powerbridge.bssp.dcr.entity.DcrAdjaccHisAcmpFormDt;
import com.powerbridge.bssp.dcr.service.IDcrAdjaccHisAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账册核销随附单证明细历史表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Service("dcrAdjaccHisAcmpFormDtService")
public class DcrAdjaccHisAcmpFormDtServiceImpl extends BaseServiceImpl<DcrAdjaccHisAcmpFormDtMapper, DcrAdjaccHisAcmpFormDt> implements IDcrAdjaccHisAcmpFormDtService {

    @Autowired
    private DcrAdjaccHisAcmpFormDtMapper dcrAdjaccHisAcmpFormDtMapper;

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param dcrAdjaccHisAcmpFormDt
     * @return Page<DcrAdjaccHisAcmpFormDt>
     */
    public Page<DcrAdjaccHisAcmpFormDt> selectByList(Page<DcrAdjaccHisAcmpFormDt> page, DcrAdjaccHisAcmpFormDt dcrAdjaccHisAcmpFormDt) {
        page.setRecords(dcrAdjaccHisAcmpFormDtMapper.selectByList(page, dcrAdjaccHisAcmpFormDt));
        return page;
    }
}
