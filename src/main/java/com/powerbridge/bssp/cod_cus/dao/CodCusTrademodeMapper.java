package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusTrademode;

public interface CodCusTrademodeMapper  extends BaseMapper<CodCusTrademode> {
	List<CodCusTrademode> selectRedisDropDown();

}