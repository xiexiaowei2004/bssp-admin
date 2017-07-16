package com.powerbridge.bssp.dcr.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.dcr.entity.DcrChgoffCusExg;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 账册报核成品正式表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-03
 */
public interface IDcrChgoffCusExgService extends IService<DcrChgoffCusExg> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrChgoffCusExg>
     */
    Page<DcrChgoffCusExg> selectByList(Page<DcrChgoffCusExg> page, String seqNo);
}
