package com.powerbridge.bssp.sas.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasDragcarHis;

/**
 * <p>
 * 扡卡历史表 服务类
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-05
 */
public interface ISasDragcarHisService extends IService<SasDragcarHis> {
    /**
     * 查询/分页
     *
     * @param page         分页
     * @param sasDragcarHis 数据
     * @return Page<sasDragcarHis>
     */
    Page<SasDragcarHis> selectSasDragcarHisList(Page<SasDragcarHis> page, SasDragcarHis sasDragcarHis);
}
