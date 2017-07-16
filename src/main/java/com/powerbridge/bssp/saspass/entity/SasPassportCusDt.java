package com.powerbridge.bssp.saspass.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName("sas_passport_cus_dt")
public class SasPassportCusDt implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 * 
	 * @pdOid 33e96225-8465-4484-9f97-8f51d45ca819
	 */
	@TableId(value = "UID")
	public String uid;
	/**
	 * 核放单编号
	 * 
	 * @pdOid 2ad95cde-c9de-4a02-a9f5-3208fb044a85
	 */
	@TableField(value = "PASSPORT_NO")
	public String passportNo;
	/**
	 * 序号
	 * 
	 * @pdOid 62dfdf72-330d-4c3e-b2f3-ba938316f0a9
	 */
	@TableField(value = "PASSPORT_SEQNO")
	public Integer passportSeqno;
	/**
	 * 变更次数
	 * 
	 * @pdOid 9ff0ea59-3bd6-4fba-912d-44a6e1d1a052
	 */
	@TableField(value = "CHG_TMS_CNT")
	public Integer chgTmsCnt;
	/**
	 * 商品料号
	 * 
	 * @pdOid 4f0a1b5b-1bce-45fd-9d78-797304f5d5da
	 */
	@TableField(value = "GDS_MTNO")
	public String gdsMtno;
	/**
	 * 商品编码
	 * 
	 * @pdOid 5709f71e-f1f9-4d02-bbdc-775ca7fed25d
	 */
	@TableField(value = "GDECD")
	public String gdecd;
	/**
	 * 商品名称
	 * 
	 * @pdOid 8c6277a3-372d-437b-bc28-47ead7ce411b
	 */@TableField(value="GDS_NM")
	public String gdsNm;
	/**
	 * 货物毛重
	 * 
	 * @pdOid 7d1e80ed-0d9b-451a-bc3d-4d3f9fd4dd10
	 */@TableField(value="GROSS_WT")
	public BigDecimal grossWt;
	/**
	 * 货物净重
	 * 
	 * @pdOid 41e84ecd-7d5c-4bec-944c-e153f6a5a6dd
	 */@TableField(value="NET_WT")
	public BigDecimal netWt;
	/**
	 * 关联商品序号
	 * 
	 * @pdOid b42372a5-7ffa-4a0d-b7cf-c5d0eb6544cd
	 */
	@TableField(value="RLT_GDS_SEQNO")
	public Integer rltGdsSeqno;

	/**
	 * 关联单证类型代码
	 */
	/*@TableField(value = "RLT_TB_TYPECD")
	private String rltTbTypecd;*/
	/**
	 * 申报单位代码
	 * 
	 * @pdOid 292501c5-c230-44a0-913b-70d641c67a4c
	 */@TableField(value="DCL_UNITCD")
	private String dclUnitcd;
	/**
	 * 申报数量
	 * 
	 * @pdOid 1f125bf4-fc0c-42f9-93d1-35ee8e6411fc
	 */@TableField(value="DCL_QTY")
	private BigDecimal dclQty;
	/**
	 * 备注
	 * 
	 * @pdOid 1d41ba09-3125-4048-b99e-5995734e46e5
	 */@TableField(value="RMK")
	private String rmk;
	/**
	 * 企业预录入编号
	 * 
	 * @pdOid 61670e59-d518-4fb4-a636-197ebfa10c2f
	 */@TableField(value="ETPS_PREENT_NO")
	private String etpsPreentNo;
	/**
	 * 关联单证编号
	 * 
	 * @pdOid 438ac279-46a1-4408-b61f-438a3f65c5ed
	 */@TableField(value="RLT_NO")
	private String rltNo;
	/**
	 * 关联单证企业备案号
	 * 
	 * @pdOid 08048ce2-163c-4222-9927-9b22debad6ad
	 */@TableField(value="COP_ENT_NO")
	private String copEntNo;
	/**
	 * 单据编号
	 * 
	 * @pdOid e5b088df-9e3c-49e9-bad0-011ac680105e
	 */@TableField(value="SEQ_NO")
	private String seqNo;
	/**
	 * 申报（录入）人代码
	 * 
	 * @pdOid 50ad1d89-d0d4-474a-8872-5be8ce2d68f1
	 */@TableField(value="INPUTER_CODE")
	private String inputerCode;
	/**
	 * 申报（录入）人名称
	 * 
	 * @pdOid 4de0e90b-fccb-4756-9189-b61f97651ae5
	 */@TableField(value="INPUTER_NAME")
	private String inputerName;
	/**
	 * 申报（录入）企业代码
	 * 
	 * @pdOid 2070607f-ba68-466e-93be-5e372dad1f4e
	 */@TableField(value="INPUT_COP_NO")
	private String inputCopNo;
	/**
	 * 申报（录入）企业名称
	 * 
	 * @pdOid c2a76734-b6bd-46d5-a16d-3f464e7b8913
	 */@TableField(value="INPUT_COP_NAME")
	private String inputCopName;
	/**
	 * 申报方式(备案/变更)
	 * 
	 * @pdOid 64e45eb6-8e28-43da-bfd2-0c24e7d809e3
	 */@TableField(value="DECLARE_MARK")
	private String declareMark;
	/**
	 * 创建人代码
	 * 
	 * @pdOid b9d77946-20d9-4abf-b00e-b7f2230d02b5
	 */@TableField(value="CREATE_BY")
	private String createBy;
	/**
	 * 创建人名称
	 * 
	 * @pdOid a995005b-6756-4ef0-a01d-a52b0bd23737
	 */@TableField(value="CREATE_NAME")
	private String createName;
	/**
	 * 创建时间
	 * 
	 * @pdOid 1befcc11-57a2-49c3-8630-2bd08ff8a1cc
	 */@TableField(value="CREATE_TIME")
	private String createTime;
	/**
	 * 修改人代码
	 * 
	 * @pdOid d2d2683c-f7b4-4daa-bdd1-8df89f2eb702
	 */@TableField(value="UPDATE_BY")
	private String updateBy;
	/**
	 * 修改人名称
	 * 
	 * @pdOid c5c605c7-2f7c-424d-97ce-27e6dbd0e4d3
	 */@TableField(value="UPDATE_NAME")
	private String updateName;
	/**
	 * 修改时间
	 * 
	 * @pdOid 80928ff3-c59e-4dd9-95fb-f8a1b94d3423
	 */@TableField(value="UPDATE_TIME")
	private String updateTime;

	@TableField(value="COL1")
	private String col1;//备用

	@TableField(value="COL2")
	private String col2;//备用

	@TableField(value="COL3")
	private String col3;//备用

	@TableField(value="COL4")
	private Double col4;//备用

	@TableField(exist = false)
	private String dclUnitcdName;

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
	public Integer getPassportSeqno() {
		return passportSeqno;
	}
	public void setPassportSeqno(Integer passportSeqno) {
		this.passportSeqno = passportSeqno;
	}
	public Integer getChgTmsCnt() {
		return chgTmsCnt;
	}
	public void setChgTmsCnt(Integer chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}
	public String getGdsMtno() {
		return gdsMtno;
	}
	public void setGdsMtno(String gdsMtno) {
		this.gdsMtno = gdsMtno;
	}
	public String getGdecd() {
		return gdecd;
	}
	public void setGdecd(String gdecd) {
		this.gdecd = gdecd;
	}
	public String getGdsNm() {
		return gdsNm;
	}
	public void setGdsNm(String gdsNm) {
		this.gdsNm = gdsNm;
	}
	public BigDecimal getGrossWt() {
		return grossWt;
	}
	public void setGrossWt(BigDecimal grossWt) {
		this.grossWt = grossWt;
	}
	public BigDecimal getNetWt() {
		return netWt;
	}
	public void setNetWt(BigDecimal netWt) {
		this.netWt = netWt;
	}
	public Integer getRltGdsSeqno() {
		return rltGdsSeqno;
	}
	
	public String getDclUnitcd() {
		return dclUnitcd;
	}
	public void setDclUnitcd(String dclUnitcd) {
		this.dclUnitcd = dclUnitcd;
	}
	public BigDecimal getDclQty() {
		return dclQty;
	}
	public void setDclQty(BigDecimal dclQty) {
		this.dclQty = dclQty;
	}
	public String getRmk() {
		return rmk;
	}
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
	public String getEtpsPreentNo() {
		return etpsPreentNo;
	}
	public void setEtpsPreentNo(String etpsPreentNo) {
		this.etpsPreentNo = etpsPreentNo;
	}
	public String getRltNo() {
		return rltNo;
	}
	public void setRltNo(String rltNo) {
		this.rltNo = rltNo;
	}
	public String getCopEntNo() {
		return copEntNo;
	}
	public void setCopEntNo(String copEntNo) {
		this.copEntNo = copEntNo;
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

	public void setRltGdsSeqno(Integer rltGdsSeqno) {
		this.rltGdsSeqno = rltGdsSeqno;
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

	public String getDclUnitcdName() {
		return dclUnitcdName;
	}

	public void setDclUnitcdName(String dclUnitcdName) {
		this.dclUnitcdName = dclUnitcdName;
	}

	/*public String getRltTbTypecd() {
		return rltTbTypecd;
	}

	public void setRltTbTypecd(String rltTbTypecd) {
		this.rltTbTypecd = rltTbTypecd;
	}*/


}
