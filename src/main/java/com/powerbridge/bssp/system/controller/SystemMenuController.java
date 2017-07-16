package com.powerbridge.bssp.system.controller;

import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.security.SystemAuthorizingUser;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.system.entity.SystemMenu;
import com.powerbridge.bssp.system.service.ISystemMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* 项目名称：bssp Maven Webapp
* 类名称：SystemRoleController   
* 类描述：菜单控制器  
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* @version
 */
@Controller
@RequestMapping("/system/sysmenu")
@CrossOrigin
public class SystemMenuController extends BaseController{

	/** 系统菜单列表 */
/*	private static final String SYSTEM_MENU_LIST = getViewPath("/admin/system/system_menu_list");
	private static final String SYSTEM_MENU_ADD = getViewPath("/admin/system/system_menu_add");*/
	private static final Gson gson = new Gson();
	@InitBinder({"systemMenu"})
	public void initBinderSystemMenu(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("systemMenu.");
	}
	
	@Autowired
	private ISystemMenuService systemMenuService;
	
	/**
	 * 进入菜单管理页面
	 */
	@RequiresPermissions("sysuser:menu:view")
	@RequestMapping("/list")
	@ResponseBody
	public String showMenuList(Model model){
		Map<String, Object> json = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		try{
			//查询所有的菜单
			List<SystemMenu> menuList = systemMenuService.selectList(null);
			//model.addAttribute("menuList", menuList);
			data.put("menuList",menuList);
		}catch (Exception e) {
			logger.error("showMenuList()--error",e);
		}
		json=this.transJson("1","成功",data);
		String jsonStr = gson.toJson(json);
		return  jsonStr;
	}
	
	/**
	* @Description: 添加菜单 
	* @param model
	* @return
	* @throws 
	*/
	@RequiresPermissions("sysuser:menu:add")
	@RequestMapping(value = "/list/add", method = RequestMethod.GET)
	@ResponseBody
	public String add(Model model) {
		Map<String, Object> json = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		List<SystemMenu> menuList = systemMenuService.selectSystemMenu();//查询所有的菜单
		//model.addAttribute("menuList", menuList);//所有菜单
		data.put("menuList",menuList);//所有菜单
		json=this.transJson("1","成功",data);
		String jsonStr = gson.toJson(json);
		return  jsonStr;
	}
	
	/**
	* @Description: 编辑菜单 
	* @return
	* @throws 
	*/
	@RequiresPermissions("sysuser:menu:edit")
	@RequestMapping(value = "/list/edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(){
		Map<String, Object> json = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		SystemMenu systemMenu = systemMenuService.selectById(Long.valueOf(getParameter("id")));
		//model.addAttribute("systemMenu", systemMenu);
		List<SystemMenu> menuList = systemMenuService.selectSystemMenu();//查询所有的菜单
		//model.addAttribute("menuList", menuList);
		data.put("systemMenu",systemMenu);//菜单信息
		data.put("menuList",menuList);//所有菜单
		json=this.transJson("1","成功",data);
		String jsonStr = gson.toJson(json);
		return  jsonStr;
	}
	
	/**
	 * 创建或修改菜单
	 * @param systemMenu
	 * @return
	 */
	@RequiresPermissions({"sysuser:menu:add","sysuser:menu:edit"})
	@RequestMapping(value = "/list/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(SystemMenu systemMenu){
		SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
		if(systemMenu.getId() == null){
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("MENU_NAME", systemMenu.getMenuName());
			List<SystemMenu> list =systemMenuService.selectByMap(columnMap);
			if(list.size()>0){
				return this.setJson(MessageConstants.BSSP_STATUS_MENU_REDUPLICATED, null);
			}
			systemMenu.setCreateBy(sysUser.getUserName());
			systemMenuService.insert(systemMenu);//插入菜单
			return this.setJson(MessageConstants.BSSP_STATUS_MENU_CREATE_SUCCESS, null);
		}else{
			systemMenu.setUpdateBy(sysUser.getUserName());
			//systemMenuService.updateSelectiveById(systemMenu);//更新菜单
			systemMenuService.updateById(systemMenu);//更新菜单
			return this.setJson(MessageConstants.BSSP_STATUS_MENU_UPDATE_SUCCESS, null);
		}
	}
	
	/**
	 * 删除菜单功能
	 */
	@RequiresPermissions("sysuser:menu:delete")
	@RequestMapping(value = "/list/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteFunction(@PathVariable("id") Long id){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			systemMenuService.deleteById(id);
			json = this.setJson(MessageConstants.BSSP_STATUS_SUCCESS, null);
		}catch (Exception e) {
			logger.error("deleteFunction()--error",e);
		}
		return json;
	}
	
	/**
	 * POST 启用/禁止
	 * @return
	 */
	@RequiresPermissions("sysuser:menu:edit")
	@RequestMapping(value = "/list/audit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> audit() {
		Long menuId = Long.valueOf(getParameter("menuId"));
		Integer status = Integer.valueOf(getParameter("status"));
		SystemMenu systemMenu = new SystemMenu();
		systemMenu.setId(Long.valueOf(menuId));
		systemMenu.setStatus(status);
		//systemMenuService.updateSelectiveById(systemMenu);
		systemMenuService.updateById(systemMenu);
		return this.setJson(MessageConstants.BSSP_STATUS_SUCCESS, null);
	}
}
