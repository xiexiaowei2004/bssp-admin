package com.powerbridge.bssp.sas.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;

import java.io.Serializable;

/**
 * <p>
 * 业务申报商品正式表
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
@TableName("sas_dcl_cus_dt")
public class SasDclCusDt implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = -7348949864219627836L;
	/**
	 * 主键
	 */
	@TableId("UID")
	private String uid;
	/**
	 * 申报表编号
	 */
	@TableField("SAS_DCL_NO")
	private String sasDclNo;
	/**
	 * 申报序号
	 */
	@TableField("SAS_DCL_SEQNO")
	private Integer sasDclSeqno;
	/**
	 * 变更次数
	 */
	@TableField("CHG_TMS_CNT")
	private Integer chgTmsCnt;
	/**
	 * 料件成品标志代码
	 */
	@TableField("MTPCK_ENDPRD_TYPECD")
	private String mtpckEndprdTypecd;
	/**
	 * 底账商品序号
	 */
	@TableField("ORIACT_GDS_SEQNO")
	private Integer oriactGdsSeqno;
	/**
	 * 商品编码
	 */
	@TableField("GDECD")
	private String gdecd;
	/**
	 * 商品名称
	 */
	@TableField("GDS_NM")
	private String gdsNm;
	/**
	 * 商品规格型号描述
	 */
	@TableField("GDS_SPCF_MODEL_DESC")
//	@NotEmpty(message = "商品规格型号描述不能为空")
	private String gdsSpcfModelDesc;
	/**
	 * 数量
	 */
	@TableField("DCL_QTY")
	private BigDecimal dclQty;
	/**
	 * 申报计量单位代码
	 */
	@TableField("DCL_UNITCD")
	private String dclUnitcd;
	/**
	 * 单价
	 */
	@TableField("DCL_UPRC_AMT")
	private BigDecimal dclUprcAmt;
	/**
	 * 总价
	 */
	@TableField("DCL_TOTAL_AMT")
	private BigDecimal dclTotalAmt;
	/**
	 * 币制代码
	 */
	@TableField("DCL_CURRCD")
	private String dclCurrcd;
	/**
	 * 许可证编号
	 */
	@TableField("LICENCE_NO")
	private String licenceNo;
	/**
	 * 许可证有效期
	 */
	@TableField("LICENCE_VALID_TIME")
	private String licenceValidTime;
	/**
	 * 实际出区数量
	 */
	@TableField("ACTL_AREAOUT_QTY")
	private BigDecimal actlAreaoutQty;
	/**
	 * 实际入区数量
	 */
	@TableField("ACTL_AREAIN_QTY")
	private BigDecimal actlAreainQty;
	/**
	 * 商品标记代码
	 */
	@TableField("GDS_MARKCD")
	private String gdsMarkcd;
	/**
	 * 商品备注
	 */
	@TableField("GDS_RMK")
	private String gdsRmk;
	/**
	 * 清单核注数量
	 */
	@TableField("INVT_VDEDT_QTY")
	private BigDecimal invtVdedtQty;
	/**
	 * 最近核注时间
	 */
	@TableField("RCNT_VDEDT_TIME")
	private String rcntVdedtTime;
	/**
	 * 修改标志代码
	 */
	@TableField("MODF_MARKCD")
	private String modfMarkcd;
	/**
	 * 修改标志
	 */
	@TableField(exist = false)
	private String modfMarkname;
	/**
	 * 备注
	 */
	@TableField("RMK")
	private String rmk;
	/**
	 * 企业预录入编号
	 */
	@TableField("ETPS_PREENT_NO")
	private String etpsPreentNo;
	/**
	 * 单据编号
	 */
	@TableField("SEQ_NO")
	private String seqNo;

	/**
	 * 备用
	 */
	@TableField(value="COL1")
	private String col1;

	/**
	 * 备用
	 */
	@TableField(value="COL2")
	private String col2;

	/**
	 * 备用
	 */
	@TableField(value="COL3")
	private String col3;

	/**
	 * 备用
	 */
	@TableField(value="COL4")
	private Double col4;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSasDclNo() {
		return sasDclNo;
	}

	public void setSasDclNo(String sasDclNo) {
		this.sasDclNo = sasDclNo;
	}

	public Integer getSasDclSeqno() {
		return sasDclSeqno;
	}

	public void setSasDclSeqno(Integer sasDclSeqno) {
		this.sasDclSeqno = sasDclSeqno;
	}



	public Integer getChgTmsCnt() {
		return chgTmsCnt;
	}

	public void setChgTmsCnt(Integer chgTmsCnt) {
		this.chgTmsCnt = chgTmsCnt;
	}



	public String getMtpckEndprdTypecd() {
		return mtpckEndprdTypecd;
	}

	public void setMtpckEndprdTypecd(String mtpckEndprdTypecd) {
		this.mtpckEndprdTypecd = mtpckEndprdTypecd;
	}

	public Integer getOriactGdsSeqno() {
		return oriactGdsSeqno;
	}

	public void setOriactGdsSeqno(Integer oriactGdsSeqno) {
		this.oriactGdsSeqno = oriactGdsSeqno;
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

	public String getGdsSpcfModelDesc() {
		return gdsSpcfModelDesc;
	}

	public void setGdsSpcfModelDesc(String gdsSpcfModelDesc) {
		this.gdsSpcfModelDesc = gdsSpcfModelDesc;
	}

	public BigDecimal getDclQty() {
		return dclQty;
	}

	public void setDclQty(BigDecimal dclQty) {
		this.dclQty = dclQty;
	}



	public String getDclUnitcd() {
		return dclUnitcd;
	}

	public void setDclUnitcd(String dclUnitcd) {
		this.dclUnitcd = dclUnitcd;
	}

	public BigDecimal getDclUprcAmt() {
		return dclUprcAmt;
	}

	public void setDclUprcAmt(BigDecimal dclUprcAmt) {
		this.dclUprcAmt = dclUprcAmt;
	}


	public BigDecimal getDclTotalAmt() {
		return dclTotalAmt;
	}

	public void setDclTotalAmt(BigDecimal dclTotalAmt) {
		this.dclTotalAmt = dclTotalAmt;
	}



	public String getDclCurrcd() {
		return dclCurrcd;
	}

	public void setDclCurrcd(String dclCurrcd) {
		this.dclCurrcd = dclCurrcd;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public BigDecimal getActlAreaoutQty() {
		return actlAreaoutQty;
	}

	public void setActlAreaoutQty(BigDecimal actlAreaoutQty) {
		this.actlAreaoutQty = actlAreaoutQty;
	}



	public BigDecimal getActlAreainQty() {
		return actlAreainQty;
	}

	public void setActlAreainQty(BigDecimal actlAreainQty) {
		this.actlAreainQty = actlAreainQty;
	}


	public String getGdsMarkcd() {
		return gdsMarkcd;
	}

	public void setGdsMarkcd(String gdsMarkcd) {
		this.gdsMarkcd = gdsMarkcd;
	}

	public String getGdsRmk() {
		return gdsRmk;
	}

	public void setGdsRmk(String gdsRmk) {
		this.gdsRmk = gdsRmk;
	}

	public BigDecimal getInvtVdedtQty() {
		return invtVdedtQty;
	}

	public void setInvtVdedtQty(BigDecimal invtVdedtQty) {
		this.invtVdedtQty = invtVdedtQty;
	}



	public String getLicenceValidTime() {
		return licenceValidTime;
	}

	public void setLicenceValidTime(String licenceValidTime) {
		this.licenceValidTime = DateUtil.timeSwitch(licenceValidTime);
	}

	public String getRcntVdedtTime() {
		return rcntVdedtTime;
	}

	public void setRcntVdedtTime(String rcntVdedtTime) {
		this.rcntVdedtTime = DateUtil.timeSwitch(rcntVdedtTime);
	}

	public String getModfMarkcd() {
		return modfMarkcd;
	}

	public void setModfMarkcd(String modfMarkcd) {
		this.modfMarkcd = modfMarkcd;
	}

	public String getModfMarkname() {
		return modfMarkname;
	}

	public void setModfMarkname(String modfMarkname) {
		this.modfMarkname = modfMarkname;
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

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
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

}
