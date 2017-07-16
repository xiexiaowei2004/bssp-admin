package com.powerbridge.bssp.inv.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.inv.entity.InvHisAcmpFormDt;
import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
public interface InvHisAcmpFormDtMapper extends BaseMapper<InvHisAcmpFormDt> {

    List<InvHisAcmpFormDt> selectByList(Page<InvHisAcmpFormDt> invHisAcmpFormDtPage, String seqNo);
}