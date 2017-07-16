package com.powerbridge.bssp.sas_cmb.dao;

import com.powerbridge.bssp.sas_cmb.entity.SasCmbBsc;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 耗料单基本信息 Mapper 接口
 * </p>
 *
 * @author huanliu
 * @since 2017-06-18
 */
@Repository("sasCmbBscMapper")
public interface SasCmbBscMapper extends BaseMapper<SasCmbBsc> {



    List<SasCmbBsc> selectSasCmbBscList(Page<SasCmbBsc> page, SasCmbBsc sasCmbBsc);
    // TODO Auto-generated method stub



}