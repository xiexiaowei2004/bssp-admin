package com.powerbridge.bssp.ems.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems.entity.EmsCusBsc;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("emsCusBscMapper")
public interface EmsCusBscMapper extends BaseMapper<EmsCusBsc> {
    /**
     * 查询加工账册备案正式表/分页
     *
     * @param page 分页
     * @param emsCusBsc
     * @return List<EmsCusBsc>
     */
    List<EmsCusBsc> selectEmsCusBscList(Page<EmsCusBsc> page, EmsCusBsc emsCusBsc);
    /**
     * 查询加工账册备案正式账册编号
     *
     * @return List<EmsPutrecBsc>
     */
    List<EmsCusBsc> selectEmsNoList(EmsCusBsc emsCusBsc);
    /**
     * 获取最大的序号
     * @param seqNo　单据编号
     * @return　序号
     */
    BigDecimal getMaxGdsSeqno(String seqNo);
}