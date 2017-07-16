package com.powerbridge.bssp.sas.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasVehicleIReg;

/**
 * @ClassName: ISasVehicleIReg
 * @Description: 车辆入区登记Service接口
 * @author: songke  
 * @createTime: 2017年5月20日 上午10:51:08 
 * @version: v1.0
 */
public interface ISasVehicleIRegService extends IService<SasVehicleIReg> {

    /**
     * 分页查询
     * @param page
     * @param sasVehicleIReg
     * @return
     */
    public Page<SasVehicleIReg> selectBySasVehicleIReg(Page<SasVehicleIReg> page, SasVehicleIReg sasVehicleIReg);

}
  
    