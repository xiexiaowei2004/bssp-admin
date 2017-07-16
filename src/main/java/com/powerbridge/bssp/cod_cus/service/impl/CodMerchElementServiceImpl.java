package com.powerbridge.bssp.cod_cus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodMerchElementMapper;
import com.powerbridge.bssp.cod_cus.entity.CodMerchElement;
import com.powerbridge.bssp.cod_cus.service.ICodMerchElementService;

@Service("codMerchElementService")
public class CodMerchElementServiceImpl extends BaseServiceImpl< CodMerchElementMapper, CodMerchElement> implements ICodMerchElementService {
	@Autowired
    private CodMerchElementMapper codMerchElementMapper;

    public List<CodMerchElement> selectBriefByList(String codeTs){
        return codMerchElementMapper.selectBriefByList(codeTs);
    }

	/*@Override
	public List<CodMerchElement> selectRedisDropDown() {
		return codMerchElementMapper.selectRedisDropDown();  
		    
	}*/

	



}   