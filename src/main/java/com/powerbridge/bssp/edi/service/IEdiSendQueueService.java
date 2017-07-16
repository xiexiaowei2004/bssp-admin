package com.powerbridge.bssp.edi.service;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.edi.entity.EdiSendQueue;

import java.util.List;

/**
 * <p>
 * 单据队列表 服务类
 * </p>
 *
 * @author simon.xie
 * @since 2017-05-20
 */
public interface IEdiSendQueueService extends IService<EdiSendQueue> {
	/**
	 * 
	 * @Description 批量插入队列表数据
	 * @param ediSendQueueList
	 * @return
	 */
	int insertCirclationInfoBatch(List<EdiSendQueue> ediSendQueueList);
}
