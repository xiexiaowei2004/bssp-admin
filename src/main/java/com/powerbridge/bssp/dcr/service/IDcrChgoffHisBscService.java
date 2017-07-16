package com.powerbridge.bssp.dcr.service;

import com.powerbridge.bssp.dcr.entity.DcrChgoffHisBsc;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 账册报核历史表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-03
 */
public interface IDcrChgoffHisBscService extends IService<DcrChgoffHisBsc> {

    /**
     * 查询加工贸易账册核销备案/分页
     *
     * @param page         分页
     * @param dcrChgoffHisBsc 数据
     * @return Page<DcrChgoffHisBsc>
     */
    Page<DcrChgoffHisBsc> selectByList(Page<DcrChgoffHisBsc> page, DcrChgoffHisBsc dcrChgoffHisBsc);
}
