package com.powerbridge.bssp.ems_bws.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsHisAcmpFormDt;

/**
 * <p>
 * 物流账册随附单证明细 服务类
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
public interface IEmsBwsHisAcmpFormDtService extends IService<EmsBwsHisAcmpFormDt> {
	  /**
     * 查询加工账册备案随单附证/分页
     *
     * @param page 分页
     * @param emsBwsHisAcmpFormDt
     * @return List<EmsBwsHisAcmpFormDt>
     */
    Page<EmsBwsHisAcmpFormDt> selectEmsBwsHisAcmpFormDtList(Page<EmsBwsHisAcmpFormDt> page, EmsBwsHisAcmpFormDt emsBwsHisAcmpFormDt);
	
	
}
