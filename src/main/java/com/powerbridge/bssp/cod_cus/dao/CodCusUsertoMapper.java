package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusUserto;

public interface CodCusUsertoMapper extends BaseMapper<CodCusUserto> {
	List<CodCusUserto> selectRedisDropDown();

}