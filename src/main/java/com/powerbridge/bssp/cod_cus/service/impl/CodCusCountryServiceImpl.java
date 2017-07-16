package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusCountryMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusCountry;
import com.powerbridge.bssp.cod_cus.service.ICodCusCountryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("codCusCountryService")
public class CodCusCountryServiceImpl extends BaseServiceImpl<CodCusCountryMapper, CodCusCountry> implements ICodCusCountryService {
	@Autowired
	private CodCusCountryMapper codCusCountryMapper;

	public List<CodCusCountry> selectRedisDropDown() {
		return codCusCountryMapper.selectRedisDropDown();

	}
}
