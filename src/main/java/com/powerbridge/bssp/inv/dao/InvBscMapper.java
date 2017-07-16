package com.powerbridge.bssp.inv.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.inv.entity.InvBsc;
import java.util.List;

/**
 * <p>
  * 保税核注清单表  Mapper 接口
 * </p>
 *
 * @author zsl
 * @since 2017-06-03
 */
public interface InvBscMapper extends BaseMapper<InvBsc> {

    /**
     * 查询
     *
     * @param page 分页
     * @param invBsc
     * @return List<SasVehicleBsc>
     */
    List<InvBsc> selectByInvBsc(Page<InvBsc> page, InvBsc invBsc);

}