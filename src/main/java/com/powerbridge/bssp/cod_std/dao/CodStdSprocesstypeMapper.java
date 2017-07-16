package com.powerbridge.bssp.cod_std.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdSprocesstype;

public interface CodStdSprocesstypeMapper extends BaseMapper<CodStdSprocesstype> {
	List<CodStdSprocesstype> selectRedisDropDown();
}