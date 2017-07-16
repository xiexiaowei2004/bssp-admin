package com.powerbridge.bssp.dcr.dao;

import com.powerbridge.bssp.dcr.entity.DcrChgoffHisBsc;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * <p>
  * 账册报核历史表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
public interface DcrChgoffHisBscMapper extends BaseMapper<DcrChgoffHisBsc> {
    /**
     * 查询加工贸易账册核销备案/分页
     *
     * @param page         分页
     * @param dcrChgoffHisBsc 加工贸易账册核销数据
     * @return List<DcrChgoffHisBsc>
     */
    List<DcrChgoffHisBsc> selectByList(Page<DcrChgoffHisBsc> page, DcrChgoffHisBsc dcrChgoffHisBsc);
}