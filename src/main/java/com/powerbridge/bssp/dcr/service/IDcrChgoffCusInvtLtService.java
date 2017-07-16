package com.powerbridge.bssp.dcr.service;

import com.powerbridge.bssp.dcr.entity.DcrChgoffCusInvtLt;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 账册报核清单正式表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-03
 */
public interface IDcrChgoffCusInvtLtService extends IService<DcrChgoffCusInvtLt> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrChgoffInvtLt>
     */
    Page<DcrChgoffCusInvtLt> selectByList(Page<DcrChgoffCusInvtLt> page, String seqNo);
}
