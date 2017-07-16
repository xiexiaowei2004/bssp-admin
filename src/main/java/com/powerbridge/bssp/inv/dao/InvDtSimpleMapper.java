package com.powerbridge.bssp.inv.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.inv.entity.InvDtSimple;
import java.util.List;

/**
 * <p>
  * 简单加工成品明细 Mapper 接口
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface InvDtSimpleMapper extends BaseMapper<InvDtSimple> {

    /**
     * 查询
     *
     * @param page 分页
     * @param invDtSimple
     * @return List<InvImg>
     */
    List<InvDtSimple> selectByInvDtSimple(Page<InvDtSimple> page, InvDtSimple invDtSimple);

}