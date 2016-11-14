package com.elevenstyle.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevenstyle.controller.base.GetLoginUserDetail;
import com.elevenstyle.model.User;
import com.elevenstyle.model.util.SysUser;
import com.elevenstyle.service.UserService;
@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private GetLoginUserDetail getLoginUserDetail;
	
	@RequestMapping("/")
	public String index(HttpSession session) {
		try {
			//获取认证用户信息
			SysUser sysUser = getLoginUserDetail.getSysUserDetail();
				if(sysUser!=null) {
					String email = sysUser.getEmail();
					User user = userService.getUserInfo(email);
					session.setAttribute("userId", user.getId());
				}
			} catch (Exception e) {
				return "index";
			}
			return "index";
	}
	
	@RequestMapping("/index")
	public String index(HttpSession session, HttpServletRequest request, Model mode) {
		try {
		//获取认证用户信息
		SysUser sysUser = getLoginUserDetail.getSysUserDetail();
			if(sysUser!=null) {
				String email = sysUser.getEmail();
				User user = userService.getUserInfo(email);
				session.setAttribute("userId", user.getId());
			}
		} catch (Exception e) {
			return "index";
		}
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
	
//	@RequestMapping("/toregister")
//	public String toregister(HttpSession session, HttpServletRequest request, Model model, RedirectAttributes attr, @RequestParam("file") MultipartFile file) throws Exception {
//		//是否登录判断
//		if(session.getAttribute("role") !=null) {
//				return "redirect:index";
//			}
//		String image = null;
//		String email = request.getParameter("email");
//		String nickname = request.getParameter("nickname");
//		String password = request.getParameter("password");
//		Integer role = 5;
//		Integer status = 0;
//		//上传头像
//		if(!StringUtils.isEmpty(file)) {
//			image = FileUpload.uploadFile(file, request,path);
//		}
//		User user = new User();
//		if(email!=null&&nickname!=null&&password!=null) {
//			user.setEmail(email);
//			user.setNickname(nickname);
//			user.setPassword(password);
//			user.setRid(1);
//			user.setStatus(status);
//			user.setImage(image);
//			int a = userService.insertUser(user);
//			attr.addFlashAttribute("msg", "注册成功，请登录！");
//			return "redirect:login";
//		}
//		model.addAttribute("msg", "注册失败！");
//		return "register";
//	}
	
	@RequestMapping("/toregister")
	public String toregister(HttpSession session, HttpServletRequest request, Model model, RedirectAttributes attr) throws Exception {
		//是否登录判断
		if(session.getAttribute("role") !=null) {
				return "redirect:index";
			}
		String image = null;
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		password = passwordEncoder().encode(password);
		User user = new User();
		if(email!=null&&nickname!=null&&password!=null) {
			user.setEmail(email);
			user.setNickname(nickname);
			user.setPassword(password);
			user.setRid(1);
			user.setStatus("y");
			user.setImage(image);
			int a = userService.insertUser(user);
			attr.addFlashAttribute("msg", "注册成功，请登录！");
			return "redirect:login";
		}
		model.addAttribute("msg", "注册失败！");
		return "register";
	}
	
	@RequestMapping("/add")
	public String add(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		//是否登录判断
		if(session.getAttribute("role") ==null) {
				return "redirect:login";
			}
			return "add";
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
	return new BCryptPasswordEncoder();
	}
}