package com.elevenstyle.controller.core;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/** 
 * @author lijie
 * @date 2016年8月15日 
 * @email lijie@6mi.com
 */
@Controller
public class BaseController {
	
	//logger
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	/**
	 * 获取请求ip
	 * @param session
	 * @param request
	 * @param response
	 * @return ipAddress
	 * @throws Exception
	 */
	public String getIp(HttpServletRequest request) throws Exception {
		String ipAddress = null;
		ipAddress = request.getHeader("x-forwarded-for"); 
		if (ipAddress == null || ipAddress.length() == 0  
		        || "unknown".equalsIgnoreCase(ipAddress)) {  
		    ipAddress = request.getHeader("Proxy-Client-IP");  
		}  
		if (ipAddress == null || ipAddress.length() == 0  
		        || "unknown".equalsIgnoreCase(ipAddress)) {  
		    ipAddress = request.getHeader("WL-Proxy-Client-IP");  
		}  
		if (ipAddress == null || ipAddress.length() == 0  
		        || "unknown".equalsIgnoreCase(ipAddress)) {  
		    ipAddress = request.getRemoteAddr();  
		          
		    //这里主要是获取本机的ip,可有可无  
		    if (ipAddress.equals("127.0.0.1") || ipAddress.endsWith("0:0:0:0:0:0:1")) {  
		        // 根据网卡取本机配置的IP  
		        InetAddress inet = null;  
		        try {  
		            inet = InetAddress.getLocalHost();  
		        } catch (UnknownHostException e) {  
		            e.printStackTrace();  
		        }  
		        ipAddress = inet.getHostAddress();  
		    }  
		}
		return ipAddress;
	}
	
}
