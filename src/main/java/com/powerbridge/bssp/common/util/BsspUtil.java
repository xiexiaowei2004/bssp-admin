package com.powerbridge.bssp.common.util;

import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.CommonConstants;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.util.json.JSON;
import com.powerbridge.bssp.common.util.json.JSONObject;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：BsspUtil
 * 类描述：项目工具类
 * 创建人：danagao
 * 创建时间：2017/6/1 19:42
 *
 * @version 1.0
 */
public class BsspUtil {

    //可以修改的状态，可以申报的状态
    private static final List chkStatusEditList = new ArrayList();

    static {
        chkStatusEditList.add(ChkStatusConstant.CHK_STATUS_S);//暂存
        chkStatusEditList.add(ChkStatusConstant.CHK_STATUS_N);//审批不通过
    }


    /**
     * @param chkStatus 单据状态
     * @return boolean
     * 只有暂存和审批不同意状态的单据才可以修改和删除
     * 返回true表示可以修改和删除
     */
    public static boolean checkStatusEdit(String chkStatus) {
        return chkStatusEditList.contains(chkStatus);
    }


    /**
     * @param beanObject 单据的bean
     * @param chkStatusName 单据状态对应的字段，不填默认为chkStatus
     * <p>
     * 只有暂存和审批不同意状态的单据才可以申报
     * 如果状态异常或向队列表插入数据失败会抛出异常
     * 申报时调用
     */
    public static void checkStatusDeclare(Object beanObject, String chkStatusName,String dclTime,boolean emapvMarkcd) throws Exception {
        if (StringUtil.isEmpty(chkStatusName)) {
            chkStatusName = "chkStatus";
        }
        if (StringUtil.isEmpty(dclTime)) {
            dclTime = "dclTime";
        }
        //获取bean中的状态
//        String chkStatus = (String) BeanKit.getProperty(beanObject, chkStatusName);

        //检查bean状态是否可以申报
//        if (StringUtil.isNotEmpty(chkStatus) && chkStatusEditList.contains(chkStatus)) {

            //向队列表插入数据
        String resultJson = insert2EdiSendQueue(beanObject);

            JSONObject jsonObject = JSON.parseObject(resultJson);
            String code = jsonObject.getString("code");
            String successfulLogo = String.valueOf(MessageConstants.BSSP_STATUS_SUCCESS.getCode());

            //更改bean状态
            if (code != null && successfulLogo.equals(code)) {//成功
                BeanKit.setProperty(beanObject, chkStatusName, ChkStatusConstant.CHK_STATUS_D);
                BeanKit.setProperty(beanObject, dclTime, DateUtil.now());
                if(emapvMarkcd){
                    BeanKit.setProperty(beanObject, "emapvMarkcd",ChkStatusConstant.EMAPV_MARKCD_SAS_B);
                }
            } else {
                throw new Exception("插入对列表失败：" + resultJson);
            }

//        } else {
//            throw new Exception("单据状态异常，不可以申报！");
//        }

    }

    private static String insert2EdiSendQueue(Object beanObject) throws Exception {
        //要传入参数名称
        String[] columns = new String[]{"seqNo", "areaCode", "docType", "bizType","chgTmsCnt"};


        //接口路径
        String serverAdrress = CommonConstants.contextPath + "/edi/ediSendQueue/emsSave";

        //产生参数
        Map<String, String> beanMap = new HashMap<>();
        for (String column : columns) {
            if (BeanKit.propertyExists(beanObject, column)) {
                beanMap.put(column, BeanKit.getProperty(beanObject, column).toString());
            }
        }

        //向队列表插入数据
        HttpClientUtil clientUtil = new HttpClientUtil();
        return clientUtil.executeByPOST(serverAdrress, beanMap);
    }


    /**
     * @param beanObject   对象
     * @param propertyName 属性
     * 根据用户所在企业过滤单据数据
     * 通常用于列表查询界面
     *  默认使用企业代码字段inputCopNo
     */
    public static void filterCopEnt(Object beanObject, String propertyName) {

        if (StringUtil.isEmpty(propertyName)) {
            propertyName = "inputCopNo";
        }

        String tradeCode = SingletonLoginUtils.getSystemUser().getInputCopNo();

        if (BeanKit.propertyExists(beanObject, propertyName)) {
            BeanKit.setProperty(beanObject, propertyName, tradeCode);
        }

    }


    public static void main(String[] args) {
        String str = "{\"code\":1,\"message\":\"成功\",\"data\":{\"uid\":\"402881ef5c715f38015c715f388a0000\",\"areaCode\":\"040901\",\"docType\":\"A0302\",\"bizType\":\"EMS101\",\"seqNo\":\"111111111\",\"status\":\"Y\",\"remarks\":null,\"createTime\":\"2017-06-04 12:31:24\",\"updateTime\":\"2017-06-04 12:31:24\"},\"total\":0}";
        JSONObject jsonObject = JSON.parseObject(str);
        String code = jsonObject.getString("code");
        System.out.println(code);
        JSONObject jsonObject2 = jsonObject.getJSONObject("data");
        String uid = jsonObject2.getString("uid");
        System.out.println(uid);

    }


}
