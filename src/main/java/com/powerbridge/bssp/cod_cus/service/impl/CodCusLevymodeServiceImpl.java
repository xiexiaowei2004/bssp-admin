package com.powerbridge.bssp.cod_cus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusLevymodeMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusLevymode;
import com.powerbridge.bssp.cod_cus.service.ICodCusLevymodeService;

@Service("codCusLevymodeService")
public class CodCusLevymodeServiceImpl extends BaseServiceImpl<CodCusLevymodeMapper, CodCusLevymode> implements ICodCusLevymodeService {
	@Autowired
    private CodCusLevymodeMapper codCusLevymodeMapper;
	
	public List<CodCusLevymode> selectRedisDropDown() {
		return codCusLevymodeMapper.selectRedisDropDown();  
		    
	}
}
