package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusTrade;

public interface CodCusTradeMapper  extends BaseMapper<CodCusTrade>{
	List<CodCusTrade> selectRedisDropDown();

}