package com.powerbridge.bssp.cod_cus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("cod_cus_complex")
public class CodCusComplex implements Serializable {
	
	@TableField(exist = false)
	private static final long serialVersionUID = 8358987677233641090L;
	
	@TableId(value = "PK_SEQ")
    private Double pkSeq;//序号
	
	@TableField(value = "CODE_T")
    private String codeT;//HS编码
	
	@TableField(value = "CODE_S")
    private String codeS;//附加编码
	
	@TableField(value = "G_NAME")
    private String gName;//商品名称
	
	@TableField(value = "LOW_RATE")
    private String lowRate;//进口优惠关税
	
	@TableField(value = "HIGH_RATE")
    private String highRate;//进口普通关税
	
	@TableField(value = "OUT_RATE")
    private String outRate;//出口关税
	
	@TableField(value = "REG_MARK")
    private String regMark;//申报标志
	
	@TableField(value = "REG_RATE")
    private String regRate;//消费税
	
	@TableField(value = "TAX_TYPE")
    private String taxType;//征税类型
	
	@TableField(value = "TAX_RATE")
    private String taxRate;//增值税
	
	@TableField(value = "COMM_RATE")
    private String commRate;//一般税率
	
	@TableField(value = "TAIWAN_RATE")
    private String taiwanRate;//对台调节税
	
	@TableField(value = "OTHER_TYPE")
    private String otherType;//其他类型
	
	@TableField(value = "OTHER_RATE")
    private String otherRate;//其他费税率
	
	@TableField(value = "UNIT_1")
    private String unit1;//法定单位
	
	@TableField(value = "UNIT_2")
    private String unit2;//法定第二单位
	
	@TableField(value = "ILOW_PRICE")
    private String ilowPrice;//进口最低单价
	
	@TableField(value = "IHIGH_PRICE")
    private String ihighPrice;//进口最高单价
	
	@TableField(value = "ELOW_PRICE")
    private String elowPrice;//出口最低单价
	
	@TableField(value = "EHIGH_PRICE")
    private String ehighPrice;//出口最高单价
	
	@TableField(value = "MAX_IN")
    private String maxIn;//最大进口量
	
	@TableField(value = "MAX_OUT")
    private String maxOut;//最大出口量
	
	@TableField(value = "CONTROL_MA")
    private String controlMa;//监管条件
	
	@TableField(value = "CHK_PRICE")
    private String chkPrice;//
	
	@TableField(value = "TARIFF_MARK")
    private String tariffMark;//
	
	@TableField(value = "NOTE_S")
    private String noteS;//备注
	
	@TableField(value = "SEQ_NO")
    private String seqNo;//
	
	@TableField(exist = false)
	private String id ;

	@TableField(exist = false)
	private String text ;

    @TableField(exist = false)
    private String unitName1;//法定单位

    @TableField(exist = false)
    private String unitName2;//法定第二单位

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

    public Double getPkSeq() {
        return pkSeq;
    }

    public void setPkSeq(Double pkSeq) {
        this.pkSeq = pkSeq;
    }

    public String getCodeT() {
        return codeT;
    }

    public void setCodeT(String codeT) {
        this.codeT = codeT == null ? null : codeT.trim();
    }

    public String getCodeS() {
        return codeS;
    }

    public void setCodeS(String codeS) {
        this.codeS = codeS == null ? null : codeS.trim();
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName == null ? null : gName.trim();
    }

    public String getLowRate() {
        return lowRate;
    }

    public void setLowRate(String lowRate) {
        this.lowRate = lowRate == null ? null : lowRate.trim();
    }

    public String getHighRate() {
        return highRate;
    }

    public void setHighRate(String highRate) {
        this.highRate = highRate == null ? null : highRate.trim();
    }

    public String getOutRate() {
        return outRate;
    }

    public void setOutRate(String outRate) {
        this.outRate = outRate == null ? null : outRate.trim();
    }

    public String getRegMark() {
        return regMark;
    }

    public void setRegMark(String regMark) {
        this.regMark = regMark == null ? null : regMark.trim();
    }

    public String getRegRate() {
        return regRate;
    }

    public void setRegRate(String regRate) {
        this.regRate = regRate == null ? null : regRate.trim();
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType == null ? null : taxType.trim();
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate == null ? null : taxRate.trim();
    }

    public String getCommRate() {
        return commRate;
    }

    public void setCommRate(String commRate) {
        this.commRate = commRate == null ? null : commRate.trim();
    }

    public String getTaiwanRate() {
        return taiwanRate;
    }

    public void setTaiwanRate(String taiwanRate) {
        this.taiwanRate = taiwanRate == null ? null : taiwanRate.trim();
    }

    public String getOtherType() {
        return otherType;
    }

    public void setOtherType(String otherType) {
        this.otherType = otherType == null ? null : otherType.trim();
    }

    public String getOtherRate() {
        return otherRate;
    }

    public void setOtherRate(String otherRate) {
        this.otherRate = otherRate == null ? null : otherRate.trim();
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1 == null ? null : unit1.trim();
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2 == null ? null : unit2.trim();
    }

    public String getIlowPrice() {
        return ilowPrice;
    }

    public void setIlowPrice(String ilowPrice) {
        this.ilowPrice = ilowPrice == null ? null : ilowPrice.trim();
    }

    public String getIhighPrice() {
        return ihighPrice;
    }

    public void setIhighPrice(String ihighPrice) {
        this.ihighPrice = ihighPrice == null ? null : ihighPrice.trim();
    }

    public String getElowPrice() {
        return elowPrice;
    }

    public void setElowPrice(String elowPrice) {
        this.elowPrice = elowPrice == null ? null : elowPrice.trim();
    }

    public String getEhighPrice() {
        return ehighPrice;
    }

    public void setEhighPrice(String ehighPrice) {
        this.ehighPrice = ehighPrice == null ? null : ehighPrice.trim();
    }

    public String getMaxIn() {
        return maxIn;
    }

    public void setMaxIn(String maxIn) {
        this.maxIn = maxIn == null ? null : maxIn.trim();
    }

    public String getMaxOut() {
        return maxOut;
    }

    public void setMaxOut(String maxOut) {
        this.maxOut = maxOut == null ? null : maxOut.trim();
    }

    public String getControlMa() {
        return controlMa;
    }

    public void setControlMa(String controlMa) {
        this.controlMa = controlMa == null ? null : controlMa.trim();
    }

    public String getChkPrice() {
        return chkPrice;
    }

    public void setChkPrice(String chkPrice) {
        this.chkPrice = chkPrice == null ? null : chkPrice.trim();
    }

    public String getTariffMark() {
        return tariffMark;
    }

    public void setTariffMark(String tariffMark) {
        this.tariffMark = tariffMark == null ? null : tariffMark.trim();
    }

    public String getNoteS() {
        return noteS;
    }

    public void setNoteS(String noteS) {
        this.noteS = noteS == null ? null : noteS.trim();
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    public String getUnitName1() {
        return unitName1;
    }

    public void setUnitName1(String unitName1) {
        this.unitName1 = unitName1;
    }

    public String getUnitName2() {
        return unitName2;
    }

    public void setUnitName2(String unitName2) {
        this.unitName2 = unitName2;
    }
}