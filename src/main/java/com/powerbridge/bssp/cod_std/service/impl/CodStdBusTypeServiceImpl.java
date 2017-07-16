package com.powerbridge.bssp.cod_std.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_std.dao.CodStdBusTypeMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdBusType;
import com.powerbridge.bssp.cod_std.service.ICodStdBusTypeService;

@Service("codStdBusTypeService")
public class CodStdBusTypeServiceImpl extends BaseServiceImpl<CodStdBusTypeMapper,CodStdBusType> implements ICodStdBusTypeService {
	@Autowired
    private CodStdBusTypeMapper codStdBusTypeMapper;
	
	
	@Override
	public List<CodStdBusType> selectRedisDropDown() {
		return codStdBusTypeMapper.selectRedisDropDown();  
		    
	}

   /*@Autowired
    private CodStdBusTypeMapper codStdBusTypeMapper;

    public List<CodStdBusType> selectBusTypeByList(){
        return codStdBusTypeMapper.selectBusTypeByList();
    }*/
}



