package com.powerbridge.bssp.sas.dao;

import com.powerbridge.bssp.sas.entity.SasVehicleTempReg;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface SasVehicleTempRegMapper {
    List<SasVehicleTempReg> selectBySasVehicleTempReg(SasVehicleTempReg sasVehicleTempReg);
}