package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusPaymode;

public interface CodCusPaymodeMapper  extends BaseMapper<CodCusPaymode> {
	List<CodCusPaymode> selectRedisDropDown();

}