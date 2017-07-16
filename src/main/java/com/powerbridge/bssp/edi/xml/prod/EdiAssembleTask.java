package com.powerbridge.bssp.edi.xml.prod;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.common.listener.WebContextListener;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.edi.entity.EdiMessageData;
import com.powerbridge.bssp.edi.entity.EdiMessageLog;
import com.powerbridge.bssp.edi.entity.EdiRoutingInfo;
import com.powerbridge.bssp.edi.service.IEdiMessageDataService;
import com.powerbridge.bssp.edi.service.IEdiRoutingInfoService;
import com.powerbridge.bssp.edi.xml.scheuler.EdiSendMessageTaskJob;
import com.powerbridge.bssp.edi.xml.util.DomWrite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.web.context.WebApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

/**
 * Created by Simon.xie on 2017/6/16.
 */
public class EdiAssembleTask {
    static final Log logger = LogFactory.getLog(EdiSendMessageTaskJob.class);

    private WebApplicationContext wac = WebContextListener.getCurrentWebApplicationContext();   // 获取容器（上下文环境）

    //报文数据服务
    private IEdiMessageDataService ediMessageDataService;

    //路由配置服务
    private IEdiRoutingInfoService ediRoutingInfoService;

    public EdiAssembleTask(){
        // 通过容器 获取service
        ediRoutingInfoService = (IEdiRoutingInfoService)wac.getBean("ediRoutingInfoService");
        ediMessageDataService = (IEdiMessageDataService)wac.getBean("ediMessageDataService");
    }

    public void job() throws Exception{
        //查询报文数据
        EntityWrapper entityWrapper = new EntityWrapper<EdiMessageData>();
        entityWrapper.eq("TRANS_TYPE", TransTypeEnum.S);//传输类型
        entityWrapper.eq("STATUS", TransTypeEnum.N);//处理标识
        List<EdiMessageData> ediMessageDataList = ediMessageDataService.selectList(entityWrapper);
        for (EdiMessageData ediMessageData : ediMessageDataList) {
            String areaCode = ediMessageData.getAreaCode();
            String bizType = ediMessageData.getBizType();
            String docType = ediMessageData.getDocType();
            String fileName = ediMessageData.getFileName();
            String serialNo = ediMessageData.getSerialNo();
            byte[] bigData = ediMessageData.getBigData();
            //查询路由表
            EntityWrapper entityWrapper2 = new EntityWrapper<EdiRoutingInfo>();
            entityWrapper.eq("AREA_CODE", areaCode);//监管场所
            entityWrapper.eq("DOC_TYPE", docType);//单据类型
            entityWrapper.eq("BIZ_TYPE", bizType);//业务类型
            entityWrapper.eq("STATUS", TransTypeEnum.Y);//启用标识
            EdiRoutingInfo ediRoutingInfo = ediRoutingInfoService.selectOne(entityWrapper2);

            String transMode = ediRoutingInfo.getTransMode();   //传输方式
            String sendUrl = ediRoutingInfo.getSendUrl();   //发送url
            switch (transMode) {
                case "LOCAL":
                    System.out.println("----------------------LOCAL--------------------");
                    SAXReader saxReader = new SAXReader();
                    Document doc = null;
                    try {
                        doc = saxReader.read(new ByteArrayInputStream(bigData), "UTF-8");
                        System.out.println("doc---------------" + doc.asXML());
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                    //输出报文
                    File srcFile = new File(sendUrl + File.separator + fileName + ".xml");
                    DomWrite.writeDocument(srcFile.toString(), doc);
                    updateEdiMessageDataStatus(areaCode, bizType, docType, serialNo);
                    insertEdiMessageLog(areaCode, bizType, docType, fileName);
                    break;
                case "IBMMQ":
                    System.out.println("----------------------IBMMQ--------------------");
                    break;
                case "MSMQ":
                    System.out.println("----------------------MSMQ--------------------");
                    break;
                case "WEB":
                    System.out.println("----------------------WEB--------------------");
                    break;
                case "WCF":
                    System.out.println("----------------------WCF--------------------");
                    break;
            }
        }
    }

    /**
     *  更新报文数据状态
     * @param areaCode
     * @param bizType
     * @param docType
     * @param serialNo
     */
    private void updateEdiMessageDataStatus(String areaCode, String bizType, String docType, String serialNo) {
        EntityWrapper entityWrapper = new EntityWrapper<EdiMessageData>();
        entityWrapper.eq("AREA_CODE", areaCode);//监管场所
        entityWrapper.eq("DOC_TYPE", docType);//单据类型
        entityWrapper.eq("BIZ_TYPE", bizType);//业务类型
        entityWrapper.eq("SERIAL_NO", serialNo);//编号
        EdiMessageData ediMessageData= new EdiMessageData();
        ediMessageData.setStatus(TransTypeEnum.Y);
        ediMessageDataService.update(ediMessageData,entityWrapper);
    }

    /**
     * 新增报文日志
     * @param areaCode
     * @param bizType
     * @param docType
     * @param fileName
     */
    private void insertEdiMessageLog(String areaCode, String bizType, String docType, String fileName) {
        EdiMessageLog ediMessageLog = new EdiMessageLog();
        ediMessageLog.setUid(UUIDGenerator.getUUID());
        ediMessageLog.setAreaCode(areaCode);
        ediMessageLog.setBizType(bizType);
        ediMessageLog.setDocType(docType);
        ediMessageLog.setFileName(fileName);
        ediMessageLog.setProcessingTime(DateUtil.now());
        String msg = "成功发送!";
        ediMessageLog.setProcessingLog(msg.getBytes());
        ediMessageLog.setCheckInfo(msg.getBytes());
        ediMessageLog.setStatus(TransTypeEnum.Y);
        ediMessageLog.setTransType(TransTypeEnum.S);
    }
}
