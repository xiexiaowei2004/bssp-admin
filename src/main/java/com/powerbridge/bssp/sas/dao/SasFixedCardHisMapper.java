package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasFixedCardHis;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author luohongsu
 * @since 2017-07-05
 */
@Repository("sasFixedCardHisMapper")
public interface SasFixedCardHisMapper extends BaseMapper<SasFixedCardHis> {
    /**
     * 查询长期卡备案信息申请表/分页
     *
     * @param page 分页
     * @param sasFixedCardHis
     * @return List<SasFixedCardHis>
     */
    List<SasFixedCardHis> selectByFixedCardHis(Page<SasFixedCardHis> page, SasFixedCardHis sasFixedCardHis);
}