package com.powerbridge.bssp.cod_cus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusDistrictrelMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusDistrictrel;
import com.powerbridge.bssp.cod_cus.service.ICodCusDistrictrelService;

@Service("codCusDistrictrelService")
public class CodCusDistrictrelServiceImpl extends BaseServiceImpl<CodCusDistrictrelMapper,CodCusDistrictrel> implements ICodCusDistrictrelService {
	@Autowired
	private CodCusDistrictrelMapper codCusDistrictrelMapper;

	public List<CodCusDistrictrel> selectRedisDropDown() {
		return codCusDistrictrelMapper.selectRedisDropDown();

	}
}
