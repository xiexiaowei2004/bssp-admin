package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusUnit;

public interface CodCusUnitMapper extends BaseMapper<CodCusUnit> {
	List<CodCusUnit> selectRedisDropDown();

}