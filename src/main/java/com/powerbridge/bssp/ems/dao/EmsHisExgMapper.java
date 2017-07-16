package com.powerbridge.bssp.ems.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems.entity.EmsHisExg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("emsHisExgMapper")
public interface EmsHisExgMapper extends BaseMapper<EmsHisExg> {
    /**
     * 查询加工账册备案料件/分页
     *
     * @param page 分页
     * @param emsHisExg
     * @return Page<EmsHisImg>
     */
    List<EmsHisExg> selectEmsHisExgList(Page<EmsHisExg> page, EmsHisExg emsHisExg);
}