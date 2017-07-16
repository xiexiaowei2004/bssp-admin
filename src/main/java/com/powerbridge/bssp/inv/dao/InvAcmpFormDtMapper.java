package com.powerbridge.bssp.inv.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.inv.entity.InvAcmpFormDt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 保税核注清单随附单证明细表 Mapper 接口
 * </p>
 *
 * @author zsl
 * @since 2017-06-20
 */
public interface InvAcmpFormDtMapper extends BaseMapper<InvAcmpFormDt> {

    List<InvAcmpFormDt> selectByList(Page<InvAcmpFormDt> invAcmpFormDtPage, String seqNo);

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    int updateAcmpFormSeqno(@Param("seqNo") String seqNo, @Param("acmpFormSeqno") int acmpFormSeqno);

}