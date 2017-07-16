package com.powerbridge.bssp.ems.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems.entity.EmsHisAcmpFormDt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("emsHisAcmpFormDtMapper")
public interface EmsHisAcmpFormDtMapper extends BaseMapper<EmsHisAcmpFormDt> {
    /**
     * 查询加工账册历史表随单附证/分页
     *
     * @param page 分页
     * @param emsHisAcmpFormDt
     * @return List<EmsHisAcmpFormDt>
     */
    List<EmsHisAcmpFormDt> selectEmsHisAcmpFormDtList(Page<EmsHisAcmpFormDt> page, EmsHisAcmpFormDt emsHisAcmpFormDt);
}