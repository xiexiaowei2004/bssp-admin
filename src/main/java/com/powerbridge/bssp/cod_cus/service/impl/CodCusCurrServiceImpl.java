package com.powerbridge.bssp.cod_cus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusCurrMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusCurr;
import com.powerbridge.bssp.cod_cus.service.ICodCusCurrService;
@Service("codCusCurrService")
public class CodCusCurrServiceImpl extends BaseServiceImpl<CodCusCurrMapper, CodCusCurr>  implements ICodCusCurrService{
	@Autowired
	private CodCusCurrMapper codCusCurrMapper;

	public List<CodCusCurr> selectRedisDropDown() {
		return codCusCurrMapper.selectRedisDropDown();

	}
}
