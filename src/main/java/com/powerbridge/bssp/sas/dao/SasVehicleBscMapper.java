package com.powerbridge.bssp.sas.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.dcr.entity.DcrChgoffBsc;
import com.powerbridge.bssp.sas.entity.SasVehicleBsc;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：SasVehicleBscMapper
 * 类描述： 车辆备案信息申请表数据访问层接口
 * 创建人：xuzuotao
 * 创建时间：2017年5月19日 下午10:54:17
 */
public interface SasVehicleBscMapper extends BaseMapper<SasVehicleBsc> {
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page 分页
     * @param sasVehicleBsc
     * @return List<SasVehicleBsc>
     */
    List<SasVehicleBsc> selectByVehicleBsc(Page<SasVehicleBsc> page, SasVehicleBsc sasVehicleBsc);
    
    /**
	 * 
	 * @Description 拼接业务单据sql
	 * @param str
	 * @return
	 */
    public SasVehicleBsc getBusinessData(@Param(value="str") String str);
}