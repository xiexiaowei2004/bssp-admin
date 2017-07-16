package com.powerbridge.bssp.sas.service;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasVehicleBsc;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ISasVehicleBscService
 * 类描述：车辆信息备案申请表业务逻辑层接口
 * 创建人：xuzuotao
 * 创建时间：2017年5月19日 下午10:12:17
 */
public interface ISasVehicleBscService extends IService<SasVehicleBsc> {
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page 分页
     * @param sasVehicleBsc
     * @return Page<SasVehicleBsc>
     */
    Page<SasVehicleBsc> selectByVehicleBsc(Page<SasVehicleBsc> page, SasVehicleBsc sasVehicleBsc);
    
    /**
	 * 
	 * @Description 拼接业务单据sql
	 * @param str
	 * @return
	 */
    public SasVehicleBsc getBusinessData(@Param(value="str") String str);
}
