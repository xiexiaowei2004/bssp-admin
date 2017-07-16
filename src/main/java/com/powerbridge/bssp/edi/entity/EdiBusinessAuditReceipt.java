package com.powerbridge.bssp.edi.entity; /***********************************************************************
 * Module:  EdiBusinessAuditReceipt.java
 * Author:  宋轲
 * Purpose: Defines the Class EdiBusinessAuditReceipt
 ***********************************************************************/

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/** 业务审核信息回执表
 * 
 * @pdOid 933bfbd7-c6d6-4f50-9ece-1a16295ef6cb */
@TableName("edi_business_audit_receipt")
public class EdiBusinessAuditReceipt {
   /** 主键
    * 
    * @pdOid 329f9f79-f690-44bd-b4b3-3bb61d3473c0 */
   @TableId("UID")
   public String uid;
   /** 报文类型
    * 
    * @pdOid 857a0fba-8093-4440-8c0b-6153b904e179 */
   @TableField("MESSAG_TYPE")
   public String messagType;
   /** 报文名称
    * 
    * @pdOid 336e7c28-e64d-44be-8509-b789c59dcd8f */
   @TableField("MESSEGE_NAME")
   public String messegeName;
   /** 企业预录入编号
    * 
    * @pdOid fcf2a381-74fb-4c98-9625-7b8c3cf5b639 */
   @TableField("ETPS_PREENT_NO")
   public String etpsPreentNo;
   /** 业务编号
    * 
    * @pdOid 08b16b10-3750-4574-9209-12945f00ac91 */
   @TableField("BUSINESS_ID")
   public String businessId;
   /** 变更/报核次数
    * 
    * @pdOid 83dc37c5-db43-44a0-aa79-03569b59d42f */
   @TableField("TMS_CNT")
   public String tmsCnt;
   /** 业务类型
    * 
    * @pdOid c04816e1-72d5-4d63-981a-5abb4412d23f */
   @TableField("TYPECD")
   public String typecd;
   /** 处理结果
    * 
    * @pdOid f6b195a9-593c-4b36-9d63-64f0c7e92e62 */
   @TableField("MANAGE_RESULT")
   public String manageResult;
   /** 处理日期
    * 
    * @pdOid 996a792d-5377-42d0-a8c2-684c1e255bcb */
   @TableField("MANAGE_DATE")
   public String manageDate;
   /** 备注
    * 
    * @pdOid 0835b4c8-4114-48c0-a47c-32c8d061fc12 */
   @TableField("RMK")
   public String rmk;
   /** 保存日期
    * 
    * @pdOid fc0e49fa-40ca-4523-8d5a-7ec9e79fff91 */
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

   public String getEtpsPreentNo() {
      return etpsPreentNo;
   }

   public void setEtpsPreentNo(String etpsPreentNo) {
      this.etpsPreentNo = etpsPreentNo;
   }

   public String getBusinessId() {
      return businessId;
   }

   public void setBusinessId(String businessId) {
      this.businessId = businessId;
   }

   public String getTmsCnt() {
      return tmsCnt;
   }

   public void setTmsCnt(String tmsCnt) {
      this.tmsCnt = tmsCnt;
   }

   public String getTypecd() {
      return typecd;
   }

   public void setTypecd(String typecd) {
      this.typecd = typecd;
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
      return "EdiBusinessAuditReceipt{" +
              "uid='" + uid + '\'' +
              ", messagType='" + messagType + '\'' +
              ", messegeName='" + messegeName + '\'' +
              ", etpsPreentNo='" + etpsPreentNo + '\'' +
              ", businessId='" + businessId + '\'' +
              ", tmsCnt='" + tmsCnt + '\'' +
              ", typecd='" + typecd + '\'' +
              ", manageResult='" + manageResult + '\'' +
              ", manageDate='" + manageDate + '\'' +
              ", rmk='" + rmk + '\'' +
              ", storageTime='" + storageTime + '\'' +
              '}';
   }
}