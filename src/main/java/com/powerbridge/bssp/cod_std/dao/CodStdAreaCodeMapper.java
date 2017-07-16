package com.powerbridge.bssp.cod_std.dao;


import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdAreaCode;

public interface CodStdAreaCodeMapper extends BaseMapper<CodStdAreaCode>{

	List<CodStdAreaCode> selectRedisDropDown();
	List<CodStdAreaCode> selectCarSortByList(String str);
}
  
    