package com.powerbridge.bssp.cod_std.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_std.dao.CodStdAreaCodeMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdAreaCode;
import com.powerbridge.bssp.cod_std.service.ICodStdAreaCodeService;




  
@Service("codStdAreaCodeService")
public class CodStdAreaCodeServiceImpl extends BaseServiceImpl< CodStdAreaCodeMapper, CodStdAreaCode> implements ICodStdAreaCodeService {

	 	@Autowired
	    private CodStdAreaCodeMapper codStdAreaCodeMapper;

	    public List<CodStdAreaCode> selectRedisDropDown(){
	        return codStdAreaCodeMapper.selectRedisDropDown();
	    }

		@Override
		public List<CodStdAreaCode> selectCarSortByList(String str) {
			return codStdAreaCodeMapper.selectCarSortByList(str);  
			    
		}


}   