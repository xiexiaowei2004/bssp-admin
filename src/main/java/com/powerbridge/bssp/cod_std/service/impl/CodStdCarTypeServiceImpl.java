package com.powerbridge.bssp.cod_std.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_std.dao.CodStdCarTypeMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdCarType;
import com.powerbridge.bssp.cod_std.service.ICodStdCarTypeService;

@Service("codStdCarTypeService")
public class CodStdCarTypeServiceImpl extends BaseServiceImpl<CodStdCarTypeMapper, CodStdCarType> implements ICodStdCarTypeService {
	@Autowired
    private CodStdCarTypeMapper codStdCarTypeMapper;
	
	
	@Override
	public List<CodStdCarType> selectRedisDropDown() {
		return codStdCarTypeMapper.selectRedisDropDown();  
		    
	}
}   