package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclBsc;

import java.util.List;

/**
 * <p>
  * 业务申报表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
public interface SasDclBscMapper extends BaseMapper<SasDclBsc> {
    /**
     * 查询/分页
     *
     * @param page         分页
     * @param sasDclBsc  数据
     * @return List<SasDclBsc>
     */
    List<SasDclBsc> selectByList(Page<SasDclBsc> page, SasDclBsc sasDclBsc);

    List<SasDclBsc> selectBySasDclBscList(Page<SasDclBsc> page, SasDclBsc sasDclBsc);
}