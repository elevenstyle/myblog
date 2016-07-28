package com.elevenstyle.mapper;

import com.elevenstyle.model.User;



public interface UserMapper {
	public User getUserInfo(String name);
	int insertUser(User user);
}
