package com.powerbridge.bssp.cod_std.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_std.dao.CodStdClearanceTypeMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdClearanceType;
import com.powerbridge.bssp.cod_std.service.ICodStdClearanceTypeService;



  
@Service("codStdClearanceTypeService")
public class CodStdClearanceTypeServiceImpl extends BaseServiceImpl< CodStdClearanceTypeMapper, CodStdClearanceType> implements ICodStdClearanceTypeService {
	@Autowired
    private CodStdClearanceTypeMapper codStdClearanceTypeMapper;
	
	
	@Override
	public List<CodStdClearanceType> selectRedisDropDown() {
		return codStdClearanceTypeMapper.selectRedisDropDown();  
		    
	}
}   