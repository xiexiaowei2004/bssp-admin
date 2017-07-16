package com.powerbridge.bssp.cod_std.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdTrpType;


public interface CodStdTrpTypeMapper extends BaseMapper<CodStdTrpType>{
	List<CodStdTrpType> selectRedisDropDown();
}