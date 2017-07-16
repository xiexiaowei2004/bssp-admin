package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusTrans;

public interface CodCusTransMapper  extends BaseMapper<CodCusTrans>{
	List<CodCusTrans> selectRedisDropDown();

}