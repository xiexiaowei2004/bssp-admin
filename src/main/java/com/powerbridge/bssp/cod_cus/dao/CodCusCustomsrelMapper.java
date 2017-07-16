package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusCustomsrel;

public interface CodCusCustomsrelMapper extends BaseMapper<CodCusCustomsrel> {
	List<CodCusCustomsrel> selectRedisDropDown();

}