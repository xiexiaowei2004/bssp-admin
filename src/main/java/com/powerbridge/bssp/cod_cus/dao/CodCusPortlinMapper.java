package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusPortlin;

public interface CodCusPortlinMapper  extends BaseMapper<CodCusPortlin> {
	List<CodCusPortlin> selectRedisDropDown();

}