package com.powerbridge.bssp.dcr.service;

import com.powerbridge.bssp.dcr.entity.DcrChgoffCusImg;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 账册报核料件正式表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-03
 */
public interface IDcrChgoffCusImgService extends IService<DcrChgoffCusImg> {
    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrChgoffCusImg>
     */
    Page<DcrChgoffCusImg> selectByList(Page<DcrChgoffCusImg> page, String seqNo);
}
