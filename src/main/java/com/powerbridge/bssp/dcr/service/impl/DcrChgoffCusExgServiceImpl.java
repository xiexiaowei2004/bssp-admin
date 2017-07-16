package com.powerbridge.bssp.dcr.service.impl;

import com.powerbridge.bssp.dcr.entity.DcrChgoffCusExg;
import com.powerbridge.bssp.dcr.dao.DcrChgoffCusExgMapper;
import com.powerbridge.bssp.dcr.service.IDcrChgoffCusExgService;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账册报核成品正式表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Service("dcrChgoffCusExgService")
public class DcrChgoffCusExgServiceImpl extends BaseServiceImpl<DcrChgoffCusExgMapper, DcrChgoffCusExg> implements IDcrChgoffCusExgService {

    @Autowired
    private DcrChgoffCusExgMapper dcrChgoffCusExgMapper;

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrChgoffCusExg>
     */
    public Page<DcrChgoffCusExg> selectByList(Page<DcrChgoffCusExg> page, String seqNo) {
        page.setRecords(dcrChgoffCusExgMapper.selectByList(page, seqNo));
        return page;
    }
}
