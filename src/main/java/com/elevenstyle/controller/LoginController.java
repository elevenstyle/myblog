package com.elevenstyle.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevenstyle.controller.core.BaseController;
import com.elevenstyle.service.UserService;

@Controller
public class LoginController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户登录页面跳转
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(HttpSession session, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Integer role = null;
		Integer status = null;
		String nickname = null;
		Long id;
		//是否登录判断
		if(session.getAttribute("role") !=null) {
			if(session.getAttribute("role").equals("ROLE_ADMIN")||session.getAttribute("role").equals("ROLE_SUPERADMIN")) {
				return "redirect:manage/admin/home";
				}
			return "redirect:manage/user/home";
		}
		return "login";
	}

	//注销登录
	@RequestMapping("/cancel")
	public String cancel(HttpSession session, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		session.invalidate();
		return "redirect:index";
	}

}
