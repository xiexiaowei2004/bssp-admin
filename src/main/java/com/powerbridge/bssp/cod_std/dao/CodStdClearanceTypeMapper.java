package com.powerbridge.bssp.cod_std.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdClearanceType;

public interface CodStdClearanceTypeMapper extends BaseMapper<CodStdClearanceType>{
	List<CodStdClearanceType> selectRedisDropDown();
}
  
    