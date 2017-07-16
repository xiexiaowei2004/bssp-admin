package com.powerbridge.bssp.entry.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.entry.dao.EntryHeadMapper;
import com.powerbridge.bssp.entry.entity.EntryHead;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 报关单表头 服务类
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-10
 */
public interface IEntryHeadService extends IService<EntryHead> {

    /**
     * 查询备案/分页
     *
     * @param page         分页
     * @return Page<EntryHead>
     */
    public Page<EntryHead> selectEntryHead(Page<EntryHead> page, EntryHead entryHead);
}
