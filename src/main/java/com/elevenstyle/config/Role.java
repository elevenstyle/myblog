package com.elevenstyle.config;

public class Role {
	
	private static Integer admin = 0;		//超级管理员
	private static Integer simpleUser =5;	//普通用户
	
	public static Integer getAdmin() {
		return admin;
	}
	public static void setAdmin(Integer admin) {
		Role.admin = admin;
	}
	public static Integer getSimpleUser() {
		return simpleUser;
	}
	public static void setSimpleUser(Integer simpleUser) {
		Role.simpleUser = simpleUser;
	}

	
}
