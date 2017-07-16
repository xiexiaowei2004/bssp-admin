package com.powerbridge.bssp.cod_std.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdEnttype;

public interface CodStdEnttypeMapper extends BaseMapper<CodStdEnttype> {
	List<CodStdEnttype> selectRedisDropDown();
}