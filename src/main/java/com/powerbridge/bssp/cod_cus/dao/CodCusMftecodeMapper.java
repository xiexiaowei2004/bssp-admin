package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusMftecode;

public interface CodCusMftecodeMapper extends BaseMapper<CodCusMftecode> {
	List<CodCusMftecode> selectRedisDropDown();

}