package com.powerbridge.bssp.cod_cus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusLctypeMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusLctype;
import com.powerbridge.bssp.cod_cus.service.ICodCusLctypeService;
@Service("codCusLctypeService")
public class CodCusLctypeServiceImpl extends BaseServiceImpl< CodCusLctypeMapper,  CodCusLctype> implements ICodCusLctypeService{
	@Autowired
    private CodCusLctypeMapper codCusLctypeMapper;
	
	public List<CodCusLctype> selectRedisDropDown() {
		return codCusLctypeMapper.selectRedisDropDown();  
		    
	}
}
