package com.powerbridge.bssp.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.powerbridge.bssp.common.security.SystemAuthorizingUser;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.system.entity.SystemMenu;
import com.powerbridge.bssp.system.entity.SystemUser;
import com.powerbridge.bssp.system.entity.SystemUserRole;
import com.powerbridge.bssp.system.service.ISystemMenuService;
import com.powerbridge.bssp.system.service.ISystemUserRoleService;
import com.powerbridge.bssp.system.service.ISystemUserService;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：MainController   
* 类描述：后台主页面表示层   
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* @version    
*
 */
@Controller
@RequestMapping("/system")
@CrossOrigin
public class MainController extends BaseController {

    /** 后台管理主界面 */
	private static final String MAIN = getViewPath("admin/main/main");
	/** 后台管理主界面初始化首页 */
	private static final String MAIN_INDEX = getViewPath("admin/main/index");
	
	@Autowired
	private ISystemMenuService systemMenuService;
	@Autowired
	private ISystemUserRoleService systemUserRoleService;
	@Autowired
	private ISystemUserService systemUserService;
	/**
	 * 进入操作中心
	 * @return
	 */
	@RequiresPermissions("system:view")
	@RequestMapping(value = "/main",method = RequestMethod.GET)
	@ResponseBody
	public String main(Model model) {
		Map<String, Object> json = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		List<SystemMenu> systemMenus = new ArrayList<SystemMenu>();
		SystemAuthorizingUser systemUser = SingletonLoginUtils.getSystemUser();
		//系统管理员具有所有菜单
		if(systemUser.getUserId() == 1){
			systemMenus = systemMenuService.selectSystemMenu();
		}else{
			List<SystemUserRole> userRoleList = systemUserRoleService.selectRoleListByAccountId(systemUser.getUserId());//获取用户角色列表
			if(userRoleList != null && userRoleList.size() > 0){
				List<Long> roleIdList = new ArrayList<Long>();
				for(SystemUserRole  systemUserRole:userRoleList){
					roleIdList.add(systemUserRole.getRoleId());
				}
				systemMenus = systemMenuService.selectSystemMenuByRole(roleIdList);//获取用户拥有的菜单
			}
		}
		
		SystemUser user = systemUserService.selectById(systemUser.getUserId());
    	
		/*model.addAttribute("systemMenus", systemMenus);
		model.addAttribute("systemUser", user);*/
		data.put("systemMenus",systemMenus);
		data.put("systemUser",user);
		json=this.transJson("1","成功获取数据",data);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(json);
		return jsonStr;
	}
	
	/**
	 * 后台管理主界面初始化首页
	 * @param request
	 * @return
	 */
	@RequiresPermissions("system:view")
	@RequestMapping(value = "/main/index", method = RequestMethod.GET)
	public String mainIndex(HttpServletRequest request) {
		return MAIN_INDEX;
	}
	
}