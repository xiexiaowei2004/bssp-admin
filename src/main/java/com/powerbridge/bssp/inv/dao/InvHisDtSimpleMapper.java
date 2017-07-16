package com.powerbridge.bssp.inv.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.inv.entity.InvCusDtSimple;
import com.powerbridge.bssp.inv.entity.InvHisDtSimple;

import java.util.List;

/**
 * <p>
  * 保税核注清单料件 Mapper 接口
 * </p>
 *
 * @author zsl
 * @since 2017-06-03
 */
public interface InvHisDtSimpleMapper extends BaseMapper<InvHisDtSimple> {


    /**
     * 查询
     *
     * @param page 分页
     * @param invCusDtSimple
     * @return List<invCusDtSimple>
     */
    List<InvHisDtSimple> selectByInvHisDt(Page<InvHisDtSimple> page, InvHisDtSimple invHisDtSimple);

}