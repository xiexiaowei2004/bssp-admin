package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasFixedCardCus;
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
@Repository("sasFixedCardCusMapper")
public interface SasFixedCardCusMapper extends BaseMapper<SasFixedCardCus> {
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page 分页
     * @param sasFixedCardCus
     * @return List<SasFixedCardCus>
     */
    List<SasFixedCardCus> selectByFixedCardCus(Page<SasFixedCardCus> page, SasFixedCardCus sasFixedCardCus);
}