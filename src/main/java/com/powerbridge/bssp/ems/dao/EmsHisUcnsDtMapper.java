package com.powerbridge.bssp.ems.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems.entity.EmsHisUcnsDt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("emsHisUcnsDtMapper")
public interface EmsHisUcnsDtMapper extends BaseMapper<EmsHisUcnsDt> {
    /**
     * 查询账册备案正式表单损耗列表
     * @param page
     * @param emsHisUcnsDt
     * @return EmsHisUcnsDt
     */
    List<EmsHisUcnsDt> selectEmsHisUcnsDtList(Page<EmsHisUcnsDt> page, EmsHisUcnsDt emsHisUcnsDt);
    /**
     * 根据主键查找单耗数据
     * @param uid
     * @return
     */
    EmsHisUcnsDt selectByUid(String uid);
}