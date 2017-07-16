package com.powerbridge.bssp.cod_cus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusCustomsrelMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusCustomsrel;
import com.powerbridge.bssp.cod_cus.service.ICodCusCustomsrelService;
@Service("codCusCustomsrelService")
public class CodCusCustomsrelServiceImpl extends BaseServiceImpl<CodCusCustomsrelMapper, CodCusCustomsrel> implements ICodCusCustomsrelService{
	@Autowired
	private CodCusCustomsrelMapper codCusCustomsrelMapper;

	public List<CodCusCustomsrel> selectRedisDropDown() {
		return codCusCustomsrelMapper.selectRedisDropDown();

	}
}
