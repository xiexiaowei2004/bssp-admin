package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusLctype;

public interface CodCusLctypeMapper extends BaseMapper<CodCusLctype> {
	List<CodCusLctype> selectRedisDropDown();

}