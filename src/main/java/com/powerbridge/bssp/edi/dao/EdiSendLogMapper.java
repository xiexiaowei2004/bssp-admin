package com.powerbridge.bssp.edi.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.edi.entity.EdiCirclationLog;
import com.powerbridge.bssp.edi.entity.EdiSendLog;

import java.util.List;

/**
 * <p>
  * 单据队列日志表 Mapper 接口
 * </p>
 *
 * @author simon.xie
 * @since 2017-05-20
 */
public interface EdiSendLogMapper extends BaseMapper<EdiSendLog> {
	
	/**
	 * 
	 * @Description 批量插入流转日志表数据
	 * @param ediSendQueueList
	 * @return
	 */
	int insertEdiCirclationLogBatch(List<EdiCirclationLog > ediCirclationLog);
}