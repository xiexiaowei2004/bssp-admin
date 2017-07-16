package com.powerbridge.bssp.edi.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;

import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 路由配置
 * </p>
 *
 * @author huanliu
 * @since 2017-06-23
 */
@TableName("edi_routing_info")
public class EdiRoutingInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId("UID")
	private String uid;
	/**
	 * 场所代码
	 */
	@TableField("AREA_CODE")
	private String areaCode;
	/**
	 * 认证码
	 */
	@TableField("TOKEN")
	private String token;
	/**
	 * 单据类型
	 */
	@TableField("DOC_TYPE")
	private String docType;
	/**
	 * 单据名称
	 */
	@TableField("DOC_NAME")
	private String docName;
	/**
	 * 业务类型
	 */
	@TableField("BIZ_TYPE")
	private String bizType;
	/**
	 * 业务名称
	 */
	@TableField("BIZ_NAME")
	private String bizName;
	/**
	 * 路由标识
	 */
	@TableField("ROUTE_TYPE")
	private String routeType;
	/**
	 * 报文头
	 */
	@TableField("PRONAME")
	private String proname;
	/**
	 * 启用标识
	 */
	@TableField("STATUS")
	private String status;
	/**
	 * 传输方式
	 */
	@TableField("TRANSMODE")
	private String transMode;
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
	private String createTime;
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
	private String updateTime;
	/**
	 * 备注
	 */
	@TableField("REMARKS")
	private String remarks;
	/**
	 * 发送方编号
	 */
	@TableField("SENDER_ID")
	private String senderId;
	/**
	 * 接收方编号
	 */
	@TableField("RECEIVER_ID")
	private String receiverId;
	/**
	 * 传输类型passagewayName
	 */
	@TableField("PASSAGEWAY")
	private String passageway;


	/**
	 * 接收url
	 */
	@TableField("RECL_URL")
	private String reclUrl;
	/**
	 * 发送url
	 */
	@TableField("SEND_URL")
	private String sendUrl;


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getTransMode() {
		return transMode;
	}

	public void setTransMode(String transMode) {
		this.transMode = transMode;
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


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getPassageway() {
		return passageway;
	}

	public void setPassageway(String passageway) {
		this.passageway = passageway;
	}

	public String getReclUrl() {
		return reclUrl;
	}

	public void setReclUrl(String reclUrl) {
		this.reclUrl = reclUrl;
	}

	public String getSendUrl() {
		return sendUrl;
	}

	public void setSendUrl(String sendUrl) {
		this.sendUrl = sendUrl;
	}


}
