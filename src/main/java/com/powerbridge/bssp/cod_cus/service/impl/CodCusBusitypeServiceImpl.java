package com.powerbridge.bssp.cod_cus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusBusitypeMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusBusitype;
import com.powerbridge.bssp.cod_cus.service.ICodCusBusitypeService;
@Service("codCusBusitypeService")
public class CodCusBusitypeServiceImpl extends BaseServiceImpl< CodCusBusitypeMapper, CodCusBusitype> implements ICodCusBusitypeService {
	@Autowired
	private CodCusBusitypeMapper codCusBusitypeMapper;
 
 public List<CodCusBusitype> selectRedisDropDown() {
		return codCusBusitypeMapper.selectRedisDropDown();  
		    
	}
}
