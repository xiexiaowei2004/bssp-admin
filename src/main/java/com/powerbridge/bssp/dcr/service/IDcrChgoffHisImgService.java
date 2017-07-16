package com.powerbridge.bssp.dcr.service;

import com.powerbridge.bssp.dcr.entity.DcrChgoffHisImg;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 账册报核料件历史表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-03
 */
public interface IDcrChgoffHisImgService extends IService<DcrChgoffHisImg> {
    /**
     * 查询 分页
     *
     * @param page  分页
     * @param dcrChgoffHisImg
     * @return Page<DcrChgoffCusImg>
     */
    Page<DcrChgoffHisImg> selectByList(Page<DcrChgoffHisImg> page, DcrChgoffHisImg dcrChgoffHisImg);
}
