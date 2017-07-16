package com.powerbridge.bssp.cod_cus.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodMerchClassifyMapper;
import com.powerbridge.bssp.cod_cus.entity.CodMerchClassify;
import com.powerbridge.bssp.cod_cus.service.ICodMerchClassifyService;



  
@Service("codMerchClassifyService")
public class CodMerchClassifyServiceImpl extends BaseServiceImpl< CodMerchClassifyMapper, CodMerchClassify> implements ICodMerchClassifyService {

	@Autowired
	private CodMerchClassifyMapper codMerchClassifyMapper;

	/*public List<CodMerchClassify> selectRedisDropDown() {
		return codMerchClassifyMapper.selectRedisDropDown();

	}*/

}   