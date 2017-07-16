package com.powerbridge.bssp.edi.service.impl;


import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cop_et.entity.EtArcrpBsc;
import com.powerbridge.bssp.edi.dao.EdiXmlMapDbMapper;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;
import com.powerbridge.bssp.edi.service.IEdiXmlMapDbService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ediXmlMapDbService")
public class EdiXmlMapDbServiceImpl extends BaseServiceImpl<EdiXmlMapDbMapper, EdiXmlMapDb> implements IEdiXmlMapDbService {
    @Autowired
    private EdiXmlMapDbMapper ediXmlMapDbMapper;

	@Override
    public String getFieldParameter(EdiXmlMapDb ediXmlMapDb) {
        return ediXmlMapDbMapper.getFieldParameter(ediXmlMapDb);
    }

	@Override
	public EtArcrpBsc getBusinessData(@Param(value="str") String str) {
        return ediXmlMapDbMapper.getBusinessData(str);
    }

	@Override
    public List<EdiXmlMapDb> getEdiXmlMapDb(Map paramMap) {
        return ediXmlMapDbMapper.getEdiXmlMapDb(paramMap);
    }
}   