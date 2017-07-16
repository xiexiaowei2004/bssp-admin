package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasFixedCardBsc;
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
@Repository("sasFixedCardBscMapper")
public interface SasFixedCardBscMapper extends BaseMapper<SasFixedCardBsc> {

    /**
     * 查询长期卡备案信息申请表/分页
     *
     * @param page 分页
     * @param sasFixedCardBsc
     * @return List<SasFixedCardBsc>
     */
    List<SasFixedCardBsc> selectByFixedCardBsc(Page<SasFixedCardBsc> page, SasFixedCardBsc sasFixedCardBsc);

    SasFixedCardBsc selectByIcCode(String icCode);
}