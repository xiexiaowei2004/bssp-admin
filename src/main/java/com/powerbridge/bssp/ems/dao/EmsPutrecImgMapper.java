package com.powerbridge.bssp.ems.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems.entity.EmsPutrecImg;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("emsPutrecImgMapper")
public interface EmsPutrecImgMapper extends BaseMapper<EmsPutrecImg> {
    /**
     * 查询加工账册备案料件/分页
     *
     * @param page 分页
     * @param emsPutrecImg
     * @return List<EmsPutrecImg>
     */
    List<EmsPutrecImg> selectEmsPutrecImgList(Page<EmsPutrecImg> page, EmsPutrecImg emsPutrecImg);
    /**
     * 获取最大的商品序号
     * @param seqNo 单据编号
     * @return 商品序号
     */
    BigDecimal getMaxGdsSeqno(String seqNo);
}