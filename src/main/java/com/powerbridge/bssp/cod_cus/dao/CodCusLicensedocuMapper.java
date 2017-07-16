package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusLicensedocu;

public interface CodCusLicensedocuMapper extends BaseMapper<CodCusLicensedocu> {
	List<CodCusLicensedocu> selectRedisDropDown();


}