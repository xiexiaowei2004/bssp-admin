package com.powerbridge.bssp.inv.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.inv.entity.InvCusDt;
import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface InvCusDtMapper extends BaseMapper<InvCusDt> {

    /**
     * 查询
     *
     * @param page 分页
     * @param InvCusDt
     * @return List<InvCusDt>
     */
    List<InvCusDt> selectByInvCusDt(Page<InvCusDt> page, InvCusDt invCusDt);

}