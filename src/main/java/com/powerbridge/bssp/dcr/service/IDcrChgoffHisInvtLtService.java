package com.powerbridge.bssp.dcr.service;

import com.powerbridge.bssp.dcr.entity.DcrChgoffHisInvtLt;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 账册报核清单历史表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-03
 */
public interface IDcrChgoffHisInvtLtService extends IService<DcrChgoffHisInvtLt> {
    /**
     * 查询 分页
     *
     * @param page  分页
     * @param dcrChgoffHisInvtLt
     * @return Page<DcrChgoffInvtLt>
     */
    Page<DcrChgoffHisInvtLt> selectByList(Page<DcrChgoffHisInvtLt> page, DcrChgoffHisInvtLt dcrChgoffHisInvtLt);
}
