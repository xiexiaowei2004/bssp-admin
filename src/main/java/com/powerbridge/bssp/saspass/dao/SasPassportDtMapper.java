package com.powerbridge.bssp.saspass.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.saspass.entity.SasPassportDt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SasPassportDtMapper extends BaseMapper<SasPassportDt> {

    /**
     * 根据ID查询
     * 关联计量单位
     * @param uid
     * @return
     */
    SasPassportDt selectByUid(@Param("uid") String uid);

    /**
     * 分页查询
     * 关联基础表格，获取中文显示
     * @param page
     * @param sasPassportDt
     * @return
     */
    List<SasPassportDt> selectBySasPassportDt(Page<SasPassportDt> page, SasPassportDt sasPassportDt);

    /**
     * 批量插入
     * @param entitys
     */
    public Integer insertByBatch(List<SasPassportDt> entitys);
}
