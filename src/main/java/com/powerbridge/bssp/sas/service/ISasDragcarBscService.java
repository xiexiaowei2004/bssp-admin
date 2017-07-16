package com.powerbridge.bssp.sas.service;

import com.powerbridge.bssp.sas.entity.SasDragcarBsc;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasDclBsc;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 扡卡备案表 服务类
 * </p>
 *
 * @author huanliu
 * @since 2017-06-21
 */
public interface ISasDragcarBscService extends IService<SasDragcarBsc> {
    /**
     * 查询/分页
     *
     * @param page         分页
     * @param sasDragcarBsc 数据
     * @return Page<sasDragcarBsc>
     */
    Page<SasDragcarBsc> selectSasDragcarBscList(Page<SasDragcarBsc> page, SasDragcarBsc sasDragcarBsc);
}
