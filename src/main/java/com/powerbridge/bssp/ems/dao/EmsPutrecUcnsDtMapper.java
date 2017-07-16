package com.powerbridge.bssp.ems.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems.entity.EmsPutrecUcnsDt;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("emsPutrecUcnsDtMapper")
public interface EmsPutrecUcnsDtMapper extends BaseMapper<EmsPutrecUcnsDt> {
    /**
     * 查询加工账册备案单损耗/分页
     *
     * @param page 分页
     * @param emsPutrecUcnsDt
     * @return List<EmsPutrecUcnsDt>
     */
    List<EmsPutrecUcnsDt> selectEmsPutrecUcnsDtList(Page<EmsPutrecUcnsDt> page, EmsPutrecUcnsDt emsPutrecUcnsDt);
    /**
     * 获取最大的序号
     *
     * @return 单据编号
     */
    BigDecimal getMaxUcnsSeqno(String seqNo);
    /**
     * 根据主键查找单耗数据
     * @param uid
     * @return
     */
    EmsPutrecUcnsDt selectByUid(@Param("uid") String uid);
}