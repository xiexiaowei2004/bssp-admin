package com.powerbridge.bssp.edi.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.edi.entity.EdiCirclationLog;

import java.util.List;

public interface EdiCirclationLogMapper extends BaseMapper<EdiCirclationLog>{

	/*public List<CodStdAreaCode> selectBriefByList(String str);*/
	
	/**
	 * 
	 * @Description 批量插入流转日志表数据
	 * @param ediSendQueueList
	 * @return
	 */
	int insertEdiCirclationLogBatch(List<EdiCirclationLog > ediCirclationLog);
}
  
    