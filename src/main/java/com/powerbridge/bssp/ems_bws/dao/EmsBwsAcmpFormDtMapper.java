package com.powerbridge.bssp.ems_bws.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsAcmpFormDt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * 账册备案正式表随附单证 Mapper 接口
 * </p>
 *
 * @author huanliu
 * @since 2017-05-25
 */
@Repository("emsBwsAcmpFormDtMapper")
public interface EmsBwsAcmpFormDtMapper extends BaseMapper<EmsBwsAcmpFormDt> {
	List<EmsBwsAcmpFormDt> selectEmsBwsAcmpFormDtList(Page<EmsBwsAcmpFormDt> page, EmsBwsAcmpFormDt emsBwsAcmpFormDt);

}