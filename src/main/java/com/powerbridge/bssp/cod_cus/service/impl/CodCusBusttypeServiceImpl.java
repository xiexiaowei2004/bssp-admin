package com.powerbridge.bssp.cod_cus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusBusttypeMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusBusttype;
import com.powerbridge.bssp.cod_cus.service.ICodCusBusttypeService;

import java.util.List;

@Service("codCusBusttypeService")
public class CodCusBusttypeServiceImpl extends BaseServiceImpl<CodCusBusttypeMapper, CodCusBusttype> implements ICodCusBusttypeService {
    @Autowired
    private CodCusBusttypeMapper codCusBusttypeMapper;

    public List<CodCusBusttype> selectBusttypeByList() {
        return codCusBusttypeMapper.selectBusttypeByList();
    }

	
	public List<CodCusBusttype> selectRedisDropDown() {
		return codCusBusttypeMapper.selectRedisDropDown();  
		    
	}

    ;
}
