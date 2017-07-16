package com.powerbridge.bssp.ems.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems.entity.EmsHisImg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("emsHisImgMapper")
public interface EmsHisImgMapper extends BaseMapper<EmsHisImg> {
    /**
     * 查询加工账册备案料件/分页
     *
     * @param page 分页
     * @param emsHisImg
     * @return Page<EmsHisImg>
     */
    List<EmsHisImg> selectEmsHisImgList(Page<EmsHisImg> page, EmsHisImg emsHisImg);
}