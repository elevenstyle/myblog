package com.elevenstyle.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevenstyle.controller.core.BaseController;
import com.elevenstyle.service.UserService;
import com.elevenstyle.util.config.CustomAuthenticationProvider;
import com.elevenstyle.util.config.CustomUserDetailService;

@Controller
public class LoginController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	/**
	 * 用户登录页面跳转
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(HttpSession session, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);
		try {
            Authentication authentication = customAuthenticationProvider.authenticate(authRequest); //调用loadUserByUsername
            SecurityContextHolder.getContext().setAuthentication(authentication);
            session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆
            return "redirect:index";
        } catch (AuthenticationException ex) {
        	model.addAttribute("msg", "用户名或密码错误");
            return "login";
        }
	}

}
