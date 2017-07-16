package com.powerbridge.bssp.saspass.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.saspass.entity.SasPassportDt;
import com.powerbridge.bssp.saspass.entity.SasPassportHisDt;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SasPassportHisDtMapper extends BaseMapper<SasPassportHisDt> {

    /**
     * 根据ID查询
     * 关联计量单位
     * @param uid
     * @return
     */
    SasPassportHisDt selectByUid(@Param("uid") String uid);

    /**
     * 分页查询
     * 关联基础表格，获取中文显示
     * @param page
     * @param sasPassportHisDt
     * @return
     */
    List<SasPassportHisDt> selectBySasPassportHisDt(Page<SasPassportHisDt> page, SasPassportHisDt sasPassportHisDt);
}
