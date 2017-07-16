package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDragcarHis;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * 扡卡历史表 Mapper 接口
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-05
 */
@Repository
public interface SasDragcarHisMapper extends BaseMapper<SasDragcarHis> {
    /**
     *
     * @param page
     * @param sasDragcarHis
     * @return  List<SasDclHis>
     */

    List<SasDragcarHis> selectSasDragcarHisList(Page<SasDragcarHis> page, SasDragcarHis sasDragcarHis);
}