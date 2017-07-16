package com.powerbridge.bssp.cod_std.dao;


import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdBusType;


public interface CodStdBusTypeMapper extends BaseMapper<CodStdBusType> {
	 //public List<CodStdBusType> selectBusTypeByList();
	List<CodStdBusType> selectRedisDropDown();
}
