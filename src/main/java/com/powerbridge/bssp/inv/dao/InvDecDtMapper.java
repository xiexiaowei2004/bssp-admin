package com.powerbridge.bssp.inv.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.inv.entity.InvDecDt;
import java.util.List;


/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zsl
 * @since 2017-06-09
 */
public interface InvDecDtMapper extends BaseMapper<InvDecDt> {

    List<InvDecDt> selectByInvDeclare(Page<InvDecDt>page,InvDecDt invDecDt);
}