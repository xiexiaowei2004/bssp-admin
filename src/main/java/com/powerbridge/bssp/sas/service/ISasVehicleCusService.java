package com.powerbridge.bssp.sas.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasVehicleCus;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ISasVehicleCusService
 * 类描述：车辆信息备案正式表业务逻辑层接口
 * 创建人：xuzuotao
 * 创建时间：2017年5月19日 下午10:12:17
 */
public interface ISasVehicleCusService extends IService<SasVehicleCus> {
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page          分页
     * @param sasVehicleCus
     * @return Page<SasVehicleCus>
     */
    Page<SasVehicleCus> selectByVehicleCus(Page<SasVehicleCus> page, SasVehicleCus sasVehicleCus);

    /**
     * @param str
     * @return
     * @Description 拼接业务单据sql
     */
    public SasVehicleCus getBusinessData(@Param(value = "str") String str);
}
