package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusMfteccode;

public interface CodCusMfteccodeMapper  extends BaseMapper<CodCusMfteccode>{
	List<CodCusMfteccode> selectRedisDropDown();

}