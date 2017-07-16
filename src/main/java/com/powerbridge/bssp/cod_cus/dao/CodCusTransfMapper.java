package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusTransf;

public interface CodCusTransfMapper extends BaseMapper<CodCusTransf> {
	List<CodCusTransf> selectRedisDropDown();

}