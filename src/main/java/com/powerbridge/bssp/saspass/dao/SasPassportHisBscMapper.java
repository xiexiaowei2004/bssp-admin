package com.powerbridge.bssp.saspass.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import com.powerbridge.bssp.saspass.entity.SasPassportHisBsc;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SasPassportHisBscMapper extends BaseMapper<SasPassportHisBsc> {

    /**
     * 根据ID查询
     * 关联基础表格，获取中文显示
     * @param uid
     * @return
     */
    SasPassportHisBsc selectByUid(@Param("uid") String uid);

    /**
     * 分页查询
     * 关联基础表格，获取中文显示
     * @param page
     * @param sasPassportHisBsc
     * @return
     */
    List<SasPassportHisBsc> selectBySasPassportHisBsc(Page<SasPassportHisBsc> page, SasPassportHisBsc sasPassportHisBsc);
}
