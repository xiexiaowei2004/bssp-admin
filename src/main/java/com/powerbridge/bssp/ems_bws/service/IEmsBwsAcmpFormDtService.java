package com.powerbridge.bssp.ems_bws.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsAcmpFormDt;

/**
 * <p>
 * 账册备案正式表随附单证 服务类
 * </p>
 *
 * @author huanliu
 * @since 2017-05-25
 */
public interface IEmsBwsAcmpFormDtService extends IService<EmsBwsAcmpFormDt> {
	  /**
     * 查询加工账册备案随单附证/分页
     *
     * @param page 分页
     * @param emsBwsAcmpFormDt
     * @return List<EmsPutrecAcmpFormDt>
     */
    Page<EmsBwsAcmpFormDt> selectEmsBwsAcmpFormDtList(Page<EmsBwsAcmpFormDt> page, EmsBwsAcmpFormDt emsBwsAcmpFormDt);


}
	
