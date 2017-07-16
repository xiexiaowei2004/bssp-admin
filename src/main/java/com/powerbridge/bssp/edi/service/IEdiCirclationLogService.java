package com.powerbridge.bssp.edi.service;


import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.edi.entity.EdiCirclationLog;

import java.util.List;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：ICodStdAreaCodeService  
* 类描述：CodStdAreaCode表业务逻辑层接口   
* 创建人：xwq
* 创建时间：2017年5月10日19:21:36
* @version
 */
public interface IEdiCirclationLogService extends IService<EdiCirclationLog>{

	/*public List<CodStdAreaCode> selectBriefByList(String customsCode);*/

	/**
	 * 
	 * @Description 批量插入流转日志表数据
	 * @param ediSendQueueList
	 * @return
	 */
	int insertEdiCirclationLogBatch(List<EdiCirclationLog > ediCirclationLog);
}
  
    