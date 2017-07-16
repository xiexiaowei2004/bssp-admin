package com.powerbridge.bssp.cod_cus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusLevytypeMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusLevytype;
import com.powerbridge.bssp.cod_cus.service.ICodCusLevytypeService;

@Service("codCusLevytypeService")
public class CodCusLevytypeServiceImpl extends BaseServiceImpl<CodCusLevytypeMapper, CodCusLevytype>implements ICodCusLevytypeService {
	@Autowired
    private CodCusLevytypeMapper codCusLevytypeMapper;
	
	public List<CodCusLevytype> selectRedisDropDown() {
		return codCusLevytypeMapper.selectRedisDropDown();  
		    
	}
}
