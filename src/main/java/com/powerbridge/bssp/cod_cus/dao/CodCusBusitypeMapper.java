package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusBusitype;

public interface CodCusBusitypeMapper extends BaseMapper<CodCusBusitype>{
	List<CodCusBusitype> selectRedisDropDown();

}