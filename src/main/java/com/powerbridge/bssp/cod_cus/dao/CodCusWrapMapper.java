package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusWrap;

public interface CodCusWrapMapper extends BaseMapper<CodCusWrap> {
	List<CodCusWrap> selectRedisDropDown();

}