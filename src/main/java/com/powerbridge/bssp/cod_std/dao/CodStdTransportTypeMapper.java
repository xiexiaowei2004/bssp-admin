package com.powerbridge.bssp.cod_std.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdTransportType;


public interface CodStdTransportTypeMapper extends BaseMapper<CodStdTransportType>{
	
	List<CodStdTransportType> selectRedisDropDown();
}