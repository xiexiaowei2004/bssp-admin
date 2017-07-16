package com.powerbridge.bssp.common.security;

import com.powerbridge.bssp.cop.entity.CopEnt;

import java.io.Serializable;


public class SystemAuthorizingUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 用户ID */
	private Long userId;

	/** 登录名 */
	private String loginName;

	/** 昵称 */
	private String userName;

	/** 真实姓名 */
	private String realName;


	/** 用户类型 */
	private Integer userType;

	/** 企业编号 */
	private String inputCopNo;

	/** 企业名称 */
	private String inputCopName			;

	/** 社会信用代码 */
	private String copGbCode;

	/** 主管海关 */
	private String customsCode;

	/** 场地代码 */
	private String areaCode;

	/*
	* 页面新增时需要显示的公共字段
	* */
	private String createTime;
	private String createBy;
	private String createName;
	private String inputerCode;
	private String inputerName;
	private String decTime;


	private CopEnt copEnt;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}



	public SystemAuthorizingUser(Long accountId, String loginName,
								 String userName, String realName) {
		super();
		this.userId = accountId;
		this.loginName = loginName;
		this.userName = userName;
		this.realName = realName;
	}

	public SystemAuthorizingUser(Long accountId, String loginName, String userName, String realName,Integer userType,String tradeCode,String entName) {
		super();
		this.userId = accountId;
		this.loginName = loginName;
		this.userName = userName;
		this.realName = realName;
		this.userType = userType;
		this.inputCopNo = tradeCode;
		this.inputCopName = entName;
	}


	public CopEnt getCopEnt() {
		return copEnt;
	}

	public void setCopEnt(CopEnt copEnt) {
		this.copEnt = copEnt;
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

	public String getCopGbCode() {
		return copGbCode;
	}

	public void setCopGbCode(String copGbCode) {
		this.copGbCode = copGbCode;
	}

	public String getCustomsCode() {
		return customsCode;
	}

	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getDecTime() {
		return decTime;
	}

	public void setDecTime(String decTime) {
		this.decTime = decTime;
	}
}
