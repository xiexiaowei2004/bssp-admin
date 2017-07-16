package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusInterface;

public interface CodCusInterfaceMapper  extends BaseMapper<CodCusInterface>{
	List<CodCusInterface> selectRedisDropDown();

}