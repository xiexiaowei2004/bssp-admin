package com.powerbridge.bssp.dcr.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.dcr.entity.DcrAdjaccCusAcmpFormDt;
import com.powerbridge.bssp.dcr.dao.DcrAdjaccCusAcmpFormDtMapper;
import com.powerbridge.bssp.dcr.service.IDcrAdjaccCusAcmpFormDtService;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账册核销随附单证明细正式表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Service("dcrAdjaccCusAcmpFormDtService")
public class DcrAdjaccCusAcmpFormDtServiceImpl extends BaseServiceImpl<DcrAdjaccCusAcmpFormDtMapper, DcrAdjaccCusAcmpFormDt> implements IDcrAdjaccCusAcmpFormDtService {

    @Autowired
    private DcrAdjaccCusAcmpFormDtMapper dcrAdjaccCusAcmpFormDtMapper;

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrAdjaccCusAcmpFormDt>
     */
    public Page<DcrAdjaccCusAcmpFormDt> selectByList(Page<DcrAdjaccCusAcmpFormDt> page, String seqNo) {
        page.setRecords(dcrAdjaccCusAcmpFormDtMapper.selectByList(page, seqNo));
        return page;
    }
}
