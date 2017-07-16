package com.powerbridge.bssp.cod_std.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdCarType;

public interface CodStdCarTypeMapper extends BaseMapper<CodStdCarType>{
	List<CodStdCarType> selectRedisDropDown();
}
  
    