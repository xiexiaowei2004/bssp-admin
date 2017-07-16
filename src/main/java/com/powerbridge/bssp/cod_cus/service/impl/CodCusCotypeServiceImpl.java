package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusCotypeMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusCotype;
import com.powerbridge.bssp.cod_cus.service.ICodCusCotypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("codCusCotypeService")
public class CodCusCotypeServiceImpl extends BaseServiceImpl<CodCusCotypeMapper, CodCusCotype> implements ICodCusCotypeService {
    @Autowired
    private CodCusCotypeMapper codCusCotypeMapper;

    public List<CodCusCotype> selectCotypeByList() {
        return codCusCotypeMapper.selectCotypeByList();
    }

	public List<CodCusCotype> selectRedisDropDown() {
		return codCusCotypeMapper.selectRedisDropDown();  
		    
	}

    
}
