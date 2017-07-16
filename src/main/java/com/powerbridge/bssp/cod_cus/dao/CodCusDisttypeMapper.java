package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusDisttype;

public interface CodCusDisttypeMapper extends BaseMapper<CodCusDisttype> {
	List<CodCusDisttype> selectRedisDropDown();

}