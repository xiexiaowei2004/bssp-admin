package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDragcarCus;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * 扡卡正式表 Mapper 接口
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-05
 */
@Repository
public interface SasDragcarCusMapper extends BaseMapper<SasDragcarCus> {
    /**
     *
     * @param page
     * @param sasDragcarCus
     * @return  List<SasDclCus>
     */

    List<SasDragcarCus> selectSasDragcarCusList(Page<SasDragcarCus> page, SasDragcarCus sasDragcarCus);
}