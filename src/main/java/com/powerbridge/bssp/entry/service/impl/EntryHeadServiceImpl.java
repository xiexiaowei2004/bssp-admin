package com.powerbridge.bssp.entry.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.entry.dao.EntryHeadMapper;
import com.powerbridge.bssp.entry.entity.EntryHead;
import com.powerbridge.bssp.entry.service.IEntryHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 报关单表头 服务实现类
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-10
 */
@Service("entryHeadService")
public class EntryHeadServiceImpl extends BaseServiceImpl<EntryHeadMapper, EntryHead> implements IEntryHeadService {

    @Autowired
    private EntryHeadMapper entryHeadMapper;
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page 分页
     * @param entryHead
     * @return Page<EntryHead>
     */
    @Override
    public Page<EntryHead> selectEntryHead(Page<EntryHead> page, EntryHead entryHead) {
        page.setRecords(entryHeadMapper.selectEntryHead(page, entryHead));
        return page;
    }
}
