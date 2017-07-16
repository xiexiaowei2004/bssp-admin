package com.powerbridge.bssp.dcr.dao;

import com.powerbridge.bssp.dcr.entity.DcrChgoffCusImg;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * <p>
  * 账册报核料件正式表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
public interface DcrChgoffCusImgMapper extends BaseMapper<DcrChgoffCusImg> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return List<DcrChgoffCusImg>
     */
    List<DcrChgoffCusImg> selectByList(Page<DcrChgoffCusImg> page, String seqNo);
}