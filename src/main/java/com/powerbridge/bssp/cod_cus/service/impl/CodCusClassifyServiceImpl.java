package com.powerbridge.bssp.cod_cus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusClassifyMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusClassify;
import com.powerbridge.bssp.cod_cus.service.ICodCusClassifyService;

@Service("codCusClassifyService")
public class CodCusClassifyServiceImpl extends BaseServiceImpl<CodCusClassifyMapper, CodCusClassify> implements ICodCusClassifyService {
	/*@Autowired
	private CodCusClassifyMapper codCusClassifyMapper;
 
 public List<CodCusClassify> selectRedisDropDown() {
		return codCusClassifyMapper.selectRedisDropDown();  
		    
	}*/
}
