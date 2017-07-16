package com.powerbridge.bssp.ems_bws.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsHisDt;
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
public interface IEmsBwsHisDtService extends IService<EmsBwsHisDt> {
	 /**
     * 根据条件查询表数据
     *
     * @param page
     * @param emsBwsHisDt
     * @return
     * @throws Exception
     */
    Page<EmsBwsHisDt> selectEmsBwsHisDtList(Page<EmsBwsHisDt> page, EmsBwsHisDt emsBwsHisDt) throws Exception;
}
