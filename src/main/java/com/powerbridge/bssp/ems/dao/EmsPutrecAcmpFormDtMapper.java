package com.powerbridge.bssp.ems.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems.entity.EmsPutrecAcmpFormDt;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("emsPutrecAcmpFormDtMapper")
public interface EmsPutrecAcmpFormDtMapper extends BaseMapper<EmsPutrecAcmpFormDt> {
    /**
     * 查询加工账册备案随单附证/分页
     *
     * @param page 分页
     * @param emsPutrecAcmpFormDt
     * @return List<EmsPutrecAcmpFormDt>
     */
    List<EmsPutrecAcmpFormDt> selectEmsPutrecAcmpFormDtList(Page<EmsPutrecAcmpFormDt> page, EmsPutrecAcmpFormDt emsPutrecAcmpFormDt);
    /**
     * 获取最大的序号
     * @param seqNo 单据编号
     * @return
     */
    BigDecimal getMaxAcmpFormDtSeqno(String seqNo);
}