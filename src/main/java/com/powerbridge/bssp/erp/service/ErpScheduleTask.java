package com.powerbridge.bssp.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by powerbridge on 2017/6/11.
 */
@Component
public class ErpScheduleTask {
    @Autowired
    private IErpMidDtImgService erpMidDtImgService;
    @Autowired
    private IErpMidDtExgService erpMidDtExgService;
    @Autowired
    private IErpMidUcnsDtService erpMidUcnsDtService;
    @Autowired
    private IErpMidBondInvtBscService erpMidBondInvtBscService;

    /*@Scheduled(cron = "0 *//*1 * * * ?")*/
    public void importExcel(){
        String time = DateFormat.getDateTimeInstance().format(new Date());
        System.out.println("定时器触发打印"+time);
        erpMidDtImgService.importExcel("d:\\erp_data\\img","d:\\erp_data\\img");
        erpMidDtExgService.importExcel("d:\\erp_data\\exg", "d:\\erp_data\\exg");
        erpMidUcnsDtService.importExcel("d:\\erp_data\\bom", "d:\\erp_data\\bom");
        erpMidBondInvtBscService.importExcel("d:\\erp_data\\bill", "d:\\erp_data\\bill");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
    }

}
