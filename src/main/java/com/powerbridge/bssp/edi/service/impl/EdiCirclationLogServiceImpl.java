package com.powerbridge.bssp.edi.service.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.edi.dao.EdiCirclationLogMapper;
import com.powerbridge.bssp.edi.entity.EdiCirclationLog;
import com.powerbridge.bssp.edi.service.IEdiCirclationLogService;




  
@Service("ediCirclationLogService")
public class EdiCirclationLogServiceImpl extends BaseServiceImpl< EdiCirclationLogMapper, EdiCirclationLog> implements IEdiCirclationLogService {
	@Autowired
	private EdiCirclationLogMapper ediCirclationLogMapper;
	 	/*@Autowired
	    private CodStdAreaCodeMapper codStdAreaCodeMapper;

	    public List<CodStdAreaCode> selectBriefByList(String str){
	        return codStdAreaCodeMapper.selectBriefByList(str);
	    }*/

	@Override
	public int insertEdiCirclationLogBatch(List<EdiCirclationLog> ediCirclationLog) {
		return ediCirclationLogMapper.insertEdiCirclationLogBatch(ediCirclationLog);
	}
}   