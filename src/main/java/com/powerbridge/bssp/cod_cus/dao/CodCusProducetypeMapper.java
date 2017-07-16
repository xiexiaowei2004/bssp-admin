package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusProducetype;

public interface CodCusProducetypeMapper  extends BaseMapper<CodCusProducetype> {
	List<CodCusProducetype> selectRedisDropDown();

}