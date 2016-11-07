package com.elevenstyle.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elevenstyle.model.Role;
import com.elevenstyle.model.User;

/** 
 * @author lijie
 * @date 2016年9月6日 
 * @email lijie@6mi.com
 */
@Service
@Transactional
@Scope("prototype")
public class CustomUserService implements UserDetailsService{
	
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	//验证登陆用户
	@Override
	public UserDetails loadUserByUsername(String email) {
		//查询用户
		User user = userService.getUserInfo(email);
		if(user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		//查询用户角色
		Role role = roleService.selectRoleById(user.getRid());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);

	}

}
