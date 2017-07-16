package com.powerbridge.bssp.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.security.SystemAuthorizingUser;
import com.powerbridge.bssp.common.util.ServletUtils;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.system.entity.SystemMenu;
import com.powerbridge.bssp.system.entity.SystemRole;
import com.powerbridge.bssp.system.entity.SystemRoleMenu;
import com.powerbridge.bssp.system.service.ISystemMenuService;
import com.powerbridge.bssp.system.service.ISystemRoleMenuService;
import com.powerbridge.bssp.system.service.ISystemRoleService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;



/**
* 项目名称：bssp Maven Webapp
* 类名称：SystemRoleController   
* 类描述：角色控制器  
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* @version
 */
@Controller
@RequestMapping("/system/sysrole")
@CrossOrigin
public class SystemRoleController extends BaseController {
	@Autowired
	private ISystemRoleService systemRoleService;
	@Autowired
	private ISystemRoleMenuService systemRoleMenuService;
	@Autowired
	private ISystemMenuService systemMenuService;
	/** 角色管理页面 */
/*	private static final String SYSTEM_ROLE_LIST = getViewPath("/admin/system/system_role_list");
	private static final String SYSTEM_ROLE_ADD = getViewPath("/admin/system/system_role_add");*/
	private static final Gson gson = new Gson();
	@InitBinder("systemRole")
	public void initBinderSysBole(WebDataBinder binder){
		binder.setFieldDefaultPrefix("systemRole.");
	}
	/**
	 * 进入角色管理页面
	 * @return ModelAndView
	 */
	@RequiresPermissions("sysuser:role:view")
	@RequestMapping("/list")
	@ResponseBody
	public String showRoleList(Model model){
		Map<String, Object> json = new HashMap<String,Object>();
		String jsonStr="";
		try{
			Map<String, Object> data = new HashMap<String,Object>();
			//查询所有的角色
			List<SystemRole> roleList = systemRoleService.selectRoleList();
			//model.addAttribute("roleList", roleList);
			List<SystemRole> systemRoles = systemRoleService.selectRoleAndNumber();
			//model.addAttribute("systemRoles", systemRoles);
			data.put("roleList",roleList);
			data.put("systemRoles",systemRoles);
			json=this.transJson("1","成功",data);
			jsonStr = gson.toJson(json);
		}catch (Exception e) {
			logger.error("showRoleList()--error",e);
			json=this.transJson("0","失败",null);
			jsonStr = gson.toJson(json);
		}
		return  jsonStr;
	}
	/**
	* @Description: 添加角色
	* @param model
	* @return
	* @throws 
	*/
	@RequiresPermissions("sysuser:role:add")
	@RequestMapping(value = "/list/add", method = RequestMethod.GET)
	@ResponseBody
	public String add(Model model) {
		Map<String, Object> json = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		List<SystemMenu> menuList = systemMenuService.selectList(null);//所有菜单
		if(menuList!=null){
			//model.addAttribute("jsonMenu", JSON.toJSON(menuList));
			data.put("jsonMenu",menuList);
			logger.info(JSON.toJSON(menuList).toString());
		}
		json=this.transJson("1","成功",data);
		String jsonStr = gson.toJson(json);
		return jsonStr;
	}
	/**
	* @Description: 编辑角色
	* @param model
	* @param roleId
	* @return
	* @throws 
	*/
	@RequiresPermissions("sysuser:role:edit")
	@RequestMapping(value = "/list/{roleId}/edit", method = RequestMethod.GET)
	@ResponseBody
	public String edit(Model model, @PathVariable Long roleId){
		Map<String, Object> json = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		SystemRole systemRole = systemRoleService.selectById(Long.valueOf(roleId));
		//model.addAttribute("systemRole", systemRole);//角色信息
		data.put("systemRole",systemRole);//角色信息
		List<SystemMenu> menuList = systemMenuService.selectList(null);//所有菜单
		if(menuList!=null){
			//根据角色，选出已经勾选的菜单
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("ROLE_ID", roleId);
			List<SystemRoleMenu> roleMenuList = systemRoleMenuService.selectByMap(columnMap);//角色拥有的菜单
			for(SystemMenu systemMenu:menuList){
				for(SystemRoleMenu systemRoleMenu:roleMenuList){
					if(systemRoleMenu.getMenuId().equals( systemMenu.getId())){
						systemMenu.setChecked(true);
					}
				}
			}
			//model.addAttribute("jsonMenu", JSON.toJSON(menuList));
			data.put("jsonMenu",menuList);
			logger.info(JSON.toJSON(menuList).toString());
		}
		json=this.transJson("1","成功",data);
		String jsonStr = gson.toJson(json);
		return  jsonStr;
	}
	
	/**
	* @Description: 创建或修改角色 
	* @param systemRole
	* @return
	* @throws 
	*/
	//@RequiresPermissions({"sysuser:role:add","sysuser:role:edit"})
	@RequestMapping(value = "/list/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save(SystemRole systemRole){
		SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
		MessageConstants messageConstants = MessageConstants.BSSP_STATUS_FAIL;
		try{
			if(systemRole.getId() == null){
				systemRoleService.insert(systemRole);//插入菜单
				//得到菜单ID串
				String menuIds = ServletUtils.getRequest().getParameter("menuIds");
				if(menuIds!=null && menuIds.trim().length()>0){
					List<SystemRoleMenu> systemRoleMenuList = new ArrayList<SystemRoleMenu>();
					String[] funArr = menuIds.split(",");
					if(funArr.length>0){
						for(String menuId : funArr){
							SystemRoleMenu roleMenu = new SystemRoleMenu();
							roleMenu.setMenuId(Long.valueOf(menuId));
							roleMenu.setRoleId(systemRole.getId());
							systemRoleMenuList.add(roleMenu);
						}
						systemRoleMenuService.insertBatch(systemRoleMenuList);
					}
				}
				messageConstants = MessageConstants.BSSP_STATUS_ROLE_CREATE_SUCCESS;
			}else{
				//systemRoleService.updateSelectiveById(systemRole);//更新角色
				systemRoleService.updateById(systemRole);//更新角色
				//删除原有的角色菜单关联记录
				SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
				systemRoleMenu.setRoleId(systemRole.getId());
				//systemRoleMenuService.deleteSelective(systemRoleMenu);
				//systemRoleMenuService.deleteById(systemRoleMenu);
				Map<String, Object> columnMap = new HashMap<String,Object>();
				columnMap.put("ROLE_ID",systemRoleMenu.getRoleId());
				systemRoleMenuService.deleteByMap(columnMap);
				//得到菜单ID串
				String menuIds = ServletUtils.getRequest().getParameter("menuIds");
				if(menuIds!=null && menuIds.trim().length()>0){
					List<SystemRoleMenu> systemRoleMenuList = new ArrayList<SystemRoleMenu>();
					String[] funArr = menuIds.split(",");
					if(funArr.length>0){
						for(String menuId : funArr){
							SystemRoleMenu roleMenu = new SystemRoleMenu();
							roleMenu.setMenuId(Long.valueOf(menuId));
							roleMenu.setRoleId(systemRole.getId());
							systemRoleMenuList.add(roleMenu);
						}
						systemRoleMenuService.insertBatch(systemRoleMenuList);
					}
				}
				messageConstants = MessageConstants.BSSP_STATUS_ROLE_UPDATE_SUCCESS;
			}
		}catch (Exception e) {
			logger.error("save()--error",e);
		}
		return this.setJson(messageConstants, null);
	}

	/**
	 * 删除角色
	 * @param roleId 角色ID
	 * @return Map<String,Object>
	 */
	@RequiresPermissions("sysuser:role:delete")
	@RequestMapping(value="/list/{roleId}/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteRole(@PathVariable("roleId") Long roleId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(roleId>0){
				systemRoleService.deleteById(roleId);
				SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
				systemRoleMenu.setRoleId(roleId);
				//systemRoleMenuService.deleteSelective(systemRoleMenu);
				Map<String, Object> columnMap = new HashMap<String,Object>();
				columnMap.put("ROLE_ID",systemRoleMenu.getRoleId());
				systemRoleMenuService.deleteByMap(columnMap);
				json = this.setJson(MessageConstants.BSSP_STATUS_ROLE_DELETE_SUCCESS, null);
			}else{
				json = this.setJson(MessageConstants.BSSP_STATUS_ROLE_DELETE_CHOOSE, null);
			}
		}catch (Exception e) {
			logger.error("deleteRole()--error");
		}
		return json;
	}

	/**
	 * POST 启用/禁止
	 * @return
	 */
	@RequiresPermissions("sysuser:role:edit")
	@RequestMapping(value = "/list/audit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult audit() {
		Long roleId = Long.valueOf(getParameter("roleId"));
		Integer status = Integer.valueOf(getParameter("status"));
		SystemRole systemRole = new SystemRole();
		systemRole.setId(Long.valueOf(roleId));
		systemRole.setStatus(status);
		//systemRoleService.updateSelectiveById(systemRole);
		systemRoleService.updateById(systemRole);
		return result(MessageConstants.BSSP_STATUS_SUCCESS);
	}
	
}

