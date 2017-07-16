package com.powerbridge.bssp.inv.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.inv.entity.InvHisBsc;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface InvHisBscMapper extends BaseMapper<InvHisBsc> {
    List<InvHisBsc> selectByInvHisBsc(Page<InvHisBsc> page, InvHisBsc invHisBsc);
}