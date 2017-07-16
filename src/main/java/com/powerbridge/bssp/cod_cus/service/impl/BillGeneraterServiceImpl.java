package com.powerbridge.bssp.cod_cus.service.impl;

import java.util.List;
import java.util.Map;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.BillGeneraterMapper;
import com.powerbridge.bssp.cod_cus.entity.BillGenerater;
import com.powerbridge.bssp.cod_cus.entity.EdiSeqInfo;
import com.powerbridge.bssp.cod_cus.entity.EdiSeqList;
import com.powerbridge.bssp.cod_cus.service.IBillGeneraterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("billGeneraterService")
public class BillGeneraterServiceImpl extends BaseServiceImpl<BillGeneraterMapper,BillGenerater> implements IBillGeneraterService {
	
	@Autowired
	private BillGeneraterMapper billGeneraterMapper;
	
	@Override
	public BillGenerater selectNumber(Map paramMap) {
		return billGeneraterMapper.selectNumber(paramMap);
	}
	
	@Override
	public BillGenerater selectNumberDate(Map paramMap) {
		return billGeneraterMapper.selectNumberDate(paramMap);
	}
	
	public List<EdiSeqList> selectEdiSeqList(EdiSeqInfo ediSeqInfo) {
		return billGeneraterMapper.selectEdiSeqList(ediSeqInfo);
	}

	/*public List<BillGenerater> selectRedisDropDown() {
		return billGeneraterMapper.selectRedisDropDown();  
		    
	}*/

}
