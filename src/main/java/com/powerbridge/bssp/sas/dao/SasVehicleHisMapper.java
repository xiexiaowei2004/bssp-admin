package com.powerbridge.bssp.sas.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasVehicleHis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：SasVehicleHisMapper
 * 类描述： 车辆备案信息历记录表数据访问层接口
 * 创建人：xuzuotao
 * 创建时间：2017年5月19日 下午10:54:17
 */
public interface SasVehicleHisMapper extends BaseMapper<SasVehicleHis> {
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page          分页
     * @param sasVehicleHis
     * @return List<SasVehicleHis>
     */
    List<SasVehicleHis> selectByVehicleHis(Page<SasVehicleHis> page, SasVehicleHis sasVehicleHis);

    /**
     * @param str
     * @return
     * @Description 拼接业务单据sql
     */
    public SasVehicleHis getBusinessData(@Param(value = "str") String str);
}