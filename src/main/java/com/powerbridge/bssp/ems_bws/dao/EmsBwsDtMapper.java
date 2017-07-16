package com.powerbridge.bssp.ems_bws.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsDt;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 物流账册备案申请明细 Mapper 接口
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
@Repository("emsBwsDtMapper")
public interface EmsBwsDtMapper extends BaseMapper<EmsBwsDt> {
	List<EmsBwsDt> selectEmsBwsDtList(Page<EmsBwsDt> page, EmsBwsDt emsBwsDt);

}