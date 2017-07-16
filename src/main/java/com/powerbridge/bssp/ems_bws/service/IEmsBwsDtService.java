package com.powerbridge.bssp.ems_bws.service;


import com.powerbridge.bssp.ems_bws.entity.EmsBwsDt;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 物流账册备案申请明细 服务类
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
public interface IEmsBwsDtService extends IService<EmsBwsDt> {
	 /**
     * 根据条件查询表数据
     *
     * @param page
     * @param emsBwsDt
     * @return
     * @throws Exception
     */
    Page<EmsBwsDt> selectEmsBwsDtList(Page<EmsBwsDt> page, EmsBwsDt emsBwsDt) throws Exception;
}