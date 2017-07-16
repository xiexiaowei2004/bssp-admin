package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusDisttypeMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusDisttype;
import com.powerbridge.bssp.cod_cus.service.ICodCusDisttypeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("codCusDisttypeService")
public class CodCusDisttypeServiceImpl extends BaseServiceImpl<CodCusDisttypeMapper, CodCusDisttype> implements ICodCusDisttypeService {
	@Autowired
	private CodCusDisttypeMapper codCusDisttypeMapper;

	public List<CodCusDisttype> selectRedisDropDown() {
		return codCusDisttypeMapper.selectRedisDropDown();

	}
}
