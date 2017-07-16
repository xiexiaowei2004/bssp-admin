package com.powerbridge.bssp.dcr.dao;

import com.powerbridge.bssp.dcr.entity.DcrChgoffHisImg;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * <p>
  * 账册报核料件历史表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
public interface DcrChgoffHisImgMapper extends BaseMapper<DcrChgoffHisImg> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param dcrChgoffHisImg
     * @return List<DcrChgoffHisImg>
     */
    List<DcrChgoffHisImg> selectByList(Page<DcrChgoffHisImg> page, DcrChgoffHisImg dcrChgoffHisImg);
}