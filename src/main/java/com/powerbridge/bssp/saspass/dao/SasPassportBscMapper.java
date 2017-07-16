package com.powerbridge.bssp.saspass.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SasPassportBscMapper extends BaseMapper<SasPassportBsc> {

    /**
     * 根据ID查询
     * 关联基础表格，获取中文显示
     * @param uid
     * @return
     */
    SasPassportBsc selectByUid(@Param("uid") String uid);

    /**
     * 分页查询
     * 关联基础表格，获取中文显示
     * @param page
     * @param sasPassportBsc
     * @return
     */
    List<SasPassportBsc> selectBySasPassportBsc(Page<SasPassportBsc> page, SasPassportBsc sasPassportBsc);
}
