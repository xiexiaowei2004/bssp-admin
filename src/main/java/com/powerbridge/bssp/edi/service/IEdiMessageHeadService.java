package com.powerbridge.bssp.edi.service;

import com.powerbridge.bssp.edi.entity.EdiMessageHead;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 报文头表 服务类
 * </p>
 *
 * @author simon.xie
 * @since 2017-06-01
 */
public interface IEdiMessageHeadService extends IService<EdiMessageHead> {
	/**
	 * 
	 * @Description 获取报文头表数据
	 * @param SeqNo 单据编号
	 * @return
	 */
	public EdiMessageHead getEdiMessageHead(String seqNo);
}
