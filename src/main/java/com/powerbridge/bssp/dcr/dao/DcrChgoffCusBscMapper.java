package com.powerbridge.bssp.dcr.dao;

import com.powerbridge.bssp.dcr.entity.DcrChgoffCusBsc;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * <p>
  * 账册报核正式表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
public interface DcrChgoffCusBscMapper extends BaseMapper<DcrChgoffCusBsc> {
    /**
     * 查询加工贸易账册核销备案/分页
     *
     * @param page         分页
     * @param dcrChgoffCusBsc 加工贸易账册核销数据
     * @return List<DcrChgoffCusBsc>
     */
    List<DcrChgoffCusBsc> selectByList(Page<DcrChgoffCusBsc> page, DcrChgoffCusBsc dcrChgoffCusBsc);
}