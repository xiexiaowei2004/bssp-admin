package com.powerbridge.bssp.dcr.service.impl;

import com.powerbridge.bssp.dcr.entity.DcrChgoffCusInvtLt;
import com.powerbridge.bssp.dcr.dao.DcrChgoffCusInvtLtMapper;
import com.powerbridge.bssp.dcr.service.IDcrChgoffCusInvtLtService;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账册报核清单正式表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Service("dcrChgoffCusInvtLtService")
public class DcrChgoffCusInvtLtServiceImpl extends BaseServiceImpl<DcrChgoffCusInvtLtMapper, DcrChgoffCusInvtLt> implements IDcrChgoffCusInvtLtService {
    @Autowired
    private DcrChgoffCusInvtLtMapper dcrChgoffCusInvtLtMapper;

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrChgoffCusInvtLt>
     */
    public Page<DcrChgoffCusInvtLt> selectByList(Page<DcrChgoffCusInvtLt> page, String seqNo) {
        page.setRecords(dcrChgoffCusInvtLtMapper.selectByList(page, seqNo));
        return page;
    }
}
