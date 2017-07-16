package com.powerbridge.bssp.saspass.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.saspass.entity.SasPassportCusDt;
import com.powerbridge.bssp.saspass.entity.SasPassportDt;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SasPassportCusDtMapper extends BaseMapper<SasPassportCusDt> {

    /**
     * 根据ID查询
     * 关联计量单位
     * @param uid
     * @return
     */
    SasPassportCusDt selectByUid(@Param("uid") String uid);

    /**
     * 分页查询
     * 关联基础表格，获取中文显示
     * @param page
     * @param sasPassportCusDt
     * @return
     */
    List<SasPassportCusDt> selectBySasPassportCusDt(Page<SasPassportCusDt> page, SasPassportCusDt sasPassportCusDt);
}
