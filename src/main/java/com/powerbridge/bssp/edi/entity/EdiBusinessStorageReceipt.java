package com.powerbridge.bssp.edi.entity; /***********************************************************************
 * Module:  EdiBusinessStorageReceipt.java
 * Author:  宋轲
 * Purpose: Defines the Class EdiBusinessStorageReceipt
 ***********************************************************************/

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/** 业务报文入库回执表
 * 
 * @pdOid ab6382b4-f556-4fc1-b372-3ad80e0077b4 */
@TableName("edi_business_storage_receipt")
public class EdiBusinessStorageReceipt {
   /** 主键
    * 
    * @pdOid 86411dca-a9a7-418a-b423-a103245715cc */
   @TableId("UID")
   public String uid;
   /** 报文类型
    * 
    * @pdOid 5ffffb2b-83ef-492d-b8b7-f16f9cf24178 */
   @TableField("MESSAG_TYPE")
   public String messagType;
   /** 报文名称
    * 
    * @pdOid 85ffd212-1732-43dc-8143-233bdd278348 */
   @TableField("MESSEGE_NAME")
   public String messegeName;
   /** 回执类型
    * 
    * @pdOid abb9a59c-35a9-4029-900d-b39cc76faba1 */
   @TableField("RESULT_TYPE")
   public String resultType;
   /** 原始报文编号
    * 
    * @pdOid 73d02846-6439-4569-b52b-e68055069acb */
   @TableField("POCKET_ID")
   public String pocketId;
   /** 企业预录入编号
    * 
    * @pdOid 3dd892b2-aa35-469c-b706-db6f5680968b */
   @TableField("ETPS_PREENT_NO")
   public String etpsPreentNo;
   /** 总包数
    * 
    * @pdOid fedcf908-3164-4607-97fb-c6029ad03d70 */
   @TableField("TOTAL_POCKET_QTY")
   public String totalPocketQty;
   /** 当前包序号
    * 
    * @pdOid 7682095e-80d3-4ce5-a4e6-1d6c0a918481 */
   @TableField("CUR_POCKET_NO")
   public String curPocketNo;
   /** 业务编号
    * 
    * @pdOid f97bd694-af47-4b58-a6fa-dbf92a43b585 */
   @TableField("BUSINESS_ID")
   public String businessId;
   /** 处理结果
    * 
    * @pdOid 533e9b60-e2a8-4a35-8dbd-2e31ed5b7acb */
   @TableField("MANAGE_RESULT")
   public String manageResult;
   /** 入库时间
    * 
    * @pdOid f45848bd-b616-4490-914b-016af89641f9 */
   @TableField("MANAGE_DATE")
   public String manageDate;
   /** 备注
    * 
    * @pdOid d4948a8b-6b20-4b1b-9257-59accbbded53 */
   @TableField("RMK")
   public String rmk;
   /** 保存日期
    * 
    * @pdOid 0c9f4963-9783-4421-9593-b8ac602f7158 */
   @TableField("STORAGE_TIME")
   public String storageTime;

   public String getUid() {
      return uid;
   }

   public void setUid(String uid) {
      this.uid = uid;
   }

   public String getMessagType() {
      return messagType;
   }

   public void setMessagType(String messagType) {
      this.messagType = messagType;
   }

   public String getMessegeName() {
      return messegeName;
   }

   public void setMessegeName(String messegeName) {
      this.messegeName = messegeName;
   }

   public String getResultType() {
      return resultType;
   }

   public void setResultType(String resultType) {
      this.resultType = resultType;
   }

   public String getPocketId() {
      return pocketId;
   }

   public void setPocketId(String pocketId) {
      this.pocketId = pocketId;
   }

   public String getEtpsPreentNo() {
      return etpsPreentNo;
   }

   public void setEtpsPreentNo(String etpsPreentNo) {
      this.etpsPreentNo = etpsPreentNo;
   }

   public String getTotalPocketQty() {
      return totalPocketQty;
   }

   public void setTotalPocketQty(String totalPocketQty) {
      this.totalPocketQty = totalPocketQty;
   }

   public String getCurPocketNo() {
      return curPocketNo;
   }

   public void setCurPocketNo(String curPocketNo) {
      this.curPocketNo = curPocketNo;
   }

   public String getBusinessId() {
      return businessId;
   }

   public void setBusinessId(String businessId) {
      this.businessId = businessId;
   }

   public String getManageResult() {
      return manageResult;
   }

   public void setManageResult(String manageResult) {
      this.manageResult = manageResult;
   }

   public String getManageDate() {
      return manageDate;
   }

   public void setManageDate(String manageDate) {
      this.manageDate = manageDate;
   }

   public String getRmk() {
      return rmk;
   }

   public void setRmk(String rmk) {
      this.rmk = rmk;
   }

   public String getStorageTime() {
      return storageTime;
   }

   public void setStorageTime(String storageTime) {
      this.storageTime = storageTime;
   }

   @Override
   public String toString() {
      return "EdiBusinessStorageReceipt{" +
              "uid='" + uid + '\'' +
              ", messagType='" + messagType + '\'' +
              ", messegeName='" + messegeName + '\'' +
              ", resultType='" + resultType + '\'' +
              ", pocketId='" + pocketId + '\'' +
              ", etpsPreentNo='" + etpsPreentNo + '\'' +
              ", totalPocketQty='" + totalPocketQty + '\'' +
              ", curPocketNo='" + curPocketNo + '\'' +
              ", businessId='" + businessId + '\'' +
              ", manageResult='" + manageResult + '\'' +
              ", manageDate='" + manageDate + '\'' +
              ", rmk='" + rmk + '\'' +
              ", storageTime='" + storageTime + '\'' +
              '}';
   }
}