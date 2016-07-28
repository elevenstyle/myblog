package com.elevenstyle.service;


import com.elevenstyle.model.User;

public interface UserService {
	User getUserInfo(String name);
	int insertUser(User user);
}
