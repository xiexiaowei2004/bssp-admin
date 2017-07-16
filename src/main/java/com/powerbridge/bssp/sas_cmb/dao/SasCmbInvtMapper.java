package com.powerbridge.bssp.sas_cmb.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas_cmb.entity.SasCmbImg;
import com.powerbridge.bssp.sas_cmb.entity.SasCmbInvt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 耗料单料件表 Mapper 接口
 * </p>
 *
 * @author huanliu
 * @since 2017-06-18
 */
@Repository("sasCmbInvtMapper")

public interface SasCmbInvtMapper extends BaseMapper<SasCmbInvt> {
    List<SasCmbInvt> selectSasCmbInvtList(Page<SasCmbInvt> page, SasCmbInvt sasCmbInvt);

}