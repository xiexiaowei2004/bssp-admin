package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusLevytype;

public interface CodCusLevytypeMapper extends BaseMapper<CodCusLevytype> {
	List<CodCusLevytype> selectRedisDropDown();


}