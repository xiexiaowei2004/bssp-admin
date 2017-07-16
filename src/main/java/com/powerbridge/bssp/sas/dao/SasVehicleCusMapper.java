package com.powerbridge.bssp.sas.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasVehicleCus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：SasVehicleCusMapper
 * 类描述： 车辆备案信息正式表数据访问层接口
 * 创建人：xuzuotao
 * 创建时间：2017年5月19日 下午10:54:17
 */
public interface SasVehicleCusMapper extends BaseMapper<SasVehicleCus> {
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page          分页
     * @param sasVehicleCus
     * @return List<SasVehicleBsc>
     */
    List<SasVehicleCus> selectByVehicleCus(Page<SasVehicleCus> page, SasVehicleCus sasVehicleCus);

    /**
     * @param str
     * @return
     * @Description 拼接业务单据sql
     */
    public SasVehicleCus getBusinessData(@Param(value = "str") String str);
}