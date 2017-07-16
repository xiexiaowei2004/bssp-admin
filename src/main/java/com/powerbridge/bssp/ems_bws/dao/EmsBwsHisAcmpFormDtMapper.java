package com.powerbridge.bssp.ems_bws.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsHisAcmpFormDt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * 物流账册随附单证明细 Mapper 接口
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
@Repository("emsBwsHisAcmpFormDtMapper")
public interface EmsBwsHisAcmpFormDtMapper extends BaseMapper<EmsBwsHisAcmpFormDt> {
	/**
	 * 查询物流账册历史表随附单证/分页
	 *
	 * @param page 分页
	 * @param emsBwsHisAcmpFormDt
	 * @return List<EmsBwsHisAcmpFormDt>
	 */
	List<EmsBwsHisAcmpFormDt> selectEmsBwsHisAcmpFormDtList(Page<EmsBwsHisAcmpFormDt> page, EmsBwsHisAcmpFormDt emsBwsHisAcmpFormDt);
}