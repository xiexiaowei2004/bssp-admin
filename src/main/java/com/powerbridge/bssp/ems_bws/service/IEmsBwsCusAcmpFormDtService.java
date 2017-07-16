package com.powerbridge.bssp.ems_bws.service;


import com.powerbridge.bssp.ems_bws.entity.EmsBwsCusAcmpFormDt;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 物流账册随附单证明细 服务类
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
public interface IEmsBwsCusAcmpFormDtService extends IService<EmsBwsCusAcmpFormDt> {
	  /**
     * 查询加工账册备案随单附证/分页
     *
     * @param page 分页
     * @param emsBwsAcmpFormDt
     * @return List<EmsPutrecAcmpFormDt>
     */
    Page<EmsBwsCusAcmpFormDt> selectEmsBwsCusAcmpFormDtList(Page<EmsBwsCusAcmpFormDt> page, EmsBwsCusAcmpFormDt emsBwsCusAcmpFormDt);
	
	
}
