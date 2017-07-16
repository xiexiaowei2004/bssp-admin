package com.powerbridge.bssp.edi.xml.scheuler;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.powerbridge.bssp.common.util.HttpClientUtil;
import com.powerbridge.bssp.common.util.json.JSONObject;
import com.powerbridge.bssp.edi.entity.EdiSendQueue;
import com.powerbridge.bssp.edi.service.IEdiSendQueueService;
import com.powerbridge.bssp.edi.service.IEdiXmlMapDbService;
import com.powerbridge.bssp.edi.xml.prod.EdiSendTask;
import com.powerbridge.bssp.edi.xml.prod.IGroupProcesser;
import com.powerbridge.bssp.edi.xml.prod.SendMessageFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @ClassName EdiSendQueueTaskJob
 * @Description 单据队列任务调度器
 * @author Simon.xie
 * @Date 2017年5月23日 上午11:31:34
 * @version 1.0.0
 */
@Component
public class EdiQueueInfoTaskJob {

	static final Log logger = LogFactory.getLog(EdiQueueInfoTaskJob.class);
	@Autowired
	private IEdiSendQueueService ediSendQueueService;
	@Autowired
	private IEdiXmlMapDbService ediXmlMapDbService;
	public void execute() {
		logger.info("-----------------报文组装任务调度器EdiSendQueueTaskJob任务启动----------");
		try {
		EdiSendTask ediSendTask  = new EdiSendTask();
		ediSendTask.job();
		logger.info("-----------------报文组装任务调度器EdiSendQueueTaskJob任务结束----------");
		} catch (Exception e) {
			System.out.println("报文组装处理异常-----------------------------" + e.getMessage());
			e.printStackTrace();
		}
	}

	
}
