package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusLevymode;

public interface CodCusLevymodeMapper extends BaseMapper<CodCusLevymode> {
	List<CodCusLevymode> selectRedisDropDown();

}