package com.powerbridge.bssp.saspass.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@TableName("sas_passport_bsc")
public class SasPassportBsc implements Serializable {

	private static final long serialVersionUID = 1L;

	public SasPassportBsc(){};

	public SasPassportBsc(String seqNo){
		this.seqNo = seqNo;
	};

	@TableId(value="UID")
	private String uid;//唯一主键
    
    @TableField(value="PASSPORT_NO")
    private String  passportNo ;//核放单编号 
    
    @TableField(value="CHG_TMS_CNT")
    private Integer chgTmsCnt;//变更次数
    
    @TableField(value="PASSPORT_TYPECD")
    private String passportTypecd;//核放单类型代码
    
    @TableField(value="SAS_PASSPORT_PREENT_NO")
    private String sasPassportPreentNo;//核放单预录入编号
    
    @TableField(value="DCL_TYPECD")
    private String dclTypecd;//申报类型代码
    
    @TableField(value="IO_TYPECD")
    private String ioTypecd;//进出标志代码
    
    @TableField(value="BIND_TYPECD")
    private String bindTypecd;//绑定类型代码
    
    @TableField(value="MASTER_CUSCD")
	@NotEmpty(message = "主管关区不能为空")
    private String masterCuscd;//主管关区代码
    
    @TableField(value="RLT_TB_TYPECD")
    private String rltTbTypecd;//关联单证类型代码
    
    @TableField(value="RLT_NO")
    private String rltNo;//关联单证编号
    
    @TableField(value="AREAIN_ETPSNO")
    private String areainEtpsno;//区内企业编码
    
    @TableField(value="AREAIN_ETPS_NM")
    private String areainEtpsNm;//区内企业名称
    
    @TableField(value="AREAIN_ETPS_SCCD")
    private String areainEtpsSccd;//区内企业社会信用代码
    
    @TableField(value="VEHICLE_NO")
    private String vehicleNo;//承运车车牌号
       
    @TableField(value="VEHICLE_IC_NO")
    private String vehicleIcNo;//IC卡号(电子车牌）
    
    @TableField(value="CONTAINER_NO")
    private String containerNo;//集装箱号

	@NumberFormat(style = NumberFormat.Style.NUMBER)
    @TableField(value="VEHICLE_WT")
    private BigDecimal vehicleWt;//车自重
    
    @TableField(value="VEHICLE_FRAME_NO")
    private String vehicleFrameNo;//车架号

	@NumberFormat(style = NumberFormat.Style.NUMBER)
    @TableField(value="VEHICLE_FRAME_WT")
    private BigDecimal vehicleFrameWt;//车架重
    
    @TableField(value="CONTAINER_TYPE")
    private String containerType;//集装箱箱型

	@NumberFormat(style = NumberFormat.Style.NUMBER)
    @TableField(value="CONTAINER_WT")
    private BigDecimal containerWt;//集装箱重

	@NumberFormat(style = NumberFormat.Style.NUMBER)
    @TableField(value="TOTAL_WT")
	@NotNull(message = "总重量不能为空")
    private BigDecimal totaLWt;//总重量

	@NumberFormat(style = NumberFormat.Style.NUMBER)
    @TableField(value="PASS_COLLECT_WT")
    private BigDecimal passCollectWt;//卡口地磅采集重量  不显示

	@NumberFormat(style = NumberFormat.Style.NUMBER)
    @TableField(value="WT_ERROR")
    private BigDecimal wtError;//  重量比对误差  不显示

	@NumberFormat(style = NumberFormat.Style.NUMBER)
    @TableField(value="TOTAL_GROSS_WT")
	@NotNull(message = "货物总毛重不能为空")
    private BigDecimal totalGrossWt;//货物总毛重

	@NumberFormat(style = NumberFormat.Style.NUMBER)
    @TableField(value="TOTAL_NET_WT")
	@NotNull(message = "货物总净重不能为空")
    private BigDecimal totalNetWt;//货物总净重
    
    @TableField(value="DCL_ER_CONC")
	@NotEmpty(message = "申请人及联系方式不能为空")
    private String dclErConc;//申请人及联系方式
    
    @TableField(value="DCL_TIME")
    private String dclTime;//申请时间
    
    @TableField(value="PASS_ID")
    private String passId;//卡口ID1
    
    @TableField(value="SECD_PASS_ID")
    private String secdPassId;//卡口ID2
    
    @TableField(value="PASS_TIME")
    private String passTime;//过卡时间1
    
    @TableField(value="SECD_PASS_TIME")
    private String secdPassTime;//过卡时间2
    
    @TableField(value="STUCD")
    private String stucd;//状态代码
    
    @TableField(value="EMAPV_MARKCD")
    private String emapvMarkcd;//审批标志代码
    
    @TableField(value="LOGISTICS_STUCD")
    private String logisticsStucd;//到检状态
    
    @TableField(value="ETPS_PREENT_NO")
    private String etpsPreentNo;//企业预录入编号
    
    @TableField(value="OWNER_SYSTEM")
	/*@NotEmpty(message = "所属系统不能为空")*/
    private String ownerSystem;//所属系统
    
    @TableField(value="I_E_FLOW")
    private String iEFlow;//进出区流向
    
    @TableField(value="ClEARANCE_TYPE")
    private String clearaNceType;//通关业务类型
    
    @TableField(value="BIND_FLAG")
    private String bindFlag;//绑定标识
    
    @TableField(value="VEHICLE_I_REGNO")
    private String vehicleIRegno;//车辆入区登记编号
    
    @TableField(value="BIND_TIME")
    private String bindTime;//绑定时间
    
    @TableField(value="PASSPORT_TYPENM")
    private String passportTypenm;//核放单类型名称
    
    @TableField(value="DCL_TYPENM")
    private String dclTypenm;//申报类型名称
    
    @TableField(value="IO_TYPENM")
    private String ioTyoenm;//
    
    @TableField(value="BIND_TYPENM")
    private String bindTypenm;//绑定类型名称
    
    @TableField(value="RLT_TB_TYPENM")
    private String rltTbTypenm;//
    
    @TableField(value="RMK")
    private String rmk;//备注
    
    @TableField(value="CONTAINER_TYPE_NM")
    private String containerTypeNm;//集装箱箱型名称
    
    @TableField(value="STUNM")
    private String stunm;//状态代码名称
    
    @TableField(value="EMAPV_MARKNM")
    private String emapvMarknm;//审批标志代码名称
    
    @TableField(value="LOGISTICS_STUNM")
    private String logisticsStunm;//到检状态名称
    
    @TableField(value="I_E_FLOW_NM")
    private String iEFlowNm;//进出区流向名称
    
    @TableField(value="BIND_FLAG_NM")
    private String bindFlagNm;//绑定标识名称
    
    @TableField(value="COP_ENT_NO")
    private String copEntNo;//企业备案号
    
    @TableField(value="MASTER_CUSNAME")
    private String masterCusname;//主管关区名称
    
    @TableField(value="AREA_CODE")
    private String areaCode;//场地代码
    
    @TableField(value="AREA_NAME")
    private String areaName;//场地名称
    
    @TableField(value="BIZ_TYPE")
    private String bizType;//业务类型
    
    @TableField(value="CHK_STATUS")
    private String chkStatus;//单据状态
    
    @TableField(value="DOC_TYPE")
    private String docType;//单据类别
    
    @TableField(value="RET_CHANNEL")
    private String retChannel;//回执状态
    
    @TableField(value="BIZ_TYPE_NAME")
    private String bizTypeName;//业务类型名称
    
    @TableField(value="CHK_STATUS_NAME")
    private String chkStatusName;//单据状态名称
    
    @TableField(value="DOC_TYPE_NAME")
    private String docTypeName;//单据类别名称
    
    @TableField(value="RET_CHANNEL_NAME")
    private String retChannelName;//回执状态名称
    
    @TableField(value="DEC_TIME")
    private String decTime;//录入日期
    
    @TableField(value="CHK_TIME")
    private String chkTime;//审批时间
    
    @TableField(value="SEQ_NO")
    private String seqNo;//单据编号
    
    @TableField(value="INPUTER_CODE")
    private String inputerCode;//申报（录入）人代码
    
    @TableField(value="INPUTER_NAME")
    private String inputerName;//申报（录入）人名称
    
    @TableField(value="INPUT_COP_NO")
    private String inputCopNo;//申报（录入）企业代码
    
    @TableField(value="INPUT_COP_NAME")
    private String inputCopName;//申报（录入）企业名称
    
    @TableField(value="DECLARE_MARK")
    private String declareMark;//申报方式(备案/变更)

    @TableField(value="CREATE_BY")
    private String createBy;//创建人代码
    
    @TableField(value="CREATE_NAME")
    private String createName;//创建人名称
    
    @TableField(value="CREATE_TIME")
    private String createTime;//创建时间
    
    @TableField(value="UPDATE_BY")
    private String updateBy;//修改人代码
    
    @TableField(value="UPDATE_NAME")
    private String updateName;//修改人名称
    
    @TableField(value="UPDATE_TIME")
    private String updateTime;//修改时间

	@TableField(value="COL1")
	private String col1;//备用

	@TableField(value="COL2")
	private String col2;//备用

	@TableField(value="COL3")
	private String col3;//备用

	@TableField(value="COL4")
	private Double col4;//备用

	@TableField(exist = false)
	private String finishValidateDateStart;//DCL_TIME

	@TableField(exist = false)
	private String finishValidateDateEnd;//DCL_TIME

	@TableField(exist = false)
	private String decTimeStart;//DEC_TIME

	@TableField(exist = false)
	private String decTimeEnd;//DEC_TIME

	@TableField(exist = false)
	private String passportTypecdName;//核放单类型名称

	@TableField(exist = false)
	private String ioTypecdName;//进出标志名称

	@TableField(exist = false)
	private String emapvMarkcdName;//审批标志名称

	@TableField(exist = false)
	private String dclTypecdName;//申报类型名称

	@TableField(exist = false)
	private String rltTbTypecdName;//关联单证类型名称

	@TableField(exist = false)
	private String masterCuscdName;//主管关区名称

	@TableField(exist = false)
	private String areaCodeName;//监管场所名称

	@TableField(exist = false)
	private String bindTypecdName;//绑定类型名称

	@TableField(exist = false)//单据类型后缀
	private String busType;

    @TableField(exist=false)
    private List<SasPassportDt> sasPassportDts;

    @TableField(exist=false)
    private List<SasPassportRlt> sasPassportRlts;
    
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public Integer getChgTmsCnt() {
		return chgTmsCnt;
	}

	public void setChgTmsCnt(Integer chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}

	public String getPassportTypecd() {
		return passportTypecd;
	}

	public void setPassportTypecd(String passportTypecd) {
		this.passportTypecd = passportTypecd;
	}

	public String getSasPassportPreentNo() {
		return sasPassportPreentNo;
	}

	public void setSasPassportPreentNo(String sasPassportPreentNo) {
		this.sasPassportPreentNo = sasPassportPreentNo;
	}

	public String getDclTypecd() {
		return dclTypecd;
	}

	public void setDclTypecd(String dclTypecd) {
		this.dclTypecd = dclTypecd;
	}

	public String getIoTypecd() {
		return ioTypecd;
	}

	public void setIoTypecd(String ioTypecd) {
		this.ioTypecd = ioTypecd;
	}

	public String getBindTypecd() {
		return bindTypecd;
	}

	public void setBindTypecd(String bindTypecd) {
		this.bindTypecd = bindTypecd;
	}

	public String getMasterCuscd() {
		return masterCuscd;
	}

	public void setMasterCuscd(String masterCuscd) {
		this.masterCuscd = masterCuscd;
	}

	public String getRltTbTypecd() {
		return rltTbTypecd;
	}

	public void setRltTbTypecd(String rltTbTypecd) {
		this.rltTbTypecd = rltTbTypecd;
	}

	public String getRltNo() {
		return rltNo;
	}

	public void setRltNo(String rltNo) {
		this.rltNo = rltNo;
	}

	public String getAreainEtpsno() {
		return areainEtpsno;
	}

	public void setAreainEtpsno(String areainEtpsno) {
		this.areainEtpsno = areainEtpsno;
	}

	public String getAreainEtpsNm() {
		return areainEtpsNm;
	}

	public void setAreainEtpsNm(String areainEtpsNm) {
		this.areainEtpsNm = areainEtpsNm;
	}

	public String getAreainEtpsSccd() {
		return areainEtpsSccd;
	}

	public void setAreainEtpsSccd(String areainEtpsSccd) {
		this.areainEtpsSccd = areainEtpsSccd;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleIcNo() {
		return vehicleIcNo;
	}

	public void setVehicleIcNo(String vehicleIcNo) {
		this.vehicleIcNo = vehicleIcNo;
	}

	public String getContainerNo() {
		return containerNo;
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}

	public BigDecimal getVehicleWt() {
		return vehicleWt;
	}

	public void setVehicleWt(BigDecimal vehicleWt) {
		this.vehicleWt = vehicleWt;
	}

	public String getVehicleFrameNo() {
		return vehicleFrameNo;
	}

	public void setVehicleFrameNo(String vehicleFrameNo) {
		this.vehicleFrameNo = vehicleFrameNo;
	}

	public BigDecimal getVehicleFrameWt() {
		return vehicleFrameWt;
	}

	public void setVehicleFrameWt(BigDecimal vehicleFrameWt) {
		this.vehicleFrameWt = vehicleFrameWt;
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public BigDecimal getContainerWt() {
		return containerWt;
	}

	public void setContainerWt(BigDecimal containerWt) {
		this.containerWt = containerWt;
	}

	public BigDecimal getTotaLWt() {
		return totaLWt;
	}

	public void setTotaLWt(BigDecimal totaLWt) {
		this.totaLWt = totaLWt;
	}

	public BigDecimal getPassCollectWt() {
		return passCollectWt;
	}

	public void setPassCollectWt(BigDecimal passCollectWt) {
		this.passCollectWt = passCollectWt;
	}

	public BigDecimal getWtError() {
		return wtError;
	}

	public void setWtError(BigDecimal wtError) {
		this.wtError = wtError;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = DateUtil.timeSwitch(col3);
	}

	public Double getCol4() {
		return col4;
	}

	public void setCol4(Double col4) {
		this.col4 = col4;
	}

	public BigDecimal getTotalGrossWt() {
		return totalGrossWt;
	}

	public void setTotalGrossWt(BigDecimal totalGrossWt) {
		this.totalGrossWt = totalGrossWt;
	}

	public BigDecimal getTotalNetWt() {
		return totalNetWt;
	}

	public void setTotalNetWt(BigDecimal totalNetWt) {
		this.totalNetWt = totalNetWt;
	}

	public String getDclErConc() {
		return dclErConc;
	}

	public void setDclErConc(String dclErConc) {
		this.dclErConc = dclErConc;
	}

	public String getDclTime() {
		return dclTime;
	}

	public void setDclTime(String dclTime) {
		this.dclTime = DateUtil.timeSwitch(dclTime);
	}

	public String getPassId() {
		return passId;
	}

	public void setPassId(String passId) {
		this.passId = passId;
	}

	public String getSecdPassId() {
		return secdPassId;
	}

	public void setSecdPassId(String secdPassId) {
		this.secdPassId = secdPassId;
	}

	public String getPassTime() {
		return passTime;
	}

	public void setPassTime(String passTime) {
		this.passTime = DateUtil.timeSwitch(passTime);
	}

	public String getSecdPassTime() {
		return secdPassTime;
	}

	public void setSecdPassTime(String secdPassTime) {
		this.secdPassTime = DateUtil.timeSwitch(secdPassTime);
	}

	public String getStucd() {
		return stucd;
	}

	public void setStucd(String stucd) {
		this.stucd = stucd;
	}

	public String getEmapvMarkcd() {
		return emapvMarkcd;
	}

	public void setEmapvMarkcd(String emapvMarkcd) {
		this.emapvMarkcd = emapvMarkcd;
	}

	public String getLogisticsStucd() {
		return logisticsStucd;
	}

	public void setLogisticsStucd(String logisticsStucd) {
		this.logisticsStucd = logisticsStucd;
	}

	public String getEtpsPreentNo() {
		return etpsPreentNo;
	}

	public void setEtpsPreentNo(String etpsPreentNo) {
		this.etpsPreentNo = etpsPreentNo;
	}

	public String getOwnerSystem() {
		return ownerSystem;
	}

	public void setOwnerSystem(String ownerSystem) {
		this.ownerSystem = ownerSystem;
	}

	public String getiEFlow() {
		return iEFlow;
	}

	public void setiEFlow(String iEFlow) {
		this.iEFlow = iEFlow;
	}

	public String getClearaNceType() {
		return clearaNceType;
	}

	public void setClearaNceType(String clearaNceType) {
		this.clearaNceType = clearaNceType;
	}

	public String getBindFlag() {
		return bindFlag;
	}

	public void setBindFlag(String bindFlag) {
		this.bindFlag = bindFlag;
	}

	public String getVehicleIRegno() {
		return vehicleIRegno;
	}

	public void setVehicleIRegno(String vehicleIRegno) {
		this.vehicleIRegno = vehicleIRegno;
	}

	public String getBindTime() {
		return bindTime;
	}

	public void setBindTime(String bindTime) {
		this.bindTime = DateUtil.timeSwitch(bindTime);
	}

	public String getPassportTypenm() {
		return passportTypenm;
	}

	public void setPassportTypenm(String passportTypenm) {
		this.passportTypenm = passportTypenm;
	}

	public String getDclTypenm() {
		return dclTypenm;
	}

	public void setDclTypenm(String dclTypenm) {
		this.dclTypenm = dclTypenm;
	}

	public String getIoTyoenm() {
		return ioTyoenm;
	}

	public void setIoTyoenm(String ioTyoenm) {
		this.ioTyoenm = ioTyoenm;
	}

	public String getBindTypenm() {
		return bindTypenm;
	}

	public void setBindTypenm(String bindTypenm) {
		this.bindTypenm = bindTypenm;
	}

	public String getRltTbTypenm() {
		return rltTbTypenm;
	}

	public void setRltTbTypenm(String rltTbTypenm) {
		this.rltTbTypenm = rltTbTypenm;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getContainerTypeNm() {
		return containerTypeNm;
	}

	public void setContainerTypeNm(String containerTypeNm) {
		this.containerTypeNm = containerTypeNm;
	}

	public String getStunm() {
		return stunm;
	}

	public void setStunm(String stunm) {
		this.stunm = stunm;
	}

	public String getEmapvMarknm() {
		return emapvMarknm;
	}

	public void setEmapvMarknm(String emapvMarknm) {
		this.emapvMarknm = emapvMarknm;
	}

	public String getLogisticsStunm() {
		return logisticsStunm;
	}

	public void setLogisticsStunm(String logisticsStunm) {
		this.logisticsStunm = logisticsStunm;
	}

	public String getiEFlowNm() {
		return iEFlowNm;
	}

	public void setiEFlowNm(String iEFlowNm) {
		this.iEFlowNm = iEFlowNm;
	}

	public String getBindFlagNm() {
		return bindFlagNm;
	}

	public void setBindFlagNm(String bindFlagNm) {
		this.bindFlagNm = bindFlagNm;
	}

	public String getCopEntNo() {
		return copEntNo;
	}

	public void setCopEntNo(String copEntNo) {
		this.copEntNo = copEntNo;
	}

	public String getMasterCusname() {
		return masterCusname;
	}

	public void setMasterCusname(String masterCusname) {
		this.masterCusname = masterCusname;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getChkStatus() {
		return chkStatus;
	}

	public void setChkStatus(String chkStatus) {
		this.chkStatus = chkStatus;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getRetChannel() {
		return retChannel;
	}

	public void setRetChannel(String retChannel) {
		this.retChannel = retChannel;
	}

	public String getBizTypeName() {
		return bizTypeName;
	}

	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
	}

	public String getChkStatusName() {
		return chkStatusName;
	}

	public void setChkStatusName(String chkStatusName) {
		this.chkStatusName = chkStatusName;
	}

	public String getDocTypeName() {
		return docTypeName;
	}

	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}

	public String getRetChannelName() {
		return retChannelName;
	}

	public void setRetChannelName(String retChannelName) {
		this.retChannelName = retChannelName;
	}

	public String getDecTime() {
		return decTime;
	}

	public void setDecTime(String decTime) {
		this.decTime = DateUtil.timeSwitch(decTime);
	}

	public String getChkTime() {
		return chkTime;
	}

	public void setChkTime(String chkTime) {
		this.chkTime = DateUtil.timeSwitch(chkTime);
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getInputerCode() {
		return inputerCode;
	}

	public void setInputerCode(String inputerCode) {
		this.inputerCode = inputerCode;
	}

	public String getInputerName() {
		return inputerName;
	}

	public void setInputerName(String inputerName) {
		this.inputerName = inputerName;
	}

	public String getInputCopNo() {
		return inputCopNo;
	}

	public void setInputCopNo(String inputCopNo) {
		this.inputCopNo = inputCopNo;
	}

	public String getInputCopName() {
		return inputCopName;
	}

	public void setInputCopName(String inputCopName) {
		this.inputCopName = inputCopName;
	}

	public String getDeclareMark() {
		return declareMark;
	}

	public void setDeclareMark(String declareMark) {
		this.declareMark = declareMark;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = DateUtil.timeSwitch(createTime);
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = DateUtil.timeSwitch(updateTime);
	}

	public String getPassportTypecdName() {
		return passportTypecdName;
	}

	public void setPassportTypecdName(String passportTypecdName) {
		this.passportTypecdName = passportTypecdName;
	}

	public String getIoTypecdName() {
		return ioTypecdName;
	}

	public void setIoTypecdName(String ioTypecdName) {
		this.ioTypecdName = ioTypecdName;
	}

	public String getEmapvMarkcdName() {
		return emapvMarkcdName;
	}

	public void setEmapvMarkcdName(String emapvMarkcdName) {
		this.emapvMarkcdName = emapvMarkcdName;
	}

	public String getDclTypecdName() {
		return dclTypecdName;
	}

	public void setDclTypecdName(String dclTypecdName) {
		this.dclTypecdName = dclTypecdName;
	}

	public String getRltTbTypecdName() {
		return rltTbTypecdName;
	}

	public void setRltTbTypecdName(String rltTbTypecdName) {
		this.rltTbTypecdName = rltTbTypecdName;
	}

	public String getMasterCuscdName() {
		return masterCuscdName;
	}

	public void setMasterCuscdName(String masterCuscdName) {
		this.masterCuscdName = masterCuscdName;
	}

	public String getFinishValidateDateStart() {
		return finishValidateDateStart;
	}

	public void setFinishValidateDateStart(String finishValidateDateStart) {
		this.finishValidateDateStart = finishValidateDateStart;
	}

	public String getFinishValidateDateEnd() {
		return finishValidateDateEnd;
	}

	public void setFinishValidateDateEnd(String finishValidateDateEnd) {
		this.finishValidateDateEnd = finishValidateDateEnd;
	}

	public String getBindTypecdName() {
		return bindTypecdName;
	}

	public void setBindTypecdName(String bindTypecdName) {
		this.bindTypecdName = bindTypecdName;
	}

	public List<SasPassportDt> getSasPassportDts() {
		return sasPassportDts;
	}

	public void setSasPassportDts(List<SasPassportDt> sasPassportDts) {
		this.sasPassportDts = sasPassportDts;
	}

	public List<SasPassportRlt> getSasPassportRlts() {
		return sasPassportRlts;
	}

	public void setSasPassportRlts(List<SasPassportRlt> sasPassportRlts) {
		this.sasPassportRlts = sasPassportRlts;
	}

	public String getDecTimeStart() {
		return decTimeStart;
	}

	public void setDecTimeStart(String decTimeStart) {
		this.decTimeStart = decTimeStart;
	}

	public String getDecTimeEnd() {
		return decTimeEnd;
	}

	public void setDecTimeEnd(String decTimeEnd) {
		this.decTimeEnd = decTimeEnd;
	}

	public String getAreaCodeName() {
		return areaCodeName;
	}

	public void setAreaCodeName(String areaCodeName) {
		this.areaCodeName = areaCodeName;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}
}

