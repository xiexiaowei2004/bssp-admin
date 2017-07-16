package com.powerbridge.bssp.ems_bws.service;

import com.powerbridge.bssp.ems_bws.entity.EmsBwsCusDt;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsDt;

import java.util.List;

/**
 * <p>
 * 物流账册正式表明细 服务类
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
public interface IEmsBwsCusDtService extends IService<EmsBwsCusDt> {
	 /**
     * 根据条件查询表数据
     *
     * @param page
     * @param emsBwsCusDt
     * @return
     * @throws Exception
     */
    Page<EmsBwsCusDt> selectEmsBwsCusDtList(Page<EmsBwsCusDt> page, EmsBwsCusDt emsBwsCusDt) throws Exception;

    /**
     * 根据账册编号，获取账册表体
     * @param bwsNo
     * @return
     */
    List<EmsBwsDt> getEmsBwsDtChgData(String bwsNo);
}
