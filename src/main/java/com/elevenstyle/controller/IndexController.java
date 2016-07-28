package com.elevenstyle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.elevenstyle.model.User;
import com.elevenstyle.service.UserService;
@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/index")
	public String indexs() {
		return "index";
	}
	
	@RequestMapping("/register")
	public String register(HttpSession session, HttpServletRequest request, Model model) throws Exception {

		//是否登录判断
		if(session.getAttribute("role") !=null) {
			return "redirect:index";
			}
			return "register";
		}
	
	@RequestMapping("/toregister")
	public String toregister(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		//是否登录判断
		if(session.getAttribute("role") !=null) {
				return "redirect:index";
			}
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		Integer role = 5;
		Integer status = 0;
		User user = new User();
		if(email!=null&&nickname!=null&&password!=null) {
			user.setEmail(email);
			user.setNickname(nickname);
			user.setPassword(password);
			user.setRole(role);
			user.setStatus(status);
			int a = userService.insertUser(user);
			return "redirect:login";
		}
		return "register";
	}
	
	@RequestMapping("/add")
	public String add(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		//是否登录判断
		if(session.getAttribute("role") ==null) {
				return "redirect:index";
			}
		
			return "redirect:login";

	}
}