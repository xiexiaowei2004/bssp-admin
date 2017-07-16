package com.powerbridge.bssp.dcr.dao;

import com.powerbridge.bssp.dcr.entity.DcrChgoffHisInvtLt;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * <p>
  * 账册报核清单历史表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
public interface DcrChgoffHisInvtLtMapper extends BaseMapper<DcrChgoffHisInvtLt> {
    /**
     * 查询 分页
     *
     * @param page  分页
     * @param dcrChgoffHisInvtLt
     * @return List<DcrChgoffHisInvtLt>
     */
    List<DcrChgoffHisInvtLt> selectByList(Page<DcrChgoffHisInvtLt> page, DcrChgoffHisInvtLt dcrChgoffHisInvtLt);
}