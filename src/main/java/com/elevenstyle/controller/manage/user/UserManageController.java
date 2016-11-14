package com.elevenstyle.controller.manage.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elevenstyle.controller.base.GetLoginUserDetail;
import com.elevenstyle.model.util.SysUser;

/** 
 * 
 * 用户后台模块
 * @author lijie
 * @date 2016年9月5日 
 * @email lijie@6mi.com
 */
@Controller
@RequestMapping("/manage/user")
public class UserManageController {
	
	@Autowired
	private GetLoginUserDetail getLoginUserDetail;
	
	private static final Logger logger = LoggerFactory.getLogger(UserManageController.class);
	
	private static final String home = "/manage/user/home";
	
	//普通用户后台首页（ROLE_USER)
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/home")
	public String home(HttpServletRequest request, ModelMap model) throws Exception {
		SysUser user = getLoginUserDetail.getSysUserDetail();
		if(user==null) {
			return "redirect:/login";
		}
		return home;
	}

}
