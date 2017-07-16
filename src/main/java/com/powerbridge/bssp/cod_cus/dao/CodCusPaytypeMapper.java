package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusPaytype;

public interface CodCusPaytypeMapper  extends BaseMapper<CodCusPaytype>{
	List<CodCusPaytype> selectRedisDropDown();

}