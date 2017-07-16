package com.powerbridge.bssp.dcr.service;

import com.powerbridge.bssp.dcr.entity.DcrChgoffHisExg;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 账册报核成品历史表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-03
 */
public interface IDcrChgoffHisExgService extends IService<DcrChgoffHisExg> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param dcrChgoffHisExg 单据编号
     * @return Page<DcrChgoffHisExg>
     */
    Page<DcrChgoffHisExg> selectByList(Page<DcrChgoffHisExg> page, DcrChgoffHisExg dcrChgoffHisExg);
}
