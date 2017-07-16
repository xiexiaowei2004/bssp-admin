package com.powerbridge.bssp.cod_cus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusLicensedocuMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusLicensedocu;
import com.powerbridge.bssp.cod_cus.service.ICodCusLicensedocuService;
@Service("codCusLicensedocuService")
public class CodCusLicensedocuServiceImpl extends BaseServiceImpl<CodCusLicensedocuMapper,  CodCusLicensedocu> implements ICodCusLicensedocuService {
	@Autowired
    private CodCusLicensedocuMapper codCusLicensedocuMapper;
	
	public List<CodCusLicensedocu> selectRedisDropDown() {
		return codCusLicensedocuMapper.selectRedisDropDown();  
		    
	}
}
