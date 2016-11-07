package com.elevenstyle.service;


import com.elevenstyle.model.User;

public interface UserService {
	User getUserInfo(String email);
	int insertUser(User user);
}
