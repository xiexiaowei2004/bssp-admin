package com.powerbridge.bssp.ems.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems.entity.EmsPutrecExg;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("emsPutrecExgMapper")
public interface EmsPutrecExgMapper extends BaseMapper<EmsPutrecExg> {
    /**
     * 查询加工账册备案成品/分页
     *
     * @param page 分页
     * @param emsPutrecExg
     * @return List<EmsPutrecExg>
     */
    List<EmsPutrecExg> selectEmsPutrecExgList(Page<EmsPutrecExg> page, EmsPutrecExg emsPutrecExg);
    /**
     * 获取最大的商品序号
     * @param seqNo 单据编号
     * @return 商品序号
     */
    BigDecimal getMaxGdsSeqno(String seqNo);
}