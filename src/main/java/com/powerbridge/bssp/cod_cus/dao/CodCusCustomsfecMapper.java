package com.powerbridge.bssp.cod_cus.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusCustomsfec;

import java.util.List;

public interface CodCusCustomsfecMapper extends BaseMapper<CodCusCustomsfec> {
	List<CodCusCustomsfec> selectRedisDropDown();
}