package com.elevenstyle.controller.manage.user;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	private static final Logger logger = LoggerFactory.getLogger(UserManageController.class);
	
	public String index(HttpServletRequest request) throws Exception {
		
		return null;
	}

}
