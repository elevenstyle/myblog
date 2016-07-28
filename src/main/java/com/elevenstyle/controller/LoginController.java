package com.elevenstyle.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevenstyle.config.Role;
import com.elevenstyle.model.User;
import com.elevenstyle.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户登录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(HttpSession session, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) throws Exception {
		String email = request.getParameter("name");
		String password = request.getParameter("password");
		Integer role = null;
		Integer status = null;
		String nickname = null;
		Long id;
		//是否登录判断
		if(session.getAttribute("role") !=null) {
			if(session.getAttribute("role") == Role.getAdmin()) {

				return "redirect:manage/admin";
				}
			if(session.getAttribute("role") == Role.getSimpleUser()) {

				return "redirect:manage/user";
				}
		}
		
		//登录验证
		if(email !=null && password !=null) {
			User user = userService.getUserInfo(email);
			
			if (user!=null) {
				role = user.getRole();
				id = user.getId();
				status = user.getStatus();
				nickname = user.getEmail();
				if(status == 2 || status == 0) {
					model.addAttribute("error", "该账户未激活或已失效！");
					return "login";
				}
				if(password.equals(user.getPassword())) {
					session.setAttribute("id", id);
					session.setAttribute("name", nickname);
					session.setAttribute("role", role);
					return "redirect:index";
				} 
				model.addAttribute("error", "用户名或密码错误！");
				return "login";
			}
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
