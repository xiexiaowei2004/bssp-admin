package com.powerbridge.bssp.inv.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.inv.entity.InvBsc;
import com.powerbridge.bssp.inv.entity.InvCusBsc;
import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface InvCusBscMapper extends BaseMapper<InvCusBsc> {
    /**
     * 查询
     *
     * @param page 分页
     * @param InvCusBsc
     * @return List<SasVehicleBsc>
     */
    List<InvCusBsc> selectByInvCusBsc(Page<InvCusBsc> page, InvCusBsc invCusBsc);
}