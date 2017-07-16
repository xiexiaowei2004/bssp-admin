package com.powerbridge.bssp.dcr.service.impl;

import com.powerbridge.bssp.dcr.entity.DcrChgoffHisInvtLt;
import com.powerbridge.bssp.dcr.dao.DcrChgoffHisInvtLtMapper;
import com.powerbridge.bssp.dcr.service.IDcrChgoffHisInvtLtService;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账册报核清单历史表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Service("dcrChgoffHisInvtLtService")
public class DcrChgoffHisInvtLtServiceImpl extends BaseServiceImpl<DcrChgoffHisInvtLtMapper, DcrChgoffHisInvtLt> implements IDcrChgoffHisInvtLtService {
    @Autowired
    private DcrChgoffHisInvtLtMapper dcrChgoffHisInvtLtMapper;

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param dcrChgoffHisInvtLt
     * @return Page<DcrChgoffHisInvtLt>
     */
    public Page<DcrChgoffHisInvtLt> selectByList(Page<DcrChgoffHisInvtLt> page, DcrChgoffHisInvtLt dcrChgoffHisInvtLt) {
        page.setRecords(dcrChgoffHisInvtLtMapper.selectByList(page, dcrChgoffHisInvtLt));
        return page;
    }
}
