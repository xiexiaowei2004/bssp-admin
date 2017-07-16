package com.powerbridge.bssp.dcr.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.dcr.entity.DcrChgoffCusBsc;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 账册报核正式表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-03
 */
public interface IDcrChgoffCusBscService extends IService<DcrChgoffCusBsc> {

    /**
     * 查询加工贸易账册核销备案/分页
     *
     * @param page         分页
     * @param dcrChgoffCusBsc 数据
     * @return Page<DcrChgoffCusBsc>
     */
    Page<DcrChgoffCusBsc> selectByList(Page<DcrChgoffCusBsc> page, DcrChgoffCusBsc dcrChgoffCusBsc);
}
