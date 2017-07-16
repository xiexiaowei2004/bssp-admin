package com.powerbridge.bssp.sas_cmb.dao;

import com.powerbridge.bssp.sas_cmb.entity.SasCmbRbg;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 耗料单边角料表 Mapper 接口
 * </p>
 *
 * @author huanliu
 * @since 2017-06-18
 */
@Repository("sasCmbRbgMapper")
public interface SasCmbRbgMapper extends BaseMapper<SasCmbRbg> {
    List<SasCmbRbg> selectSasCmbRbgList(Page<SasCmbRbg> page, SasCmbRbg sasCmbRbg);

}