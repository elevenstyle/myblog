package com.elevenstyle.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elevenstyle.mapper.UserMapper;
import com.elevenstyle.model.User;
import com.elevenstyle.service.UserService;
@Service
@Transactional
@Scope("prototype")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserInfo(String email) {
		User user = userMapper.getUserInfo(email);
		return user;
	}

	@Override
	public int insertUser(User user) {
		return userMapper.insertUser(user);
	}

}
