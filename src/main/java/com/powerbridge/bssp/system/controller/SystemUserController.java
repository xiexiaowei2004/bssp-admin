package com.powerbridge.bssp.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.security.SystemAuthorizingUser;
import com.powerbridge.bssp.common.util.MD5Utils;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UploadFileUtils;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.common.util.toolbox.WebUtil;
import com.powerbridge.bssp.system.entity.QueryUser;
import com.powerbridge.bssp.system.entity.SystemRole;
import com.powerbridge.bssp.system.entity.SystemUser;
import com.powerbridge.bssp.system.entity.SystemUserLoginLog;
import com.powerbridge.bssp.system.entity.SystemUserRole;
import com.powerbridge.bssp.system.service.ISystemRoleService;
import com.powerbridge.bssp.system.service.ISystemUserLoginLogService;
import com.powerbridge.bssp.system.service.ISystemUserRoleService;
import com.powerbridge.bssp.system.service.ISystemUserService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
* 项目名称：bssp Maven Webapp
* 类名称：SystemUserController   
* 类描述：系统管理员控制器   
* 创建人：
* 创建时间：2017年4月27日 下午10:12:17
* @version
 */
@Controller
@RequestMapping("/system/sysuser")
@CrossOrigin
public class SystemUserController extends BaseController {

	/** 系统管理员列表 */
	//private static final String SYSTEM_USER_LIST = getViewPath("admin/system/system_user_list");
	/** 用户个人资料 */
	//private static final String SYSTEM_USER_MESSAGE = getViewPath("admin/system/system_user_message");
	/** 用户登录日志 */
	//private static final String USER_LOGIN_LOG = getViewPath("admin/user/user_login_log");
	/** 管理员分类查看 */
	//private static final String SYSTEM_USER_ROLE = getViewPath("admin/system/system_user_role");
	/** 创建或者修改用户界面 */
	//private static final String SYSTEM_USER_ADDUSER = getViewPath("admin/system/system_user_add");
	/** 系统管理员信息界面 */
	//private static final String SYSTEM_USER_INFO = getViewPath("admin/system/system_user_info");
	/** 系统管理员头像界面 */
	private static final String SYSTEM_USER_AVATAR = getViewPath("admin/system/system_user_avatar");

	private static final  Gson gson = new Gson();

	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private ISystemUserLoginLogService systemUserLoginLogService;
	@Autowired
	private ISystemRoleService systemRoleService;
	@Autowired
	private ISystemUserRoleService systemUserRoleService;


	@InitBinder("systemUser")
	public void initBinderSystemUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("systemUser.");
	}

	@InitBinder("queryUser")
	public void initQueryUser(WebDataBinder dinder) {
		dinder.setFieldDefaultPrefix("queryUser.");
	}
	
	/**
	 * GET 管理员列表
	 * @param queryUser
	 * @return
	 */
	@RequiresPermissions("sysuser:list:view")
	@RequestMapping(value = "/list")
	@ResponseBody
	public String list(Model model, @ModelAttribute("queryUser") QueryUser queryUser) {
		Map<String, Object> json = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();

		//如果登录用户用企业编号,只能查看同企业用户
		String loginUserTradCode = SingletonLoginUtils.getSystemUser().getInputCopNo();
		if(!StringUtil.isEmpty(loginUserTradCode)){
			queryUser.setTradeCode(loginUserTradCode);
		}


		int sysUserNumber = systemUserService.selectAllSystemUserNumber();
		//model.addAttribute("sysUserNumber", sysUserNumber);
		List<SystemUser> systemUsers = systemUserService.selectAllSystemUser(queryUser);
		//model.addAttribute("systemUsers", systemUsers);
		List<SystemRole> systemRoles = systemRoleService.selectRoleAndNumber();
		//model.addAttribute("systemRoles", systemRoles);
		data.put("sysUserNumber",sysUserNumber);// 用户总数量
		data.put("systemUsers",systemUsers);// 用户列表
		data.put("systemRoles",systemRoles);// 权限列表
		json=this.transJson("1","成功",data);
		String jsonStr = gson.toJson(json);
		return  jsonStr;
	}
	
	/**
	 * GET 个人资料
	 * @param userId 用户编号
	 * @return
	 */
	@RequiresPermissions("sysuser:list:view")
	@RequestMapping(value = "/list/{userId}/detail", method = RequestMethod.GET)
	@ResponseBody
	public String detail(Model model, @PathVariable Long userId){
		Map<String, Object> json = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		SystemUser user = systemUserService.selectById(userId);
		model.addAttribute("user", user);//用户信息
		List<SystemUserRole> systemUserRoles = systemUserRoleService.selectRoleListByAccountId(userId);
		StringBuffer userRole = new StringBuffer();
		for(SystemUserRole systemUserRole : systemUserRoles){
			userRole.append(systemUserRole.getRoleName());
			userRole.append("&nbsp");
		}
		data.put("userRole",userRole);//用户权限
		json=this.transJson("1","成功",data);
		String jsonStr = gson.toJson(json);
		return  jsonStr;
	}
	
	/**
	 * Get 用户登录日志
	 * @param accountId
	 * @return
	 */
	@RequiresPermissions("sysuser:list:view")
	@RequestMapping(value = "/list/{accountId}/log", method = RequestMethod.GET)
	@ResponseBody
	public String userLog(Model model, @PathVariable Long accountId){
		Map<String, Object> json = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		List<SystemUserLoginLog> systemUserLoginLogList = systemUserLoginLogService.selectUserLoginLog(accountId);
		data.put("systemUserLoginLogList",systemUserLoginLogList);
		json=this.transJson("1","成功",data);
		String jsonStr = gson.toJson(json);
		return  jsonStr;
	}
	
	/**
	 * POST 启用/禁止用户
	 * @return
	 */
	@RequiresPermissions("sysuser:list:edit")
	@RequestMapping(value = "/list/audit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult audit() {
		Long accountId = Long.valueOf(getParameter("accountId"));
		Integer status = Integer.valueOf(getParameter("status"));
		systemUserService.updateUserStatus(accountId, status);
		return result(MessageConstants.BSSP_STATUS_SUCCESS);
	}
	
	/**
	 * DELETE 删除用户
	 * @return
	 */
	@RequiresPermissions("sysuser:list:delete")
	@RequestMapping(value = "/list/{accountId}/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@PathVariable Long accountId) {
		systemUserService.deleteSysUser(accountId);
		return this.setJson(MessageConstants.BSSP_STATUS_SUCCESS, null);
	}
	
	/**
	 * GET 角色分类下管理员列表
	 * @param roleId
	 * @return
	 */
	@RequiresPermissions("sysuser:list:view")
	@RequestMapping(value = "/list/{roleId}/role", method = RequestMethod.GET)
	@ResponseBody
	public String listrole(Model model, @PathVariable Long roleId) {
		Map<String, Object> json = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		List<SystemUser> systemUsers = systemUserService.selectSysUserByRoleId(roleId);
		data.put("systemUsers",systemUsers);
		json=this.transJson("1","成功获取数据",data);
		String jsonStr = gson.toJson(json);
		return  jsonStr;
	}
	
	/**
	 * GET 修改用户页面
	 * @return
	 */
	@RequiresPermissions("sysuser:list:edit")
	@RequestMapping(value = "/list/{accountId}/edit", method = RequestMethod.GET)
	@ResponseBody
	public String edit(Model model, @PathVariable Long accountId){
		Map<String, Object> json = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		SystemUser systemUser = systemUserService.selectById(Long.valueOf(accountId));
		//model.addAttribute("systemUser", systemUser);
		List<SystemRole> systemRoles = systemRoleService.selectRoleList();
		//model.addAttribute("systemRoles", systemRoles);
		List<SystemUserRole> systemRoleList = systemUserRoleService.selectRoleListByAccountId(accountId);
		//model.addAttribute("systemRoleList", systemRoleList);
		int userType =SingletonLoginUtils.getSystemUser().getUserType();
		data.put("loginUserType",userType);//登录用户的用户类型,用于前端判断
		data.put("systemUser",systemUser);//用户信息
		data.put("systemRoles",systemRoles);//所有角色
		data.put("systemRoleList",systemRoleList);//分配角色
		json=this.transJson("1","成功",data);
		String jsonStr = gson.toJson(json);
		return  jsonStr;
	}
	
	/**
	 * GET 创建用户页面
	 * @return
	 */
	@RequiresPermissions("sysuser:list:add")
	@RequestMapping(value = "/list/add", method = RequestMethod.GET)
	@ResponseBody
	public String add(Model model) {
		Map<String, Object> json = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();


		//待入默认的企业信息
		int userType =SingletonLoginUtils.getSystemUser().getUserType();
		SystemUser systemUser = new SystemUser();

		systemUser.setUserType(SingletonLoginUtils.getSystemUser().getUserType());
		//获取当前登录用户的菜单权限
		if(userType==1){//企业用户
			systemUser.setLoginName("");
			systemUser.setUserName("");
			systemUser.setTradeCode(SingletonLoginUtils.getSystemUser().getInputCopNo());
			systemUser.setEntName(SingletonLoginUtils.getSystemUser().getInputCopName());
			List<SystemUserRole> systemRoleList = systemUserRoleService.selectRoleListByAccountId(SingletonLoginUtils.getSystemUserId());
			data.put("systemRoleList",systemRoleList);//分配角色
		}
		data.put("systemUser",systemUser);//用户信息
		data.put("loginUserType",userType);//登录用户的用户类型,用于前端判断

		List<SystemRole> systemRoles = systemRoleService.selectRoleList();
		data.put("systemRoles",systemRoles);
		json=this.transJson("1","待入用戶基本信息",data);
		String jsonStr = gson.toJson(json);
		return  jsonStr;
	}
	
	/**
	 * POST 创建或修改用户
	 * @return
	 */
	@RequiresPermissions({"sysuser:list:add","sysuser:list:edit"})
	@RequestMapping(value = "/list/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(SystemUser systemUser){
		String[] roleIds = getParameterValues("roleId");
		if(!WebUtil.isEmail(systemUser.getEmail())){
			return this.setJson(MessageConstants.BSSP_STATUS_EMAIL_INPUT_ERROR,null);
		}
		if(!WebUtil.isTelephone(systemUser.getTelephone())){
			return this.setJson(MessageConstants.BSSP_STATUS_PHONE_INPUT_ERROR, null);
		}

		//当前登录用户是企业用户，则新增的用户企业与当前用户相同
		if (SingletonLoginUtils.getSystemUser().getUserType() == 1) {
			systemUser.setTradeCode(SingletonLoginUtils.getSystemUser().getInputCopNo());
			systemUser.setEntName(SingletonLoginUtils.getSystemUser().getInputCopName());
		}


		if(systemUser.getId() == null){
			if(systemUserService.checkLoginName(systemUser.getLoginName())){
				return this.setJson(MessageConstants.BSSP_STATUS_USER_REDUPLICATED,null);
			}
			systemUserService.insertSystemUser(systemUser, roleIds);//创建用户及插入角色记录
			return this.setJson(MessageConstants.BSSP_STATUS_USER_CREATE_SUCCESS, null);
		}else{
			systemUserService.updateUserInfoBySystem(systemUser, roleIds);//更新用户及角色记录
			return this.setJson(MessageConstants.BSSP_STATUS_USER_UPDATE_SUCCESS, null);
		}
	}
	
	/**
	 * GET 管理员个人信息界面
	 * @return
	 */
	@RequiresPermissions("sysuser:info:view")
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ResponseBody
	public String view(Model model) {
		String jsonStr="";
		Map<String, Object> json = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
		if (sysUser != null) {
			SystemUser systemUser = systemUserService.selectByLoginName(sysUser.getLoginName());
			//model.addAttribute("systemUser", systemUser);// 用户信息

			List<SystemUserLoginLog> systemUserLoginLogList = systemUserLoginLogService.selectUserLoginLog(systemUser.getId());
			//model.addAttribute("systemUserLoginLogList", systemUserLoginLogList);

			List<SystemUserRole> systemUserRoles = systemUserRoleService.selectRoleListByAccountId(systemUser.getId());
			StringBuffer userRole = new StringBuffer();
			for (SystemUserRole systemUserRole : systemUserRoles) {
				userRole.append(systemUserRole.getRoleName());
				userRole.append(" ");
			}
			//model.addAttribute("userRole", userRole);
			data.put("systemUser",systemUser);// 用户信息
			data.put("systemUserLoginLogList",systemUserLoginLogList);// 用户日志
			data.put("userRole",userRole);// 用户权限
			json=this.transJson("1","成功获取数据",data);
			jsonStr = gson.toJson(json);
		}

		return  jsonStr;
	}

	/**
	 * GET 获取登录用户信息
	 *
	 * @return
	 */
//	@RequiresPermissions("sysuser:info:view")
	@RequestMapping(value = "/loginuser", method = RequestMethod.GET)
	@ResponseBody
	public String loginUserInfo() {
		String jsonStr = "";
		String now = DateUtil.now();
		Map<String, Object> json = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();

		sysUser.setCreateTime(now);
		sysUser.setDecTime(now);
		sysUser.setCreateBy(String.valueOf(sysUser.getUserId()));
		sysUser.setCreateName(sysUser.getUserName());
		sysUser.setInputerCode(String.valueOf(sysUser.getUserId()));
		sysUser.setInputerName(sysUser.getUserName());


		data.put("loginuser", sysUser);// 用户权限
		json = this.transJson("1", "成功获取数据", data);
		jsonStr = gson.toJson(json);

		return jsonStr;
	}
	
	
	/**
	 * POST 更新管理员信息
	 * @param systemUser
	 * @return
	 */
	@RequiresPermissions("sysuser:info:edit")
	@RequestMapping(value = "/info/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> edit(SystemUser systemUser){
		SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
		if (sysUser != null) {
			systemUser.setId(sysUser.getUserId());
			systemUserService.updateUserInfo(systemUser);
			return this.setJson(MessageConstants.BSSP_STATUS_SUCCESS, null);
		}else{
			return this.setJson(MessageConstants.BSSP_STATUS_USER_OVERTIME, null);
		}
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @return
	 */
	@RequiresPermissions("sysuser:info:edit")
	@RequestMapping(value = "/info/edit/psw", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editPwd(HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
		if (sysUser != null) {
			SystemUser systemUser = systemUserService.selectByLoginName(sysUser.getLoginName());
			// 原密码
			String nowPassword = request.getParameter("nowPassword") == null ? ""
					: request.getParameter("nowPassword");
			// 新密码
			String newPassword = request.getParameter("newPassword") == null ? ""
					: request.getParameter("newPassword");
			// 确认密码
			String confirmPwd = request.getParameter("confirmPwd") == null ? ""
					: request.getParameter("confirmPwd");
			if(!MD5Utils.getMD5(nowPassword).equals(systemUser.getLoginPassword())){
				return this.setJson(MessageConstants.BSSP_STATUS_PASSWORD_EXIST_ERROR,null);
			}
			if(!WebUtil.isPassword(newPassword)){
				return this.setJson(MessageConstants.BSSP_STATUS_PASSWORD_INPUT_ERROR,null);
			}
			if(!newPassword.equals(confirmPwd)){
				return json = this.setJson(MessageConstants.BSSP_STATUS_PASSWORD_UPDATE_ERROR, null);
			}
			systemUserService.updateUserPws(systemUser.getId(), newPassword);
			return json = this.setJson(MessageConstants.BSSP_STATUS_PASSWORD_UPDATE_SUCCESS, null);
		}else{
			return json = this.setJson(MessageConstants.BSSP_STATUS_USER_OVERTIME, null);
		}
	}
	
	/**
	 * 设置头像页面
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String setAvatar() {
		return SYSTEM_USER_AVATAR;
	}
    @RequestMapping(value = "/upload",method = RequestMethod.POST, produces="application/json;charset=utf-8")  
	@ResponseBody
	public Map<String, Object> uploadHeadPortrait(MultipartFile avatar_file,String avatar_src,String avatar_data, HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		if (!avatar_file.isEmpty()) {
			try{
		        //判断文件的MIMEtype
		        String type = avatar_file.getContentType();
		        if(type == null || !type.toLowerCase().startsWith("image/")){
					json = this.setJson(MessageConstants.BSSP_STATUS_IMAGE_FILE_ERROR, null);
		        	return  json;
		        }
				//头像存放文件
				String dir = "icon";
				Map<String, Object> returnMap = UploadFileUtils.Upload(request,avatar_file,avatar_data,dir);
				//返回的布尔型参数的值为true，如果字符串参数不为null，是相等的，忽略大小写字符串“true”。
				if (Boolean.parseBoolean(returnMap.get("flag").toString()) == true) {
                    SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
    				if (sysUser != null) {
						SystemUser systemUser = systemUserService.selectByLoginName(sysUser.getLoginName());
						systemUser.setPicImg(returnMap.get("savaPath").toString());
						systemUserService.insertOrUpdate(systemUser);
					}
					json = this.setJson(MessageConstants.BSSP_STATUS_IMAGE_UPDATE_SUCCESS, returnMap.get("savaPath").toString());
					return json;
				} 
			}catch(Exception e){
				logger.error("ImageUploadController.uploadHeadPortrait", e);
				json = this.setJson(MessageConstants.BSSP_STATUS_IMAGE_UPDATE_FAIL, null);
				return json;
			}
		}
		json = this.setJson(MessageConstants.BSSP_STATUS_IMAGE_FILE_ERROR, null);
    	return  json;
	}

}