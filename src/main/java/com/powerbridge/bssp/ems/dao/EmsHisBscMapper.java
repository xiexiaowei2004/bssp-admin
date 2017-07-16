package com.powerbridge.bssp.ems.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems.entity.EmsHisBsc;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("emsHisBscMapper")
public interface EmsHisBscMapper extends BaseMapper<EmsHisBsc> {
    /**
     * 查询加工账册备案正式表/分页
     *
     * @param page 分页
     * @param emsHisBsc
     * @return List<EmsHisBsc>
     */
    List<EmsHisBsc> selectEmsHisBscList(Page<EmsHisBsc> page, EmsHisBsc emsHisBsc);
}