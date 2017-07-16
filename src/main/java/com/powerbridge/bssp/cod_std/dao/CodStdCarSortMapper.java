package com.powerbridge.bssp.cod_std.dao;


import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdCarSort;


public interface CodStdCarSortMapper extends BaseMapper<CodStdCarSort>{
	//public List<CodStdCarSort> selectCarSortByList();
	List<CodStdCarSort> selectRedisDropDown();
}
