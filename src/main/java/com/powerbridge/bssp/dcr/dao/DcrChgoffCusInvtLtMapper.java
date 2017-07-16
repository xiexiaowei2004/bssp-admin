package com.powerbridge.bssp.dcr.dao;

import com.powerbridge.bssp.dcr.entity.DcrChgoffCusInvtLt;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * <p>
  * 账册报核清单正式表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
public interface DcrChgoffCusInvtLtMapper extends BaseMapper<DcrChgoffCusInvtLt> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return List<DcrChgoffCusInvtLt>
     */
    List<DcrChgoffCusInvtLt> selectByList(Page<DcrChgoffCusInvtLt> page, String seqNo);
}