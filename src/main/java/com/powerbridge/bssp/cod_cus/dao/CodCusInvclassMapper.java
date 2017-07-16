package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusInvclass;

public interface CodCusInvclassMapper extends BaseMapper<CodCusInvclass> {
	List<CodCusInvclass> selectRedisDropDown();

}