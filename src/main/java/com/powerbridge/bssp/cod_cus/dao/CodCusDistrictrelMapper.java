package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusDistrictrel;

public interface CodCusDistrictrelMapper extends BaseMapper<CodCusDistrictrel> {
	
	List<CodCusDistrictrel> selectRedisDropDown();

}