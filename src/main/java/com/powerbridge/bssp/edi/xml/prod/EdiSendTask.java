package com.powerbridge.bssp.edi.xml.prod;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.common.listener.WebContextListener;
import com.powerbridge.bssp.edi.entity.EdiSendQueue;
import com.powerbridge.bssp.edi.service.IEdiSendQueueService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @ClassName EdiSendTask
 * @Description 发送报文
 * @author Simon.xie
 * @Date 2017年5月23日 上午11:31:34
 * @version 1.0.0
 */
public class EdiSendTask {
    static final Log logger = LogFactory.getLog(EdiSendTask.class);

    private WebApplicationContext wac = WebContextListener.getCurrentWebApplicationContext();   // 获取容器（上下文环境）

    //报文队列服务
    private IEdiSendQueueService ediSendQueueService;

    public EdiSendTask() {
        // 通过容器 获取service
        ediSendQueueService = (IEdiSendQueueService) wac.getBean("ediSendQueueService");
    }
    public void job() throws Exception{
       /* HttpClientUtil httpClientUtil = new HttpClientUtil();
        String Str = httpClientUtil.executeByGET("http://localhost:8090/bssp-admin/edi/ediSendQueue/emsList");
        JSONObject jsonObject=(JSONObject) JSONObject.parse(Str);
        String data = jsonObject.get("data").toString();
        Gson gson = new Gson();
        List<EdiSendQueue> ediSendQueueList = gson.fromJson(data.toString(), new TypeToken<List<EdiSendQueue>>() {
        }.getType());*/

        //1.查询所有Status为“1”要处理的队列表数据
        EntityWrapper entityWrapper = new EntityWrapper<EdiSendQueue>();
        entityWrapper.eq("Status", "N");//处理标识
        List<EdiSendQueue> ediSendQueueList = ediSendQueueService.selectList(entityWrapper);

        int ediSendQueueListSize = ediSendQueueList.size();
        for (int i = 0; i < ediSendQueueListSize; i++) {
            String areaCode = ediSendQueueList.get(i).getAreaCode();  //监管场所
            String docType = ediSendQueueList.get(i).getDocType();  //单据类型
            String bizType = ediSendQueueList.get(i).getBizType();  //业务类型
            String seqNo = ediSendQueueList.get(i).getSeqNo();      //单据编号
            BigDecimal chgTmsCnt = ediSendQueueList.get(i).getChgTmsCnt();  //变更次数

            final IGroupProcesser processer = SendMessageFactory.createGroupProcesser(docType,bizType);
            processer.execute(docType,bizType,areaCode,seqNo,chgTmsCnt);
        }
    }
}
