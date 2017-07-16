package com.powerbridge.bssp.edi.entity; /***********************************************************************
 * Module:  EdiBusinessCheckReceipt.java
 * Author:  宋轲
 * Purpose: Defines the Class EdiBusinessCheckReceipt
 ***********************************************************************/

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/** 业务检查信息回执表
 * 
 * @pdOid e0dc1bcf-af9c-4175-ba75-db067f2b9a43 */
@TableName("edi_business_check_receipt")
public class EdiBusinessCheckReceipt {
   /** 主键
    * 
    * @pdOid 07a79fbe-6bbb-4f45-bc69-da38218fa32e */
   @TableId("UID")
   public String uid;
   /** 企业预录入编号
    * 
    * @pdOid 8a9e2fe6-81bd-495d-86af-de0d3649644a */
   @TableField("ETPS_PREENT_NO")
   public String etpsPreentNo;
   /** 业务编号
    * 
    * @pdOid 933ee8cd-113c-406e-8d73-0e385d2e655f */
   @TableField("BUSINESS_ID")
   public String businessId;
   /** 检查信息
    * 
    * @pdOid 2610099d-2a83-4593-a28b-1356e2129245 */
   @TableField("NOTE")
   public String note;
   /** 保存日期
    * 
    * @pdOid 8e258f24-9c4d-4830-8f69-0e78f856ff9d */
   @TableField("STORAGE_TIME")
   public String storageTime;

   public String getUid() {
      return uid;
   }

   public void setUid(String uid) {
      this.uid = uid;
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

   public String getNote() {
      return note;
   }

   public void setNote(String note) {
      this.note = note;
   }

   public String getStorageTime() {
      return storageTime;
   }

   public void setStorageTime(String storageTime) {
      this.storageTime = storageTime;
   }

   @Override
   public String toString() {
      return "EdiBusinessCheckReceipt{" +
              "uid='" + uid + '\'' +
              ", etpsPreentNo='" + etpsPreentNo + '\'' +
              ", businessId='" + businessId + '\'' +
              ", note='" + note + '\'' +
              ", storageTime='" + storageTime + '\'' +
              '}';
   }
}