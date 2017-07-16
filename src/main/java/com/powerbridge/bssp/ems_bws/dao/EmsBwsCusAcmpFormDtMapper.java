package com.powerbridge.bssp.ems_bws.dao;

import com.powerbridge.bssp.ems_bws.entity.EmsBwsAcmpFormDt;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsCusAcmpFormDt;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 物流账册随附单证明细 Mapper 接口
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
@Repository("emsBwsCusAcmpFormDtMapper")
public interface EmsBwsCusAcmpFormDtMapper extends BaseMapper<EmsBwsCusAcmpFormDt> {
	List<EmsBwsCusAcmpFormDt> selectEmsBwsCusAcmpFormDtList(Page<EmsBwsCusAcmpFormDt> page, EmsBwsCusAcmpFormDt emsBwsCusAcmpFormDt);



}