package com.elevenstyle.mapper;

import com.elevenstyle.model.entity.User;



public interface UserMapper {
	public User getUserInfo(String name);
	int insertUser(User user);
}
