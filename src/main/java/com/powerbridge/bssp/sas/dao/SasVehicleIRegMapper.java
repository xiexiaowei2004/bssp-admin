package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasVehicleIReg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SasVehicleIRegMapper extends BaseMapper<SasVehicleIReg> {

    List<SasVehicleIReg> selectBySasVehicleIReg(Page<SasVehicleIReg> page, SasVehicleIReg sasVehicleIReg);
}