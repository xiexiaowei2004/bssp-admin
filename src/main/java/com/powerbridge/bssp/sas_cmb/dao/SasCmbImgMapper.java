package com.powerbridge.bssp.sas_cmb.dao;

import com.powerbridge.bssp.sas_cmb.entity.SasCmbImg;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 耗料单料件表 Mapper 接口
 * </p>
 *
 * @author huanliu
 * @since 2017-06-18
 */
@Repository("sasCmbImgMapper")

public interface SasCmbImgMapper extends BaseMapper<SasCmbImg> {
    List<SasCmbImg> selectSasCmbImgList(Page<SasCmbImg> page, SasCmbImg sasCmbImg);

}