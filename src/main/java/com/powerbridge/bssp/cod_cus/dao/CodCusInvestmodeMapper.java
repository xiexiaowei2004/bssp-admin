package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusInvestmode;

public interface CodCusInvestmodeMapper extends BaseMapper<CodCusInvestmode> {
	List<CodCusInvestmode> selectRedisDropDown();

}