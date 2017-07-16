package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusCurr;

public interface CodCusCurrMapper extends BaseMapper<CodCusCurr>{
	List<CodCusCurr> selectRedisDropDown();

}