package com.powerbridge.bssp.inv.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.inv.entity.InvCusAcmpFormDt;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface InvCusAcmpFormDtMapper extends BaseMapper<InvCusAcmpFormDt> {
    List<InvCusAcmpFormDt> selectByList(Page<InvCusAcmpFormDt> invCusAcmpFormDtPage, String seqNo);
}