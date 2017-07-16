package com.powerbridge.bssp.cop_et.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 联网企业档案库料件历史表
 *
 * @author willChen
 * @since 2017-07-03 09:40:50
 */
@TableName("et_his_img")
public class EtHisImg implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 7093298149434240348L;
    /**
     * 主键
     */
    @TableId("UID")
    private String uid;
    /**
     * 联网企业档案库编号
     */
    @TableField("ET_ARCRP_NO")
    private String etArcrpNo;
    /**
     * 变更次数
     */
    @TableField("CHG_TMS_CNT")
    private Integer chgTmsCnt;
    /**
     * 商品序号
     */
    @TableField("GDS_SEQNO")
    private Integer gdsSeqno;
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
     * 修改标记代码
     */
    @TableField("MODF_MARKCD")
    private String modfMarkcd;
    /**
     * 修改标记名称
     */
    @TableField("MODF_MARKNAME")
    private String modfMarkname;
    /**
     * 料件成品类型代码
     */
    @TableField("MTPCK_ENDPRD_TYPECD")
    private String mtpckEndprdTypecd;
    /**
     * 备注
     */
    @TableField("RMK")
    private String rmk;
    /**
     * 单据编号
     */
    @TableField("SEQ_NO")
    private String seqNo;
    /**
     * 创建人代码
     */
    @TableField("CREATE_BY")
    private String createBy;
    /**
     * 创建人名称
     */
    @TableField("CREATE_NAME")
    private String createName;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 修改人代码
     */
    @TableField("UPDATE_BY")
    private String updateBy;
    /**
     * 修改人名称
     */
    @TableField("UPDATE_NAME")
    private String updateName;
    /**
     * 修改时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;
    /**
     * 商品料号
     */
    @TableField("GDS_MTNO")
    private String gdsMtno;
    /**
     * 入库日期
     */
    @TableField("ADD_TIME")
    private Date addTime;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEtArcrpNo() {
        return etArcrpNo;
    }

    public void setEtArcrpNo(String etArcrpNo) {
        this.etArcrpNo = etArcrpNo;
    }

    public Integer getChgTmsCnt() {
        return chgTmsCnt;
    }

    public void setChgTmsCnt(Integer chgTmsCnt) {
        this.chgTmsCnt = chgTmsCnt;
    }

    public Integer getGdsSeqno() {
        return gdsSeqno;
    }

    public void setGdsSeqno(Integer gdsSeqno) {
        this.gdsSeqno = gdsSeqno;
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

    public String getMtpckEndprdTypecd() {
        return mtpckEndprdTypecd;
    }

    public void setMtpckEndprdTypecd(String mtpckEndprdTypecd) {
        this.mtpckEndprdTypecd = mtpckEndprdTypecd;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getGdsMtno() {
        return gdsMtno;
    }

    public void setGdsMtno(String gdsMtno) {
        this.gdsMtno = gdsMtno;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

}
