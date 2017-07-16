package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusCountry;

public interface CodCusCountryMapper extends BaseMapper<CodCusCountry>{
	List<CodCusCountry> selectRedisDropDown();

}