package com.powerbridge.bssp.inv.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.inv.entity.InvCusDt;
import com.powerbridge.bssp.inv.entity.InvHisDt;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface InvHisDtMapper extends BaseMapper<InvHisDt> {

    /**
     * 查询
     *
     * @param page 分页
     * @param InvHisDt
     * @return List<InvHisDt>
     */
    List<InvHisDt> selectByInvHisDt(Page<InvHisDt> page, InvHisDt invHisDt);
}