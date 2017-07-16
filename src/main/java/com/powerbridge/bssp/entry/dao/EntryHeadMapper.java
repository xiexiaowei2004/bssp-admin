package com.powerbridge.bssp.entry.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.entry.entity.EntryHead;

import java.util.List;

/**
 * <p>
  * 报关单表头 Mapper 接口
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-10
 */
public interface EntryHeadMapper extends BaseMapper<EntryHead> {

    List<EntryHead> selectEntryHead(Page<EntryHead> page, EntryHead entryHead);
}