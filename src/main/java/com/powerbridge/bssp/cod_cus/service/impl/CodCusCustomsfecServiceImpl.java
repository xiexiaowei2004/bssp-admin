package com.powerbridge.bssp.cod_cus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusCustomsfecMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusCustomsfec;
import com.powerbridge.bssp.cod_cus.service.ICodCusCustomsfecService;
@Service("codCusCustomsfecService")
public class CodCusCustomsfecServiceImpl extends BaseServiceImpl<CodCusCustomsfecMapper, CodCusCustomsfec> implements ICodCusCustomsfecService{

	@Autowired
	private CodCusCustomsfecMapper codCusCustomsfecMapper;
	
	@Override
	public List<CodCusCustomsfec> selectRedisDropDown() {
		return codCusCustomsfecMapper.selectRedisDropDown();
	}

}
